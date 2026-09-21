package com.sum1t.thirdeye.data.speech

import android.content.Context
import android.media.AudioAttributes
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import com.sum1t.thirdeye.domain.repository.speech.Speaker
import java.util.Locale

private const val TAG = "Speaker"

// Slightly quicker than conversational. Regular screen-reader users run far
// faster than this; it should become a stored preference rather than a constant.
private const val SPEECH_RATE = 1.1f

class SpeakerImpl(context: Context) : Speaker {

    private val appContext = context.applicationContext

    private var engine: TextToSpeech? = null
    private var isReady = false

    // The engine takes a moment to connect, and the first thing the app wants
    // to say arrives before that. Hold the latest request and speak it on ready
    // rather than dropping it.
    private var pendingText: String? = null

    init {
        engine = TextToSpeech(appContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                configure()
                isReady = true
                pendingText?.let { speak(it) }
                pendingText = null
            } else {
                Log.e(TAG, "TextToSpeech init failed, status=$status")
            }
        }
    }

    private fun configure() {
        engine?.apply {
            val result = setLanguage(Locale.getDefault())
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Log.w(TAG, "Default locale unavailable, falling back to US English")
                setLanguage(Locale.US)
            }

            setSpeechRate(SPEECH_RATE)

            // Routes as accessibility speech: ducks media instead of fighting it,
            // and follows the accessibility volume where the device separates it.
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
            )

            setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    Log.d(TAG, "speaking: $utteranceId")
                }

                override fun onDone(utteranceId: String?) {
                    Log.d(TAG, "done: $utteranceId")
                }

                @Deprecated("Required override; the int-code overload is called instead.")
                override fun onError(utteranceId: String?) {
                    Log.e(TAG, "error: $utteranceId")
                }

                override fun onError(utteranceId: String?, errorCode: Int) {
                    Log.e(TAG, "error: $utteranceId code=$errorCode")
                }
            })
        }
    }

    @Synchronized
    override fun speak(text: String, interrupt: Boolean) {
        if (text.isBlank()) return

        if (!isReady) {
            pendingText = text
            return
        }

        engine?.speak(
            text,
            if (interrupt) TextToSpeech.QUEUE_FLUSH else TextToSpeech.QUEUE_ADD,
            null,
            text.hashCode().toString()
        )
    }

    @Synchronized
    override fun stop() {
        pendingText = null
        engine?.stop()
    }

    @Synchronized
    override fun shutdown() {
        pendingText = null
        isReady = false
        engine?.stop()
        engine?.shutdown()
        engine = null
    }
}

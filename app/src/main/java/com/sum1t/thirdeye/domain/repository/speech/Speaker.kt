package com.sum1t.thirdeye.domain.repository.speech

/**
 * Spoken output. This is the app's primary output channel, not a decoration,
 * so callers must be able to speak without knowing whether the engine has
 * finished initialising.
 */
interface Speaker {

    /**
     * @param interrupt true replaces whatever is currently being spoken.
     * Stale speech is worse than silence here: the user acts on what they
     * hear, so fresh information must win rather than queue behind old.
     */
    fun speak(text: String, interrupt: Boolean = true)

    fun stop()

    fun shutdown()
}

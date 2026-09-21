package com.sum1t.thirdeye.presentation.screens.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sum1t.thirdeye.R
import com.sum1t.thirdeye.domain.model.ThemePalette
import com.sum1t.thirdeye.ui.theme.themePreviewColor
import org.koin.androidx.compose.koinViewModel

private val SwatchSize = 56.dp
private val SwatchDotSize = 28.dp
private val CardShape = RoundedCornerShape(28.dp)

/**
 * The screen is its own preview: every choice repaints it immediately. Controls
 * are grouped on surfaces so the eye has edges to land on, and each group names
 * its current value in words rather than relying on colour or thumb position.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel(),
    onContinue: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Surface(color = MaterialTheme.colorScheme.background) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                        .height(64.dp),
                    shape = CircleShape,
                    onClick = {
                        viewModel.onEvent(OnboardingEvent.CompleteOnboarding)
                        onContinue()
                    }
                ) {
                    Text(
                        text = "Get started",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = maxHeight)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(56.dp),
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Make it easy to see",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp, bottom = 36.dp),
                    text = "Choose what is most comfortable to look at.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                SettingGroup {
                    DarkModeToggle(
                        checked = uiState.isDarkMode,
                        onCheckedChange = {
                            viewModel.onEvent(OnboardingEvent.ToggleDarkMode)
                        }
                    )
                }

                Spacer(Modifier.height(16.dp))

                SettingGroup {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectableGroup(),
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 12.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ThemePalette.entries.forEach { palette ->
                            ThemeItem(
                                palette = palette,
                                selected = palette == uiState.palette,
                                onSelect = {
                                    viewModel.onEvent(OnboardingEvent.SelectPalette(palette))
                                }
                            )
                        }
                    }

                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = uiState.palette.label,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

/** A bounded surface so each control reads as one object, not floating parts. */
@Composable
private fun SettingGroup(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = CardShape,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.40f)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}

@Composable
private fun DarkModeToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val trackWidth = 172.dp
    val trackHeight = 88.dp
    val thumbSize = 72.dp
    val trackPadding = 8.dp

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) trackWidth - thumbSize - (trackPadding * 2) else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "thumbOffset"
    )
    val trackColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.primary,
        label = "trackColor"
    )
    val thumbColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.onPrimary,
        label = "thumbColor"
    )

    // toggleable() merges its descendants, so the label below becomes this
    // switch's accessible name rather than a separate, unlabelled node.
    Column(
        modifier = modifier.toggleable(
            value = checked,
            role = Role.Switch,
            onValueChange = onCheckedChange
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(trackWidth)
                .height(trackHeight)
                .clip(CircleShape)
                .background(trackColor)
                .padding(trackPadding),
            contentAlignment = Alignment.CenterStart
        ) {
            // The inactive option stays visible on the far side, so the control
            // shows both states rather than only the current one.
            Icon(
                modifier = Modifier
                    .align(if (checked) Alignment.CenterStart else Alignment.CenterEnd)
                    .padding(horizontal = 20.dp)
                    .size(30.dp),
                painter = painterResource(
                    if (checked) R.drawable.ic_light_mode else R.drawable.ic_dark_mode
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.55f)
            )

            Box(
                modifier = Modifier
                    .offset(x = thumbOffset)
                    .size(thumbSize)
                    .clip(CircleShape)
                    .background(thumbColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(34.dp),
                    painter = painterResource(
                        if (checked) R.drawable.ic_dark_mode else R.drawable.ic_light_mode
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = if (checked) "Dark mode" else "Light mode",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun ThemeItem(
    palette: ThemePalette,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = themePreviewColor(palette)

    val itemScale by animateFloatAsState(
        targetValue = if (selected) 1.08f else 1f,
        animationSpec = spring(dampingRatio = 0.75f, stiffness = 450f),
        label = "scale"
    )

    Box(
        modifier = modifier
            .size(SwatchSize)
            .scale(itemScale)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface)
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onSelect
            )
            // Colour alone is not a label; give the swatch a spoken name.
            .semantics { contentDescription = palette.label },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(SwatchDotSize)
                .clip(CircleShape)
                .background(color)
        )

        if (selected) {
            Box(
                modifier = Modifier
                    .size(SwatchSize)
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
            )
        }
    }
}

internal val ThemePalette.label: String
    get() = when (this) {
        ThemePalette.DEFAULT -> "Default"
        ThemePalette.HIGH_CONTRAST -> "High contrast"
        ThemePalette.AMBER -> "Amber"
        ThemePalette.OCEAN -> "Ocean"
        ThemePalette.MONOCHROME -> "Monochrome"
    }

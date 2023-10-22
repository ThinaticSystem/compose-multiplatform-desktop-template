package ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/** まんなかに置く */
@Composable
fun Center(
    modifier: Modifier = Modifier,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        propagateMinConstraints = propagateMinConstraints,
        content = content,
    )
}

package epicarchitect.beehive

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@SuppressLint("ConflictingOnColor")
@Composable
fun BeehiveTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDarkTheme) {
            darkColors(
                primary = colorResource(R.color.brand),
                primaryVariant = colorResource(R.color.brand),
                secondary = colorResource(R.color.brand),
                onSecondary = Color(0xFFF1F1F1),
                onPrimary = Color(0xFFF1F1F1),
                onError = Color(0xFFF1F1F1),
                error = Color(0xFFE48801),
                background = colorResource(R.color.background_dark)
            )
        } else {
            lightColors(
                primary = colorResource(R.color.brand),
                primaryVariant = colorResource(R.color.brand),
                secondary = colorResource(R.color.brand),
                onSecondary = Color(0xFF2C2C2C),
                onPrimary = Color(0xFFF1F1F1),
                onError = Color(0xFFF1F1F1),
                error = Color(0xFFff9800),
                background = colorResource(R.color.background_light)
            )
        },
        shapes = Shapes(
            small = RoundedCornerShape(12.dp),
            medium = RoundedCornerShape(24.dp),
            large = RoundedCornerShape(24.dp),
        ),
    ) {
        Surface(
            color = MaterialTheme.colors.background,
            content = content
        )
    }
}

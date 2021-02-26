package com.vanpra.composematerialdialogs.datetime

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.util.Locale
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun DialogTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        modifier = modifier.fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        color = MaterialTheme.colors.onBackground,
        fontSize = 20.sp,
        style = TextStyle(fontWeight = FontWeight.W600)
    )
}

internal fun Float.getOffset(angle: Double): Offset =
    Offset((this * cos(angle)).toFloat(), (this * sin(angle)).toFloat())

internal val LocalDate.yearMonth: YearMonth
    get() = YearMonth.of(this.year, this.month)

internal val Month.fullLocalName: String
    get() = this.getDisplayName(java.time.format.TextStyle.FULL, Locale.getDefault())

internal val Month.shortLocalName: String
    get() = this.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault())

internal val DayOfWeek.shortLocalName: String
    get() = this.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault())

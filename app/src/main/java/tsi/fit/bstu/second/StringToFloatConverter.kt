package tsi.fit.bstu.second

import androidx.databinding.InverseMethod
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object StringToFloatConverter {
    @InverseMethod("floatToString")
    @JvmStatic
    fun stringToFloat(value: String): Float {
        val current: Locale = Locale.getDefault()
        val numberFormat = NumberFormat.getNumberInstance(current)
        numberFormat.maximumFractionDigits = 1
        numberFormat.minimumFractionDigits = 1
        return numberFormat.parse(value)?.toFloat() ?: 0f
    }


    @JvmStatic
    fun floatToString(value: Float?): String {
        val current: Locale = Locale.getDefault()
        val numberFormat = DecimalFormat.getNumberInstance(current)
        numberFormat.maximumFractionDigits = 1
        numberFormat.minimumFractionDigits = 1
        numberFormat.isGroupingUsed = false

        return numberFormat.format(value)
    }


    @JvmStatic
    fun floatToStringBeautified(value: Float): String {
        val current: Locale = Locale.getDefault()
        val numberFormat = DecimalFormat.getNumberInstance(current)
        numberFormat.maximumFractionDigits = 1
        numberFormat.minimumFractionDigits = 1
        return numberFormat.format(value)
    }
}


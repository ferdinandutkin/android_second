package tsi.fit.bstu.second

import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

class CalcViewModel : ViewModel() {
    var averageFuel = ObservableFloat(12.3f)

    var fuel = ObservableFloat(13.4f)

    var distance = ObservableFloat(12.2f)

    var fuelCost =  ObservableFloat(20.18f)

    var selectedModePosition = ObservableInt(0)

    var selectedMode : ObservableField<Mode> = object : ObservableField<Mode>(selectedModePosition) {
        override fun get(): Mode {
            return Mode.values()[selectedModePosition.get()]
        }
    }

    var result : ObservableField<CalculationResult> = object : ObservableField<CalculationResult>(averageFuel, fuel, distance, fuelCost, selectedMode) {
        override fun get(): CalculationResult {
            val averageFuel = averageFuel.get()
            val fuel = fuel.get()
            val distance = distance.get()
            val fuelCost = fuelCost.get()
            val mode = selectedMode.get()

            val totalFuelCost = fuel * fuelCost

            return when(mode) {
                Mode.Average -> CalculationResult.Average(totalFuelCost, (fuel / distance) * 100)
                Mode.Total -> CalculationResult.Total(totalFuelCost, (averageFuel / 100) * distance)
                else -> throw Exception("hz sho")
            }
        }
    }

    var averageModeResult : ObservableField<CalculationResult.Average?> = object : ObservableField<CalculationResult.Average?>(result) {
        override fun get(): CalculationResult.Average? {
            return result.get() as? CalculationResult.Average
        }
    }

    var totalModeResult : ObservableField<CalculationResult.Total?> = object : ObservableField<CalculationResult.Total?>(result) {
        override fun get(): CalculationResult.Total? {
            return result.get() as? CalculationResult.Total
        }
    }



}




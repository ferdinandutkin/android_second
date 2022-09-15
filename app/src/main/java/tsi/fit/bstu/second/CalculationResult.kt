package tsi.fit.bstu.second

open class CalculationResult private constructor(val totalFuelCost: Float) {
    class Average(totalFuelCost: Float, val averageFuel: Float) : CalculationResult(totalFuelCost)
    class Total(totalFuelCost: Float, val totalFuel: Float) : CalculationResult(totalFuelCost)
}
package tsi.fit.bstu.second

enum class Mode(val value : String) {
    Average("Рассчитать общий расход"),
    Total("Рассчитать средний расход на 100 км");

    override fun toString() : String {
        return value
    }
}
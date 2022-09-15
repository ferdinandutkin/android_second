package tsi.fit.bstu.second

enum class Mode(val value : String) {
    Average("Рассчитать средний расход на 100 км"),
    Total("Рассчитать общий расход");

    override fun toString() : String {
        return value
    }
}
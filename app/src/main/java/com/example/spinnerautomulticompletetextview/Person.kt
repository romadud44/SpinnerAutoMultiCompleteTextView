package com.example.spinnerautomulticompletetextview

data class Person(
    val name: String,
    val lastName: String,
    val age: String,
    val position: String
) {
    companion object {
        val positionsArray = mutableListOf(
            "Инженер",
            "Учитель",
            "Строитель",
            "Электрик",
            "Разработчик",
            "Директор",
            "Врач"
        )
    }

    override fun toString(): String {
        return "$name - $position"
    }
}



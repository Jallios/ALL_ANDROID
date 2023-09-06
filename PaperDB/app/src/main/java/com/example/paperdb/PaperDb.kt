package com.example.paperdb

import android.util.Log.d
import com.example.paperdb.PaperDb.Companion.getCar
import io.paperdb.Paper

class PaperDb {
    companion object {

        fun addItem(carItem: Car) {

            val car = getCar()

            val targetItem = car.singleOrNull { it == carItem }
            if (targetItem == null) {
                car.add(carItem)
            }
            saveCar(car)
        }

        fun removeItem(carItem: Car) {
            val car = getCar()

            val targetItem = car.singleOrNull { it == carItem }
            if (targetItem != null) {

                car.remove(targetItem)
            }
            saveCar(car)
        }

        fun saveCar(car: MutableList<Car>) {
            Paper.book("car").write("car", car)
        }

        fun getCar(): MutableList<Car> {
            return Paper.book("car").read("car", mutableListOf())!!
        }

    }
}
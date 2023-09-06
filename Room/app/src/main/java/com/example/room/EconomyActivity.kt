package com.example.room


import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Half.toFloat
import android.util.Log
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF


class EconomyActivity : AppCompatActivity() {
    lateinit var pieChart: PieChart
    lateinit var economy_category: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_economy)
        pieChart = findViewById(R.id.pieChart)
        economy_category = findViewById(R.id.economy_category)

        val pref : SharedPreferences
        pref = getSharedPreferences("1",MODE_PRIVATE)
        val login = pref.getString("login","")

        val db = AppDatabase.getAppDataBase(applicationContext)
        val countDao = db?.countDao()
        val userDao = db?.userDao()

        val uid = userDao!!.getUserName(login!!)

        pieChart.setUsePercentValues(true)

        val countInNal = countDao!!.getUid(uid.id!!,1,2131231029)
        val countInNeNal = countDao!!.getUid(uid.id!!,1,2131231037)

        pieChartAdd(countInNal,countInNeNal)

        economy_category.setOnClickListener {
            if (economy_category.isChecked){
                val countOutNal = countDao!!.getUid(uid.id!!,2,2131231029)
                val countOutNeNal = countDao!!.getUid(uid.id!!,2,2131231037)
                pieChartAdd(countOutNal,countOutNeNal)
            }
            else{
                val countInNal = countDao!!.getUid(uid.id!!,1,2131231029)
                val countInNeNal = countDao!!.getUid(uid.id!!,1,2131231037)

                pieChartAdd(countInNal,countInNeNal)
            }
        }
    }

    fun pieChartAdd(listNal: List<Count>,listNeNal: List<Count>){
        val arrayCount = ArrayList<PieEntry>()

        arrayCount.add(PieEntry(listNal.size.toFloat()))
        arrayCount.add(PieEntry(listNeNal.size.toFloat()))

        val dataSet = PieDataSet(arrayCount, "")

        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        pieChart.data = data
        pieChart.highlightValues(null)
        pieChart.invalidate()
        pieChart.animateXY(5000, 5000)

    }
}
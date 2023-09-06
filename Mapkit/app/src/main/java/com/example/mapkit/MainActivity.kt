package com.example.mapkit

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.*
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.runtime.Error
import org.intellij.lang.annotations.Language

class MainActivity : AppCompatActivity(), DrivingSession.DrivingRouteListener{

    lateinit var map:MapView
    lateinit var  to:Spinner
    lateinit var fabUser:FloatingActionButton
    lateinit var fabAB:FloatingActionButton

    lateinit var locationmapkit: UserLocationLayer

    private var mapObjects: MapObjectCollection? = null
    private var drivingRouter: DrivingRouter? = null
    private var drivingSession: DrivingSession? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var fromDot:Point = Point(55.689370, 37.255318)
    private var sharaga:Point = Point(55.664827, 37.597756)
    private var GYM:Point = Point(55.683899, 37.287109)

    private var coordinates:ArrayList<Point> = arrayListOf(sharaga, GYM)

    private var coorFrom:Point? = null
    private var coorTo:Point? = null

    @SuppressLint("MissingPermission", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("943659a5-6dce-4a58-a4d8-7abc73af508d")
        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_main)

        map = findViewById(R.id.map)
        to = findViewById(R.id.to)
        fabUser = findViewById(R.id.user)
        fabAB = findViewById(R.id.AdotB)

        map.map.move(CameraPosition(Point(55.689370, 37.255318),11.0f,0.0f,0.0f))
        Animation(Animation.Type.SMOOTH,5f)
        val mapInstance = MapKitFactory.getInstance()

        locationUser()
        locationmapkit = mapInstance.createUserLocationLayer(map.mapWindow)
        locationmapkit.isVisible = true

        map.map.mapObjects.addPlacemark(fromDot)
        map.map.mapObjects.addPlacemark(sharaga)
        map.map.mapObjects.addPlacemark(GYM)

        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()
        mapObjects = map.map.mapObjects.addCollection()

        if (to != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, coordinates)
            to.adapter = adapter

            to.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    mapObjects!!.clear()
                    coorTo = coordinates[position]
                    submitRequest(fromDot!!,coorTo!!)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        fabAB.setOnClickListener {
            if (to != null) {
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, coordinates)
                to.adapter = adapter

                to.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>,
                                                view: View, position: Int, id: Long) {
                        mapObjects!!.clear()
                        coorTo = coordinates[position]
                        submitRequest(fromDot!!,coorTo!!)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
            }
        }

        fabUser.setOnClickListener {
            mapObjects!!.clear()

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: android.location.Location? ->
                fromDot= Point(
                    location?.latitude.toString().toDouble(),
                    location?.longitude.toString().toDouble()
                )
                true
            }
            if (to != null) {
                val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, coordinates)
                to.adapter = adapter

                to.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>,
                                                view: View, position: Int, id: Long) {
                        mapObjects!!.clear()
                        coorTo = coordinates[position]
                        submitRequest(fromDot!!,coorTo!!)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
            }
        }

    }

    override fun onStop() {
        map.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        map.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    private fun locationUser(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 0)
            return
        }
    }

    override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
        for(route in p0){
            mapObjects!!.addPolyline(route.geometry)
        }

    }

    override fun onDrivingRoutesError(p0: Error) {
        var errorMessage = "Неизвестная ошибка!"
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
    }

    fun submitRequest(from:Point,to:Point) {

        val drivingOptions = DrivingOptions()
        val vehicleOptions = VehicleOptions()
        val requestPoints:ArrayList<RequestPoint> = ArrayList()
        requestPoints.add(RequestPoint(from,RequestPointType.WAYPOINT,null))
        requestPoints.add(RequestPoint(to,RequestPointType.WAYPOINT,null))
        drivingSession = drivingRouter!!.requestRoutes(requestPoints,drivingOptions,vehicleOptions,this)
    }
}
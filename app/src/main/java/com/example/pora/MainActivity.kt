package com.example.pora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.lib.City
import com.example.lib.Regions
import com.example.lib.Restaurant
import kotlinx.coroutines.awaitAll

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var a = Regions("Goriska")
        a.generateSampleCities(10)
        a.generateRestaurantsPerCity(10)
        println(a.toStringAll())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exitButton = findViewById<Button>(R.id.BTExit)
        exitButton.setOnClickListener {
            finish()
        }
        val regionArr: MutableList<Regions> = mutableListOf()
        val addButton = findViewById<Button>(R.id.BTAdd)
        addButton.setOnClickListener {
            val ETRegion = findViewById<EditText>(R.id.ETRegion)
            val ETCity = findViewById<EditText>(R.id.ETCity)
            val ETAvgCost = findViewById<EditText>(R.id.ETAvgCost)
            val ETRating = findViewById<EditText>(R.id.ETRating)
            val ETType = findViewById<EditText>(R.id.ETType)
            val ETRestaurant = findViewById<EditText>(R.id.ETRestaurant)



            var regionName: String? = null
            var cityName: String? = null
            var avgCost: Double? = null
            var rating: String? = null
            var type: String? = null
            var restaurantName: String? = null

            try {
                regionName = ETRegion.text.toString()
                cityName = ETCity.text.toString()
                avgCost = ETAvgCost.text.toString().toDouble()
                rating = ETRating.text.toString()
                type = ETType.text.toString()
                restaurantName = ETRestaurant.text.toString()

                if (regionName.isEmpty() || regionName.isEmpty() || cityName.isEmpty() || avgCost <= 0.0 || rating.isEmpty() || type.isEmpty()) {
                    throw Exception()
                }
                var regionExists : Boolean = false
                var tempRegion: Regions? = null
                for ( region in regionArr){
                    if(region.regionName == regionName){
                        tempRegion = region
                        regionExists = true
                    }
                }
                if(tempRegion == null){
                    tempRegion = Regions(regionName)
                }
                var tempCity = tempRegion.findCity(cityName)
                if(tempCity == null) {
                    tempCity = City(cityName)
                    tempRegion.cities.add(tempCity)
                }

                var tempRestaurant = tempCity.findRestaurant(restaurantName)
                if(tempRestaurant == null){
                    tempRestaurant = Restaurant(restaurantName,avgCost,type)
                    tempRestaurant.ratings.add(rating)
                    tempCity.addRestaurant(tempRestaurant)
                }
                else{
                    tempRestaurant.ratings.add(rating)
                }

                if(!regionExists)
                    regionArr.add(tempRegion)

            } catch (e: Exception) {
                Log.e("exception", "Input field empty: " + e.message!!)
            }

        }
        val infoButton = findViewById<Button>(R.id.BTInfo)
        infoButton.setOnClickListener {
            if(regionArr.isEmpty())
                println("No data")
            else
                for( region in regionArr)
                    println(region.toStringAll())
        }


    }
}

fun print2(msg:String){
    Log.v("output",msg)
}

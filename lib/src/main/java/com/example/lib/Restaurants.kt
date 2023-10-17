package com.example.lib
import io.github.serpro69.kfaker.faker
import java.util.UUID


class Regions(var regionName: String,
              val id: UUID = UUID.randomUUID(),
              val cities: MutableList<City> = mutableListOf()) {

    fun findCity( cityname2: String): City?{
        for( city in cities) {
            if (city.cityName == cityname2) {
                return city
            }
        }
        return null
    }
    fun compareTo( obj2:Regions):Int{
        if(regionName.contains(obj2.regionName)){
            return cities.size.compareTo(obj2.cities.size)
        }
        return regionName.compareTo(obj2.regionName)
    }
    fun generateSampleCities(i:Int){
        val faker = faker {}
        var j = 0
        while(j <= i){
            cities.add(City(faker.address.city()))
             j++
        }
    }
    fun generateRestaurantsPerCity(i:Int){
        if(cities.isNotEmpty()){
            val j = cities.size
            var z = 0
            while(z < j){
                cities[z].generateSampleRestaurants(i)
                z++
            }
        }
    }
    fun addCity(cityName: String){
        cities.add(City(cityName))
    }
    override fun toString(): String {
        var i = 0
        var str = ""
        while(i < cities.size){
            str += cities[i].cityName + if(i == cities.size - 1){ ". "} else { ", "}
            i++
        }
        return "$regionName cities in region: $str\n"
    }

    fun toStringAll(): String {
        var i = 0
        var str = ""
        while(i < cities.size){
            str += cities[i].toString() + if(i == cities.size - 1){ ""} else { ", "}
            i++
        }
        return "$regionName $str\n"
    }
}
class City(var cityName: String,
              val id: UUID = UUID.randomUUID(),
              val restaurants: MutableList<com.example.lib.Restaurant> = mutableListOf(),
              var numberOfRestaurants:Int = restaurants.size) {

    fun compareTo( obj2:City):Int{
        if(cityName.contains(obj2.cityName)){
            return restaurants.size.compareTo(obj2.restaurants.size)
        }
        return cityName.compareTo(obj2.cityName)
    }
    fun findRestaurant(restaurantName: String):Restaurant?{
        for( restaurant in restaurants)
            if(restaurant.compareTo(restaurantName))
                return restaurant
        return null
    }
    fun addRestaurant(resturantname:String, avgMoneySpent: Double, rating: String,type: String){
        val temp=com.example.lib.Restaurant(resturantname,avgMoneySpent,type)
        restaurants.add(temp)
        temp.ratings.add(rating)

        numberOfRestaurants++
    }
    fun addRestaurant(restaurant: Restaurant){
        restaurants.add(restaurant)
        numberOfRestaurants++
    }
    fun compareTo( cityname2:String):Boolean{
        return cityName == cityname2
    }
    fun generateSampleRestaurants(i:Int){
        val faker = faker {}
        var j = 0
        while(j <= i){
            var money=faker.money.amount(
                IntRange(20,100),true,"",".")
            var temp = com.example.lib.Restaurant(faker.restaurant.name(),money.replace("$","").toDouble(),faker.restaurant.type())
            restaurants.add(temp)
            temp.ratings.add(faker.restaurant.review())
            j++
        }
        numberOfRestaurants = restaurants.size
    }

    override fun toString(): String {
        var i = 0
        var str = ""
        while(i < restaurants.size){
            str += restaurants[i].toString()
            i++
        }
        return "$numberOfRestaurants restaurnats in $cityName : \n$str"
    }
}
class Restaurant(var restaurantName: String,
                 var avgMoneySpent:Double,
                 var type:String,
                 var ratings:MutableList<String> = mutableListOf(),
                 val id: UUID = UUID.randomUUID()) {

    override fun toString(): String {
        var str = "Name: $restaurantName, average money spent: $avgMoneySpent € type of restaurant: $type, ratings:"
        for(rating in ratings){
            str += "$rating "
        }
        return "$str\n"
    }
    fun compareTo( obj2:com.example.lib.Restaurant):Int{
        if(restaurantName.contains(obj2.restaurantName)){
            return type.compareTo(obj2.type)
        }
        return restaurantName.compareTo(obj2.restaurantName)
    }
    fun compareTo(restaurantName2: String):Boolean{
        return restaurantName == restaurantName2
    }


}
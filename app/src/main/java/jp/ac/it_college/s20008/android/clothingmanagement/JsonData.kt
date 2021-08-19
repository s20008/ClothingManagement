package jp.ac.it_college.s20008.android.clothingmanagement

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class JsonData(
    val content : String,
    val money : String,
    val job : String,
    val love : String,
    val total : String,
    val item : String,
    val color : String,
    val day : String,
    val rank : String,
    val sign : String
)

data class horo(val horosfcope: String)

@RequiresApi(api = Build.VERSION_CODES.O)
fun getSights() : List<JsonData> {
    val url =  getday()
    val data = URL(url)
    val bufferedReader = BufferedReader(InputStreamReader(data.openStream()))
    val str: String = bufferedReader.readText()
    val obj = Json.decodeFromString<List<JsonData>>(str)
    return obj
}

@RequiresApi(Build.VERSION_CODES.O)
fun getday(): String {
    val today = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    return dateFormatter.format(today)
}
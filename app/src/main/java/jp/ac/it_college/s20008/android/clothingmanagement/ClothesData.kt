package jp.ac.it_college.s20008.android.clothingmanagement

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ClothesData : RealmObject(){
    @PrimaryKey
    var id: Long = 0
    var price: String = ""
    var size: String = ""
    var type: String = ""
    var place: String = ""
}
package jp.ac.it_college.s20008.android.clothingmanagement

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import jp.ac.it_college.s20008.android.clothingmanagement.databinding.ActivityClothesBinding


class ClothesActivity:AppCompatActivity() {
    private lateinit var binding: ActivityClothesBinding
    private val args: ClothesActivityArgs by navArgs()

    private lateinit var realm: Realm


    companion object {
        const val CLOTHES_IMAGE_ID = "clothes_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClothesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        realm = Realm.getDefaultInstance()

        if (args.clothesId != -1L){
            val clothes = realm.where<ClothesData>()
                .equalTo("id", args.clothesId).findFirst()
            binding.priceText.setText(clothes?.price)
            binding.sizeText.setText(clothes?.size)
            binding.typeText.setText(clothes?.type)
            binding.placeText.setText(clothes?.place)
        }
        binding.saveButton.setOnClickListener { onSaveTapped() }

    }

    private fun onSaveTapped() {
        when(args.clothesId){
            -1L -> {
                realm.executeTransaction { db: Realm ->
                    val maxId = db.where<ClothesData>().max("id")
                    val nextId = (maxId?.toLong() ?: 0L) + 1L
                    val clothes = db.createObject<ClothesData>(nextId)
                    clothes.price = binding.priceText.text.toString()
                    clothes.size = binding.sizeText.text.toString()
                    clothes.type = binding.typeText.text.toString()
                    clothes.place = binding.placeText.text.toString()
                }
            }
            else -> {
                realm.executeTransaction { db: Realm ->
                    val clothes = db.where<ClothesData>()
                        .equalTo("id",args.clothesId).findFirst()
                    clothes?.price = binding.priceText.text.toString()
                    clothes?.size = binding.sizeText.text.toString()
                    clothes?.type = binding.typeText.text.toString()
                    clothes?.place = binding.placeText.text.toString()
                }

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


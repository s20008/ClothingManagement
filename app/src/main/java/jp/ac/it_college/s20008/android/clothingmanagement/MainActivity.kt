package jp.ac.it_college.s20008.android.clothingmanagement

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import io.realm.Realm
import jp.ac.it_college.s20008.android.clothingmanagement.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val clothes = mutableListOf<Clothes>()
    var count = 0

    private lateinit var realm: Realm

    private val previewLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data")
            setPicture(imageBitmap)
        }

    }

    private fun setPicture(imageBitmap: Any?) {

        clothes.add(Clothes("picture${++count}", imageBitmap))

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        realm = Realm.getDefaultInstance()

        binding.takePhotoBtn.setOnClickListener {
            preview()
        }

        binding.fuButton.setOnClickListener { view ->
            onLuckycolorTap()
        }

        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = ClothesAdapter(this, clothes)
        binding.recyclerView.adapter = adapter

    }

    private fun onLuckycolorTap() {
        val intent = Intent(this,LuckyColor::class.java)
        startActivity(intent)
    }

    private fun preview() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {
                previewLauncher.launch(intent)
            }
        }
    }
}




//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode){
//            fromAlbum -> {
//                if (resultCode == Activity.RESULT_OK && data != null) {
//                    data.data?.let { uri ->
//                        val bitmap = getBitmapFromUri(uri)
////                        binding.imageView2.setImageBitmap(bitmap)
//                        bitmap?.let { setPicture(it) }
//                    }
//                }
//            }
//        }
//    }
//
//    private fun getBitmapFromUri(uri: Uri) = contentResolver
//        .openFileDescriptor(uri,"r")?.use {
//            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
//        }





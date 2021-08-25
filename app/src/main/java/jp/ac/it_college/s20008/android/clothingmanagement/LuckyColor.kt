package jp.ac.it_college.s20008.android.clothingmanagement

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import jp.ac.it_college.s20008.android.clothingmanagement.databinding.ActivityLuckyColorBinding

class LuckyColor : AppCompatActivity() {
    //bindingの設定
    private lateinit var binding: ActivityLuckyColorBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLuckyColorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val myimage = binding.imageView
        val resResult: Fortune = Fortune().getFortune()

        binding.color.text = resResult.name
        myimage.setColorFilter(Color.parseColor(resResult.description))

        //backbuttonを押すとmainに戻る
        binding.backbutton.setOnClickListener { finish() }

    }
}
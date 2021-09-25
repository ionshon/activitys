package com.inu.activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inu.activitys.databinding.ActivityMainBinding
import com.inu.activitys.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.to1.text = intent.getStringExtra("from1")
        binding.to2.text = intent.getIntExtra("from2", 0).toString()

        binding.btnClose.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("STRING_INTENT_KEY", binding.editMessage.text.toString())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
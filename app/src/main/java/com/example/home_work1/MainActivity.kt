package com.example.home_work1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.home_work1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var imageUri: Uri? = null
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                imageUri = result.data?.data
                binding.imageView.setImageURI(imageUri)
            }
        }

        val imageUri = Uri.parse("android.resource://$packageName/${R.drawable.dog}")

        binding.imageView.setImageURI(imageUri)

        binding.buttonScreen2.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("imageUri", imageUri.toString())
            startActivity(intent)
        }
    }
}

package com.example.tbc_homework_12

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import com.example.tbc_homework_12.databinding.ActivityMainBinding

fun getList(context: Context): List<String>{
    return listOf(
        getString(context.contentResolver, "app_name"),
        getString(context.contentResolver, "app_name")
    )
}
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
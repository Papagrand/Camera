package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createPhotosDirectoryAndFile()
    }
    private fun createPhotosDirectoryAndFile() {
        val photosDirectory = File(applicationContext.externalMediaDirs.first(), "photos")

        if (!photosDirectory.exists()) {
            photosDirectory.mkdir()
        }

        val dateFile = File(photosDirectory, "date.txt")

        if (!dateFile.exists()) {
            dateFile.createNewFile()
        }
    }

}
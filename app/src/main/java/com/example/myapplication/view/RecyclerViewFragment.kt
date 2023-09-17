package com.example.myapplication.view

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class RecyclerViewFragment : Fragment() {
    private lateinit var goToCameraButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        goToCameraButton = view.findViewById(R.id.goToCameraButton)

        // Чтение данных из файла и создание списка PhotoData
        val path = "/storage/emulated/0/Android/media/com.example.myapplication/photos"
        val file = File(path, "date.txt")
        val photoDataList = readDataFromFile(file)
        // Генерация имен фотографий в зависимости от количества данных
        for ((index, photoData) in photoDataList.withIndex()) {
            photoData.photoName = "photo_${index + 1}"
        }

        // Инициализация адаптера и установка его в RecyclerView
        adapter = MyAdapter(photoDataList)
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager


        goToCameraButton = view.findViewById(R.id.goToCameraButton)
        goToCameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_recyclerViewFragment_to_cameraFragment)
        }
    }


    private fun readDataFromFile(file: File): List<MyAdapter.PhotoData> {
        val data = mutableListOf<MyAdapter.PhotoData>()
        if (file.exists()) {
            try {
                val reader = BufferedReader(FileReader(file))
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    // Преобразование строки в объект PhotoData и добавление его в список
                    data.add(MyAdapter.PhotoData(photoName = "", photoDateTime = line ?: ""))
                }
                reader.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        data.removeAt(0)
        return data
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerViewFragment().apply {
            }
    }
}
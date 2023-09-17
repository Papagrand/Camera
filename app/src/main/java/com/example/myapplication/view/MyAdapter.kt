package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.myapplication.R
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: List<PhotoData>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photoNameTextView: TextView = view.findViewById(R.id.photoNameTextView)
        val photoDateTimeTextView: TextView = view.findViewById(R.id.photoDateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        holder.photoNameTextView.text = currentItem.photoName
        holder.photoDateTimeTextView.text = currentItem.photoDateTime
    }

    override fun getItemCount(): Int {
        return data.size
    }

    data class PhotoData(var photoName: String, val photoDateTime: String)

}
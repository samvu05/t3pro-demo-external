package com.t3h.demoexternal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.t3h.demoexternal.databinding.ItemFileBinding
import java.io.File


class FileAdapter(val model: MainModel<Any?>) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {
    var files = mutableListOf<ItemFile>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        return FileViewHolder(
            ItemFileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount() = files.size

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.binding.item = files[position]
        holder.binding.root.setOnClickListener {
            if (File(files[position].path).isDirectory) {
                model.updateAllFolderOrFile(files[position].path)
            } else {

            }
        }
    }



    class FileViewHolder(val binding: ItemFileBinding) : RecyclerView.ViewHolder(binding.root)
}
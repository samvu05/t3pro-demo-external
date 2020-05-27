package com.t3h.demoexternal

import android.os.Build
import android.os.Environment
import android.os.StatFs
import androidx.lifecycle.MutableLiveData
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MainModel<SDCardInfo> {
    private val format = SimpleDateFormat("DD/MM/YYYY")
    val listFiles = MutableLiveData<MutableList<ItemFile>>()
    var currentPath = ""
    fun updateAllFolderOrFile(path: String) {
        currentPath = path
        val listItemFile = mutableListOf<ItemFile>()
        val filePath = File(path)
        if (filePath.isDirectory) {
            for (file in filePath.listFiles()) {
                listItemFile.add(
                    ItemFile(
                        file.path,
                        file.name,
                        if (file.isDirectory) {
                            when (file.name) {
                                "Music" -> R.drawable.folder_music
                                "Podcasts" -> R.drawable.folder_podcasts
                                "Download" -> R.drawable.folder_download
                                "Alarms" -> R.drawable.folder_alarm
                                "Movies" -> R.drawable.folder_movies
                                "Pictures" -> R.drawable.folder_picture
                                else -> R.drawable.folder_browse
                            }
                        } else {
                            R.drawable.folder_porn
                        },
                        getFilesCount(file).toString(),
                        format.format(
                            Date(file.lastModified())
                        )
                    )
                )
            }
        }
        listFiles.value = listItemFile
    }

    fun getFilesCount(file: File): Int {
        var files: Array<File>? = file.listFiles();
        var count = 0;
        if (files != null) {
            for (f in files)
                if (f.isDirectory())
                    count += getFilesCount(f);
                else
                    count++
        };

        return count;
    }

    fun readFile(itemFile: ItemFile) {

    }

}
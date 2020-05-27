package com.t3h.demoexternal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.t3h.demoexternal.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var model:MainModel<Any?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        model = MainModel()
        binding.rc.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=FileAdapter(model)
        }

        binding.model = model
        model.listFiles.observe(this, object : Observer<MutableList<ItemFile>>{
            override fun onChanged(t: MutableList<ItemFile>?) {
                if (t != null){
                    (binding.rc.adapter as FileAdapter).files = t
                }
                binding.rc.adapter!!.notifyDataSetChanged()

            }
        })


        model.updateAllFolderOrFile(
            Environment.getExternalStorageDirectory().path
        )
    }

    override fun onBackPressed() {
        if (model.currentPath.equals( Environment.getExternalStorageDirectory().path)){
            super.onBackPressed()
        }else {
            model.updateAllFolderOrFile(
                File(model.currentPath).parent
            )
        }

    }

}

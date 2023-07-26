package com.example.baseadapter

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.baseadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    var array = mutableListOf<String>("boom", "room", "moon")
    lateinit var adapter: ArrayAdapter<String>
    lateinit var baseAdapterClass: BaseAdapterClass
    lateinit var binding: ActivityMainBinding
    var studentList = mutableListOf<StudentDataClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        binding.listView.setOnClickListener {}

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            AlertDialog.Builder(this)
                .setTitle("Delete or Update")
                .setMessage("do you want to delete or update?")
                .setPositiveButton("Delete", { _, _ ->
                    array.removeAt(position)
                    adapter.notifyDataSetChanged()
                })
                .setNegativeButton("update", { _, _ ->
                    showUpdateDialog(position)
                })
                .show()
        }

        binding.listView.setOnItemLongClickListener { parent, view, position, id ->
            array.removeAt(position)
            adapter.notifyDataSetChanged()
            return@setOnItemLongClickListener true
        }

        binding.fab.setOnClickListener()
        {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.add_item_layout)
            dialog.show()
            var etName = dialog.findViewById<EditText>(R.id.etName)
            var btnAdd = dialog.findViewById<Button>(R.id.btnAdd)

            btnAdd.setOnClickListener {
                if (etName.text.toString().isNullOrEmpty()) {
                    etName.error = "enter your name"
                } else {
                    array.add(etName.text.toString())
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
        }

        baseAdapterClass = BaseAdapterClass(studentList)
        binding.listView.adapter = baseAdapterClass
        studentList.add(StudentDataClass(1, "boom"))
        studentList.add(StudentDataClass(2, "room"))
        studentList.add(StudentDataClass(name = "moon"))
        binding.listView.setOnItemClickListener { adapter, view, position, id ->
            System.out.println("position $position id $id")
            studentList.removeAt(position)
            baseAdapterClass.notifyDataSetChanged()
        }
    }



     fun showUpdateDialog(position: Int) {
         array.set(position, "drool")
         adapter.notifyDataSetChanged()

    }


}





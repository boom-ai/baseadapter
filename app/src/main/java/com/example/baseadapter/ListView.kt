package com.example.baseadapter

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.baseadapter.databinding.ActivityMainBinding

class mainActivity : AppCompatActivity() {
    private var array = mutableListOf<String>("boom", "room", "moon")
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var baseAdapterClass: BaseAdapterClass
    private lateinit var binding: ActivityMainBinding
    private var studentList = mutableListOf<StudentDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            showDeleteOrUpdateDialog(position)
        }

        binding.listView.setOnItemLongClickListener { _, _, position, _ ->
            array.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }

        binding.fab.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_item_layout)
            dialog.show()

            val etName = dialog.findViewById<EditText>(R.id.etName)
            val btnAdd = dialog.findViewById<Button>(R.id.btnAdd)

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

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            studentList.removeAt(position)
            baseAdapterClass.notifyDataSetChanged()
        }
    }

    private fun showDeleteOrUpdateDialog(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Delete or Update")
            .setMessage("Do you want to delete or update?")
            .setPositiveButton("Delete") { _, _ ->
                if (position in 0 until array.size) {
                    array.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Update") { _, _ ->
                showUpdateDialog(position)
            }
            .show()
    }

    private fun showUpdateDialog(position: Int) {
        if (position in 0 until array.size) {
            array[position] = "drool"
            adapter.notifyDataSetChanged()
        }
    }
}

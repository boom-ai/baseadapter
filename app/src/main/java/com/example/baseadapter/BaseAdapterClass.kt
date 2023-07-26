package com.example.baseadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class BaseAdapterClass (var studentList: MutableList<StudentDataClass>) : BaseAdapter() {
    override fun getCount(): Int {
        return studentList.size
    }

    override fun getItem(position: Int): Any
    {
        return studentList[position]
    }


    override fun getItemId(position: Int): Long {
        return studentList[position].id ?: 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater
            .from(parent?.context).inflate(R.layout.item_adapter, parent, false)
        var tvId = view.findViewById<TextView>(R.id.tvId)
        var tvName = view.findViewById<TextView>(R.id.tvName)

        tvId.setText("${studentList[position].id}")
        tvName.setText("${studentList[position].name}")
        return view

    }
}

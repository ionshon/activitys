package com.inu.activitys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*
import java.text.SimpleDateFormat


class CustomAdapter : RecyclerView.Adapter<Holder>() { //어댑터 상속 클래스
    var listData = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(itemView: View): RecyclerView.ViewHolder(itemView) { //뷰홀더 상속 클래스
    init {
        itemView.setOnClickListener {
            Toast.makeText(itemView.context, "클릭된 아이템 = ${itemView.textTitle.text}",
            Toast.LENGTH_SHORT).show()
        }
    }
    fun setMemo(memo: Memo) {
        itemView.textNo.text = "${memo.no}"
        itemView.textTitle.text = memo.title

        var sdf = SimpleDateFormat("yyyy/MM/dd")
        var formattedDate = sdf.format(memo.timestamp)
        itemView.textDate.text = formattedDate
    }
}
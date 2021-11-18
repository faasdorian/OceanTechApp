package br.com.zenith.oceantechapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.zenith.oceantechapp.activity.R
import br.com.zenith.oceantechapp.model.Porto

class PortosListAdapter(val data: List<Porto>, onPortoListener: OnPortoListener) : RecyclerView.Adapter<PortosListAdapter.MyViewHolder>() {
    private val mOnPortoListener: OnPortoListener = onPortoListener

    class MyViewHolder(row: View, onPortoListener: OnPortoListener) : RecyclerView.ViewHolder(row), View.OnClickListener {
        val layout = row.findViewById<LinearLayout>(R.id.layout)
        val textView = row.findViewById<TextView>(R.id.tv_nome)
        var onPortoListener: OnPortoListener = onPortoListener

        init {
            layout.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onPortoListener.onPortoClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent, false)
        return MyViewHolder(layout, mOnPortoListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = data[position].nome
    }
    override fun getItemCount(): Int = data.size

    interface OnPortoListener {
        fun onPortoClick(position: Int)
    }

}
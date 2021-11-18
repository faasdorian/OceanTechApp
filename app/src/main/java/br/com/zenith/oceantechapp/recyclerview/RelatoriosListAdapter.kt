package br.com.zenith.oceantechapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.zenith.oceantechapp.activity.R
import br.com.zenith.oceantechapp.model.Relatorio

class RelatoriosListAdapter(val data: List<Relatorio>, onRelatorioListener: OnRelatorioListener) : RecyclerView.Adapter<RelatoriosListAdapter.MyViewHolder>() {
    private val mOnRelatorioListener: OnRelatorioListener = onRelatorioListener

    class MyViewHolder(row: View, onRelatorioListener: OnRelatorioListener) : RecyclerView.ViewHolder(row), View.OnClickListener {
        val layout = row.findViewById<LinearLayout>(R.id.layout)
        val textView = row.findViewById<TextView>(R.id.tv_nome)
        var onRelatorioListener: OnRelatorioListener = onRelatorioListener

        init {
            layout.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onRelatorioListener.onRelatorioClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent, false)
        return MyViewHolder(layout, mOnRelatorioListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = data[position].data
    }
    override fun getItemCount(): Int = data.size

    interface OnRelatorioListener {
        fun onRelatorioClick(position: Int)
    }

}
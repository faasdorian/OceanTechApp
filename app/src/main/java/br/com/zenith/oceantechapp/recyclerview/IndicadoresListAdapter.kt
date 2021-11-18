package br.com.zenith.oceantechapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.zenith.oceantechapp.activity.R
import br.com.zenith.oceantechapp.model.Indicador

class IndicadoresListAdapter(val data: List<Indicador>) : RecyclerView.Adapter<IndicadoresListAdapter.MyViewHolder>() {

    class MyViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val layout = row.findViewById<LinearLayout>(R.id.layout)
        val tvNome = row.findViewById<TextView>(R.id.tv_nome)
        val tvValor = row.findViewById<TextView>(R.id.tv_valor)
        val tvParametro = row.findViewById<TextView>(R.id.tv_parametro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_indicador,
            parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvNome.text = data[position].nome
        holder.tvValor.text = data[position].valor.toString()
        holder.tvParametro.text = data[position].parametro
    }
    override fun getItemCount(): Int = data.size

}
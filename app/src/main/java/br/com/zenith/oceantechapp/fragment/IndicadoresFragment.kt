package br.com.zenith.oceantechapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.zenith.oceantechapp.activity.R
import br.com.zenith.oceantechapp.activity.databinding.FragmentIndicadoresBinding
import br.com.zenith.oceantechapp.model.Indicador
import br.com.zenith.oceantechapp.recyclerview.IndicadoresListAdapter
import br.com.zenith.oceantechapp.service.IndicadorServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndicadoresFragment : Fragment() {

    private lateinit var bindings: FragmentIndicadoresBinding
    private lateinit var rv: RecyclerView
    private lateinit var indicadores: List<Indicador>
    private val args by navArgs<IndicadoresFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.fragment_indicadores, container, false)
        rv = bindings.rv
        rv.layoutManager = LinearLayoutManager(this.context)

        bindings.tvTitle1.append(args.relatorio.data)
        bindings.tvTitle2.append(args.porto.nome)

        indicadores = mutableListOf()
        getIndicadores()

        return bindings.root
    }

    private fun getIndicadores() {
        IndicadorServiceImpl().service.getAllIndicadores(args.relatorio.id).enqueue(object : Callback<List<Indicador>> {
            override fun onResponse(call: Call<List<Indicador>>, response: Response<List<Indicador>>) {
                if (response.isSuccessful) {
                    indicadores = response.body()!!
                    if (indicadores.isEmpty()) {
                        notFound()
                    } else {
                        found()
                        rv.adapter = IndicadoresListAdapter(indicadores)
                    }
                }
            }
            override fun onFailure(call: Call<List<Indicador>>, t: Throwable) {
                notFound()
            }
        })
    }

    private fun found() {
        bindings.progressBar.visibility = View.GONE
        bindings.rv.visibility = View.VISIBLE
    }

    private fun notFound() {
        bindings.progressBar.visibility = View.GONE
        bindings.iNotFound.visibility = View.VISIBLE
        bindings.tvNotFound.visibility = View.VISIBLE
    }

}
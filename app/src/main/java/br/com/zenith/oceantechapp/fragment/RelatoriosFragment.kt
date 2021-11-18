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
import br.com.zenith.oceantechapp.activity.databinding.FragmentRelatoriosBinding
import br.com.zenith.oceantechapp.model.Relatorio
import br.com.zenith.oceantechapp.recyclerview.RelatoriosListAdapter
import br.com.zenith.oceantechapp.service.RelatorioServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RelatoriosFragment : Fragment(), RelatoriosListAdapter.OnRelatorioListener {

    private lateinit var bindings: FragmentRelatoriosBinding
    private lateinit var rv: RecyclerView
    private lateinit var relatorios: List<Relatorio>
    private val args by navArgs<RelatoriosFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.fragment_relatorios, container, false)
        rv = bindings.rv
        rv.layoutManager = LinearLayoutManager(this.context)

        relatorios = mutableListOf()
        getRelatorios()

        return bindings.root
    }

    private fun getRelatorios() {
        RelatorioServiceImpl().service.getAllRelatorios(args.porto.id).enqueue(object : Callback<List<Relatorio>> {
            override fun onResponse(call: Call<List<Relatorio>>, response: Response<List<Relatorio>>) {
                if (response.isSuccessful) {
                    relatorios = response.body()!!
                    if (relatorios.isEmpty()) {
                        notFound()
                    } else {
                        found()
                        rv.adapter = RelatoriosListAdapter(relatorios, this@RelatoriosFragment)
                    }
                }
            }
            override fun onFailure(call: Call<List<Relatorio>>, t: Throwable) {
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

    override fun onRelatorioClick(position: Int) {
        val action = RelatoriosFragmentDirections.actionRelatoriosFragmentToIndicadoresFragment(relatorios[position], args.porto)
        findNavController().navigate(action)
    }

}
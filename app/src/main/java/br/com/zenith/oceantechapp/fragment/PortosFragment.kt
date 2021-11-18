package br.com.zenith.oceantechapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.zenith.oceantechapp.activity.R
import br.com.zenith.oceantechapp.activity.databinding.FragmentPortosBinding
import br.com.zenith.oceantechapp.model.Porto
import br.com.zenith.oceantechapp.recyclerview.PortosListAdapter
import br.com.zenith.oceantechapp.service.PortoServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PortosFragment : Fragment(), PortosListAdapter.OnPortoListener {

    private lateinit var bindings: FragmentPortosBinding
    private lateinit var rv: RecyclerView
    private lateinit var portos: List<Porto>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.fragment_portos, container, false)
        rv = bindings.rv
        rv.layoutManager = LinearLayoutManager(this.context)

        portos = mutableListOf()
        getPortos()

        return bindings.root
    }

    private fun getPortos() {
        PortoServiceImpl().service.getAllPortos().enqueue(object : Callback<List<Porto>> {

            override fun onResponse(call: Call<List<Porto>>, response: Response<List<Porto>>) {
                if (response.isSuccessful) {
                    portos = response.body()!!
                    if (portos.isEmpty()) {
                        notFound()
                    } else {
                        found()
                        rv.adapter = PortosListAdapter(portos, this@PortosFragment)
                    }
                }
            }

            override fun onFailure(call: Call<List<Porto>>, t: Throwable) {
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

    override fun onPortoClick(position: Int) {
        val action = PortosFragmentDirections.actionPortosFragmentToRelatoriosFragment(portos[position])
        findNavController().navigate(action)
    }

}
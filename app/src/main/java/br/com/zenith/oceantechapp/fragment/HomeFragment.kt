package br.com.zenith.oceantechapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import br.com.zenith.oceantechapp.activity.R
import br.com.zenith.oceantechapp.activity.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var bindings: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setupListeners()
        return bindings.root
    }

    private fun setupListeners() {
        bindings.buttonPortos.setOnClickListener {
            goToPortosFragment()
        }

        bindings.buttonIntegrantes.setOnClickListener {
            goToIntegrantesFragment()
        }
    }

    private fun goToPortosFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToPortosFragment()
        findNavController().navigate(action)
    }

    private fun goToIntegrantesFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToIntegrantesFragment()
        findNavController().navigate(action)
    }

}
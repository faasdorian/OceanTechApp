package br.com.zenith.oceantechapp.fragment.ui.login

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import br.com.zenith.oceantechapp.activity.R
import br.com.zenith.oceantechapp.activity.databinding.FragmentLoginBinding
import br.com.zenith.oceantechapp.fragment.HomeFragment
import br.com.zenith.oceantechapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var bindings: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        auth = Firebase.auth

        setupListeners()

        return bindings.root
    }

    private fun setupListeners() {
        bindings.login.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val username = bindings.username.text.toString()
        val password = bindings.password.text.toString()

        if (username != "" && password != "") {
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this.requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val user = User(auth.currentUser?.email!!, auth.currentUser?.uid!!)
                        goToHomeFragment(user)
                    } else {
                        Toast.makeText(this.context, "E-mail e/ou senha inválido(s)",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this.context, "Campo(s) e-mail e/ou senha não preenchido(s)",
                Toast.LENGTH_SHORT).show()
        }

    }

    private fun goToHomeFragment(user: User) {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(user)
        findNavController().navigate(action)
    }

}
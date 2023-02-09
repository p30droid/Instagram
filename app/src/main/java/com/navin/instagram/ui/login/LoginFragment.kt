package com.navin.instagram.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.navin.instagram.R
import com.navin.instagram.databinding.FragmentLoginBinding
import com.navin.instagram.models.LoginModel
import com.navin.instagram.models.State
import okhttp3.ResponseBody


class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding
    lateinit var owner : LifecycleOwner
    lateinit var viewModel: LoginViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.owner = this
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.isLogin().observe(owner, Observer<Boolean>{
            if(it==true){
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_dashboardFragment)
            }
        })


        binding.btnLogin.setOnClickListener({

            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            viewModel.login(username, password).observe(owner, Observer<LoginModel>{
                Log.e("","")
                if(it.data.state==State.SUCCESS.state) {
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_dashboardFragment)

                }else {
                    Toast.makeText(requireContext(),"Error", Toast.LENGTH_LONG).show()
                }
            })


        })

    }


}
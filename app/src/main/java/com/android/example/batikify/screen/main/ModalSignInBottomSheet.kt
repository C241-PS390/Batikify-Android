package com.android.bottomsheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.android.example.batikify.R
import com.android.example.batikify.data.pref.UserModel
import com.android.example.batikify.factory.AuthViewModelFactory
import com.android.example.batikify.screen.home.HomeActivity
import com.android.example.batikify.screen.main.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class ModalSignInBottomSheet: BottomSheetDialogFragment() {

    private val mainViewModel: MainViewModel by viewModels {
        AuthViewModelFactory.getAuthInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.modal_signin_bottom_sheet_content, container, false)

        val emailEditText: TextInputEditText = view.findViewById(R.id.emailEditText)
        val passwordEditText: TextInputEditText = view.findViewById(R.id.passwordEditText)
        val btnSignIn: Button = view.findViewById(R.id.btnSignIn)

        btnSignIn.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateInputs(email, password)) {
                performSignIn(email, password)
            }
        }

        return view
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun performSignIn(email: String, password: String) {
        Toast.makeText(context, "Signing in with email: $email", Toast.LENGTH_SHORT).show()
        mainViewModel.login(email,password)

        mainViewModel.loginResult.observeForever{response ->
            if(response.status == "success"){
                val token = response.data?.token
                if(token != null){
                    val user = UserModel(email = email, token = token, isLogin = true)
                    mainViewModel.saveSession(user)
                    val intent = Intent(requireActivity(), HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }else{
                    Log.d(TAG,"Token is null")
                }
            }else{
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val TAG = "ModalSignInBottomSheet"
    }
}

package com.android.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.android.example.batikify.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class ModalSignInBottomSheet : BottomSheetDialogFragment() {

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
        dismiss()
    }

    companion object {
        const val TAG = "ModalSignInBottomSheet"
    }
}

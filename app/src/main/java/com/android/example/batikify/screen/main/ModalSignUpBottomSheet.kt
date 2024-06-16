package com.android.example.batikify.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.android.example.batikify.R
import com.android.example.batikify.data.response.ErrorResponse
import com.android.example.batikify.databinding.ModalSignupBottomSheetContentBinding
import com.android.example.batikify.factory.AuthViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalSignUpBottomSheet : BottomSheetDialogFragment() {
    private var binding: ModalSignupBottomSheetContentBinding? = null
    private val signUpViewModel by viewModels<SignUpViewModel> {
        AuthViewModelFactory.getAuthInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ModalSignupBottomSheetContentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnSignUp?.setOnClickListener {
            val fullName = binding?.nameEditText?.text.toString().trim()
            val email = binding?.emailEditText?.text.toString().trim()
            val password = binding?.passwordEditText?.text.toString().trim()
            val passwordConfirmation = binding?.passwordConfirmationEditText?.text.toString().trim()

            signUpViewModel.register(fullName, email, password, passwordConfirmation)
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        signUpViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }
        signUpViewModel.status.observe(viewLifecycleOwner) { status ->
            status?.let { statusResponse ->
                handleStatusResponse(statusResponse)
            }
        }
        signUpViewModel.errors.observe(viewLifecycleOwner) { errors ->
            if (errors.isNotEmpty()) {
                displayValidationErrors(errors)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.btnSignUp?.isEnabled = !isLoading
        binding?.btnSignUp?.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
        binding?.btnSignUp?.text = if (isLoading) getString(R.string.loading) else getString(R.string.sign_up)
    }

    private fun handleStatusResponse(statusResponse: String) {
        signUpViewModel.message.observe(viewLifecycleOwner) { message ->
            if (statusResponse == getString(R.string.success)) {
                showAlertDialog(getString(R.string.yeah), message, getString(R.string.next)) {
                    dismiss()
                }
            }
        }
    }

    private fun showAlertDialog(title: String, message: String?, buttonText: String, onPositive: (() -> Unit)? = null) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(buttonText) { dialog, _ ->
                onPositive?.invoke()
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    private fun displayValidationErrors(errors: List<ErrorResponse>) {
        binding?.nameInputLayout?.error = null
        binding?.emailInputLayout?.error = null
        binding?.passwordInputLayout?.error = null
        binding?.passwordConfirmationInputLayout?.error = null

        errors.forEach { error ->
            when (error.field) {
                getString(R.string.response_fullname) -> binding?.nameInputLayout?.error = error.message
                getString(R.string.response_email) -> binding?.emailInputLayout?.error = error.message
                getString(R.string.response_password) -> binding?.passwordInputLayout?.error = error.message
                getString(R.string.response_passwordconfirmation) -> binding?.passwordConfirmationInputLayout?.error = error.message
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        const val TAG = "ModalSignUpBottomSheet"
    }
}
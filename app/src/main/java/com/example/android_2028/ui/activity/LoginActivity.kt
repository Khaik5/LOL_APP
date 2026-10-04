package com.example.android_2028.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.android_2028.R
import com.example.android_2028.databinding.ActivityLoginBinding
import com.example.android_2028.ui.utils.applyTopInsetMargin
import com.example.android_2028.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnBack.applyTopInsetMargin(8)

        initListener()
        initCollect()
        setupKeyboardInset()
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if (email.isBlank()) {
                binding.edtEmail.setBackgroundResource(R.drawable.bg_edittext_error)
            }
            if (password.isBlank()) {
                binding.edtPassword.setBackgroundResource(R.drawable.bg_edittext_error)
            }
            viewModel.handleLogin(email, password)
        }
        binding.btnShowPassword.setOnClickListener {
            viewModel.showPassWord()
        }
        binding.edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edtEmail.setBackgroundResource(R.drawable.bg_edittext_focus)
            } else {
                if (binding.edtEmail.text.toString().trim().isBlank()) {
                    binding.edtEmail.setBackgroundResource(R.drawable.bg_edittext_error)
                } else {
                    binding.edtEmail.setBackgroundResource(R.drawable.bg_edittext_normal)
                }
            }
        }
        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.edtEmail.hasFocus()) {
                    binding.edtEmail.setBackgroundResource(R.drawable.bg_edittext_focus)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.edtPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edtPassword.setBackgroundResource(R.drawable.bg_edittext_focus)
            } else {
                if (binding.edtPassword.text.toString().trim().isBlank()) {
                    binding.edtPassword.setBackgroundResource(R.drawable.bg_edittext_error)
                } else {
                    binding.edtPassword.setBackgroundResource(R.drawable.bg_edittext_normal)
                }
            }
        }
        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.edtPassword.hasFocus()) {
                    binding.edtPassword.setBackgroundResource(R.drawable.bg_edittext_focus)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun initCollect() {
        lifecycleScope.launch {
            viewModel.message.collect { message ->
                Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                if (message == "Login successful.") {
                    val intent = Intent(this@LoginActivity, MainHomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.itShowLoading.collect { isLoading ->
                binding.loading.visibility = if (isLoading) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }

        lifecycleScope.launch {
            viewModel.itShowPassWord.collect { showPassword ->
                if (showPassword) {
                    binding.edtPassword.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    binding.edtPassword.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                binding.edtPassword.setSelection(binding.edtPassword.text.length)
            }
        }
    }

    private fun setupKeyboardInset() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            val isKeyboardOpen = ime.bottom > 0
            val bottomSpace = if (isKeyboardOpen) {
                ime.bottom + 12
            } else {
                systemBars.bottom + 50
            }
            val btnLp = binding.btnLogin.layoutParams as ViewGroup.MarginLayoutParams
            btnLp.bottomMargin = bottomSpace
            binding.btnLogin.layoutParams = btnLp
            if (isKeyboardOpen) {
                binding.layoutLogin.translationY = -40f
            } else {
                binding.layoutLogin.translationY = 0f
            }
            insets
        }
    }
}
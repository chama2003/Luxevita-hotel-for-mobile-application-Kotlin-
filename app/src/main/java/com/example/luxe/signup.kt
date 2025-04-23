package com.example.luxe


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

import com.example.luxe.signupdb.sign_info
import com.example.luxe.signupdb.signdb
import com.example.luxe.signupdb.signviewmodel
import com.example.luxe.signupdb.signviewmodelfactory
class signup : AppCompatActivity() {
    private lateinit var sname: EditText
    private lateinit var saddrss: EditText
    private lateinit var password: EditText
    private lateinit var confirmpassword: EditText

    private lateinit var gender: RadioGroup
    private lateinit var viewModel: signviewmodel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sname = findViewById(R.id.editTextFullName)
        saddrss = findViewById(R.id.editTextAddress)
        password = findViewById(R.id.pass)
        confirmpassword = findViewById(R.id.editTextConfirmPassword)

        gender = findViewById(R.id.radioGroupGender)




        val dao = signdb.getInstance(application).sign_dao()
        val factory = signviewmodelfactory(dao)
        viewModel = ViewModelProvider(this, factory).get(signviewmodel::class.java)

        val homeBtn = findViewById<Button>(R.id.buttonBack)
        homeBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val add = findViewById<Button>(R.id.buttonSignUp)

        add.setOnClickListener {
            savesignData()

        }

    } private fun savesignData() {
        val sname = sname.text.toString()
        val saddrss = saddrss.text.toString()
        val password = password.text.toString()
        if (password != confirmpassword.text.toString()) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }
        if (sname.isEmpty() || saddrss.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedRadioId = gender.checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedRadioId)
        if (selectedRadioId != -1) {

            val gen = selectedRadioButton.text.toString()
            val sign = sign_info(id = 0, sname, saddrss, gen, password)
            viewModel.insertsign(sign)
            Toast.makeText(this, "sign data saved succrssfully", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
        }




    }

}

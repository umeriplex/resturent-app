package com.example.luckybroastcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.luckybroastcustomer.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_sign_up);
    }
}
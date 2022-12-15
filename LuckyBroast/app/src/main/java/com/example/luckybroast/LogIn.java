package com.example.luckybroast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.luckybroast.databinding.ActivityLogInBinding;

public class LogIn extends AppCompatActivity {
    ActivityLogInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
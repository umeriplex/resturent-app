package com.example.luckybroast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.luckybroast.databinding.ActivityAddNewBinding;

public class AddNew extends AppCompatActivity {

    ActivityAddNewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
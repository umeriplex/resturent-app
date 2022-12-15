package com.example.luckybroastcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.luckybroastcustomer.databinding.ActivityItemDetailsBinding;
import com.example.luckybroastcustomer.models.Items;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {

    ActivityItemDetailsBinding binding;
    String name, desc, image, cat;
    int price;
    Cart cart;
    Items currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backImageDetails.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.cartImage.setOnClickListener(v -> {
            startActivity(new Intent(ItemDetailsActivity.this, CartActivity.class));
        });
        cart = TinyCartHelper.getCart();


        name = getIntent().getStringExtra("food_name");
        desc = getIntent().getStringExtra("food_desc");
        image = getIntent().getStringExtra("food_image");
        cat = getIntent().getStringExtra("food_cat");
        price = getIntent().getIntExtra("food_price", 0);

        setDataIntoView();
        cartImp();


    }

    private void setDataIntoView() {
        binding.nameDetails.setText(name);
        binding.descDetails.setText(desc);
        binding.priceDetails.setText("Rs. " + price);
        Glide.with(this).load(image).into(binding.imageDetails);
        binding.titleTVDetails.setText(name);
    }

    private void cartImp() {
        currentItem = new Items(name, image, price, cat);
        binding.addBTN.setOnClickListener(v -> {
            cart.addItem(currentItem, 1);
            binding.addBTN.setEnabled(false);
            binding.addBTN.setText("Added in cart");
        });

    }
}
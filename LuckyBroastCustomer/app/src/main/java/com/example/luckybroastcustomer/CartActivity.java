package com.example.luckybroastcustomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.luckybroastcustomer.adapters.CartAdapter;
import com.example.luckybroastcustomer.databinding.ActivityCartBinding;
import com.example.luckybroastcustomer.models.Items;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    List<Items> list;
    CartAdapter adapter;
    Cart cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backImageCart.setOnClickListener(v->{onBackPressed();});
        cart = TinyCartHelper.getCart();

        getCartItems();

    }

    private void getCartItems(){
        list = new ArrayList<>();
        for (Map.Entry<Item, Integer> item : cart.getAllItemsWithQty().entrySet()){
            Items items = (Items) item.getKey();
            int qnt = item.getValue();
            items.setQnt(qnt);

            list.add(items);
        }
        binding.cartRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, list, new CartAdapter.CartListeners() {
            @Override
            public void onQuantityChanged() {
                binding.total.setText(String.format("PKR %.2f",cart.getTotalPrice()));
            }
        });
        binding.cartRecView.setAdapter(adapter);

        binding.total.setText(String.format("PKR %.2f",cart.getTotalPrice()));

    }
}
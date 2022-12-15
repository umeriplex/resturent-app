package com.example.luckybroastrasturent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.luckybroastrasturent.databinding.ActivityMainBinding;
import com.example.luckybroastrasturent.models.Items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private List<Items> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addNew.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNewActivity.class));
        });
        gotoFoodActivity();
    }

    private void gotoFoodActivity(){

        binding.fastFoodcardView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Fast Foods");
            startActivity(intent);
        });

        binding.DesicardView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Desi Foods");
            startActivity(intent);
        });

        binding.BBQcardView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","BBQ");
            startActivity(intent);
        });

        binding.chineseCardView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Chinese Foods");
            startActivity(intent);
        });

        binding.dealsCardView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Deals");
            startActivity(intent);
        });

        binding.iceCreamCardView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Ice Creams");
            startActivity(intent);
        });

        binding.drinksCardView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Drinks");
            startActivity(intent);
        });

        binding.offersCardView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Offers");
            startActivity(intent);
        });


    }
}
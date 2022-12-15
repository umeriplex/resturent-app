package com.example.luckybroastcustomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.luckybroastcustomer.adapters.FoodAdapter;
import com.example.luckybroastcustomer.databinding.ActivityFoodBinding;
import com.example.luckybroastcustomer.models.Items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    ActivityFoodBinding binding;
    String title;
    List<Items> list;
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title = getIntent().getStringExtra("name");
        binding.backImage.setOnClickListener(v->{onBackPressed();});

        getItems();

    }

    private void getItems() {
        list = new ArrayList<>();
        binding.foodRecView.setLayoutManager(new LinearLayoutManager(this));
        binding.foodRecView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new FoodAdapter(this, list);
        binding.foodRecView.setAdapter(adapter);
        binding.titleTV.setText(title);

        if (title.equals("Fast Foods"))
            getFastFoods();
        if (title.equals("BBQ"))
            getBBQ();
        if (title.equals("Desi Foods"))
            getDesi();
        if (title.equals("Chinese Foods"))
            getChinese();
        if (title.equals("Drinks"))
            getDrinks();
        if (title.equals("Ice Creams"))
            getIceCreams();
        if (title.equals("Deals"))
            getDeals();

    }

    private void getFastFoods() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Fast food");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);
                }
                adapter.notifyDataSetChanged();
                if (list.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Items!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDrinks() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Drinks");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);
                }
                adapter.notifyDataSetChanged();
                if (list.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Items!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIceCreams() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Ice cream");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);
                }
                adapter.notifyDataSetChanged();
                if (list.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Items!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDesi() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Desi");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);
                }
                adapter.notifyDataSetChanged();
                if (list.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Items!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getBBQ() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("BBQ");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);
                }
                adapter.notifyDataSetChanged();
                if (list.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Items!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getChinese() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Chines");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);
                }
                adapter.notifyDataSetChanged();
                if (list.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Items!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getDeals() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Deals");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);
                }
                adapter.notifyDataSetChanged();
                if (list.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Deals!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
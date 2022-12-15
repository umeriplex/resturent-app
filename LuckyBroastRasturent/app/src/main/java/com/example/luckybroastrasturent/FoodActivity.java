package com.example.luckybroastrasturent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.luckybroastrasturent.adapters.DealsAdapter;
import com.example.luckybroastrasturent.adapters.ItemAdapter;
import com.example.luckybroastrasturent.databinding.ActivityFoodBinding;
import com.example.luckybroastrasturent.models.Items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    ActivityFoodBinding binding;
    private Toolbar toolbar;
    String title;
    List<Items> list;
    ItemAdapter itemAdapter;
    DealsAdapter dealsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.toolBarFood);
        setSupportActionBar(toolbar);
        binding.toolBarFood.setOnClickListener(v -> {
            onBackPressed();
        });

        title = getIntent().getStringExtra("name");

        getItems();

    }

    private void getItems() {
        list = new ArrayList<>();
        binding.foodRec.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(this, list);
        binding.foodRec.setAdapter(itemAdapter);
        getSupportActionBar().setTitle(title);

        if (title.equals("Desi Foods")) {
            getDesi();
        }
        if (title.equals("Fast Foods")) {
            getFastFood();
        }
        if (title.equals("BBQ")) {
            getBBQ();
        }
        if (title.equals("Chinese Foods")) {
            getChinese();
        }
        if (title.equals("Deals")) {
            getDeals();
        }
        if (title.equals("Ice Creams")) {
            getIceCreams();
        }
        if (title.equals("Drinks")) {
            getDrinks();
        }
        if (title.equals("Offers")) {
            getOffers();
        }

    }

    private void getOffers() {
        List<Items> offerList = new ArrayList<>();
        binding.foodRec.setLayoutManager(new LinearLayoutManager(this));
        dealsAdapter = new DealsAdapter(this, offerList);
        binding.foodRec.setAdapter(dealsAdapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Offers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                offerList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    offerList.add(items);
                }
                dealsAdapter.notifyDataSetChanged();
                if (offerList.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Offers!!", Toast.LENGTH_LONG).show();
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
                itemAdapter.notifyDataSetChanged();
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
                itemAdapter.notifyDataSetChanged();
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
                itemAdapter.notifyDataSetChanged();
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
                itemAdapter.notifyDataSetChanged();
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
                itemAdapter.notifyDataSetChanged();
                if (list.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Items!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getFastFood() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Fast food");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    list.add(items);
                }
                itemAdapter.notifyDataSetChanged();
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
        List<Items> dealList = new ArrayList<>();
        binding.foodRec.setLayoutManager(new LinearLayoutManager(this));
        dealsAdapter = new DealsAdapter(this, dealList);
        binding.foodRec.setAdapter(dealsAdapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Deals");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dealList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    dealList.add(items);
                }
                dealsAdapter.notifyDataSetChanged();
                if (dealList.isEmpty()) {
                    Toast.makeText(FoodActivity.this, "No Deals!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

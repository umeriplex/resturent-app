package com.example.luckybroastcustomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.Toast;

import com.example.luckybroastcustomer.adapters.PinAdapter;
import com.example.luckybroastcustomer.databinding.ActivitySearchBinding;
import com.example.luckybroastcustomer.models.Items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;
    List<Items> list;
    PinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backImageS.setOnClickListener(v -> {
            onBackPressed();
        });

        String query = getIntent().getStringExtra("query");
        binding.titleTVS.setText(query);
        binding.searchRecView.setLayoutManager(new LinearLayoutManager(this));
        getData(query);
    }

    private void getData(String query) {
        list = new ArrayList<>();
        binding.searchRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PinAdapter(this, list);
        binding.searchRecView.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin");
        Query search = reference.orderByChild("name").startAt(query).endAt(query+"\uf8ff");
        search.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.getClass();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    for (DataSnapshot itemSnapshot : categorySnapshot.getChildren()) {
                        Items items = itemSnapshot.getValue(Items.class);
                        list.add(items);
                    }
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(SearchActivity.this, list.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
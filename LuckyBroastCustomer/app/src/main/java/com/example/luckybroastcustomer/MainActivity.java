package com.example.luckybroastcustomer;

import static androidx.constraintlayout.motion.widget.Debug.getLoc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewbinding.ViewBinding;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.luckybroastcustomer.adapters.PinAdapter;
import com.example.luckybroastcustomer.databinding.ActivityMainBinding;
import com.example.luckybroastcustomer.models.Items;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.SimpleOnSearchActionListener;

import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    ActivityMainBinding binding;
    LocationManager locationManager;
    PinAdapter adapter;
    List<Items> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.userAddressTV.setSelected(true);

        //Runtime permissions
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        //getting location
        getUserLocation();

        //getting Offers
        getOffers();

        //getting pinned item
        getPinItems();

        //going to food items activities
        gotoFoodActivity();

        // implement search bar
        search();

        binding.bagImage.setOnClickListener(v->{startActivity(new Intent(MainActivity.this,CartActivity.class));});

    }

    private void search(){
        binding.searchBar.setOnSearchActionListener(new SimpleOnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                super.onSearchStateChanged(enabled);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                super.onSearchConfirmed(text);
                if (text==null){
                    return;
                }else {
                    Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                    intent.putExtra("query",text.toString());
                    startActivity(intent);
                }
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                super.onButtonClicked(buttonCode);
            }
        });
    }

    private void gotoFoodActivity(){
        binding.ffImage.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Fast Foods");
            startActivity(intent);
        });

        binding.bb1Image.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","BBQ");
            startActivity(intent);
        });

        binding.desiImage.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Desi Foods");
            startActivity(intent);
        });

        binding.cfImage.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Chinese Foods");
            startActivity(intent);
        });

        binding.drinksImage.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Drinks");
            startActivity(intent);
        });

        binding.iceImage.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Ice Creams");
            startActivity(intent);
        });

        binding.dealsImage.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,FoodActivity.class);
            intent.putExtra("name","Deals");
            startActivity(intent);
        });
    }

    private void getPinItems() {
        list = new ArrayList<>();
        binding.pinItemsRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PinAdapter(this, list);
        binding.pinItemsRecView.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.getClass();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()){
                    for (DataSnapshot itemSnapshot : categorySnapshot.getChildren()){
                        Items items = itemSnapshot.getValue(Items.class);
                        if (items.isPin()){list.add(items);}

                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });

    }

    private void getOffers() {
        List<Items> offerList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child("Offers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Items items = dataSnapshot.getValue(Items.class);
                    binding.carousel.addData(new CarouselItem(items.getImage(), items.getName()));
                    offerList.add(items);
                }
                binding.carousel.setCarouselListener(new CarouselListener() {
                    @Nullable
                    @Override
                    public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
                        return null;
                    }

                    @Override
                    public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {

                    }

                    @Override
                    public void onClick(int i, @NonNull CarouselItem carouselItem) {
                        Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
                        intent.putExtra("food_name", offerList.get(i).getName());
                        intent.putExtra("food_desc", offerList.get(i).getDesc());
                        intent.putExtra("food_price", offerList.get(i).getPrice());
                        intent.putExtra("food_image", offerList.get(i).getImage());
                        intent.putExtra("food_cat", offerList.get(i).getCategory());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @SuppressLint("MissingPermission")
    private void getUserLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, MainActivity.this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Utils.lat = location.getLatitude();
        Utils.lon = location.getLongitude();
        try {
            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Utils.address = addresses.get(0).getAddressLine(0);

            binding.userAddressTV.setText(Utils.address);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}
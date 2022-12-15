package com.example.luckybroastrasturent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.luckybroastrasturent.databinding.ActivityAddNewBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AddNewActivity extends AppCompatActivity {

    ActivityAddNewBinding binding;
    private Toolbar toolbar;

    private Uri uri;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.toolBarAddNew);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add new item");

        binding.backAddNew.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.addImageTXT.setOnClickListener(v -> {
            setImageFromGallery();
        });

        addItem();

    }

    private void addItem() {
        binding.addBTN.setOnClickListener(v -> {
            final String name = binding.nameET.getText().toString().trim();
            final String desc = binding.descET.getText().toString().trim();
            final String category = binding.categorySpinner.getSelectedItem().toString();

            if (TextUtils.isEmpty(name)) {
                binding.nameET.setError("enter name");
                return;
            }

            if (TextUtils.isEmpty(desc)) {
                binding.descET.setError("enter some description");
                return;
            }

            if (uri == null) {
                Toast.makeText(this, "select an image", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(binding.priceET.getText().toString())) {
                binding.priceET.setError("enter price");
                return;
            }

            if (category.equalsIgnoreCase("Select category")) {
                Toast.makeText(this, "Select category", Toast.LENGTH_SHORT).show();
                return;
            }

            else {
                binding.pBar.setVisibility(View.VISIBLE);
                binding.addBTN.setVisibility(View.INVISIBLE);
                final int price = Integer.parseInt(binding.priceET.getText().toString().trim());
                databaseReference = FirebaseDatabase.getInstance().getReference().child("admin").child(category).child(name);
                HashMap singleItem = new HashMap();
                singleItem.put("name", name);
                singleItem.put("desc", desc);
                singleItem.put("price", price);
                singleItem.put("category", category);
                singleItem.put("pin", false);
                databaseReference.updateChildren(singleItem).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "data inserted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error : " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                //finish();
                if (uri != null) {
                    final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("item images").child(name);
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                    byte[] data = byteArrayOutputStream.toByteArray();
                    UploadTask uploadTask = filePath.putBytes(data);

                    uploadTask.addOnFailureListener(e -> {
                        Toast.makeText(this, "Image upload failed!!", Toast.LENGTH_SHORT).show();
                    });

                    uploadTask.addOnSuccessListener(taskSnapshot -> {
                        if (taskSnapshot.getMetadata() != null && taskSnapshot.getMetadata().getReference() != null) {
                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                            result.addOnSuccessListener(uri -> {
                                String imageURL = uri.toString();
                                Map newImageMap = new HashMap();
                                newImageMap.put("image", imageURL);
                                databaseReference.updateChildren(newImageMap).addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddNewActivity.this, "image uploaded", Toast.LENGTH_SHORT).show();
                                        binding.pBar.setVisibility(View.INVISIBLE);
                                    } else {
                                        Toast.makeText(AddNewActivity.this, "Error : " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                finish();
                            });
                        }
                    });

                }


            }
        });


    }

    private void setImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2526);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2526 && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            binding.addImageTXT.setImageURI(uri);
        }
    }
}
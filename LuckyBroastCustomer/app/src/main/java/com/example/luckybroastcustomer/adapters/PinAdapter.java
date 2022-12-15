package com.example.luckybroastcustomer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.luckybroastcustomer.ItemDetailsActivity;
import com.example.luckybroastcustomer.R;
import com.example.luckybroastcustomer.databinding.PinItemsBinding;
import com.example.luckybroastcustomer.models.Items;

import java.util.List;

public class PinAdapter extends RecyclerView.Adapter<PinAdapter.PinViewHolder> {

    Context context;
    List<Items> list;

    public PinAdapter(Context context, List<Items> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PinViewHolder(LayoutInflater.from(context).inflate(R.layout.pin_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PinViewHolder holder, int position) {
        Items items = list.get(position);


        holder.binding.pinName.setText(items.getName());
        holder.binding.pinDesc.setText(items.getDesc());
        holder.binding.pinPrice.setText("Rs. " + items.getPrice());
        holder.binding.pinCategory.setText(items.getCategory());
        Glide.with(context).load(items.getImage()).into(holder.binding.pinImage);

        holder.binding.pinMain.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailsActivity.class);
            intent.putExtra("food_name", items.getName());
            intent.putExtra("food_desc", items.getDesc());
            intent.putExtra("food_price", items.getPrice());
            intent.putExtra("food_image", items.getImage());
            intent.putExtra("food_cat", items.getCategory());
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PinViewHolder extends RecyclerView.ViewHolder {
        PinItemsBinding binding;

        public PinViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = PinItemsBinding.bind(itemView);
        }
    }
}

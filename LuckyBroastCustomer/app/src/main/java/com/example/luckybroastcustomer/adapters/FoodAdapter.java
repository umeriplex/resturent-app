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
import com.example.luckybroastcustomer.databinding.FoodItemBinding;
import com.example.luckybroastcustomer.databinding.PinItemsBinding;
import com.example.luckybroastcustomer.models.Items;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    Context context;
    List<Items> list;

    public FoodAdapter(Context context, List<Items> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.food_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Items items = list.get(position);
        holder.binding.foodItemName.setText(items.getName());
        holder.binding.foodItemDesc.setText(items.getDesc());
        holder.binding.foodItemPrice.setText("Rs. " + items.getPrice());
        Glide.with(context).load(items.getImage()).into(holder.binding.foodItemImage);

        holder.binding.mainItem.setOnClickListener(v -> {
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

    class FoodViewHolder extends RecyclerView.ViewHolder {
        FoodItemBinding binding;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = FoodItemBinding.bind(itemView);
        }
    }
}

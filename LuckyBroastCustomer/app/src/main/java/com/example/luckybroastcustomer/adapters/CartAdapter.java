package com.example.luckybroastcustomer.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.luckybroastcustomer.R;
import com.example.luckybroastcustomer.databinding.CartDialogeBinding;
import com.example.luckybroastcustomer.databinding.CartItemBinding;
import com.example.luckybroastcustomer.models.Items;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViweHolder> {
    Context context;
    List<Items> cartList;
    CartListeners cartListeners;
    Cart cart;

    public interface CartListeners {
        public void onQuantityChanged();
    }

    public CartAdapter(Context context, List<Items> cartList, CartListeners cartListeners) {
        this.context = context;
        this.cartList = cartList;
        this.cartListeners = cartListeners;
        cart = TinyCartHelper.getCart();
    }

    @NonNull
    @Override
    public CartViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViweHolder(LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViweHolder holder, int position) {
        Items cartItem = cartList.get(position);
        holder.binding.nameC.setText(cartItem.getName());
        holder.binding.priceC.setText("PKR " + cartItem.getPrice());
        Glide.with(context).load(cartItem.getImage()).into(holder.binding.imageC);
        holder.binding.quantityC.setText(cartItem.getQnt() + " item(s)");

        holder.itemView.setOnClickListener(v -> {
            CartDialogeBinding cartDialogeBinding = CartDialogeBinding.inflate(LayoutInflater.from(context));
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setView(cartDialogeBinding.getRoot())
                    .create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));

            cartDialogeBinding.nameD.setText(cartItem.getName());
            cartDialogeBinding.count.setText(String.valueOf(cartItem.getQnt()));

            cartDialogeBinding.buttonPlus.setOnClickListener(p -> {
                int q = cartItem.getQnt();
                if (q >= 50) {
                    Toast.makeText(context, "For Big Deals Please contact on 03402093883", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    q++;
                    cartItem.setQnt(q);
                    cartDialogeBinding.count.setText(String.valueOf(q));
                }

                notifyDataSetChanged();
                cart.updateItem(cartItem, cartItem.getQnt());
                cartListeners.onQuantityChanged();
            });

            cartDialogeBinding.buttonMinus.setOnClickListener(m -> {
                int q = cartItem.getQnt();
                if (q > 1)
                    q--;
                cartItem.setQnt(q);
                cartDialogeBinding.count.setText(String.valueOf(q));

                notifyDataSetChanged();
                cart.updateItem(cartItem, cartItem.getQnt());
                cartListeners.onQuantityChanged();
            });

            cartDialogeBinding.delBTN.setOnClickListener(s -> {
                cart.removeItem(cartItem);
                cartList.remove(cartItem);
                cartListeners.onQuantityChanged();
                notifyDataSetChanged();
                dialog.dismiss();
            });

            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class CartViweHolder extends RecyclerView.ViewHolder {
        CartItemBinding binding;

        public CartViweHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartItemBinding.bind(itemView);
        }
    }
}

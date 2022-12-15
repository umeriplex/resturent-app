package com.example.luckybroastrasturent.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.luckybroastrasturent.R;
import com.example.luckybroastrasturent.databinding.DealsSingleItemBinding;
import com.example.luckybroastrasturent.models.Items;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder> {

    Context context;
    List<Items> list;

    public DealsAdapter(Context context, List<Items> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DealsViewHolder(LayoutInflater.from(context).inflate(R.layout.deals_single_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DealsViewHolder holder, int position) {
        Items items = list.get(position);
        holder.binding.dealName.setText(items.getName());
        holder.binding.dealDesc.setText(items.getDesc());
        holder.binding.dealPrice.setText(items.getPrice()+"PKR");
        Glide.with(context).load(items.getImage()).into(holder.binding.dealImage);

        holder.binding.mainDeal.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete item")
                        .setMessage("are you sure to delete this item ?")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("admin").child(items.getCategory()).child(items.getName());
                                reference.removeValue();
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("no", null).show();
                return true;

            }
        });

        holder.binding.mainDeal.setOnClickListener(view -> {
            DialogPlus dialog = DialogPlus.newDialog(context)
                    .setContentHolder(new ViewHolder(R.layout.dialoge_item))
                    .setExpanded(true,1050)  // This will enable the expand feature, (similar to android L share dialog)
                    .create();

            View myView = dialog.getHolderView();
            EditText name = myView.findViewById(R.id.nameDia);
            EditText desc = myView.findViewById(R.id.descDia);
            EditText price = myView.findViewById(R.id.priceDia);
            ImageView image = myView.findViewById(R.id.imageDia);
            EditText category = myView.findViewById(R.id.catDia);
            Button btn = myView.findViewById(R.id.doneBTN);
            name.setText(items.getName());
            desc.setText(items.getDesc());
            category.setText(items.getCategory());
            price.setText(items.getPrice()+"");
            Glide.with(context).load(items.getImage()).into(image);

            dialog.show();
            btn.setOnClickListener(view1 -> {
                dialog.dismiss();

            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DealsViewHolder extends RecyclerView.ViewHolder{
        DealsSingleItemBinding binding;
        public DealsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DealsSingleItemBinding.bind(itemView);
        }
    }
}

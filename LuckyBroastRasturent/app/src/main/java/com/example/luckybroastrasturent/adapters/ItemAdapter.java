package com.example.luckybroastrasturent.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.luckybroastrasturent.R;
import com.example.luckybroastrasturent.databinding.SingleItemBinding;
import com.example.luckybroastrasturent.models.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    Context context;
    List<Items> list;

    public ItemAdapter(Context context, List<Items> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Items items = list.get(position);
        holder.binding.itemName.setText(items.getName());
        holder.binding.itemDesc.setText(items.getDesc());
        holder.binding.itemCategory.setText(items.getCategory());
        holder.binding.itemPrice.setText(items.getPrice() + "PKR");
        Glide.with(context).load(items.getImage()).into(holder.binding.itemImage);

        holder.binding.mainItem.setOnLongClickListener(new View.OnLongClickListener() {
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

        holder.binding.mainItem.setOnClickListener(view -> {
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
            CheckBox pin = myView.findViewById(R.id.pinBox);
            Button btn = myView.findViewById(R.id.doneBTN);
            name.setText(items.getName());
            desc.setText(items.getDesc());
            category.setText(items.getCategory());
            price.setText(items.getPrice()+"");
            Glide.with(context).load(items.getImage()).into(image);
            if (items.isPin()){
                pin.setChecked(true);
            }else {
                pin.setChecked(false);
            }
            dialog.show();

            pin.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b){
                    DatabaseReference reference =FirebaseDatabase.getInstance().getReference().child("admin").child(items.getCategory()).child(items.getName()).child("pin");
                    reference.setValue(true);
                }
                else {
                    DatabaseReference reference =FirebaseDatabase.getInstance().getReference().child("admin").child(items.getCategory()).child(items.getName()).child("pin");
                    reference.setValue(false);
                }
            });

            btn.setOnClickListener(view1 -> {
                dialog.dismiss();

            });
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        SingleItemBinding binding;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SingleItemBinding.bind(itemView);
        }
    }
}

package com.example.finalproject;

import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import io.realm.Case;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class MenuAdapter extends RealmRecyclerViewAdapter<MenuItem, MenuAdapter.MyViewHolder> {
    MenuList context;

    public MenuAdapter(MenuList context, @Nullable RealmResults<MenuItem> data)
    {
        super(data, true); // autoupdate true
        this.context = context;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create row view / viewHolder
        // only a limited number will be created
        View view = context.getLayoutInflater().inflate(R.layout.activity_menu_row, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // copy from the result object to the viewHolder for display
        MenuItem menuItem = getItem(position);

        // fill in the ViewHolder
        // non-strings need to be converted to String via String.valueOf()
        holder.itemName.setText(menuItem.getItemName());
        holder.price.setText(menuItem.getPrice());

        holder.delete.setTag(menuItem);

        File getImageDir = context.getExternalCacheDir();
        File savedImage = new File(getImageDir, menuItem.getMenuImage());


        Picasso.with(context)
                .load(savedImage)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(holder.image);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView itemName;
        TextView price;
        ImageView image;
        Button delete;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            itemName = itemView.findViewById(R.id.menuItemName);
            price = itemView.findViewById(R.id.menuItemPrice);
            image = itemView.findViewById(R.id.menuItemImage);
            delete = itemView.findViewById(R.id.deleteMenuItem);

            delete.setOnClickListener(deleteListener);
        }
    }
    private View.OnClickListener deleteListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
//            Tag is attaching arbitrary object to an existing object
            com.example.finalproject.MenuItem menuItem = (MenuItem) v.getTag();
//            System.out.println(u);
            context.delete(menuItem);
        }
    };

}


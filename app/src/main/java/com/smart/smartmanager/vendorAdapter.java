package com.smart.smartmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by sachinpatil on 20/02/17.
 */

public class vendorAdapter extends RecyclerView.Adapter<VendorViewHolder> {

    private ArrayList<vendorModel> list;
    public vendorAdapter(ArrayList<vendorModel> Data) {
        list = Data;
    }

    @Override
    public VendorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemvendor, parent, false);
        VendorViewHolder holder = new VendorViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final VendorViewHolder holder, int position) {

        holder.titleTextView.setText(list.get(position).getName());
        // holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
        // holder.coverImageView.setTag(list.get(position).getContactno());
        // holder.likeImageView.setTag(R.drawable.telephone);

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}

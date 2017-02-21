package com.smart.smartmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sachinpatil on 20/02/17.
 */

public class VendorViewHolder extends RecyclerView.ViewHolder{

    public TextView titleTextView;
    public ImageView coverImageView;
    public ImageView likeImageView;
    public ImageView shareImageView;

    public VendorViewHolder(View v){
        super(v);
        titleTextView = (TextView) v.findViewById(R.id.Name);
    }

}

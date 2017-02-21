package com.smart.smartmanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sachinpatil on 19/02/17.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTextView;
    public ImageView coverImageView;
    public ImageView likeImageView;
    public ImageView shareImageView;

    public UserViewHolder(View v){
        super(v);
        titleTextView = (TextView) v.findViewById(R.id.Name);
    }


}

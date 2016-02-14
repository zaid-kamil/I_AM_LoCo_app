package com.xaidworkz.www.i_am_loco_app.helpers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xaidworkz.www.i_am_loco_app.R;

public class ViewHolder extends RecyclerView.ViewHolder  {

    final ImageView image;
    final ImageView infoIcon;
    final TextView text;

    public ViewHolder(View view) {
        super(view);
        image = (ImageView) view.findViewById(R.id.item_image1);
        infoIcon = (ImageView) view.findViewById(R.id.item_ic_info);
        text = (TextView) view.findViewById(R.id.item_text1);

    }

}

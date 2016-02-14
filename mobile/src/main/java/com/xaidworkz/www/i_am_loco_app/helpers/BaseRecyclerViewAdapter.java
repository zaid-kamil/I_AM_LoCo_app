package com.xaidworkz.www.i_am_loco_app.helpers;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.xaidworkz.www.i_am_loco_app.R;
import com.xaidworkz.www.i_am_loco_app.fragments.DetailFragment;

import java.util.List;

/**
 *
 */
public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<?> itemsList;
    private final AppCompatActivity activity;

    private int layout;
    private Context context;


    public BaseRecyclerViewAdapter(AppCompatActivity activity, int layout, List<?> itemsList) {
        this.activity = activity;
        this.itemsList = itemsList;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ViewHolder(inflater.inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final JobsHolder data = (JobsHolder) itemsList.get(position);
        final int image = data.getImageJob();
        final String text = data.getTextProfileName();
        Picasso.with(context).load(image).fit().into(holder.image);
        holder.text.setText(text);
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDescritionDialog(holder, position,data);
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDescritionDialog(holder, position,data);
            }
        });


    }

    private void showDescritionDialog(ViewHolder holder, int i, JobsHolder data) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down, R.anim.slide_in_up, R.anim.slide_out_down);
        DetailFragment fragment = DetailFragment.newInstance(data);
        transaction.add(R.id.root, fragment).addToBackStack("detail");
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}

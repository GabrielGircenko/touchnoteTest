package com.gircenko.gabriel.touchnotetest.ui.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gircenko.gabriel.touchnotetest.R;
import com.gircenko.gabriel.touchnotetest.model.Item;
import com.gircenko.gabriel.touchnotetest.ui.fragments.OnItemInListClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel Gircenko on 13-Oct-16.
 */

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> implements View.OnClickListener {

    private List<Item> mItems = new ArrayList<>();
    private Context mContext;
    private OnItemInListClickListener mCallback;

    public GridViewAdapter(Context context) {
        this(context, null);
    }

    public GridViewAdapter(Context context, List<Item> items) {
        mContext = context;
        try {
            mCallback = (OnItemInListClickListener) context;

        } catch (Exception e) {}
        mItems = items;
    }

    @Override
    public GridViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_in_grid, parent, false);
        v.setOnClickListener(this);
        GridViewAdapter.ViewHolder vh = new GridViewAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(GridViewAdapter.ViewHolder holder, int position) {
        final Item item = mItems.get(position);

        holder.title.setText(item.title);
        holder.imageUrl = item.image;
        Picasso.with(mContext).load(item.image).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        String imageUrl;

        public ViewHolder(View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.item_image);
            title = (TextView) v.findViewById(R.id.item_title);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onClick(imageUrl);
                    Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setItems(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}

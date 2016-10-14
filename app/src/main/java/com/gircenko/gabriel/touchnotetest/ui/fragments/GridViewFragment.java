package com.gircenko.gabriel.touchnotetest.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gircenko.gabriel.touchnotetest.R;
import com.gircenko.gabriel.touchnotetest.model.Item;
import com.gircenko.gabriel.touchnotetest.ui.adapters.GridViewAdapter;
import com.gircenko.gabriel.touchnotetest.ui.adapters.ListViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 14-Oct-16.
 */

@SuppressLint("ValidFragment")
public class GridViewFragment extends Fragment {

    private GridViewAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    @BindView(R.id.list)
    protected RecyclerView mRecyclerView;

    public GridViewFragment(Context context, List<Item> items) {
        mAdapter = new GridViewAdapter(context, items);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (mAdapter == null) {
            mAdapter = new GridViewAdapter(getActivity());
        }

        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setItems(List<Item> items) {
        mAdapter.setItems(items);
    }

    public int getItemsSize() {
        if (mAdapter != null) {
            return mAdapter.getItemCount();

        } else {
            return -1;
        }
    }
}

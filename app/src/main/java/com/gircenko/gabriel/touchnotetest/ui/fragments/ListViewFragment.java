package com.gircenko.gabriel.touchnotetest.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gircenko.gabriel.touchnotetest.R;
import com.gircenko.gabriel.touchnotetest.model.Item;
import com.gircenko.gabriel.touchnotetest.ui.adapters.ListViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 14-Oct-16.
 */

@SuppressLint("ValidFragment")
public class ListViewFragment extends Fragment {

    private ListViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    @BindView(R.id.list)
    protected RecyclerView mRecyclerView;

    public ListViewFragment(Context context, List<Item> items) {
        mAdapter = new ListViewAdapter(context, items);
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
            mAdapter = new ListViewAdapter(getActivity());
        }

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setHasFixedSize(true);
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

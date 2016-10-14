package com.gircenko.gabriel.touchnotetest.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.gircenko.gabriel.touchnotetest.R;
import com.gircenko.gabriel.touchnotetest.main.IMainView;
import com.gircenko.gabriel.touchnotetest.main.MainPresenter;
import com.gircenko.gabriel.touchnotetest.model.Item;
import com.gircenko.gabriel.touchnotetest.ui.fragments.GridViewFragment;
import com.gircenko.gabriel.touchnotetest.ui.fragments.ListViewFragment;
import com.gircenko.gabriel.touchnotetest.ui.fragments.OnItemInListClickListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView, OnItemInListClickListener {

    private String url = "http://www.mocky.io/v2/57ee2ca8260000f80e1110fa";
    private List<Item> mItems = new ArrayList<>();
    private MainPresenter mPresenter;
    @BindView(R.id.frame)
    FrameLayout mFrame;
    private ListViewFragment mListViewFragment;
    private GridViewFragment mGridViewFragment;
    /** True if the list view is shown. False if grid view is shown. */
    private boolean mIsChecked = false;
    public static final String EXTRA_IMAGE = "EXTRA_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mItems = request(url);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        invalidateOptionsMenu();
                    }
                });
            }
        }).start();
    }

    private List<Item> request(String urlString) {
        List<Item> response = new ArrayList<>();
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(inputStream);
            Type type = new TypeToken<List<Item>>(){}.getType();
            response = gson.fromJson(reader, type);

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
            Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT);
        }

        return response;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_checkable);
        setActionBarAndFragment(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_checkable:
                mIsChecked = !item.isChecked();
                setActionBarAndFragment(item);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setActionBarAndFragment(MenuItem item) {
        item.setChecked(mIsChecked);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (mIsChecked) {
            item.setIcon(R.drawable.ic_view_grid_white_24dp);
            if (mListViewFragment == null || mListViewFragment.getItemsSize() != -1) {
                mListViewFragment = new ListViewFragment(this, mItems);

            } else if (mListViewFragment.getItemsSize() != mItems.size()) {
                mListViewFragment.setItems(mItems);
            }
            ft.replace(R.id.frame, mListViewFragment);

        } else {
            item.setIcon(R.drawable.ic_view_list_white_24dp);
            if (mGridViewFragment == null || mGridViewFragment.getItemsSize() != -1) {
                mGridViewFragment = new GridViewFragment(this, mItems);
                
            } else if (mGridViewFragment.getItemsSize() != mItems.size()) {
                mGridViewFragment.setItems(mItems);
            }

            ft.replace(R.id.frame, mGridViewFragment);
        }

        ft.commit();
    }

    @Override
    public void onClick(String imageUrl) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(EXTRA_IMAGE, imageUrl);
        startActivity(intent);
    }
}

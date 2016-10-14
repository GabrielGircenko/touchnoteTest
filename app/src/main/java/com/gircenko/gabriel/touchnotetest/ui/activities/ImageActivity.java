package com.gircenko.gabriel.touchnotetest.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.gircenko.gabriel.touchnotetest.R;
import com.gircenko.gabriel.touchnotetest.helpers.ImageHelper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 14-Oct-16.
 */

public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.seekbar)
    SeekBar seekBar;

    private Bitmap originalBitmap;

    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            originalBitmap = bitmap;
            image.setImageBitmap(originalBitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ButterKnife.bind(this);

        if (getIntent().hasExtra(MainActivity.EXTRA_IMAGE)) {
            Picasso.with(this).load(getIntent().getStringExtra(MainActivity.EXTRA_IMAGE)).into(target);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (originalBitmap != null) {
                    image.setImageBitmap(ImageHelper.getRoundedCornerBitmap(originalBitmap, i));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        Picasso.with(this).cancelRequest(target);
        super.onDestroy();
    }
}

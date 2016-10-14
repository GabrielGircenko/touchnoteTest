package com.gircenko.gabriel.touchnotetest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gabriel Gircenko on 13-Oct-16.
 */

public class Item {
    @SerializedName("id")
    public String id;

    @SerializedName("description")
    public String description;

    @SerializedName("date")
    public String date;

    @SerializedName("tags")
    public String[] tags;

    @SerializedName("title")
    public String title;

    @SerializedName("image")
    public String image;
}

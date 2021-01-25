package com.example.popularmovies.pojo;

import android.net.Uri;
import android.util.Log;

import java.net.URL;

import static android.content.ContentValues.TAG;

public final class NetworkUtils {

    public static URL buildUrl(String uri)
    {
        Uri builtUri = Uri.parse(uri).buildUpon().build();

        URL url = null;
        try
        {
            url = new URL(builtUri.toString());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);

        return url;
    }
}

package com.example.popularmovies.pojo;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import com.example.popularmovies.R;

public final class MoviesJsonData {
    public static String[] getSimpleStringfromJSON(Context context, String moviesJsonStr) throws JSONException {

        final String OWM_MESSAGE_CODE = "cod";

        String[] parseMoviesData = null;


        JSONObject moviesJson = new JSONObject(moviesJsonStr);

        if (moviesJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = moviesJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray moviesArray = moviesJson.getJSONArray("results");

        parseMoviesData = new String[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {


            JSONObject movieObject = moviesArray.getJSONObject(i);
            String name = movieObject.getString("original_title");


            parseMoviesData[i] = name;
        }


        return parseMoviesData;

    }

    public static String[] getImageAddress(Context context, String moviesJsonStr) throws JSONException {
        final String OWM_MESSAGE_CODE = "cod";

        String[] mSrc = null;

        JSONObject moviesJson = new JSONObject(moviesJsonStr);

        if (moviesJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = moviesJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray moviesArray = moviesJson.getJSONArray("results");
        mSrc = new String[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {


            JSONObject movieObject = moviesArray.getJSONObject(i);
            String imageSrc = movieObject.getString("poster_path");


            mSrc[i] = context.getString(R.string.basic_path) + context.getString(R.string.image_size1) + imageSrc;
        }
        return mSrc;

    }

    public static String[] getMoviesOverview(Context context, String moviesJsonStr) throws JSONException {
        final String OWM_MESSAGE_CODE = "cod";

        String[] overview = null;

        JSONObject moviesJson = new JSONObject(moviesJsonStr);

        if (moviesJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = moviesJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray moviesArray = moviesJson.getJSONArray("results");
        overview = new String[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {


            JSONObject movieObject = moviesArray.getJSONObject(i);
            String movieOverview = movieObject.getString("overview");


            overview[i] = movieOverview;
        }
        return overview;

    }

}

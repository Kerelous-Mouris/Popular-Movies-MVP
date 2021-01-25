package com.example.popularmovies.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import com.example.popularmovies.pojo.HttpRequest;
import com.example.popularmovies.pojo.MoviesJsonData;
import com.example.popularmovies.pojo.NetworkUtils;

import java.net.URL;

public class MoviePresenter implements LoaderManager.LoaderCallbacks<String[]> {

    private MovieView view;
    private String URL_KEY;
    private String basicUri;
    private int LOADER_ID;
    private String[] mSrc;
    private String[] overView;
    Context context;
    LoaderManager loaderManager;

    public MoviePresenter(MovieView view, String URL_KEY, String basicUri, int LOADER_ID, Context context, LoaderManager loaderManager) {
        this.view = view;
        this.URL_KEY = URL_KEY;
        this.basicUri = basicUri;
        this.LOADER_ID = LOADER_ID;
        this.context = context;
        this.loaderManager = loaderManager;
    }

    public void loadMoviesData() {
        Bundle bundle = new Bundle();
        bundle.putString(URL_KEY, basicUri);


        Loader<String[]> loader = loaderManager.getLoader(LOADER_ID);

        if (loader == null) {
            loaderManager.initLoader(LOADER_ID, bundle, this);
        } else
            loaderManager.restartLoader(LOADER_ID, bundle, this);
    }

    @NonNull
    @Override
    public Loader<String[]> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<String[]>(context) {
            @Nullable
            @Override
            public String[] loadInBackground() {
                if (args.isEmpty())
                    return null;

                String uri = args.getString(URL_KEY);


                URL moviesRequestUrl = NetworkUtils.buildUrl(uri);

                try {
                    String jsonMoviesResponse = HttpRequest.request(moviesRequestUrl);
                    String[] simpleJsonMoviesData = MoviesJsonData.getSimpleStringfromJSON(context, jsonMoviesResponse);
                    mSrc = MoviesJsonData.getImageAddress(context, jsonMoviesResponse);
                    overView = MoviesJsonData.getMoviesOverview(context, jsonMoviesResponse);

                    return simpleJsonMoviesData;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onStartLoading() {
                if (args == null)
                    return;

                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String[]> loader, String[] data) {


        if (data != null)
            view.onDataRetrieved(data.length,data,mSrc,overView);


    }

    @Override
    public void onLoaderReset(@NonNull Loader<String[]> loader) {

    }


}

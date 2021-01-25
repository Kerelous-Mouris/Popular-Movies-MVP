package com.example.popularmovies.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.popularmovies.R;
import com.example.popularmovies.pojo.GreenAdapter;

public class MainActivity extends AppCompatActivity implements MovieView {

    private GreenAdapter mAdapter;
    private RecyclerView mMoviesList;
    private Intent onClickIntent;
    private static final int LOADER_ID = 18;
    private static final String URL_KEY = "urlKey";
    String basicUri = "https://api.themoviedb.org/3/movie/popular?api_key=2e572b097b879578d69b00ce5691dc0e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        onClickIntent = new Intent(this, DetailedActivity.class);


        mMoviesList = (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mMoviesList.setLayoutManager(layoutManager);
        LoaderManager loaderManager = LoaderManager.getInstance(this);

        MoviePresenter presenter = new MoviePresenter(this, URL_KEY, basicUri, LOADER_ID, getApplicationContext(), loaderManager);
        presenter.loadMoviesData();

    }


    @Override
    public void onDataRetrieved(int length, String[] data, String[] mSrc, String[] overView) {
        mAdapter = new GreenAdapter(data.length, data, mSrc, overView, onClickIntent);
        mMoviesList.setAdapter(mAdapter);
    }
}
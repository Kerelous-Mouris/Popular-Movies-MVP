package com.example.popularmovies.ui;

public interface MovieView {
    void onDataRetrieved(int length,String[] data,String[] mSrc,String[] overView);
    void showErrorImage(boolean failed);
}

package com.example.popularmovies.pojo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.popularmovies.R;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.MovieViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();
    private static String[] mMoviesTitleList;
    private static String[] mMoviesImagesList;
    private static String[] mOverView;
    private int mMovieItems;
    private Intent intent;

    public GreenAdapter(int numberList, String[] moviesTitleList, String[] moviesImageList, String[] overView, Intent onClickIntent) {
        intent = onClickIntent;
        mMovieItems = numberList;
        mOverView = overView;
        mMoviesTitleList = moviesTitleList;
        mMoviesImagesList = moviesImageList;
    }


    @NonNull
    @Override
    public GreenAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final Context context = viewGroup.getContext();
        int layoutForListItem = R.layout.list_items;
        final LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutForListItem, viewGroup, shouldAttachToParentImmediately);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view, new MovieViewHolder.IMyViewHolderClicks() {
            @Override
            public void onItemClick(int position) {

                intent.putExtra("description", mOverView[position]);
                intent.putExtra("ImageSource", mMoviesImagesList[position]);
                context.startActivity(intent);

            }
        });
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(position);
    }



    @Override
    public int getItemCount() {
        return mMovieItems;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listItemMovieTextView;
        ImageView listItemMovieImageView;
        IMyViewHolderClicks mListener;

        public MovieViewHolder(View itemView, IMyViewHolderClicks listener) {
            super(itemView);
            mListener = listener;
            listItemMovieTextView = (TextView) itemView.findViewById(R.id.movieTitle);
            listItemMovieImageView = (ImageView) itemView.findViewById(R.id.movieImage);
            listItemMovieImageView.setOnClickListener(this);
            listItemMovieTextView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            listItemMovieTextView.setText(mMoviesTitleList[listIndex]);
            Picasso.get().load(mMoviesImagesList[listIndex]).into(listItemMovieImageView);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getAdapterPosition());
        }

        public interface IMyViewHolderClicks {
            void onItemClick(int position);
        }


    }
}

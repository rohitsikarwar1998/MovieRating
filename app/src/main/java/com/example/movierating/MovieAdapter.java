package com.example.movierating;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private List<MovieData> mMovieList;

    public MovieAdapter(Context context, List<MovieData> movieData) {
        mContext = context;
        mMovieList = movieData;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view =inflater.inflate(R.layout.movie_list_layout,null);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        MovieData movieData = mMovieList.get(position);

        holder.movieName.setText(movieData.getMovieName());
        Picasso.get().load(movieData.getImagePath()).into(holder.poster);
        holder.rating.setText(movieData.getRating());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        TextView rating,movieName;

         public MovieViewHolder(@NonNull View itemView) {
             super(itemView);

             poster=itemView.findViewById(R.id.movie_poster);
             rating=itemView.findViewById(R.id.movie_rating);
             movieName=itemView.findViewById(R.id.movie_name);


         }
     }
}

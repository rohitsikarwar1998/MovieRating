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

    private onMovieClickListener mOnMovieClickListener;

    public MovieAdapter(Context context, List<MovieData> movieData,onMovieClickListener onMovieClickListener) {
        mContext = context;
        mMovieList = movieData;
        this.mOnMovieClickListener=onMovieClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view =inflater.inflate(R.layout.movie_list_layout,null);
        return new MovieViewHolder(view,mOnMovieClickListener);
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

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView poster;
        TextView rating,movieName;

        onMovieClickListener mOnMovieClickListener;

         public MovieViewHolder(@NonNull View itemView,onMovieClickListener movieClickListener) {
             super(itemView);

             poster=itemView.findViewById(R.id.movie_poster);
             rating=itemView.findViewById(R.id.movie_rating);
             movieName=itemView.findViewById(R.id.movie_name);

             this.mOnMovieClickListener=movieClickListener;
             itemView.setOnClickListener(this);
         }

        @Override
        public void onClick(View v) {
             mOnMovieClickListener.onMovieClick(getAbsoluteAdapterPosition());
        }
    }

     public interface onMovieClickListener{
        void onMovieClick(int position);
     }
}

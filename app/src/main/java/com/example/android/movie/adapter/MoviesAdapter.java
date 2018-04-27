package com.example.android.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.movie.R;
import com.example.android.movie.activity.MainActivity;
import com.example.android.movie.activity.OnItemClickListener;
import com.example.android.movie.model.Movie;

import java.util.List;

/**
 * Created by Obafemi Ogunkola on 20/04/2018
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    public RecyclerView mRecyclerView;


    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    private OnItemClickListener clickListener;
    private MoviesAdapter mAdapter;




    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View view;
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;


        public MovieViewHolder(View v) {

            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);

            moviesLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.recyclerViewListClicked(v,this.getAdapterPosition());
        }

    }
    public Movie getItem(final int position){
        return null ;
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        MovieViewHolder viewHolder=new MovieViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());

        //holder.moviesLayout.setTag(movies.get(position).getTitle());
        //holder.moviesLayout.setTag(movies.get(position).getOverview());

        holder.moviesLayout.setTag(movies.get(position).getOverview());
        holder.moviesLayout.setTag(R.id.one,movies.get(position).getTitle());
        holder.moviesLayout.setTag(R.id.two,movies.get(position).getReleaseDate());
        holder.moviesLayout.setTag(R.id.three,movies.get(position).getVideo());
        holder.moviesLayout.setTag(R.id.four,movies.get(position).getBackdropPath());



    }

    @Override
    public int getItemCount() {
        return movies == null? 0: movies.size();
    }
}
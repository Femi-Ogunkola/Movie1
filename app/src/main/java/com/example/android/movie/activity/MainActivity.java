package com.example.android.movie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movie.R;
import com.example.android.movie.adapter.MoviesAdapter;
import com.example.android.movie.model.Movie;
import com.example.android.movie.model.MovieResponse;
import com.example.android.movie.rest.ApiClient;
import com.example.android.movie.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener  {

    public List<Movie> movies ;
    private static final String TAG = MainActivity.class.getSimpleName();

    public MoviesAdapter mAdapter;
    private int rowLayout;
    public RecyclerView mRecylerView;



    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "d06908e61d8552827c1ead6097f5c4b8";
    private final static String SORT_BY = "popularity.desc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }
        mRecylerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter=new MoviesAdapter(movies,rowLayout,this);
        mRecylerView.setAdapter(mAdapter);

        mAdapter.setClickListener(this);









        mRecylerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), mRecylerView, new OnItemClickListener() {
            @Override
            public void recyclerViewListClicked(View view, int position) {
                String m,m1,m2,m4;
                 m =(String) view.getTag();
                 m1 =(String)view.getTag(R.id.one);
                 m2 =(String)view.getTag(R.id.two);
                 m4 = (String)view.getTag(R.id.four);


               // Toast.makeText(getApplicationContext() ,m1 + "  movie "+ m ,Toast.LENGTH_SHORT).show();
                Intent detailIntent = new Intent(MainActivity.this,MovieDetailActivity.class);
                detailIntent.putExtra("Overview",m);
                detailIntent.putExtra("Title",m1);
                detailIntent.putExtra("Date",m2);
                detailIntent.putExtra("Poster",m4);

                startActivity(detailIntent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }


        }));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getPopularMovies(API_KEY, SORT_BY,2018);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                mRecylerView.setAdapter(new MoviesAdapter(movies,R.layout.list_item,getApplicationContext()));
                Log.d(TAG, "Number of movies received: " + movies.size());
            }
            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    @Override
    public void recyclerViewListClicked(View view, int position) {
        final Movie movie = movies.get(position);
        Toast.makeText(this, "this is clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}

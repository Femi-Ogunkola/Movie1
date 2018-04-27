package com.example.android.movie.rest;

import com.example.android.movie.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Obafemi Ogunkola on 20/04/2018.
 */

public interface ApiInterface {
    @GET("movie/top_rated" )
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey,@Query("language") String language );

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey,@Query("sort_by") String sortby, @Query("primary_release_date.gte") int Date);


}
//api.themoviedb.org/3/discover/movie?api_key=d06908e61d8552827c1ead6097f5c4b8&
// language=en-US
// &sort_by=popularity.desc
// &include_adult=true
// &include_video=false
// &page=1&
// primary_release_date.gte=2018&
// release_date.gte=2018

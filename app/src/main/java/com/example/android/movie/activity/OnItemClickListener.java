package com.example.android.movie.activity;

import android.view.View;
import android.widget.TextView;

import com.example.android.movie.model.Movie;

/**
 * Created by Obafemi Ogunkola on 22/04/2018.
 */

public interface OnItemClickListener {
     void recyclerViewListClicked(View view, int position);


    void onLongClick(View view, int position);


}

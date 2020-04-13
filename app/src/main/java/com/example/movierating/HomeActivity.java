package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HomeActivity extends AppCompatActivity implements MovieAdapter.onMovieClickListener{

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    private List<MovieData> mMovieList;

    final String movieApi="https://api.themoviedb.org/3/movie/popular?api_key=6063ccfe3463c953387a54cd17bcf2fd&language=en-US&page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mRecyclerView=findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        letsDoSomeNetworking();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void Logout(View view) {
        //remove session and go to login activity

        SessionManagement sessionManagement=new SessionManagement(this);
        sessionManagement.removeSession();

        moveToLoginActivity();

    }
    private void moveToLoginActivity(){
        Intent intent =new Intent(this,MainLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void letsDoSomeNetworking(){
        //for fetching data from online

        AsyncHttpClient client=new AsyncHttpClient();

        client.get(movieApi,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                mMovieList=fromJson(response);

                mMovieAdapter=new MovieAdapter(HomeActivity.this,mMovieList,HomeActivity.this);
                mRecyclerView.setAdapter(mMovieAdapter);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(HomeActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<MovieData> fromJson(JSONObject jsonObject){


        List<MovieData> mMovieDataList=new ArrayList<>();

        String movieName;
        double rating;
        String mRating;    //rating with string
        String imageUri;  //actual url of the image
        String imagePath;
        int movieId;

        try {
            JSONArray results=jsonObject.getJSONArray("results");


            for(int pos=0;pos<results.length();pos++){
                movieName=results.getJSONObject(pos).getString("title");
                rating=results.getJSONObject(pos).getDouble("vote_average");
                movieId=results.getJSONObject(pos).getInt("id");
                imagePath=results.getJSONObject(pos).getString("poster_path");

                mRating="Rating : "+rating;
                imageUri="http://image.tmdb.org/t/p/w92/"+imagePath;

                mMovieDataList.add(new MovieData(movieName,mRating,imageUri,movieId));
            }

        } catch (JSONException e) {
            Log.d("info", "fromJson: "+e);
        }

        return mMovieDataList;

    }

    @Override
    public void onMovieClick(int position) {
           Intent intent =new Intent(this,MovieInfo.class);
           intent.putExtra("id",mMovieList.get(position).getMovieId());
           startActivity(intent);
    }
}

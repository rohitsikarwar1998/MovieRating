package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.PriorityQueue;

import cz.msebera.android.httpclient.Header;


public class MovieInfo extends AppCompatActivity {

    private int movieId;
    private TextView mGenre,mRating,mDescription;
    private ImageView mPoster;
    private String api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        movieId=getIntent().getIntExtra("id",0);

        api="https://api.themoviedb.org/3/movie/"+movieId+"?api_key=6063ccfe3463c953387a54cd17bcf2fd&language=en-US";

        mGenre=findViewById(R.id.detail_genre);
        mRating=findViewById(R.id.detail_rating);
        mDescription=findViewById(R.id.detail_description);
        mPoster=findViewById(R.id.detail_poster);

        letsDoSomeNetworking();


    }

    public void Back(View view) {
        Intent intent =new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
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
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(api,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                setDataToAllViews(response);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(MovieInfo.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataToAllViews(JSONObject response){


        try {
            String imageUri="http://image.tmdb.org/t/p/w342/"+response.getString("poster_path");
            Picasso.get().load(imageUri).into(mPoster);
            mRating.setText("Rating : "+response.getString("vote_average"));

            String genre1,genre2;

            genre1=response.getJSONArray("genres").getJSONObject(0).getString("name");
            genre2=response.getJSONArray("genres").getJSONObject(1).getString("name");

            mGenre.setText("Genre : "+genre1+" "+genre2);

            mDescription.setText("Description : "+response.getString("overview"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}

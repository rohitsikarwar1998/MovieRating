package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    private List<MovieData> mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mMovieList=new ArrayList<>();

        mRecyclerView=findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mMovieList.add(new MovieData("sholay","Rating : "+9.0,"http://image.tmdb.org/t/p/w92//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"));
        mMovieList.add(new MovieData("sholay","Rating : "+9.0,"http://image.tmdb.org/t/p/w92//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"));
        mMovieList.add(new MovieData("sholay", "Rating : " + 9.0, "http://image.tmdb.org/t/p/w92//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"));

        mMovieAdapter=new MovieAdapter(this,mMovieList);
        mRecyclerView.setAdapter(mMovieAdapter);
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
}

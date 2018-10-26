package ajikamaludin.id.mybioskopv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {
    private RecyclerView recyclerView;
    private ArrayList<Movie> list;

    private String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=67b6380268ec08352e29f665e862f113";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainRecycler);
        recyclerView.setHasFixedSize(true);
        getSupportLoaderManager().initLoader(0, null, (LoaderManager.LoaderCallbacks<ArrayList<Movie>>)this).forceLoad();
    }

    private void showRecyclerCardView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardViewMovieAdapter CardViewMovieAdapter = new CardViewMovieAdapter(this);
        CardViewMovieAdapter.setMovies(list);
        recyclerView.setAdapter(CardViewMovieAdapter);
    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new MovieDataAsync(this, URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movie>> loader, ArrayList<Movie> movies) {
        this.list = movies;
        Log.d("COUNT : ", String.valueOf(movies.size()));
        showRecyclerCardView();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movie>> loader) {
        this.list = null;
    }
}

package ajikamaludin.id.mybioskopv3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerView recyclerView;
    private ArrayList<Movie> list;

    private String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=67b6380268ec08352e29f665e862f113";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainRecycler);
        recyclerView.setHasFixedSize(true);

        new MovieDataAsync().execute(URL);
    }

    private void showRecyclerCardView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardViewMovieAdapter CardViewMovieAdapter = new CardViewMovieAdapter(this);
        CardViewMovieAdapter.setMovies(list);
        recyclerView.setAdapter(CardViewMovieAdapter);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}

package ajikamaludin.id.mybioskopv3;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDataAsync extends AsyncTask<String, Integer, ArrayList<Movie>> {

    private String movies = null;

    @Override
    protected ArrayList<Movie> doInBackground(String... strings) {
        final ArrayList<Movie> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(strings[0])
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                movies = response.body().string();
                try{
                    JSONObject objData = new JSONObject(movies);
                    final JSONArray arrayResults = objData.getJSONArray("results");
                    for(int i = 0; i < arrayResults.length(); i++){
                        JSONObject objMovie = new JSONObject(arrayResults.get(i).toString());
                        String title = objMovie.getString("title");
                        String overview = objMovie.getString("overview");
                        String releaseDate = objMovie.getString("releaseDate");
                        String imgPoster = "http://image.tmdb.org/t/p/w185" + objMovie.getString("poster_path");
                        list.add(new Movie(title, overview, releaseDate, imgPoster));
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        return list;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> list) {
        super.onPostExecute(list);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}

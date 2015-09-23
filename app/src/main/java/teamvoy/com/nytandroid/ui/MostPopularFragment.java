package teamvoy.com.nytandroid.ui;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.retrofit.most_popular.RestClient;
import teamvoy.com.nytandroid.retrofit.RestInterface;
import teamvoy.com.nytandroid.retrofit.most_popular.MostPopular;

/**
 * A placeholder fragment containing a simple view.
 */
public class MostPopularFragment extends Fragment {
    private RestInterface restInterface;
    private String TAG=this.getClass().getSimpleName();


    public MostPopularFragment() {
        this.restInterface = RestClient.getInstance().getClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_main, container, false);
     //   new ATask().execute();

        restInterface.getMostViewed("all-sections","7", "700",
                getResources().getString(R.string.api_key_most_popular), new Callback<MostPopular>() {
            @Override
            public void success(MostPopular mostPopular, Response response) {
                Log.d(TAG, "status="+mostPopular.status+" numResults="+mostPopular.numResults);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, ""+error.getMessage());
            }
        });


        return rootview;
    }
    class ATask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            URL url=null;
            BufferedReader reader=null;

            try {
                url= new URL("http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?offset=20&api-key=15ea7158f83b3180d0d50107fdd7b196:10:73022442");
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                Log.d(TAG, reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }




            return null;
        }
    }
}

package teamvoy.com.nytandroid.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.retrofit.RestClient;
import teamvoy.com.nytandroid.retrofit.RestInterface;
import teamvoy.com.nytandroid.retrofit.most_popular.MostPopular;

/**
 * A placeholder fragment containing a simple view.
 */
public class MostPopularFragment extends Fragment {
    private RestInterface restInterface;
    private String TAG=this.getClass().getSimpleName();


    public MostPopularFragment() {
        this.restInterface = RestClient.getInstance("results").getClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_main, container, false);
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
}

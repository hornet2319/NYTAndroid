package teamvoy.com.nytandroid.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.retrofit.RestClient;
import teamvoy.com.nytandroid.retrofit.most_popular.MostPopular;

/**
 * A placeholder fragment containing a simple view.
 */
public class MostPopularFragment extends Fragment {
    private RestClient restClient;
    private String TAG=this.getClass().getSimpleName();


    @SuppressWarnings("true")
    public MostPopularFragment(RestClient restClient) {
        this.restClient=restClient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_main, container, false);

        restClient.getMostViewed("all-sections", 7, "20&api-key=15ea7158f83b3180d0d50107fdd7b196:10:73022442", new Callback<MostPopular>() {
            @Override
            public void success(MostPopular mostPopular, Response response) {
                Log.d(TAG, "status="+mostPopular.status+" numResults="+mostPopular.numResults);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });


        return rootview;
    }
}

package teamvoy.com.nytandroid.retrofit.most_popular;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import teamvoy.com.nytandroid.retrofit.RestInterface;

/**
 * Created by lubomyrshershun on 9/23/15.
 */
public class RestClient {
    private static RestClient instance;
    private static final String BASE_URL = "http://api.nytimes.com";
    private RestInterface restInterface;

    private RestClient() {
        GsonBuilder  gson = new GsonBuilder()
                .registerTypeAdapter(Result.class, new RestDeserializer(Result.class, "results"));
        RestAdapter restAdapter= new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson.create()))
                .build();
         restInterface=restAdapter.create(RestInterface.class);
    }
    public static RestClient getInstance(){
        if (instance==null) instance=new RestClient();
        return instance;
    }
    public RestInterface getClient(){
        return restInterface;
    }
}

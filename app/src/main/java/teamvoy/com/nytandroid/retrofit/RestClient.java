package teamvoy.com.nytandroid.retrofit;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import teamvoy.com.nytandroid.retrofit.RestInterface;
import teamvoy.com.nytandroid.retrofit.article.Doc;
import teamvoy.com.nytandroid.retrofit.most_popular.Result;

/**
 * Created by lubomyrshershun on 9/23/15.
 */
public class RestClient {
    private static RestClient instance;
    private static final String BASE_URL = "http://api.nytimes.com";
    private static RestInterface restInterface;
    private static GsonBuilder gson;
    private static RestAdapter restAdapter;

    private RestClient() {
    }

    public static RestClient getInstance(String tag) {
        
        switch (tag) {
            case "results": {
                gson = new GsonBuilder()
                        .registerTypeAdapter(Result.class, new RestDeserializer(Result.class, "results"))
                .disableHtmlEscaping();
                init();
                break;
            }
            case "docs": {
                gson = new GsonBuilder()
                        .registerTypeAdapter(Doc.class, new RestDeserializer(Doc.class, "docs"))
                .disableHtmlEscaping();

                init();
                break;
            }
            default: {
                restAdapter = new RestAdapter.Builder()
                        .setEndpoint(BASE_URL)
                        .build();
            }

        }
        restInterface = restAdapter.create(RestInterface.class);

        if (instance == null) instance = new RestClient();
        return instance;
    }

    private static void init() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson.create()))
                .build();
    }

    public RestInterface getClient() {
        return restInterface;
    }
}

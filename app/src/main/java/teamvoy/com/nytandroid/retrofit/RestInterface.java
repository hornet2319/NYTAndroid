package teamvoy.com.nytandroid.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import teamvoy.com.nytandroid.retrofit.most_popular.MostPopular;
import teamvoy.com.nytandroid.retrofit.most_popular.Section;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public interface RestInterface {
    /**---------------------
     *   Most popular API   *
     * ---------------------
     * for additional information check link bellow
     * http://developer.nytimes.com/docs/most_popular_api/
     *
     *
     * @param time_period=1, or 7, or 30.
     * @param offset The first 20 results are shown by default.
     *               To page through the results, set offset to the appropriate value.
     *
     */
    //  http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?offset=20&api-key=15ea7158f83b3180d0d50107fdd7b196:10:73022442

    @GET("/svc/mostpopular/v2/mostviewed/{section}/{time-period}.json")
    void getMostViewed(@Path("section") String section,
                                     @Path("time-period") String time_period,
                                     @Query("offset") String offset,
                                     @Query("api-key")String api_key ,Callback<MostPopular> callback);

    /** getting list of sections
     * @param most = mostemailed | mostshared | mostviewed
     */

    @GET("/svc/mostpopular/v2/{most}/sections-list?api-key={api-key}")
    void getSections(@Path("most") String most, Callback<Section> callback);
}

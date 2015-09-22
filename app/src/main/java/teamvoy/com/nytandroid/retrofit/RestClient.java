package teamvoy.com.nytandroid.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;
import teamvoy.com.nytandroid.retrofit.most_popular.MostPopular;
import teamvoy.com.nytandroid.retrofit.most_popular.Section;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public interface RestClient {
    /**---------------------
     *   Most popular API   *
     * ---------------------
     * for additional information check link bellow
     * http://developer.nytimes.com/docs/most_popular_api/
     *
     *
     * @param time_period=1, or 7, or 30.
     * @param offset_apikey The first 20 results are shown by default.
     *               To page through the results, set offset to the appropriate value.
     *                      &api-key={api-key}
     */

    @GET("/svc/mostpopular/v2/mostviewed/{section}/{time-period}.json")
    void getMostViewed(@Path("section") String section,
                                     @Path("time-period") int time_period,
                                     @Query("offset") String offset_apikey, Callback<MostPopular> callback);

    /** getting list of sections
     * @param most = mostemailed | mostshared | mostviewed
     */

    @GET("/svc/mostpopular/v2/{most}/sections-list?api-key={api-key}")
    void getSections(@Path("most") String most, Callback<Section> callback);
}

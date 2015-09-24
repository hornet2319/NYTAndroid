package teamvoy.com.nytandroid.retrofit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import teamvoy.com.nytandroid.retrofit.article.Article;
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
     * @param section     (required) section (for all sections use 'all-sections')
     * @param time_period (optional) value 1, or 7, or 30.
     * @param offset      (optional) The first 20 results are shown by default.To page through the results, set offset to the appropriate value.
     * @param api_key     (required) Most popular Api key
     */
    @GET("/svc/mostpopular/v2/mostviewed/{section}/{time-period}.json")
    void getMostViewed(@Path("section") String section,
                                     @Path("time-period") String time_period,
                                     @Query("offset") String offset,
                                     @Query("api-key")String api_key ,Callback<MostPopular> callback);

    /** getting list of sections
     * @param most = mostemailed | mostshared | mostviewed
     * @param api_key     (required) Most popular Api key
     */
    @GET("/svc/mostpopular/v2/{most}/sections-list")
    void getSections(@Path("most") String most,
                     @Query("api-key")String api_key ,
                     Callback<Section> callback);


    /**----------------------
     *   Article search API  *
     * ----------------------
     * for additional information check link below
     * http://developer.nytimes.com/docs/read/article_search_api_v2
     */

    /** getting list of articles
     * @param q            (optional) Search query term.Search is performed on the article body, headline and byline. (use + as separator)
     * @param fq           (optional) filter query info: http://developer.nytimes.com/docs/read/article_search_api_v2#filters
     * @param begin_date   (optional) yyyymmdd
     * @param end_date     (optional) yyyymmdd
     * @param sort         (optional) = newest | oldest
     * @param page         (optional) Page through search results 10 at a time (e.g., for results 20-29, set page=2)
     * @param api_key      (required) Article Search Api key
     */
    @GET("/svc/search/v2/articlesearch.json")
    void getArticles(@Query("q") String q,
                     @Query("fq") String fq,
                     @Query("begin_date") String begin_date,
                     @Query("end_date") String end_date,
                     @Query("sort") String sort,
                     @Query("page") Integer page,
                     @Query("api-key") String api_key,
                     Callback<Article> callback

    );
}

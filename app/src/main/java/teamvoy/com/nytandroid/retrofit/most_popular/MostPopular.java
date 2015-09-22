package teamvoy.com.nytandroid.retrofit.most_popular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class MostPopular {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("copyright")
    @Expose
    public String copyright;
    @SerializedName("num_results")
    @Expose
    public Integer numResults;
    @SerializedName("results")
    @Expose
    public List<Result> results = new ArrayList<Result>();
}

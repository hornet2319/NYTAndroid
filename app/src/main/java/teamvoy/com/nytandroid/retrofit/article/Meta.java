package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Meta {
    @SerializedName("hits")
    @Expose
    public long hits;
    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("offset")
    @Expose
    public Integer offset;

}

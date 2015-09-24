package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Keyword {
    @SerializedName("rank")
    @Expose
    public String rank;
    @SerializedName("is_major")
    @Expose
    public String isMajor;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("value")
    @Expose
    public String value;



}

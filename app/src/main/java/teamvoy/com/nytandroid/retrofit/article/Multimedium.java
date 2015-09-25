package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Multimedium {
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("subtype")
    @Expose
    public String subtype;
   /* @SerializedName("legacy")
    @Expose
    public Legacy legacy; */
    @SerializedName("type")
    @Expose
    public String type;

}

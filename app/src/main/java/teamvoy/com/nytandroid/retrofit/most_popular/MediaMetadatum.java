package teamvoy.com.nytandroid.retrofit.most_popular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class MediaMetadatum {
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("width")
    @Expose
    public Integer width;
}

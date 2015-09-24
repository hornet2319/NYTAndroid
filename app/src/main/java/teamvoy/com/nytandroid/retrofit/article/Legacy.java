package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Legacy {
    @SerializedName("thumbnailheight")
    @Expose
    public String thumbnailheight;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("thumbnailwidth")
    @Expose
    public String thumbnailwidth;

}

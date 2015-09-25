package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Headline {

    @SerializedName("main")
    @Expose
    public String main;

    @SerializedName("name")
    @Expose
    public String name;
}

package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class ArticleResponse {
    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("docs")
    @Expose
    public List<Doc> docs = new ArrayList<Doc>();


}


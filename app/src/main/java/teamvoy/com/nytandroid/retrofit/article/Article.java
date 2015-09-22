package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Article {

    @SerializedName("response")
    @Expose
    public ArticleResponse response;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("copyright")
    @Expose
    public String copyright;

    public ArticleResponse getResponse() {
        return response;
    }

    public void setResponse(ArticleResponse response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
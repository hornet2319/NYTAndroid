package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Meta {
    @SerializedName("hits")
    @Expose
    public Integer hits;
    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("offset")
    @Expose
    public Integer offset;

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}

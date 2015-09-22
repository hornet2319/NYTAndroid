package teamvoy.com.nytandroid.retrofit.most_popular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Result {
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("adx_keywords")
    @Expose
    public String adxKeywords;
    @SerializedName("column")
    @Expose
    public String column;
    @SerializedName("section")
    @Expose
    public String section;
    @SerializedName("byline")
    @Expose
    public String byline;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("abstract")
    @Expose
    public String _abstract;
    @SerializedName("published_date")
    @Expose
    public String publishedDate;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("asset_id")
    @Expose
    public Integer assetId;
    @SerializedName("views")
    @Expose
    public Integer views;
    @SerializedName("des_facet")
    @Expose
    public List<String> desFacet = new ArrayList<String>();
    @SerializedName("org_facet")
    @Expose
    public String orgFacet;
    @SerializedName("per_facet")
    @Expose
    public List<String> perFacet = new ArrayList<String>();
    @SerializedName("geo_facet")
    @Expose
    public List<String> geoFacet = new ArrayList<String>();
    @SerializedName("media")
    @Expose
    public List<Medium> media = new ArrayList<Medium>();
}

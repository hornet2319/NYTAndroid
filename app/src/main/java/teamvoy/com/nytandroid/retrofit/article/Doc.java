package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Doc {

    @SerializedName("web_url")
    @Expose
    public String webUrl;
    @SerializedName("snippet")
    @Expose
    public String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    public String leadParagraph;
   /* @SerializedName("abstract")
    @Expose
    public Object _abstract;
    @SerializedName("print_page")
    @Expose
    public Object printPage;*/
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> multimedia = new ArrayList<Multimedium>();
    @SerializedName("headline")
    @Expose
    public Headline headline;
  /*  @SerializedName("keywords")
    @Expose
    public List<Keyword> keywords = new ArrayList<Keyword>(); *
    @SerializedName("pub_date")
    @Expose
    public String pubDate;
    @SerializedName("document_type")
    @Expose
    public String documentType;
    @SerializedName("news_desk")
    @Expose
    public String newsDesk;*/
    @SerializedName("section_name")
    @Expose
    public String sectionName;
    @SerializedName("byline")
    @Expose
    public Byline byline;

    @SerializedName("_id")
    @Expose
    public String Id;


}
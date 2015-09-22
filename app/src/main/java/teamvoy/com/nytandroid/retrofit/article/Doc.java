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
    @SerializedName("abstract")
    @Expose
    public Object _abstract;
    @SerializedName("print_page")
    @Expose
    public Object printPage;
    @SerializedName("blog")
    @Expose
    public List<Object> blog = new ArrayList<Object>();
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("multimedia")
    @Expose
    public List<Multimedium> multimedia = new ArrayList<Multimedium>();
    @SerializedName("headline")
    @Expose
    public Headline headline;
    @SerializedName("keywords")
    @Expose
    public List<Keyword> keywords = new ArrayList<Keyword>();
    @SerializedName("pub_date")
    @Expose
    public String pubDate;
    @SerializedName("document_type")
    @Expose
    public String documentType;
    @SerializedName("news_desk")
    @Expose
    public String newsDesk;
    @SerializedName("section_name")
    @Expose
    public String sectionName;
    @SerializedName("subsection_name")
    @Expose
    public String subsectionName;
    @SerializedName("byline")
    @Expose
    public Byline byline;
    @SerializedName("type_of_material")
    @Expose
    public String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    public String Id;
    @SerializedName("word_count")
    @Expose
    public String wordCount;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    public Object get_abstract() {
        return _abstract;
    }

    public void set_abstract(Object _abstract) {
        this._abstract = _abstract;
    }

    public Object getPrintPage() {
        return printPage;
    }

    public void setPrintPage(Object printPage) {
        this.printPage = printPage;
    }

    public List<Object> getBlog() {
        return blog;
    }

    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }
}
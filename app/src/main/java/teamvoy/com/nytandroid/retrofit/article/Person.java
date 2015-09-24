package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Person {
    @SerializedName("organization")
    @Expose
    public String organization;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("rank")
    @Expose
    public Integer rank;
    @SerializedName("lastname")
    @Expose
    public String lastname;

}

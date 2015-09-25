package teamvoy.com.nytandroid.retrofit.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Byline {

  /*  @SerializedName("person")
    @Expose
    public List<Person> person = new ArrayList<Person>(); */
    @SerializedName("original")
    @Expose
    public String original;

}

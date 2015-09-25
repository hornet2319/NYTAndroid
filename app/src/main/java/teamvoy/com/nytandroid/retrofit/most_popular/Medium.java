package teamvoy.com.nytandroid.retrofit.most_popular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubomyrshershun on 9/22/15.
 */
public class Medium {
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("subtype")
        @Expose
        public String subtype;
      /*  @SerializedName("caption")
        @Expose
        public String caption;
        @SerializedName("copyright")
        @Expose
        public String copyright; */
        @SerializedName("media-metadata")
        @Expose
        public List<MediaMetadatum> mediaMetadata = new ArrayList<MediaMetadatum>();
}

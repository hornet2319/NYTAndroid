package teamvoy.com.nytandroid.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;

/**
 * Created by lubomyrshershun on 9/23/15.
 */
public class RestDeserializer<T> implements JsonDeserializer<T> {
    String TAG=getClass().getSimpleName();
    private Class<T> mClass;
    private String mKey;
    public RestDeserializer(Class<T> targetClass, String key) {
        mClass = targetClass;
        mKey = key;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        validateArray("media", json);
        validateArray("org_facet", json);
        validateArray("des_facet", json);
        validateArray("per_facet", json);
        validateObject("byline",json);


        return new Gson().fromJson(json, mClass);
    }
    private void validateArray(String tag, JsonElement json) {
        if (json.getAsJsonObject().has(tag)) {
            JsonElement content = json.getAsJsonObject().get(tag);

            if (content.toString().length() < 3) {
              //  Log.e(TAG, "costylize " + tag);
             //   Log.e(TAG, tag + "=" + content.toString());
                JsonObject jo = json.getAsJsonObject();
                jo.remove(tag);
                jo.add(tag, new JsonArray());
                json = jo;

            }
          //  Log.d(TAG, tag + "=" + json.getAsJsonObject().get(tag).toString());
        }

    }
    private void validateObject(String tag, JsonElement json){
        if (json.getAsJsonObject().has(tag)) {
            JsonElement content = json.getAsJsonObject().get(tag);

            if (content.toString().length() < 3) {
                //  Log.e(TAG, "costylize " + tag);
                //   Log.e(TAG, tag + "=" + content.toString());
                JsonObject jo = json.getAsJsonObject();
                jo.remove(tag);
                jo.add(tag, new JsonObject());
                json = jo;

            }
            //  Log.d(TAG, tag + "=" + json.getAsJsonObject().get(tag).toString());
        }
    }

}

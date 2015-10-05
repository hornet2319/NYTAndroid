package teamvoy.com.nytandroid.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.retrofit.RestClient;
import teamvoy.com.nytandroid.retrofit.RestInterface;
import teamvoy.com.nytandroid.retrofit.most_popular.Section;
import teamvoy.com.nytandroid.retrofit.most_popular.SectionResult;

public class MostPopularFilterActivity extends AppCompatActivity {
    String TAG="MostPopularFiletrActivity";
    private SettingsFragment settings=null;
    private static String section_value;
    private static Integer time_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button confirm= (Button)findViewById(R.id.filter_confirm_btn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Log.d(TAG,"sections="+section_value);
                intent.putExtra("sections", section_value);
                intent.putExtra("time-period", time_value);
                setResult(RESULT_OK, intent);
                finish();
            }
        });



        RestInterface restClient=RestClient.getInstance("").getClient();
        restClient.getSections("mostviewed", getResources().getString(R.string.api_key_most_popular), new Callback<Section>() {
            @Override
            public void success(Section section, Response response) {
                settings = new SettingsFragment();
                settings.sectionsData=toStringArray(section.results);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, settings)
                        .commit();

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });


    }

    private String[] toStringArray(List<SectionResult> results) {
        String[] data=new String[results.size()];
        if (results.isEmpty()) return data;
        int i=0;
        for (SectionResult item:results) {
            Log.d(TAG, "item name="+item.name);
            data[i]=item.name;
            i++;
        }
        return data;
    }

    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        String TAG="SettingsFragment";
        private String[] sectionsData;
        private ListPreference sections=null;
        private ListPreference time_period;
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            addPreferencesFromResource(R.xml.pref_most_popular);
            Log.d(TAG, "sectionsData="+sectionsData.toString());
            sections=(ListPreference)findPreference("most_section");
            time_period=(ListPreference)findPreference("most_period");
            sections.setEntries(sectionsData);
            sections.setEntryValues(sectionsData);

            sections.setOnPreferenceChangeListener(this);
            time_period.setOnPreferenceChangeListener(this);



        }
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            switch (preference.getKey()){
                case "most_section":{
                    sections.setSummary("");
                    section_value=value.toString().replace("[","").replace("]","").replaceAll(" ","");
                    sections.setSummary(section_value);
                    break;
                }
                case "most_period":{
                    time_value=Integer.parseInt(value.toString());
                    String[]values=getResources().getStringArray(R.array.pref_most_days_values);
                    String[]titles=getResources().getStringArray(R.array.pref_most_days_titles);
                    for (int i=0;i<values.length;i++){
                        if (value.toString().equals(values[i]))
                            time_period.setSummary(titles[i]);
                    }
                    break;
                }
            }
            return false;
        }
    }
}

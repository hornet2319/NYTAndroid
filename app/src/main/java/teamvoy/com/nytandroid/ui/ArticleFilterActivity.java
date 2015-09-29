package teamvoy.com.nytandroid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.utils.DatePreference;

public class ArticleFilterActivity extends AppCompatActivity {
    static String TAG="ArticleFilterActivity";
    private static String begin_value;
    private static String end_value;
    private static String section_value;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_filter);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);

        //setting the color of up arrow
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        final SettingsFragment settings=new SettingsFragment();
        //setting actionbar
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, settings)
                .commit();
        confirm= (Button)findViewById(R.id.filter_confirm_btn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "begin_value="+begin_value);
                Log.d(TAG, "end_value=" + end_value);
                Log.d(TAG, "sort="+settings.article_sort.getValue());
                Intent intent =new Intent();
                Log.d(TAG, "sections="+settings.sections.getValues().toString());
                intent.putExtra("sections",section_value);
                intent.putExtra("begin_date", begin_value);
                intent.putExtra("end_date", end_value);
                intent.putExtra("sort",settings.article_sort.getValue());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        public DatePreference begin_date=null;
        public DatePreference end_date=null;
        public MultiSelectListPreference sections=null;
        ListPreference article_sort;
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            addPreferencesFromResource(R.xml.pref_article);
            article_sort=(ListPreference)findPreference("article_sort");

            begin_date= (DatePreference) findPreference("begin_date");
            end_date= (DatePreference) findPreference("end_date");
            sections=(MultiSelectListPreference) findPreference("article_section");
           // article_sort.setSummary(getResources().getStringArray(R.array.pref_article_sort_titles)[0]);
            article_sort.setOnPreferenceChangeListener(this);
            begin_date.setOnPreferenceChangeListener(this);
            end_date.setOnPreferenceChangeListener(this);
            sections.setOnPreferenceChangeListener(this);


        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            switch (preference.getKey()){
                case "article_sort":{
                    if (newValue.toString().equals("newest"))
                        preference.setSummary(getResources().getStringArray(R.array.pref_article_sort_titles)[0]);
                    else preference.setSummary(getResources().getStringArray(R.array.pref_article_sort_titles)[1]);
                    break;
                }
                case "begin_date":{
                    begin_date.setSummary(newValue.toString());
                    begin_value=begin_date.getTextF(newValue.toString());


                    break;
                }
                case "end_date":{
                    end_date.setSummary(newValue.toString());
                    end_value=end_date.getTextF(newValue.toString());

                    break;
                }
                case "article_section":{
                    Log.d(TAG, "" + newValue.toString());
                    sections.setSummary("");
                    section_value=newValue.toString().replace("[","").replace("]","");
                    sections.setSummary(section_value);

                    break;
                }

            }

            return false;
        }
    }
}

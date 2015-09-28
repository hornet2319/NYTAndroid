package teamvoy.com.nytandroid.ui;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.utils.DatePreference;

public class ArticleFilterActivity extends AppCompatActivity {
    static String TAG="ArticleFilterActivity";
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

        //setting actionbar
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        private DatePreference begin_date=null;
        private DatePreference end_date=null;
        private MultiSelectListPreference sections=null;
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            addPreferencesFromResource(R.xml.pref_article);
            Preference article_sort=getPreferenceManager().findPreference("article_sort");

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
                    break;
                }
                case "end_date":{
                    end_date.setSummary(newValue.toString());
                    break;
                }
                case "article_section":{
                    Log.d(TAG, ""+newValue.toString());
                    sections.setSummary("");
                    sections.setSummary(newValue.toString());

                    break;
                }

            }

            return false;
        }
    }
}

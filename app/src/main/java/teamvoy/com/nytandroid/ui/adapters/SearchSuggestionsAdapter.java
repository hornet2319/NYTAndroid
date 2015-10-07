package teamvoy.com.nytandroid.ui.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import teamvoy.com.nytandroid.R;

/**
 * Created by lubomyrshershun on 10/7/15.
 */
public class SearchSuggestionsAdapter extends SimpleCursorAdapter
{
    private static final String[] mFields  = { "_id", "result" };
    private static final String[] mVisible = { "result" };
    private static final int[]    mViewIds = { R.id.suggestion_text };
    private SharedPreferences sPrefs;


    public SearchSuggestionsAdapter(Context context)
    {
        super(context, R.layout.suggestion_list_item, null, mVisible, mViewIds, 0);
        sPrefs=context.getSharedPreferences("queries",Context.MODE_PRIVATE);
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint)
    {
        return new SuggestionsCursor(constraint, sPrefs);
    }

    private static class SuggestionsCursor extends AbstractCursor
    {
        private ArrayList<String> mResults;

        public SuggestionsCursor(CharSequence constraint, SharedPreferences sPrefs)
        {
            //TODO get queries here (in progress)
            //demo method
         /*   final int count = 100;
            mResults = new ArrayList<String>(count);
            for(int i = 0; i < count; i++){
                mResults.add("Result " + (i + 1));
            }*/
            //working method
            int queryCounter=sPrefs.getInt("counter",0);
            mResults = new ArrayList<String>(queryCounter);
            if (queryCounter>0){
               for (int i=0;i<queryCounter;i++) {
                   mResults.add(sPrefs.getString("query"+i,""));
               }
            }
            if(!TextUtils.isEmpty(constraint)){
                String constraintString = constraint.toString().toLowerCase(Locale.ROOT);
                Iterator<String> iter = mResults.iterator();
                while(iter.hasNext()){
                    if(!iter.next().toLowerCase(Locale.ROOT).startsWith(constraintString))
                    {
                        iter.remove();
                    }
                }
            }
        }

        @Override
        public int getCount()
        {
            return mResults.size();
        }

        @Override
        public String[] getColumnNames()
        {
            return mFields;
        }

        @Override
        public long getLong(int column)
        {
            if(column == 0){
                return mPos;
            }
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public String getString(int column)
        {
            if(column == 1){
                return mResults.get(mPos);
               // return "";
            }
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public short getShort(int column)
        {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public int getInt(int column)
        {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public float getFloat(int column)
        {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public double getDouble(int column)
        {
            throw new UnsupportedOperationException("unimplemented");
        }

        @Override
        public boolean isNull(int column)
        {
            return false;
        }

    }
}


package teamvoy.com.nytandroid.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.retrofit.RestClient;
import teamvoy.com.nytandroid.retrofit.RestInterface;
import teamvoy.com.nytandroid.retrofit.article.Article;
import teamvoy.com.nytandroid.retrofit.article.Doc;
import teamvoy.com.nytandroid.ui.ArticleFilterActivity;
import teamvoy.com.nytandroid.ui.MainActivity;
import teamvoy.com.nytandroid.ui.adapters.ArticleRecyclerAdapter;

/**
 * Created by lubomyrshershun on 9/24/15.
 */
public class NewsFragment extends Fragment {
    private RestInterface restInterface;
    private String TAG = this.getClass().getSimpleName();

    private Callback<Article> callback;
    private SwipeRefreshLayout swipe;
    private List<Article> articleList;
    private ArticleRecyclerAdapter adapter;
    private boolean _loading = true;
    private FloatingActionButton fab;
    private FilterStorage filter=null;

    private String SearchQuery=null;


    public NewsFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);
        restInterface = RestClient.getInstance("docs").getClient();
        articleList = new ArrayList<>();
        swipe = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe);
        swipe.setColorSchemeColors(getResources().getColor(R.color.accent_400));
        fab = (FloatingActionButton) rootview.findViewById(R.id.fab);

        final RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.dummyfrag_scrollableview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArticleRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);


        //register callback
        callback = new Callback<Article>() {
            @Override
            public void success(Article article, Response response) {
                if (swipe.isRefreshing()) swipe.setRefreshing(false);
                articleList.add(article);
                List<Doc> data = new ArrayList<>();
                for (int i = 0; i < articleList.size(); i++) {
                    data.addAll(articleList.get(i).response.docs);
                }
                adapter.setData(data);
                adapter.notifyDataSetChanged();
                _loading = true;
                Log.d(TAG, "status=" + article.status + " hits=" + article.response.meta.hits);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
            }
        };
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fab click
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            fab.hide();
                            recyclerView.removeOnScrollListener(this);
                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });
                recyclerView.smoothScrollToPosition(0);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int totalItemCount;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) fab.hide();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //  if (lastVisibleItemPosition>10) fab.show();
                if (_loading) {

                    if (lastVisibleItemPosition == totalItemCount - 1) {
                        _loading = false;

                        getArticle(SearchQuery, null, null, null, "newest", articleList.size());
                    }
                }

            }
        });
        //set listener for swipe
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               refresh();
            }
        });

        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu)
    {
        MenuItem filter_disabled = menu.findItem(R.id.action_filter);
        MenuItem filter_enabled = menu.findItem(R.id.action_filter_enabled);

        if(isFilterModeEnabled())
        {
            filter_disabled.setVisible(false);
            filter_enabled.setVisible(true);

        }
        else
        {
            filter_disabled.setVisible(true);
            filter_enabled.setVisible(false);
        }
    }

    private boolean isFilterModeEnabled() {
        if (filter!=null) return true;
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_news, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                SearchQuery=newText;
                refresh();
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search: {
                break;
            }
            case R.id.action_filter: {
                Intent intent = new Intent(getActivity(), ArticleFilterActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case R.id.action_filter_enabled:{
                filter=null;
                getActivity().invalidateOptionsMenu();
                refresh();
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        getActivity().invalidateOptionsMenu();
        filter=new FilterStorage(
                data.getStringExtra("sections"),
                data.getStringExtra("begin_date"),
                data.getStringExtra("end_date"),
                data.getStringExtra("sort"));


    }
    private void getArticle(String q, String fq, String begin_date, String end_date, String order, Integer page) {
        if (isFilterModeEnabled()){

            restInterface.getArticles(validateQuery(q),filter.getSections(),filter.getBegin_date(),filter.getEnd_date(),filter.getSort(),page
            ,getResources().getString(R.string.api_key_article_search), callback);
        }
        else restInterface.getArticles(validateQuery(q), fq, begin_date, end_date, order, page
                , getResources().getString(R.string.api_key_article_search), callback);
    }
    private void refresh(){
        articleList.clear();
        adapter.setData(null);
        adapter.notifyDataSetChanged();
        getArticle(SearchQuery, null, null, null, "newest", null);
    }

    private String validateQuery(String searchQuery) {
        if (searchQuery==null) return null;
        if (searchQuery.isEmpty()) return null;
        else return searchQuery;
    }

    public class FilterStorage {
        private String sections,begin_date,end_date,sort;

        public FilterStorage(String sections, String begin_date, String end_date, String sort) {
            this.sections = split(sections);
            this.begin_date = begin_date;
            this.end_date = end_date;
            this.sort = sort;
        }

        private String split(String sections) {
            if (sections==null) return null;
            String result="section_name:(";
            String[] array;
            array=sections.split(", ");
            for (int i=0;i<array.length;i++){
                result+="\""+array[i]+"\"";
            }
            result+=")";
            return result;
        }

        public String getSections() {
            return sections;
        }

        public String getBegin_date() {
            if (begin_date!=null) return begin_date;
            else return null;
        }

        public String getEnd_date() {
            if (end_date!=null) return end_date;
            else return null;
        }

        public String getSort() {
            return sort;
        }
    }
}

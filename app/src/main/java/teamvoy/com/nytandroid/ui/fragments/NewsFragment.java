package teamvoy.com.nytandroid.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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


    public NewsFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootview = inflater.inflate(R.layout.fragment_main, container, false);
        restInterface = RestClient.getInstance("docs").getClient();
        articleList =new ArrayList<>();
        swipe = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe);
        swipe.setColorSchemeColors(getResources().getColor(R.color.accent_400));
        fab=(FloatingActionButton)rootview.findViewById(R.id.fab);


        final RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.dummyfrag_scrollableview);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new ArticleRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);


        //register callback
     callback= new Callback<Article>() {
         @Override
         public void success(Article article, Response response) {
                if (swipe.isRefreshing()) swipe.setRefreshing(false);
                articleList.add(article);
                List<Doc> data=new ArrayList<>();
                for (int i=0; i<articleList.size(); i++){
                    data.addAll(articleList.get(i).response.docs);
                }
                adapter.setData(data);
             adapter.notifyDataSetChanged();
             _loading=true;
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
                totalItemCount=layoutManager.getItemCount();
                if(layoutManager.findFirstCompletelyVisibleItemPosition()==0) fab.hide();
                int lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition>10) fab.show();
                if(_loading){

                    if (  lastVisibleItemPosition== totalItemCount-1) {
                        _loading = false;

                        getArticle(null, null, null, null, "newest", articleList.size());
                    }
                }

            }
        });
        //set listener for swipe
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                articleList.clear();
                adapter.setData(null);
                adapter.notifyDataSetChanged();
                 getArticle(null, null, null, null, "newest", null);
            }
        });
        getArticle(null, null, null, null, "newest", null);
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getArticle(String q, String fq, String begin_date, String end_date, String order, Integer page){

        restInterface.getArticles(q, fq, begin_date, end_date, order, page
                , getResources().getString(R.string.api_key_article_search), callback);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_news, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_search:{
                //TODO handle search item click here
                break;
            }
        }


        return super.onOptionsItemSelected(item);
    }
}

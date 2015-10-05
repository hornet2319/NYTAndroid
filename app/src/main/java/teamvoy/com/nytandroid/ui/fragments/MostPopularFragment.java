package teamvoy.com.nytandroid.ui.fragments;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import teamvoy.com.nytandroid.retrofit.most_popular.MostPopular;
import teamvoy.com.nytandroid.retrofit.most_popular.Result;
import teamvoy.com.nytandroid.ui.MostPopularFilterActivity;
import teamvoy.com.nytandroid.ui.adapters.MostPopularRecyclerAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MostPopularFragment extends Fragment {
    private RestInterface restInterface;
    private String TAG=this.getClass().getSimpleName();
    private Callback<MostPopular> callback;
    private FilterStorage filter;
    private SwipeRefreshLayout swipe;
    private List<MostPopular> mostPopulars;
    private MostPopularRecyclerAdapter adapter;
    private boolean _loading = true;
    private boolean isFilterModeEnabled=false;
    private FloatingActionButton fab;


    public MostPopularFragment() {
        filter=new FilterStorage();
        this.restInterface = RestClient.getInstance("results").getClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootview=inflater.inflate(R.layout.fragment_main, container, false);
        mostPopulars =new ArrayList<>();
        swipe = (SwipeRefreshLayout) rootview.findViewById(R.id.swipe);
        swipe.setColorSchemeColors(getResources().getColor(R.color.accent_400));
        fab=(FloatingActionButton)rootview.findViewById(R.id.fab);


        final RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.dummyfrag_scrollableview);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MostPopularRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);

     callback=new Callback<MostPopular>() {
            @Override
            public void success(MostPopular mostPopular, Response response) {
                Log.d(TAG, "status="+mostPopular.status+" numResults="+mostPopular.numResults);
                if (swipe.isRefreshing()) swipe.setRefreshing(false);
                mostPopulars.add(mostPopular);
                List<Result> data=new ArrayList<>();
                for (int i=0; i< mostPopulars.size(); i++){
                    data.addAll(mostPopulars.get(i).results);
                }
                adapter.setData(data);
                adapter.notifyDataSetChanged();
                _loading=true;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, ""+error.getMessage());
            }
        };
        //,
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
                // if (lastVisibleItemPosition>10) fab.show();
                if (_loading) {

                    if (lastVisibleItemPosition == totalItemCount - 1) {
                        _loading = false;
                        getMostPopular("" + (mostPopulars.size() * 20));
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

    private void getMostPopular(String offset){
        restInterface.getMostViewed(filter.getSections(), filter.getTime_period(), offset,
                getResources().getString(R.string.api_key_most_popular), callback);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        isFilterModeEnabled=true;
        getActivity().invalidateOptionsMenu();
        filter.setSections(data.getStringExtra("sections"));
        filter.setTime_period(data.getIntExtra("time-period", 7));
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_most_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    public void onPrepareOptionsMenu(Menu menu)
    {
        MenuItem filter_disabled = menu.findItem(R.id.action_filter);
        MenuItem filter_enabled = menu.findItem(R.id.action_filter_enabled);

        if(isFilterModeEnabled)
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
                Intent intent = new Intent(getActivity(), MostPopularFilterActivity.class);
                startActivityForResult(intent,2);
                break;
            }
            case R.id.action_filter_enabled:{
                isFilterModeEnabled=false;
                filter.empty();
                getActivity().invalidateOptionsMenu();
                refresh();
            }
        }


        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        mostPopulars.clear();
        adapter.setData(null);
        adapter.notifyDataSetChanged();
        getMostPopular("");
    }

    private class FilterStorage {
        private String sections;
        private Integer time_period;

        public FilterStorage() {
            sections=null;
            time_period=null;
        }
        public void empty(){
            sections=null;
            time_period=null;
        }
        public void setSections(String sections) {
            this.sections = sections;
        }

        public void setTime_period(Integer time_period) {
            this.time_period = time_period;
        }

        public String getSections() {
            if (sections==null) return "all-sections";
            return sections;
        }

        public String getTime_period() {
            if (time_period==null) return "7";
            return ""+time_period;
        }
    }
}

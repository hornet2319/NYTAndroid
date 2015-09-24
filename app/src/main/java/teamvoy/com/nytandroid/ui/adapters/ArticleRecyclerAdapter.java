package teamvoy.com.nytandroid.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.retrofit.article.Article;
import teamvoy.com.nytandroid.retrofit.article.Doc;

/**
 * Created by lubomyrshershun on 9/24/15.
 */
public class ArticleRecyclerAdapter extends RecyclerView.Adapter<ArticleRecyclerAdapter.VersionViewHolder> {
    private String TAG=getClass().getSimpleName();
    List<Doc> data;
    Context context;
    AdapterView.OnItemClickListener clickListener;

    public ArticleRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlist_item_article, parent, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data==null ? 0 : data.size();
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int pos) {
        Doc item=data.get(pos);

        holder.section_name.setText(item.sectionName);
        holder.header.setText(item.headline.main);
        if (item.byline==null) holder.author.setVisibility(View.GONE);
        holder.author.setText(item.byline.original);
        holder.content.setText(item.snippet);
        if(item.multimedia.size()>0){

            Picasso.with(context).load("http://static01.nyt.com/"+item.multimedia.get(0).url).into(holder.image);
        }

    }

    public void setData(List<Doc> data) {
        this.data = data;
    }

    public static class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView section_name, header,author,content;
        public VersionViewHolder(View view) {
            super(view);
            image=(ImageView)view.findViewById(R.id.article_img);
            section_name=(TextView)view.findViewById(R.id.article_section_name);
            header=(TextView)view.findViewById(R.id.article_header);
            author=(TextView)view.findViewById(R.id.article_autor);
            content=(TextView)view.findViewById(R.id.article_content);
        }

        @Override
        public void onClick(View view) {
            //TODO handle click here
        }
    }
}

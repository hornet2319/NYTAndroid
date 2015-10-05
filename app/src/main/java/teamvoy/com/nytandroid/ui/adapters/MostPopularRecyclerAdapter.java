package teamvoy.com.nytandroid.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import teamvoy.com.nytandroid.R;
import teamvoy.com.nytandroid.retrofit.most_popular.MediaMetadatum;
import teamvoy.com.nytandroid.retrofit.most_popular.Medium;
import teamvoy.com.nytandroid.retrofit.most_popular.Result;
import teamvoy.com.nytandroid.ui.ContentActivity;

/**
 * Created by lubomyrshershun on 9/25/15.
 */
public class MostPopularRecyclerAdapter  extends RecyclerView.Adapter<MostPopularRecyclerAdapter.VersionViewHolder>{
    List<Result> data;
    Context context;

    public MostPopularRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlist_item_article, parent, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {
        Result item=data.get(position);

        holder.type.setText(item.type);
        if(item.type.isEmpty()) hide(holder.type);

        holder.section_name.setText(item.section);
        if(item.section.isEmpty()) hide(holder.section_name);

        holder.author.setText(item.byline);
        if(item.byline.isEmpty()) hide(holder.author);

        holder.date.setText(item.publishedDate);
        if(item.publishedDate.isEmpty()) hide(holder.date);

        holder.header.setText(item.title);
        if(item.title.isEmpty()) hide(holder.header);

        holder.content.setText(item._abstract);
        if(item._abstract.isEmpty()) hide(holder.content);

        if (item.media.size()>0) {
            Medium content=item.media.get(0);
            for (int i=0; i<content.mediaMetadata.size();i++){
                if(content.mediaMetadata.get(i).format.equals("Normal")) Picasso.with(context).load(content.mediaMetadata.get(i).url).into(holder.image);
            }

        }
    }
    private void hide(View view){
        view.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return data==null ? 0 : data.size();
    }
    public void setData(List<Result> data) {
        this.data = data;
    }

    public class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView section_name, header,author,content,date,type;
        public VersionViewHolder(View view) {
            super(view);
            image=(ImageView)view.findViewById(R.id.article_img);
            date=(TextView)view.findViewById(R.id.article_date);
            type=(TextView)view.findViewById(R.id.article_type);
            section_name=(TextView)view.findViewById(R.id.article_section_name);
            header=(TextView)view.findViewById(R.id.article_header);
            author=(TextView)view.findViewById(R.id.article_autor);
            content=(TextView)view.findViewById(R.id.article_content);
            type.setVisibility(View.VISIBLE);
            date.setVisibility(View.VISIBLE);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, ContentActivity.class);
            intent.putExtra("url",data.get(getAdapterPosition()).url);
            intent.putExtra("section",data.get(getAdapterPosition()).section);
            context.startActivity(intent);
        }
    }
}

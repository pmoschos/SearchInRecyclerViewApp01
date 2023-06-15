package gr.aueb.cf.newsapp001.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import gr.aueb.cf.newsapp001.R;
import gr.aueb.cf.newsapp001.models.News;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private ArrayList<News> newsArrayList;
    private ArrayList<News> newsArrayListFull;

    public MyAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayListFull = newsArrayList;
        this.newsArrayList = new ArrayList<>(newsArrayListFull);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        holder.headingTV.setText(news.getHeading());
        holder.headingImage.setImageResource(news.getTitleImage());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return newsFilter;
    }

    public final Filter newsFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<News> filteredArrayList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredArrayList.addAll(newsArrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (News news : newsArrayListFull) {
                    if (news.getHeading().toLowerCase().contains(filterPattern)) {
                        filteredArrayList.add(news);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredArrayList;
            results.count = filteredArrayList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            newsArrayList.clear();
            newsArrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView headingTV;
        private ShapeableImageView headingImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            headingTV = itemView.findViewById(R.id.headingTV);
            headingImage = itemView.findViewById(R.id.imageTitleSIV);
        }


    }
}

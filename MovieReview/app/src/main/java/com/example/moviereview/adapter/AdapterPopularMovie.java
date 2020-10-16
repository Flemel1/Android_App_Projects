package com.example.moviereview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviereview.R;
import com.example.moviereview.model.PopularMovie;

import java.util.List;

import static com.example.moviereview.variablemodel.Variable.URL_IMAGE;
import static com.example.moviereview.variablemodel.Variable.URL_IMAGE_500W;

public class AdapterPopularMovie extends RecyclerView.Adapter<AdapterPopularMovie.MyHolderView> {
    private Context context;
    private List<PopularMovie> popularMovieList;

    public AdapterPopularMovie(Context context, List<PopularMovie> popularMovieList) {
        this.context = context;
        this.popularMovieList = popularMovieList;
    }

    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_movie_item, parent, false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderView holder, int position) {
        PopularMovie popularMovie = popularMovieList.get(position);
        String rating = String.valueOf(popularMovie.getVoteAverage());
        Glide.with(context).load(URL_IMAGE_500W.concat(popularMovie.getPosterPath())).into(holder.image);
        holder.title.setText(popularMovie.getTitle());
        holder.rating.setText(rating);
    }

    @Override
    public int getItemCount() {
        return popularMovieList.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView rating;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_movie);
            title = itemView.findViewById(R.id.title_popular_movie);
            rating = itemView.findViewById(R.id.rating_popular_movie);
        }
    }
}

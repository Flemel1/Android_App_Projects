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
import com.example.moviereview.model.NowPlayingMovie;

import java.util.List;

import static com.example.moviereview.variablemodel.Variable.URL_IMAGE;

public class AdapterNowPlayingMovie extends RecyclerView.Adapter<AdapterNowPlayingMovie.MyViewHolder> {
    private List<NowPlayingMovie> nowPlayingMovieList;
    private Context context;

    public AdapterNowPlayingMovie(Context context, List<NowPlayingMovie> nowPlayingMovieList) {
        this.context = context;
        this.nowPlayingMovieList = nowPlayingMovieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.now_playing_movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NowPlayingMovie nowPlayingMovie = nowPlayingMovieList.get(position);
        holder.title.setText(nowPlayingMovie.getTitle());
        Glide.with(context).load(URL_IMAGE.concat(nowPlayingMovie.getPosterPath())).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return nowPlayingMovieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_popular_movie);
            title = itemView.findViewById(R.id.title_popular_movie);
        }
    }
}

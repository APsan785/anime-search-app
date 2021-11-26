package com.apsan.retrofit.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apsan.retrofit.APIPackage.AnimeObject;
import com.apsan.retrofit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.viewHolder>{

    private ArrayList<AnimeObject> anime_obj_list;
    private Context mContext;
    private onItemClickListener mListener;

    public Adapter(ArrayList<AnimeObject> anime_obj_list, Context mContext) {
        this.anime_obj_list = anime_obj_list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.cell, parent, false);
        return new viewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        AnimeObject obj = anime_obj_list.get(position);
        holder.title_anime.setText(obj.getTitle());
        Picasso.get().load(obj.getImageUrl()).into(holder.imageView);
        holder.airing_anime.setText(obj.getAiring().toString());
        holder.score_anime.setText(obj.getScore().toString());
        holder.episodes_anime.setText(obj.getEpisodes().toString());

        holder.title_anime1.setText(obj.getTitle());
        Picasso.get().load(obj.getImageUrl()).into(holder.imageView1);
        holder.airing_anime1.setText(obj.getAiring().toString());
        holder.score_anime1.setText(obj.getScore().toString());
        holder.episodes_anime1.setText(obj.getEpisodes().toString());

        holder.type_result.setText(obj.getType());
        holder.synopsis_result.setText(obj.getSynopsis());
        if(obj.getStartDate()!=null && obj.getEndDate()!=null){
        holder.date_result.setText(obj.getStartDate().substring(0, 10)+
                " TO "+obj.getEndDate().substring(0,10));}
        if(obj.getUrl()!=null){
            holder.url_result.setText(obj.getUrl());
        }
    }

    @Override
    public int getItemCount() {
        return anime_obj_list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView ;
        TextView title_anime;
        TextView airing_anime;
        TextView episodes_anime;
        TextView score_anime;

        ImageView imageView1;
        TextView title_anime1;
        TextView airing_anime1;
        TextView episodes_anime1;
        TextView score_anime1;

        TextView type_result;
        TextView synopsis_result;
        TextView date_result;
        TextView url_result;

        public viewHolder(@NonNull View itemView, onItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_anime);
            title_anime = itemView.findViewById(R.id.title_anime);
            airing_anime = itemView.findViewById(R.id.airing_result);
            episodes_anime = itemView.findViewById(R.id.episodes_result);
            score_anime = itemView.findViewById(R.id.score_result);

            imageView1 = itemView.findViewById(R.id.image_anime1);
            title_anime1 = itemView.findViewById(R.id.title_anime1);
            airing_anime1 = itemView.findViewById(R.id.airing_result1);
            episodes_anime1 = itemView.findViewById(R.id.episodes_result1);
            score_anime1 = itemView.findViewById(R.id.score_result1);

            type_result = itemView.findViewById(R.id.type_result);
            synopsis_result = itemView.findViewById(R.id.synopsis_result);
            date_result = itemView.findViewById(R.id.runDate_result);
            url_result = itemView.findViewById(R.id.url_result);

            itemView.setOnClickListener(v -> {
                if(listener!= null){
                    int position = getBindingAdapterPosition();
                    if(position!= RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });
        }

    }
    public interface onItemClickListener {
        void onItemClick(int position);
    }
    public void setonItemClickListner(onItemClickListener listener){
        mListener = listener;
    }
}

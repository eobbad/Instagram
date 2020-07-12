package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    int numLikes;
    boolean userLiked = false;


    public PostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post = posts.get(position);
        holder.bind(post);
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements com.example.instagram.ViewHolder {


        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvTimeStamp;
        private TextView tvNumLikes;
        private TextView tvUsername2;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvNumLikes = itemView.findViewById(R.id.tvNumLikes);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            tvUsername2 = itemView.findViewById(R.id.tvUsername2);
            ImageView ivFavorite = itemView.findViewById(R.id.ivFavorite);

            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (userLiked) {

                    } else {
                        posts.get(pos).addLike();
                        userLiked = true;
                    }
                }
            });


        }




        public void addAll(List<Post> list) {
            posts.addAll(list);
            notifyDataSetChanged();
        }
        public void clear() {
            posts.clear();
            notifyDataSetChanged();
        }


        public void bind(Post post) {

            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvTimeStamp.setText(post.getCreatedTime());
            tvNumLikes.setText(post.getLikes() + " likes");
            tvUsername2.setText(post.getUser().getUsername());
            ParseFile image =  post.getImage();
            if(image != null){
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            }

        }


        @Override
        public void onClick(View view) {

        }
    }
}
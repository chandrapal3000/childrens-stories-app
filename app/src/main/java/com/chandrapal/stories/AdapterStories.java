package com.chandrapal.stories;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterStories extends RecyclerView.Adapter<AdapterStories.ViewHolderStories> {
    private List<Model> dataHolder;
    private OnStoriesClickListener onStoriesClickListener;
    private View viewSingleRowHome;
    int linearChildCount = 1;
    boolean topBar = true;
    boolean secondBar = true;
    boolean showMore = true;

    public AdapterStories(List<Model> dataHolder, OnStoriesClickListener onStoriesClickListener) {
        this.dataHolder = dataHolder;
        this.onStoriesClickListener = onStoriesClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderStories onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_stories, parent,false);
        viewSingleRowHome = view;
        return new ViewHolderStories(view, onStoriesClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderStories holder, int position) {

        holder.title.setText(dataHolder.get(position).getStoriesTitle());
        String cover_image_string = dataHolder.get(position).getStoriesCoverImage();
        Picasso.get().load(cover_image_string).into(holder.cover_image);

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class ViewHolderStories extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView username, uploadedTime, title, branch, subjectAndChapter, showMore;
        ShapeableImageView cover_image;
        OnStoriesClickListener onStoriesClickListenerVH;
        public ViewHolderStories(@NonNull @NotNull View itemView, OnStoriesClickListener onStoriesClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title_single_row_stories);
            cover_image = itemView.findViewById(R.id.shape_able_image_view_cover_image_single_row_stories);
            this.onStoriesClickListenerVH = onStoriesClickListener;

            title.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onStoriesClickListener.onStoriesClick(getAdapterPosition());
        }
    }
    public interface OnStoriesClickListener{
        void onStoriesClick(int position);
    }

}

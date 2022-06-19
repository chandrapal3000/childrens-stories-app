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

public class AdapterStoriesLanguageHindi extends RecyclerView.Adapter<AdapterStoriesLanguageHindi.ViewHolderStoriesLanguageHindi> {
    private List<Model> dataHolder;
    private OnStoriesLanguageHindiClickListener onStoriesLanguageHindiClickListener;
    private View viewSingleRowHome;
    int linearChildCount = 1;
    boolean topBar = true;
    boolean secondBar = true;
    boolean showMore = true;

    public AdapterStoriesLanguageHindi(List<Model> dataHolder, OnStoriesLanguageHindiClickListener onStoriesLanguageHindiClickListener) {
        this.dataHolder = dataHolder;
        this.onStoriesLanguageHindiClickListener = onStoriesLanguageHindiClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderStoriesLanguageHindi onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_hindi_stories, parent,false);
        viewSingleRowHome = view;
        return new ViewHolderStoriesLanguageHindi(view, onStoriesLanguageHindiClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderStoriesLanguageHindi holder, int position) {

        holder.title.setText(dataHolder.get(position).getStoriesTitle());
        Picasso.get().load(dataHolder.get(position).getStoriesCoverImage()).into(holder.cover_image);

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class ViewHolderStoriesLanguageHindi extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView username, uploadedTime, title, branch, subjectAndChapter, showMore;
        ShapeableImageView cover_image;
        OnStoriesLanguageHindiClickListener onStoriesLanguageHindiClickListenerVH;
        public ViewHolderStoriesLanguageHindi(@NonNull @NotNull View itemView, OnStoriesLanguageHindiClickListener onStoriesLanguageHindiClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title_single_row_hindi_stories);
            cover_image = itemView.findViewById(R.id.shape_able_image_view_cover_image_single_row_hindi_stories);
            this.onStoriesLanguageHindiClickListenerVH = onStoriesLanguageHindiClickListener;

            title.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onStoriesLanguageHindiClickListener.onStoriesLanguageHindiClick(getAdapterPosition());
        }
    }
    public interface OnStoriesLanguageHindiClickListener{
        void onStoriesLanguageHindiClick(int position);
    }

}

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

public class AdapterStoriesLanguageEnglish extends RecyclerView.Adapter<AdapterStoriesLanguageEnglish.ViewHolderStoriesLanguageEnglish> {
    private List<Model> dataHolder;
    private OnStoriesLanguageEnglishClickListener onStoriesLanguageEnglishClickListener;
    private View viewSingleRowHome;
    int linearChildCount = 1;
    boolean topBar = true;
    boolean secondBar = true;
    boolean showMore = true;

    public AdapterStoriesLanguageEnglish(List<Model> dataHolder, OnStoriesLanguageEnglishClickListener onStoriesLanguageEnglishClickListener) {
        this.dataHolder = dataHolder;
        this.onStoriesLanguageEnglishClickListener = onStoriesLanguageEnglishClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderStoriesLanguageEnglish onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_hindi_stories, parent,false);
        viewSingleRowHome = view;
        return new ViewHolderStoriesLanguageEnglish(view, onStoriesLanguageEnglishClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderStoriesLanguageEnglish holder, int position) {

        holder.title.setText(dataHolder.get(position).getStoriesTitle());
        Picasso.get().load(dataHolder.get(position).getStoriesCoverImage()).into(holder.cover_image);

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class ViewHolderStoriesLanguageEnglish extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView username, uploadedTime, title, branch, subjectAndChapter, showMore;
        ShapeableImageView cover_image;
        OnStoriesLanguageEnglishClickListener onStoriesLanguageEnglishClickListenerVH;
        public ViewHolderStoriesLanguageEnglish(@NonNull @NotNull View itemView, OnStoriesLanguageEnglishClickListener onStoriesLanguageEnglishClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title_single_row_hindi_stories);
            cover_image = itemView.findViewById(R.id.shape_able_image_view_cover_image_single_row_hindi_stories);
            this.onStoriesLanguageEnglishClickListenerVH = onStoriesLanguageEnglishClickListener;

            title.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onStoriesLanguageEnglishClickListener.onStoriesLanguageEnglishClick(getAdapterPosition());
        }
    }
    public interface OnStoriesLanguageEnglishClickListener{
        void onStoriesLanguageEnglishClick(int position);
    }

}

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

public class AdapterStoriesTagsTenaliRama extends RecyclerView.Adapter<AdapterStoriesTagsTenaliRama.ViewHolderStoriesTagsTenaliRama> {
    private List<Model> dataHolder;
    private OnStoriesTagsTenaliRamaClickListener onStoriesTagsTenaliRamaClickListener;
    private View viewSingleRowHome;
    int linearChildCount = 1;
    boolean topBar = true;
    boolean secondBar = true;
    boolean showMore = true;

    public AdapterStoriesTagsTenaliRama(List<Model> dataHolder, OnStoriesTagsTenaliRamaClickListener onStoriesTagsTenaliRamaClickListener) {
        this.dataHolder = dataHolder;
        this.onStoriesTagsTenaliRamaClickListener = onStoriesTagsTenaliRamaClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderStoriesTagsTenaliRama onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_hindi_stories, parent,false);
        viewSingleRowHome = view;
        return new ViewHolderStoriesTagsTenaliRama(view, onStoriesTagsTenaliRamaClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderStoriesTagsTenaliRama holder, int position) {

        holder.title.setText(dataHolder.get(position).getStoriesTitle());
        Picasso.get().load(dataHolder.get(position).getStoriesCoverImage()).into(holder.cover_image);

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class ViewHolderStoriesTagsTenaliRama extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView username, uploadedTime, title, branch, subjectAndChapter, showMore;
        ShapeableImageView cover_image;
        OnStoriesTagsTenaliRamaClickListener onStoriesTagsTenaliRamaClickListenerVH;
        public ViewHolderStoriesTagsTenaliRama(@NonNull @NotNull View itemView, OnStoriesTagsTenaliRamaClickListener onStoriesTagsTenaliRamaClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title_single_row_hindi_stories);
            cover_image = itemView.findViewById(R.id.shape_able_image_view_cover_image_single_row_hindi_stories);
            this.onStoriesTagsTenaliRamaClickListenerVH = onStoriesTagsTenaliRamaClickListener;

            title.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onStoriesTagsTenaliRamaClickListener.onStoriesTagsTenaliRamaClick(getAdapterPosition());
        }
    }
    public interface OnStoriesTagsTenaliRamaClickListener{
        void onStoriesTagsTenaliRamaClick(int position);
    }

}

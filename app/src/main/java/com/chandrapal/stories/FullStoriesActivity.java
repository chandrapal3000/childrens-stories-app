package com.chandrapal.stories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class FullStoriesActivity extends AppCompatActivity {

    TextView title;
    WebView webView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ShapeableImageView cover_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_stories);

//        title = findViewById(R.id.textview_title_activity_full_stories);
        webView = findViewById(R.id.web_view_activity_full_stories);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout_activity_full_stories);
        cover_image = findViewById(R.id.cover_image_full_stories);

        Picasso.get().load(getIntent().getStringExtra("storiesCoverImage")).into(cover_image);
        String titleString = getIntent().getStringExtra("storiesTitle");
        collapsingToolbarLayout.setTitle(titleString);
//        collapsingToolbarLayout.setCollapsedTitleTextColor(Integer.parseInt("#f7f3f2"));
//        title.setText(titleString);
        webView.loadData(getIntent().getStringExtra("storiesStoriesText"), "text/html", "utf-8");

    }
}
package com.chandrapal.stories;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements AdapterStories.OnStoriesClickListener, AdapterStoriesLanguageHindi.OnStoriesLanguageHindiClickListener, AdapterStoriesLanguageEnglish.OnStoriesLanguageEnglishClickListener, AdapterStoriesTagsKings.OnStoriesTagsKingsClickListener, AdapterStoriesTagsAkbarAndBirbal.OnStoriesTagsAkbarAndBirbalClickListener, AdapterStoriesTagsTenaliRama.OnStoriesTagsTenaliRamaClickListener {

    String WEB_URL_GET_STORIES = "https://manage-college.000webhostapp.com/get_stories.php";
    AdapterStories adapterStoriesAll;
    AdapterStoriesLanguageHindi adapterStoriesLanguageHindi;
    AdapterStoriesLanguageEnglish adapterStoriesLanguageEnglish;
    AdapterStoriesTagsKings adapterStoriesTagsKings;
    AdapterStoriesTagsAkbarAndBirbal adapterStoriesTagsAkbarAndBirbal;
    AdapterStoriesTagsTenaliRama adapterStoriesTagsTenaliRama;
    private List<Model> allStoriesHolder;
    private List<Model> hindiStoriesHolder;
    private List<Model> englishStoriesHolder;
    private List<Model> kingsStoriesHolder;
    private List<Model> akbarAndBirbalStoriesHolder;
    private List<Model> tenaliRamaStoriesHolder;

    RecyclerView recyclerViewAll, recyclerViewHindi, recyclerViewEnglish, recyclerViewKings, recyclerViewAkbarAndBirbal, recyclerViewTenaliRama;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewAll = findViewById(R.id.recycler_view_all_language_activity_main);
        recyclerViewHindi = findViewById(R.id.recycler_view_hindi_activity_main);
        recyclerViewEnglish = findViewById(R.id.recycler_view_english_activity_main);
        recyclerViewKings = findViewById(R.id.recycler_view_type_kings_activity_main);
        recyclerViewAkbarAndBirbal = findViewById(R.id.recycler_view_tags_akbar_and_birbal_activity_main);
        recyclerViewTenaliRama = findViewById(R.id.recycler_view_tags_tenalirama_activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_activity_main);
        Menu menu = toolbar.getMenu();

        toolbar.setOnMenuItemClickListener(item->{
            if(item.getItemId()==R.id.action_about_me){
                Toast.makeText(this,"Visit my website", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://chandrapal.gq"));
                startActivity(intent);
            }
            if(item.getItemId()==R.id.action_about_me_action){
                Toast.makeText(this,"Visit my website", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://chandrapal.gq"));
                startActivity(intent);
            }

            return false;
        });

//        progressBar = findViewById(R.id.progress_bar_activity_main);

        allStoriesHolder = new ArrayList<>();
        hindiStoriesHolder = new ArrayList<>();
        englishStoriesHolder = new ArrayList<>();
        kingsStoriesHolder = new ArrayList<>();
        akbarAndBirbalStoriesHolder = new ArrayList<>();
        tenaliRamaStoriesHolder = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, WEB_URL_GET_STORIES, response -> {

//            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();

//            progressBar.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    String message = jsonArray.getJSONObject(0).getString("message");
                    jsonArray.getJSONObject(0).getString("message");
                    String type = jsonArray.getJSONObject(0).getString("message");
                    if(message.equals("positive")) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Model stories = new Model();
                            stories.setStoriesId(jsonObject.getString("id"));
                            stories.setStoriesTitle(jsonObject.getString("title"));
                            stories.setStoriesTags(jsonObject.getString("tags"));
                            stories.setStoriesLanguage(jsonObject.getString("language"));
                            String cover_image_string = jsonObject.getString("cover_image");
                            if(cover_image_string.equals("")){
                                cover_image_string = "https://manage-college.000webhostapp.com/upload_image/images/458723684.jpg";
                            }
                            stories.setStoriesCoverImage(cover_image_string);
                            stories.setStoriesStoriesText(jsonObject.getString("story_text"));
                            stories.setStoriesUploadedBy(jsonObject.getString("uploaded_by"));
                            stories.setStoriesUserId(jsonObject.getString("user_id"));
                            stories.setStoriesType(jsonObject.getString("type"));
                            stories.setStoriesUploadedTime(jsonObject.getString("uploaded_time"));
                            stories.setStoriesStatus(jsonObject.getString("status"));
                            stories.setStoriesPinned(jsonObject.getString("pinned"));

                            allStoriesHolder.add(stories);
                        }

                        recyclerViewAll.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                        adapterStoriesAll = new AdapterStories(allStoriesHolder, this);
                        recyclerViewAll.setAdapter(adapterStoriesAll);
                        
//                        hindi stories

                        for (int i = 0; i < jsonArray.length(); i++) {
                            if(jsonArray.getJSONObject(i).getString("language").toLowerCase().equals("hindi")){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Model stories = new Model();
                                stories.setStoriesId(jsonObject.getString("id"));
                                stories.setStoriesTitle(jsonObject.getString("title"));
                                stories.setStoriesTags(jsonObject.getString("tags"));
                                stories.setStoriesLanguage(jsonObject.getString("language"));
                                String cover_image_string = jsonObject.getString("cover_image");
                                if(cover_image_string.equals("")){
                                    cover_image_string = "https://manage-college.000webhostapp.com/upload_image/images/458723684.jpg";
                                }
                                stories.setStoriesCoverImage(cover_image_string);
                                stories.setStoriesStoriesText(jsonObject.getString("story_text"));
                                stories.setStoriesUploadedBy(jsonObject.getString("uploaded_by"));
                                stories.setStoriesUserId(jsonObject.getString("user_id"));
                                stories.setStoriesType(jsonObject.getString("type"));
                                stories.setStoriesUploadedTime(jsonObject.getString("uploaded_time"));
                                stories.setStoriesStatus(jsonObject.getString("status"));
                                stories.setStoriesPinned(jsonObject.getString("pinned"));

                                hindiStoriesHolder.add(stories);
                            }
                        }

                        recyclerViewHindi.setLayoutManager(new GridLayoutManager(getApplicationContext(),2, GridLayoutManager.HORIZONTAL, false));
                        adapterStoriesLanguageHindi = new AdapterStoriesLanguageHindi(hindiStoriesHolder, this);
                        recyclerViewHindi.setAdapter(adapterStoriesLanguageHindi);


//                        English stories

                        for (int i = 0; i < jsonArray.length(); i++) {
                            if(jsonArray.getJSONObject(i).getString("language").toLowerCase().equals("english")){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Model stories = new Model();
                                stories.setStoriesId(jsonObject.getString("id"));
                                stories.setStoriesTitle(jsonObject.getString("title"));
                                stories.setStoriesTags(jsonObject.getString("tags"));
                                stories.setStoriesLanguage(jsonObject.getString("language"));
                                String cover_image_string = jsonObject.getString("cover_image");
                                if(cover_image_string.equals("")){
                                    cover_image_string = "https://manage-college.000webhostapp.com/upload_image/images/458723684.jpg";
                                }
                                stories.setStoriesCoverImage(cover_image_string);
                                stories.setStoriesStoriesText(jsonObject.getString("story_text"));
                                stories.setStoriesUploadedBy(jsonObject.getString("uploaded_by"));
                                stories.setStoriesUserId(jsonObject.getString("user_id"));
                                stories.setStoriesType(jsonObject.getString("type"));
                                stories.setStoriesUploadedTime(jsonObject.getString("uploaded_time"));
                                stories.setStoriesStatus(jsonObject.getString("status"));
                                stories.setStoriesPinned(jsonObject.getString("pinned"));

                                englishStoriesHolder.add(stories);
                            }
                        }

                        recyclerViewEnglish.setLayoutManager(new GridLayoutManager(getApplicationContext(),1, GridLayoutManager.HORIZONTAL, false));
                        adapterStoriesLanguageEnglish= new AdapterStoriesLanguageEnglish(englishStoriesHolder, this);
                        recyclerViewEnglish.setAdapter(adapterStoriesLanguageEnglish);


//                        Kings

                        for (int i = 0; i < jsonArray.length(); i++) {
                            String str = jsonArray.getJSONObject(i).getString("tags");
                            ArrayList<String> tags = new ArrayList<>(Arrays.asList(str.split(",[ ]*") )  );
                                if (tags.contains("king") || tags.contains("kings") ) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Model stories = new Model();
                                    stories.setStoriesId(jsonObject.getString("id"));
                                    stories.setStoriesTitle(jsonObject.getString("title"));
                                    stories.setStoriesTags(jsonObject.getString("tags"));
                                    stories.setStoriesLanguage(jsonObject.getString("language"));
                                    String cover_image_string = jsonObject.getString("cover_image");
                                    if (cover_image_string.equals("")) {
                                        cover_image_string = "https://manage-college.000webhostapp.com/upload_image/images/458723684.jpg";
                                    }
                                    stories.setStoriesCoverImage(cover_image_string);
                                    stories.setStoriesStoriesText(jsonObject.getString("story_text"));
                                    stories.setStoriesUploadedBy(jsonObject.getString("uploaded_by"));
                                    stories.setStoriesUserId(jsonObject.getString("user_id"));
                                    stories.setStoriesType(jsonObject.getString("type"));
                                    stories.setStoriesUploadedTime(jsonObject.getString("uploaded_time"));
                                    stories.setStoriesStatus(jsonObject.getString("status"));
                                    stories.setStoriesPinned(jsonObject.getString("pinned"));

                                    kingsStoriesHolder.add(stories);
                                }
                        }

                        recyclerViewKings.setLayoutManager(new GridLayoutManager(getApplicationContext(),1, GridLayoutManager.HORIZONTAL, false));
                        adapterStoriesTagsKings= new AdapterStoriesTagsKings(kingsStoriesHolder,this);
                        recyclerViewKings.setAdapter(adapterStoriesTagsKings);


//                        Akbar and birbal

                        for (int i = 0; i < jsonArray.length(); i++) {
                            String str = jsonArray.getJSONObject(i).getString("tags");
                            ArrayList<String> tags = new ArrayList<>(Arrays.asList(str.split(",[ ]*") )  );
                            if (tags.contains("akbar") || tags.contains("birbal") ) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Model stories = new Model();
                                stories.setStoriesId(jsonObject.getString("id"));
                                stories.setStoriesTitle(jsonObject.getString("title"));
                                stories.setStoriesTags(jsonObject.getString("tags"));
                                stories.setStoriesLanguage(jsonObject.getString("language"));
                                String cover_image_string = jsonObject.getString("cover_image");
                                if (cover_image_string.equals("")) {
                                    cover_image_string = "https://manage-college.000webhostapp.com/upload_image/images/458723684.jpg";
                                }
                                stories.setStoriesCoverImage(cover_image_string);
                                stories.setStoriesStoriesText(jsonObject.getString("story_text"));
                                stories.setStoriesUploadedBy(jsonObject.getString("uploaded_by"));
                                stories.setStoriesUserId(jsonObject.getString("user_id"));
                                stories.setStoriesType(jsonObject.getString("type"));
                                stories.setStoriesUploadedTime(jsonObject.getString("uploaded_time"));
                                stories.setStoriesStatus(jsonObject.getString("status"));
                                stories.setStoriesPinned(jsonObject.getString("pinned"));

                                akbarAndBirbalStoriesHolder.add(stories);
                            }
                        }

                        recyclerViewAkbarAndBirbal.setLayoutManager(new GridLayoutManager(getApplicationContext(),1, GridLayoutManager.HORIZONTAL, false));
                        adapterStoriesTagsAkbarAndBirbal= new AdapterStoriesTagsAkbarAndBirbal(akbarAndBirbalStoriesHolder,this);
                        recyclerViewAkbarAndBirbal.setAdapter(adapterStoriesTagsAkbarAndBirbal);


//                        tenalirama

                        for (int i = 0; i < jsonArray.length(); i++) {
                            String str = jsonArray.getJSONObject(i).getString("tags");
                            ArrayList<String> tags = new ArrayList<>(Arrays.asList(str.split(",[ ]*") )  );
                            if (tags.contains("tenalirama") || tags.contains("tenali rama") ) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Model stories = new Model();
                                stories.setStoriesId(jsonObject.getString("id"));
                                stories.setStoriesTitle(jsonObject.getString("title"));
                                stories.setStoriesTags(jsonObject.getString("tags"));
                                stories.setStoriesLanguage(jsonObject.getString("language"));
                                String cover_image_string = jsonObject.getString("cover_image");
                                if (cover_image_string.equals("")) {
                                    cover_image_string = "https://manage-college.000webhostapp.com/upload_image/images/458723684.jpg";
                                }
                                stories.setStoriesCoverImage(cover_image_string);
                                stories.setStoriesStoriesText(jsonObject.getString("story_text"));
                                stories.setStoriesUploadedBy(jsonObject.getString("uploaded_by"));
                                stories.setStoriesUserId(jsonObject.getString("user_id"));
                                stories.setStoriesType(jsonObject.getString("type"));
                                stories.setStoriesUploadedTime(jsonObject.getString("uploaded_time"));
                                stories.setStoriesStatus(jsonObject.getString("status"));
                                stories.setStoriesPinned(jsonObject.getString("pinned"));

                                tenaliRamaStoriesHolder.add(stories);
                            }
                        }

                        recyclerViewTenaliRama.setLayoutManager(new GridLayoutManager(getApplicationContext(),1, GridLayoutManager.HORIZONTAL, false));
                        adapterStoriesTagsTenaliRama= new AdapterStoriesTagsTenaliRama(tenaliRamaStoriesHolder,this);
                        recyclerViewTenaliRama.setAdapter(adapterStoriesTagsTenaliRama);
                        
                    } else {
                        Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show();
                        new MaterialAlertDialogBuilder(this)
                                .setTitle("Server response")
                                .setMessage("Looks like database is empty!, No data found")
                                .setCancelable(true)
                                .setPositiveButton("Ok", (dialog, which) -> {
                                    dialog.cancel();
                                })
                                .show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }, error -> {
            
//            progressBar.setVisibility(View.GONE);
            
                Toast.makeText(this, error.toString().trim(), Toast.LENGTH_LONG).show();

                new MaterialAlertDialogBuilder(this)
                        .setTitle("Connection error")
                        .setMessage("Could not connect to the server, Make sure you are connected to the internet, if your internet is fine then that may be a server error")
                        .setCancelable(true)
                        .setPositiveButton("Try Again", (dialog, which) -> {
                            startActivity(new Intent(this, MainActivity.class));
                            finish();
                        })
                        .show();
        }){
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<>();
                data.put("column", "");
                data.put("value", "");
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onStoriesClick(int position) {
        Toast.makeText(this, allStoriesHolder.get(position).getStoriesTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FullStoriesActivity.class);
        intent.putExtra("storiesId", allStoriesHolder.get(position).getStoriesId());
        intent.putExtra("storiesTitle", allStoriesHolder.get(position).getStoriesTitle());
        intent.putExtra("storiesTags", allStoriesHolder.get(position).getStoriesTags());
        intent.putExtra("storiesLanguage", allStoriesHolder.get(position).getStoriesLanguage());
        intent.putExtra("storiesCoverImage", allStoriesHolder.get(position).getStoriesCoverImage());
        intent.putExtra("storiesStoriesText", allStoriesHolder.get(position).getStoriesStoriesText());
        intent.putExtra("storiesUploadedBy", allStoriesHolder.get(position).getStoriesUploadedBy());
        intent.putExtra("storiesUserId", allStoriesHolder.get(position).getStoriesUserId());
        intent.putExtra("storiesType", allStoriesHolder.get(position).getStoriesType());
        intent.putExtra("storiesUploadedTime", allStoriesHolder.get(position).getStoriesUploadedTime());
        intent.putExtra("storiesStatus", allStoriesHolder.get(position).getStoriesStatus());
        intent.putExtra("storiesPinned", allStoriesHolder.get(position).getStoriesPinned());
        startActivity(intent);
    }

    @Override
    public void onStoriesLanguageHindiClick(int position) {
        List<Model> tempStoriesHolder;
        tempStoriesHolder = new ArrayList<>(hindiStoriesHolder);
        Toast.makeText(this, tempStoriesHolder.get(position).getStoriesTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FullStoriesActivity.class);
        intent.putExtra("storiesId", tempStoriesHolder.get(position).getStoriesId());
        intent.putExtra("storiesTitle", tempStoriesHolder.get(position).getStoriesTitle());
        intent.putExtra("storiesTags", tempStoriesHolder.get(position).getStoriesTags());
        intent.putExtra("storiesLanguage", tempStoriesHolder.get(position).getStoriesLanguage());
        intent.putExtra("storiesCoverImage", tempStoriesHolder.get(position).getStoriesCoverImage());
        intent.putExtra("storiesStoriesText", tempStoriesHolder.get(position).getStoriesStoriesText());
        intent.putExtra("storiesUploadedBy", tempStoriesHolder.get(position).getStoriesUploadedBy());
        intent.putExtra("storiesUserId", tempStoriesHolder.get(position).getStoriesUserId());
        intent.putExtra("storiesType", tempStoriesHolder.get(position).getStoriesType());
        intent.putExtra("storiesUploadedTime", tempStoriesHolder.get(position).getStoriesUploadedTime());
        intent.putExtra("storiesStatus", tempStoriesHolder.get(position).getStoriesStatus());
        intent.putExtra("storiesPinned", tempStoriesHolder.get(position).getStoriesPinned());
        startActivity(intent);
    }

    @Override
    public void onStoriesLanguageEnglishClick(int position) {
        List<Model> tempStoriesHolder;
        tempStoriesHolder = new ArrayList<>(englishStoriesHolder);
        Toast.makeText(this, tempStoriesHolder.get(position).getStoriesTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FullStoriesActivity.class);
        intent.putExtra("storiesId", tempStoriesHolder.get(position).getStoriesId());
        intent.putExtra("storiesTitle", tempStoriesHolder.get(position).getStoriesTitle());
        intent.putExtra("storiesTags", tempStoriesHolder.get(position).getStoriesTags());
        intent.putExtra("storiesLanguage", tempStoriesHolder.get(position).getStoriesLanguage());
        intent.putExtra("storiesCoverImage", tempStoriesHolder.get(position).getStoriesCoverImage());
        intent.putExtra("storiesStoriesText", tempStoriesHolder.get(position).getStoriesStoriesText());
        intent.putExtra("storiesUploadedBy", tempStoriesHolder.get(position).getStoriesUploadedBy());
        intent.putExtra("storiesUserId", tempStoriesHolder.get(position).getStoriesUserId());
        intent.putExtra("storiesType", tempStoriesHolder.get(position).getStoriesType());
        intent.putExtra("storiesUploadedTime", tempStoriesHolder.get(position).getStoriesUploadedTime());
        intent.putExtra("storiesStatus", tempStoriesHolder.get(position).getStoriesStatus());
        intent.putExtra("storiesPinned", tempStoriesHolder.get(position).getStoriesPinned());
        startActivity(intent);
    }

    @Override
    public void onStoriesTagsKingsClick(int position) {
        List<Model> tempStoriesHolder;
        tempStoriesHolder = new ArrayList<>(kingsStoriesHolder);
        Toast.makeText(this, tempStoriesHolder.get(position).getStoriesTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FullStoriesActivity.class);
        intent.putExtra("storiesId", tempStoriesHolder.get(position).getStoriesId());
        intent.putExtra("storiesTitle", tempStoriesHolder.get(position).getStoriesTitle());
        intent.putExtra("storiesTags", tempStoriesHolder.get(position).getStoriesTags());
        intent.putExtra("storiesLanguage", tempStoriesHolder.get(position).getStoriesLanguage());
        intent.putExtra("storiesCoverImage", tempStoriesHolder.get(position).getStoriesCoverImage());
        intent.putExtra("storiesStoriesText", tempStoriesHolder.get(position).getStoriesStoriesText());
        intent.putExtra("storiesUploadedBy", tempStoriesHolder.get(position).getStoriesUploadedBy());
        intent.putExtra("storiesUserId", tempStoriesHolder.get(position).getStoriesUserId());
        intent.putExtra("storiesType", tempStoriesHolder.get(position).getStoriesType());
        intent.putExtra("storiesUploadedTime", tempStoriesHolder.get(position).getStoriesUploadedTime());
        intent.putExtra("storiesStatus", tempStoriesHolder.get(position).getStoriesStatus());
        intent.putExtra("storiesPinned", tempStoriesHolder.get(position).getStoriesPinned());
        startActivity(intent);
    }

    @Override
    public void onStoriesTagsAkbarAndBirbalClick(int position) {
        List<Model> tempStoriesHolder;
        tempStoriesHolder = new ArrayList<>(akbarAndBirbalStoriesHolder);
        Toast.makeText(this, tempStoriesHolder.get(position).getStoriesTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FullStoriesActivity.class);
        intent.putExtra("storiesId", tempStoriesHolder.get(position).getStoriesId());
        intent.putExtra("storiesTitle", tempStoriesHolder.get(position).getStoriesTitle());
        intent.putExtra("storiesTags", tempStoriesHolder.get(position).getStoriesTags());
        intent.putExtra("storiesLanguage", tempStoriesHolder.get(position).getStoriesLanguage());
        intent.putExtra("storiesCoverImage", tempStoriesHolder.get(position).getStoriesCoverImage());
        intent.putExtra("storiesStoriesText", tempStoriesHolder.get(position).getStoriesStoriesText());
        intent.putExtra("storiesUploadedBy", tempStoriesHolder.get(position).getStoriesUploadedBy());
        intent.putExtra("storiesUserId", tempStoriesHolder.get(position).getStoriesUserId());
        intent.putExtra("storiesType", tempStoriesHolder.get(position).getStoriesType());
        intent.putExtra("storiesUploadedTime", tempStoriesHolder.get(position).getStoriesUploadedTime());
        intent.putExtra("storiesStatus", tempStoriesHolder.get(position).getStoriesStatus());
        intent.putExtra("storiesPinned", tempStoriesHolder.get(position).getStoriesPinned());
        startActivity(intent);
    }

    @Override
    public void onStoriesTagsTenaliRamaClick(int position) {
        List<Model> tempStoriesHolder;
        tempStoriesHolder = new ArrayList<>(tenaliRamaStoriesHolder);
        Toast.makeText(this, tempStoriesHolder.get(position).getStoriesTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FullStoriesActivity.class);
        intent.putExtra("storiesId", tempStoriesHolder.get(position).getStoriesId());
        intent.putExtra("storiesTitle", tempStoriesHolder.get(position).getStoriesTitle());
        intent.putExtra("storiesTags", tempStoriesHolder.get(position).getStoriesTags());
        intent.putExtra("storiesLanguage", tempStoriesHolder.get(position).getStoriesLanguage());
        intent.putExtra("storiesCoverImage", tempStoriesHolder.get(position).getStoriesCoverImage());
        intent.putExtra("storiesStoriesText", tempStoriesHolder.get(position).getStoriesStoriesText());
        intent.putExtra("storiesUploadedBy", tempStoriesHolder.get(position).getStoriesUploadedBy());
        intent.putExtra("storiesUserId", tempStoriesHolder.get(position).getStoriesUserId());
        intent.putExtra("storiesType", tempStoriesHolder.get(position).getStoriesType());
        intent.putExtra("storiesUploadedTime", tempStoriesHolder.get(position).getStoriesUploadedTime());
        intent.putExtra("storiesStatus", tempStoriesHolder.get(position).getStoriesStatus());
        intent.putExtra("storiesPinned", tempStoriesHolder.get(position).getStoriesPinned());
        startActivity(intent);
    }
}
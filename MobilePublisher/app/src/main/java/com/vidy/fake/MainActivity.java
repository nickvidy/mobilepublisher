package com.vidy.fake;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nicho.fakepublisherdashboard.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vidy.fake.datamodel.embeds.Embed;
import com.vidy.fake.datamodel.embeds.EmbedsResult;
import com.vidy.fake.datamodel.login.Login;
import com.vidy.fake.datamodel.login.LoginResult;
import com.vidy.fake.datamodel.post.CreatePost;
import com.vidy.fake.datamodel.post.CreatePostResult;
import com.vidy.fake.datamodel.search.Datum;
import com.vidy.fake.datamodel.search.SearchResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String APP_ID = "33ea91a8-86f0-49dd-a6f5-938ba5ec701e";
    private static final String POST_ID = "postId";
    private static final String EMAIL = "pub@test.com";
    private static final String PASSWORD = "password";

    EditText searchEditText;
    Button goButton;
    EditText phraseEditText;
    Button okButton;
    EditText occurrenceEditText;
    RecyclerView searchRecyclerView;
    TextView appIdValue;
    TextView postIdValue;
    RecyclerAdapter adapter;
    ProgressBar progressBar;
    ImageView okImageView;
    TextView clipIdValue;
    EditText postIdEditText;
    Button postIdSetButton;

    private LoginResult callResult;
    private VidyService vidyService;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.edittext_search);
        goButton = findViewById(R.id.button_go);
        phraseEditText = findViewById(R.id.edittext_phrase);
        okButton = findViewById(R.id.button_ok);
        occurrenceEditText = findViewById(R.id.edittext_occurrence);
        searchRecyclerView = findViewById(R.id.recyclerview_search);
        appIdValue = findViewById(R.id.textview_appid_value);
        postIdValue = findViewById(R.id.textview_postid_value);
        progressBar = findViewById(R.id.progressbar);
        clipIdValue = findViewById(R.id.textview_clipid_value);
        okImageView = findViewById(R.id.imageview_ok);
        postIdEditText = findViewById(R.id.edittext_postid);
        postIdSetButton = findViewById(R.id.button_postid_set);

        searchRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new RecyclerAdapter(this, new ArrayList<Datum>(), new RecyclerAdapter.Listener() {
            @Override
            public void updateClipId(String clipId) {
                clipIdValue.setText(clipId);
            }
        });
        searchRecyclerView.setAdapter(adapter);

        SharedPreferences sharedPref = getSharedPreferences(
                getPackageName(), Context.MODE_PRIVATE);

        appIdValue.setText(APP_ID);
        postIdValue.setText(sharedPref.getString(POST_ID,"test01"));

        vidyService = VidyServiceFactory.getVidyService();

        Call<LoginResult> loginCall = vidyService.login(new Login(EMAIL, PASSWORD));
        loginCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Log.d(TAG, "onResponse()");
                token = response.body().getData().getToken();
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d(TAG, "onFailure()");
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = goButton.getText().toString();
                if(!text.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    Call<SearchResult> searchCall = vidyService.search(text);
                    searchCall.enqueue(new Callback<SearchResult>() {
                        @Override
                        public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                            Log.d(TAG, "onResponse()");
                            final SearchResult searchResult = response.body();
                            GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            System.out.println(gson.toJson(searchResult));
                            adapter.setItems(searchResult.getData());
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onFailure(Call<SearchResult> call, Throwable t) {
                            Log.d(TAG, "onFailure()");
                        }
                    });
                }
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cllpId = clipIdValue.getText().toString();
                final String appId = appIdValue.getText().toString();
                final String postId = postIdValue.getText().toString();
                final String phrase = phraseEditText.getText().toString();
                final String occurrence = occurrenceEditText.getText().toString();

                if(!clipIdValue.equals("") && !phrase.equals("") && !occurrence.equals("")) {
                    Call<EmbedsResult> call = vidyService.embeds(new Embed(appId, postId, cllpId, phrase+"/"+occurrence), "Bearer "+token);
                    call.enqueue(new Callback<EmbedsResult>() {
                        @Override
                        public void onResponse(Call<EmbedsResult> call, Response<EmbedsResult> response) {
                            okImageView.setVisibility(View.VISIBLE);
                            okImageView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    okImageView.setVisibility(View.INVISIBLE);
                                }
                            },3000);
                        }

                        @Override
                        public void onFailure(Call<EmbedsResult> call, Throwable t) {
                            Log.d(TAG, "onFailure()");
                        }
                    });
                }
            }
        });

        postIdSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String postId = postIdEditText.getText().toString();
                final String appId = appIdValue.getText().toString();

                if(!postId.equals("")) {
                    Call<CreatePostResult> call = vidyService.createPost(appId, postId, new CreatePost());
                    call.enqueue(new Callback<CreatePostResult>() {
                        @Override
                        public void onResponse(Call<CreatePostResult> call, Response<CreatePostResult> response) {
                            postIdValue.setText(postId);
                            SharedPreferences sharedPref = getSharedPreferences(
                                    getPackageName(), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(POST_ID, postId);
                            editor.commit();
                        }

                        @Override
                        public void onFailure(Call<CreatePostResult> call, Throwable t) {
                            Log.d(TAG, "onFailure()");
                        }
                    });
                }
            }
        });
    }


}

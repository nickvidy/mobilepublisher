package com.vidy.fake;

import com.vidy.fake.datamodel.embeds.Embed;
import com.vidy.fake.datamodel.embeds.EmbedsResult;
import com.vidy.fake.datamodel.login.Login;
import com.vidy.fake.datamodel.login.LoginResult;
import com.vidy.fake.datamodel.post.CreatePost;
import com.vidy.fake.datamodel.post.CreatePostResult;
import com.vidy.fake.datamodel.search.SearchResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jake on 3/8/2018.
 */

public interface VidyService {


    @POST("/auth/login")
    Call<LoginResult> login(@Body Login login);

    @GET("/search")
    Call<SearchResult> search(@Query("q") String searchQuery);

    @POST("/embeds")
    Call<EmbedsResult> embeds(@Body Embed embed, @Header("Authorization") String token);

    @PUT("/apps/{APPID}/content/{POSTID}")
    Call<CreatePostResult> createPost(@Path("APPID") String appId, @Path("POSTID") String postId, @Body CreatePost createPost);

}

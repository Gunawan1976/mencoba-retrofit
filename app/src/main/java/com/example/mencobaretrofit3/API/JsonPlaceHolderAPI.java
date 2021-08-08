package com.example.mencobaretrofit3.API;

import com.example.mencobaretrofit3.model.Comments;
import com.example.mencobaretrofit3.model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderAPI {

    @GET("/posts")
    Call<List<Post>>getPost(
            @Query("userId")Integer[] userId,
            @Query("_sort")String sort,
            @Query("_order")String order
    );
    @GET("/posts")
    Call<List<Post>>getPost(
            @QueryMap Map<String, String> parameters
            );

    @GET("/posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

}

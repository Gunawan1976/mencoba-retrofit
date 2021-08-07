package com.example.mencobaretrofit3.API;

import com.example.mencobaretrofit3.model.Comments;
import com.example.mencobaretrofit3.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {

    @GET("/posts")
    Call<List<Post>>getPost(
            @Query("userId")int userId,
            @Query("_sort")String sort,
            @Query("_order")String order
    );

    @GET("/posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

}

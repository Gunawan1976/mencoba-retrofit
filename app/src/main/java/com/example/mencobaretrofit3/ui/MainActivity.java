package com.example.mencobaretrofit3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mencobaretrofit3.API.JsonPlaceHolderAPI;
import com.example.mencobaretrofit3.R;
import com.example.mencobaretrofit3.model.Comments;
import com.example.mencobaretrofit3.model.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewRESULT;

    private JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewRESULT = findViewById(R.id.tv_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/posts/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        getPosts();
        //getComments();
    }

    private void getPosts() {
        Map<String,String>parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","asc");

        Call<List<Post>> call = jsonPlaceHolderAPI.getPost(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewRESULT.setText("code"+response.code());
                    return;
                }
                List<Post> posts = response.body();

                for (Post post : posts){
                    String content = "";
                    content += "ID: "+ post.getId()+"\n";
                    content += "user ID: "+ post.getuserId()+"\n";
                    content += "Title: "+ post.getTitle()+"\n";
                    content += "Text: "+ post.getText()+"\n\n";

                    textViewRESULT.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewRESULT.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comments>> call = jsonPlaceHolderAPI.getComments(3);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()){
                    textViewRESULT.setText("Code:"+response.code());
                    return;
                }
                List<Comments> comments = response.body();

                for (Comments commentts : comments){
                    String content = "";
                    content += "ID: "+ commentts.getId()+"\n";
                    content += "Post ID: "+ commentts.getPostId()+"\n";
                    content += "Name: "+ commentts.getName()+"\n";
                    content += "Email: "+ commentts.getEmail()+"\n";
                    content += "Text: "+ commentts.getText()+"\n\n";

                    textViewRESULT.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                textViewRESULT.setText(t.getMessage());
            }
        });
    }
}
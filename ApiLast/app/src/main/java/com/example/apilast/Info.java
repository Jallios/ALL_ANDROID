package com.example.apilast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apilast.models.InfoFilm;
import com.example.apilast.models.RootTrailer;
import com.example.apilast.models.Trailer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Info extends YouTubeBaseActivity {

    ImageView imageView;
    TextView name, detail, rating;
    ApiInterface apiInterface;
    int id;
    YouTubePlayerView ytPlayer;
    String api_key = "AIzaSyAWhtyr0J0wXt_bCaoRjB-UTEzVQH965pM";

    //YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.img_film_info);
        name = findViewById(R.id.film_name_info);
        detail = findViewById(R.id.film_description_info);
        rating = findViewById(R.id.ratingChange);

        ytPlayer = (YouTubePlayerView)findViewById(R.id.ytPlayer);
        apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        id = getIntent().getIntExtra("item", 0);

        Call<InfoFilm> getFilm = apiInterface.getFilmById(id);

        getFilm.enqueue(new Callback<InfoFilm>() {
            @Override
            public void onResponse(Call<InfoFilm> call, Response<InfoFilm> response) {
               if (response.isSuccessful()){
                   InfoFilm film = response.body();

                   Picasso.with(getApplicationContext()).load(film.getPosterUrl()).into(imageView);
                   name.setText(film.getNameRu());
                   detail.setText(film.getDescription());
                   rating.setText(String.valueOf(film.getRatingImdb()));
                   getVideo(response.body().kinopoiskId);
               }
            }

            @Override
            public void onFailure(Call<InfoFilm> call, Throwable t) {
                Toast.makeText(Info.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("err", t.getMessage());
            }
        });



    }

    public void onBackPressed(){
        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);
        overridePendingTransition(R.anim.up,R.anim.down);
    }

    private void getVideo(int idGet){

        Call<RootTrailer> getTrailer = apiInterface.getFilmTrailer(idGet);
        getTrailer.enqueue(new Callback<RootTrailer>() {
            @Override
            public void onResponse(Call<RootTrailer> call, Response<RootTrailer> response) {
                if (response.isSuccessful()){
                    Log.d("succses", ""+response.body().total);
                    String url;
                    for (int i = 0; i < response.body().getItems().size();i++){
                        Log.d("tr", response.body().getItems().get(i).url);
                        if (response.body().getItems().get(i).site.equals("YOUTUBE")){
                            Trailer trailer =  response.body().getItems().get(i);
                            if (trailer.getUrl().contains("v=")){
                                url = trailer.getUrl().split("v=")[1];
                                Log.d("video",url);
                                setVideo(url);
                                Log.d("video",url);

                            }
                            else if (trailer.getUrl().contains("/v/")){
                                url = trailer.getUrl().split("/v/")[1];
                                Log.d("video",url);
                                setVideo(url);
                                Log.d("video",url);
                            }
                            else if (trailer.getUrl().contains("/")){
                                url = trailer.getUrl().split("youtu.be/")[1];
                                Log.d("video",url);
                                setVideo(url);
                                Log.d("video",url);
                            }
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RootTrailer> call, Throwable t) {

            }
        });

    }

    private void setVideo(String url){
        Log.d("video",url);
        ytPlayer.initialize(api_key, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(String.valueOf(url));
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                Log.d("playerErr", youTubeInitializationResult.toString());

            }
        });
    }


}
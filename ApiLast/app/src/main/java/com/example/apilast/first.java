package com.example.apilast;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apilast.models.Film;
import com.example.apilast.models.Root;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class first extends Fragment {

    RecyclerView recyclerView;
    ApiInterface apiInterface;
    RecycleAdapter.OnStateClickListener stateClickListener;
    ProgressBar progressBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Call<Root> getList = apiInterface.getFilmListTop();

        stateClickListener = new RecycleAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Film state, int position) {

                Intent a = new Intent(getContext(), Info.class);
                a.putExtra("item", state.getFilmId());
                startActivity(a);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.up,R.anim.down);
            }
        };

        getList.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(response.isSuccessful()){
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setHasFixedSize(true);
                    ArrayList<Film> film = response.body().getFilms();
                    RecycleAdapter adapter = new RecycleAdapter(getContext(),film, stateClickListener);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycle_view_first);
        progressBar = root.findViewById(R.id.progress_first);
        apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        return root;


    }

}
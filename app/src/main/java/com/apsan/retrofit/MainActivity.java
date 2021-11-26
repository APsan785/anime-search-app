package com.apsan.retrofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apsan.retrofit.APIPackage.APIfetch;
import com.apsan.retrofit.APIPackage.AnimeObject;
import com.apsan.retrofit.APIPackage.ResultFromAPI;
import com.apsan.retrofit.RecyclerView.Adapter;
import com.apsan.retrofit.databinding.ActivityMainBinding;
import com.ramotion.foldingcell.FoldingCell;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    ArrayList<AnimeObject> animeObjectList = new ArrayList<>();
    ActivityMainBinding binding;
    APIfetch apIfetch;
    Adapter adapter;
    OkHttpClient client;
    Request request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        String[] container = {"Anime", "Manga", "Character", "Person",};

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.spinner_list, container);
        aa.setDropDownViewResource(R.layout.spinner_list);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(aa);

        adapter = new Adapter(animeObjectList, this);
        adapter.setonItemClickListner(new Adapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AnimeObject object = animeObjectList.get(position);
                View contentview = ((FoldingCell) binding.recyclerView.getLayoutManager().findViewByPosition(position)).getChildAt(0);
                View titleView = ((FoldingCell) binding.recyclerView.getLayoutManager().findViewByPosition(position)).getChildAt(1);

                Log.d(TAG, Integer.toString(titleView.getHeight()));
                ((FoldingCell) binding.recyclerView.getLayoutManager().findViewByPosition(position)).toggle(false);
                Log.d(TAG, Integer.toString(contentview.getHeight()));
       /*         View view = binding.recyclerView.getLayoutManager().findViewByPosition(position);
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                float height = view.getHeight();
                Log.d(TAG, view.getHeight() +"aaaa");*/
            }
        });
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request newRequest = originalRequest.newBuilder()
                            .header("x-rapidapi-key", "2588996a38msh93c4dc82410e25ap1dd3a1jsnd5ffa21e63a5")
                            .header("x-rapidapi-host", "jikan1.p.rapidapi.com")
                            .build();
                    return chain.proceed(newRequest);
                })
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jikan1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apIfetch = retrofit.create(APIfetch.class);


        binding.searchIcon.setOnClickListener(v -> {
            String search_group = binding.spinner.getSelectedItem().toString().toLowerCase();
            String query = binding.searchBox.getText().toString();
            getResult(search_group, query, v);
        });

        binding.searchBox.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String search_group = binding.spinner.getSelectedItem().toString().toLowerCase();
                String query = binding.searchBox.getText().toString();
                getResult(search_group, query,v);
                return true;
            }
            return false;
        });
    }

    private void getResult(String search_group, String query, View view) {
        hideKeyboard(view);
       binding.progressBar.setVisibility(View.VISIBLE);
        Call<ResultFromAPI> call = apIfetch.getResult(search_group, query);
        call.enqueue(new Callback<ResultFromAPI>() {
            @Override
            public void onResponse(Call<ResultFromAPI> call, Response<ResultFromAPI> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, Integer.toString(response.code()), Toast.LENGTH_LONG).show();
                    binding.progressBar.setVisibility(View.INVISIBLE);

                    return;
                }
                if (search_group.equals("anime")) {
                    ResultFromAPI result = response.body();
                    animeObjectList.clear();
                    animeObjectList.addAll(result.getResults());
                    adapter.notifyDataSetChanged();
                    binding.recyclerView.smoothScrollToPosition(0);
                    binding.progressBar.setVisibility(View.INVISIBLE);
                } else
                    Toast.makeText(MainActivity.this, "Not finished yet...", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ResultFromAPI> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                binding.progressBar.setVisibility(View.INVISIBLE);

            }
        });

    }
/*
    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("animeObjectList",animeObjectList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        animeObjectList = savedInstanceState.getParcelableArrayList("animeObjectList");
        adapter.notifyDataSetChanged();
    }
*/
    public void openLink(View view) {
        TextView urlResult = findViewById(R.id.url_result);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlResult.getText().toString())));
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }

}

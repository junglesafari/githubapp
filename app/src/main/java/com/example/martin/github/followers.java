package com.example.martin.github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class followers extends AppCompatActivity {
    ArrayList<String> datatoshow;
    ListView listView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_followers );
        listView = findViewById( R.id.listviewfollowers );
        progressBar=findViewById( R.id.progressbar1 );
        datatoshow = new ArrayList<>();


        final Intent intent = getIntent();
        String search = intent.getStringExtra( Main2Activity.DATA_SEND_KEY );


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.github.com/users/" ).addConverterFactory( GsonConverterFactory.create() );

        Retrofit retrofit = builder.build();

        followersinterface servise = retrofit.create( followersinterface.class );

        Call<ArrayList<pojoclassforfollower>> call = servise.getfollowers( search );

        call.enqueue( new Callback<ArrayList<pojoclassforfollower>>() {
            @Override
            public void onResponse(Call<ArrayList<pojoclassforfollower>> call, Response<ArrayList<pojoclassforfollower>> response) {
                ArrayList<pojoclassforfollower> followername = response.body();
                datatoshow.clear();
                for (int i = 0; i < followername.size(); i++) {
                    datatoshow.add( followername.get( i ).login );
                }
                ArrayAdapter adapter = new ArrayAdapter<>( followers.this, android.R.layout.simple_list_item_1, datatoshow );
                listView.setAdapter( adapter );
                progressBar.setVisibility( View.INVISIBLE );
                listView.setVisibility( View.VISIBLE );
            }

            @Override
            public void onFailure(Call<ArrayList<pojoclassforfollower>> call, Throwable t) {

            }
        } );


        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1=new Intent( getApplicationContext(),MainActivity.class );
                intent1.putExtra( Main2Activity.DATA_SEND_KEY,datatoshow.get( i ) );
               startActivity( intent1 );
            }
        } );


    }
}

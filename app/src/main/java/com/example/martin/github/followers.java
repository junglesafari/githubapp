package com.example.martin.github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    ArrayList<pojoclassforfollower> datatoshow;
   // ListView listView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_followers );
     //   listView = findViewById( R.id.listviewfollowers );
       recyclerView=findViewById( R.id.recyclerviewfollowers );
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
                datatoshow.clear();
                 datatoshow = response.body();
                 followersrecycleradaptor adapter=new followersrecycleradaptor( followers.this, datatoshow, new repoclicklistener() {
                    @Override
                    public void onRepoClick(View view, int position) {
                        Intent intent1=new Intent( followers.this,MainActivity.class );
                        intent1.putExtra( Main2Activity.DATA_SEND_KEY, datatoshow.get( position ).login );
                        startActivity( intent1 );
                    }
                } );
                LinearLayoutManager manager=new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                recyclerView.addItemDecoration(new DividerItemDecoration(followers.this,DividerItemDecoration.VERTICAL));
               recyclerView.setLayoutManager( manager );
                recyclerView.setAdapter( adapter );
                progressBar.setVisibility( View.INVISIBLE );
                recyclerView.setVisibility( View.VISIBLE );

            }

            @Override
            public void onFailure(Call<ArrayList<pojoclassforfollower>> call, Throwable t) {

            }
        } );


    }
}

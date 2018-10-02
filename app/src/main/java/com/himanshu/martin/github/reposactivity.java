package com.himanshu.martin.github;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class reposactivity extends AppCompatActivity {
    //ArrayList<pojoclassforrepo> arrayList;
    ArrayList<pojoclassforrepo> data;
   // ListView listView;
    RecyclerView recyclerView;
    public static final String URL_SEND_KEY = "urlsendkey";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reposactivity );
        data = new ArrayList<>();
       // listView = findViewById( R.id.listview );
       recyclerView=findViewById( R.id.recyclerview );
        progressBar =findViewById( R.id.progressbar3 );



//        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(),webviewforrepo.class);
//                //  intent.setAction(Intent.ACTION_VIEW);
//                //Uri uri = Uri.parse(data.get( i ).html_url);
//                intent.putExtra( URL_SEND_KEY, data.get( i ).html_url );
//                startActivity( intent );
//            }
//        } );
        Intent intent = getIntent();
        String username = intent.getStringExtra( Main2Activity.DATA_SEND_KEY );
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.github.com/users/" ).addConverterFactory( GsonConverterFactory.create() );
        Retrofit retrofit = builder.build();
        reposearchinterface service = retrofit.create( reposearchinterface.class );
        Call<ArrayList<pojoclassforrepo>> call = service.getreporesult( username, "repos" );
        call.enqueue( new Callback<ArrayList<pojoclassforrepo>>() {
            @Override
            public void onResponse(Call<ArrayList<pojoclassforrepo>> call, Response<ArrayList<pojoclassforrepo>> response) {
                data.clear();
                data = response.body();
                Gson gson = new Gson();
                String datatosend = gson.toJson( data );
                Log.d( "mainactivity2", "string " + datatosend );
                //repolistadaptor adaptor = new repolistadaptor( getApplicationContext(), data );
               // listView.setAdapter( adaptor );
                repolistrecycleradaptor adaptor=new repolistrecycleradaptor( getApplicationContext(), data, new repoclicklistener() {
                    @Override
                    public void onRepoClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(),webviewforrepo.class);
                  intent.setAction(Intent.ACTION_VIEW);

                intent.putExtra( URL_SEND_KEY, data.get( position ).html_url );
                startActivity( intent );
                    }
                } );
                recyclerView.setAdapter( adaptor );
                LinearLayoutManager manager=new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                recyclerView.addItemDecoration(new DividerItemDecoration(reposactivity.this,DividerItemDecoration.VERTICAL));

                 recyclerView.setLayoutManager( manager );
                progressBar.setVisibility( View.GONE );
                recyclerView.setVisibility( View.VISIBLE );
                //  listView.setVisibility( View.VISIBLE );

            }

            @Override
            public void onFailure(Call<ArrayList<pojoclassforrepo>> call, Throwable t) {

            }
        } );


    }
}

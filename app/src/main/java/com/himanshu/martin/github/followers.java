package com.himanshu.martin.github;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class followers extends AppCompatActivity {
    ArrayList<pojoclassforfollower> datatoshow;
   boolean isscrolling=false;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    int currentintem;
    int totleitem;
    int scrolleditem;
    followersrecycleradaptor adapter;
    LinearLayoutManager manager;
    int pagenumber=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_followers );
       recyclerView=findViewById( R.id.recyclerviewfollowers );
        progressBar=findViewById( R.id.progressbar1 );
        datatoshow = new ArrayList<>();


        final Intent intent = getIntent();
        final String search = intent.getStringExtra( Main2Activity.DATA_SEND_KEY );

        fetchdata( pagenumber,search );
        recyclerView.addOnScrollListener( new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged( recyclerView, newState );

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isscrolling = true;
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled( recyclerView, dx, dy );
                //the total number of items that are visible to screen can be obtained by this method
                currentintem= manager.getChildCount();
                //the total number of items in adaptor  can be obtain by this method;
                totleitem=manager.getItemCount();
                // the total number of item that are scrolled up can be obtained by this method
                scrolleditem=manager.findFirstVisibleItemPosition();


                if(isscrolling&&(currentintem+scrolleditem)==totleitem){
                    fetchdata(pagenumber+1,search  );
                }else{
                    Toast.makeText( followers.this,"else block ",Toast.LENGTH_SHORT ).show();
                }


            }
        } );
    }

    void fetchdata(final int pagenumber, String search){
            final int pagenumber1=pagenumber;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.github.com/users/" ).addConverterFactory( GsonConverterFactory.create() );

        Retrofit retrofit = builder.build();

        followersinterface servise = retrofit.create( followersinterface.class );
        String url=search+"/followers"+"?page="+pagenumber1;
        Call<ArrayList<pojoclassforfollower>> call = servise.getfollowers( url );

        call.enqueue( new Callback<ArrayList<pojoclassforfollower>>() {
            @Override
            public void onResponse(Call<ArrayList<pojoclassforfollower>> call, Response<ArrayList<pojoclassforfollower>> response) {
                if(pagenumber1==1){datatoshow.clear();}
                datatoshow.addAll(  response.body()) ;
                Log.d( "followers",datatoshow+"" );
                if(datatoshow==null){
                    isscrolling=false;
                }
                adapter=new followersrecycleradaptor( followers.this, datatoshow, new repoclicklistener() {
                    @Override
                    public void onRepoClick(View view, int position) {
                        Intent intent1=new Intent( followers.this,MainActivity.class );
                        intent1.putExtra( Main2Activity.DATA_SEND_KEY, datatoshow.get( position ).login );
                        startActivity( intent1 );
                    }
                } );
                manager=new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL,false);
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

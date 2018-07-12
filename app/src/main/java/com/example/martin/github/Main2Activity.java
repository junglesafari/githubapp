package com.example.martin.github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Main2Activity extends AppCompatActivity {
    Button searchbutton;
    EditText searchtext;
    ImageView gitimage;
    String search;
   // ProgressBar progressBar;
    public static final String DATA_SEND_KEY = "jsondatasend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        searchbutton = findViewById( R.id.search_button );
        searchtext = findViewById( R.id.searchtext );
        gitimage = findViewById( R.id.imageView1 );
       // progressBar = findViewById( R.id.progressbar );
    }


    public void search(View view) {
//        //progressBar.setVisibility( View.VISIBLE );
//        searchbutton.setVisibility( View.GONE );
//        gitimage.setVisibility( View.GONE );
//        searchtext.setVisibility( View.GONE );
        search = searchtext.getText().toString().trim();
        Intent intent=new Intent( getApplicationContext(),MainActivity.class );
        intent.putExtra( DATA_SEND_KEY,search );
        startActivity( intent );

//        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.github.com/" ).addConverterFactory( GsonConverterFactory.create() );
//
//        Retrofit retrofit = builder.build();
//
//        usersearchinterface service = retrofit.create( usersearchinterface.class );
//
//        Call<userinformationclass> call = service.getresult( search );
//
//        call.enqueue( new Callback<userinformationclass>() {
//            @Override
//            public void onResponse(Call<userinformationclass> call, Response<userinformationclass> response) {
//                //  Toast.makeText( Main2Activity.this,"something went wrong  "+search,Toast.LENGTH_SHORT ).show();
//                userinformationclass data = response.body();
//                Log.d( "mainactivity2", "data" + data );
//                Gson gson = new Gson();
//                String datatosend = gson.toJson( data );
//                Log.d( "mainactivity2", "string " + datatosend );
//
//
//                if (!datatosend.equals( "null" )) {
//                    Intent intent = new Intent( getApplicationContext(), MainActivity.class );
//                    intent.putExtra( DATA_SEND_KEY, datatosend );
//                    startActivity( intent );
//                    Log.d( "mainactivity2", "string 2" + datatosend );
//                } else {
//                    Intent intent = new Intent( getApplicationContext(), pagenotfound.class );
//                    startActivity( intent );
//                    Log.d( "mainactivity2", "string 3 " + datatosend );
//
//                }
//                progressBar.setVisibility( View.GONE );
//                searchbutton.setVisibility( View.VISIBLE );
//                gitimage.setVisibility( View.VISIBLE );
//                searchtext.setVisibility( View.VISIBLE );
//            }
//
//            @Override
//            public void onFailure(Call<userinformationclass> call, Throwable t) {
//                Toast.makeText( getApplicationContext(), "user not found", Toast.LENGTH_SHORT ).show();
//            }
//        } );

    }
}

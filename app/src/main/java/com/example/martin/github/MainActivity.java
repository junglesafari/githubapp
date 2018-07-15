package com.example.martin.github;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ScrollView rootlayout;
    EditText searchtext;
    TextView username;
    ImageView userImage;
    TextView follower;
    TextView following;
    TextView gitrepos;
    TextView mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mail = findViewById( R.id.textView3 );
        gitrepos = findViewById( R.id.textView );
        following = findViewById( R.id.textView7 );
        follower = findViewById( R.id.textView6 );
        userImage = findViewById( R.id.avatarimage );
        username = findViewById( R.id.textView4 );
        searchtext = findViewById( R.id.searchbar );
        progressBar = findViewById( R.id.progressbar );
        rootlayout = findViewById( R.id.rootlayout );
        progressBar.setVisibility( View.VISIBLE );
        rootlayout.setVisibility( View.GONE );


        final Intent intent = getIntent();
        final String search = intent.getStringExtra( Main2Activity.DATA_SEND_KEY );

        follower.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (follower.getText().toString().equals( "0   Followers" )) {
                    Intent intent1 = new Intent( getApplicationContext(), lol.class );
                    startActivity( intent1 );
                } else {
                    Intent intent1 = new Intent( getApplicationContext(), followers.class );
                    intent1.putExtra( Main2Activity.DATA_SEND_KEY, search );
                    startActivity( intent1 );
                }
            }
        } );


        gitrepos.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = gitrepos.getText().toString().trim();
                if (text.equals( "Git repositories" )) {
                    //do nothing
                } else {
                    Intent intent = new Intent( getApplicationContext(), reposactivity.class );
                    intent.putExtra( Main2Activity.DATA_SEND_KEY, search );
                    startActivity( intent );
                }
            }
        } );


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl( "https://api.github.com/" ).addConverterFactory( GsonConverterFactory.create() );

        Retrofit retrofit = builder.build();

        usersearchinterface service = retrofit.create( usersearchinterface.class );

        Call<userinformationclass> call = service.getresult( search );

        call.enqueue( new Callback<userinformationclass>() {
            @Override
            public void onResponse(Call<userinformationclass> call, Response<userinformationclass> response) {
                  userinformationclass data = response.body();
                Log.d( "mainactivity2", "data" + data );
                Gson gson = new Gson();
                String datatosend = gson.toJson( data );
                Log.d( "mainactivity2", "string " + datatosend );

                if (data != null) {
                    progressBar.setVisibility( View.GONE );
                    rootlayout.setVisibility( View.VISIBLE );
                    Log.d( "mainactivity2", "string 2" + datatosend );
                    searchtext.setText( data.html_url );
                    username.setText( data.login );
                    follower.setText( data.followers + "   Followers" );
                    following.setText( data.following + "  Following" );
                    gitrepos.setText( data.public_repos + "Repos" );


                    Picasso.with( MainActivity.this ).load( String.valueOf( data.avatar_url ) ).fit().placeholder( R.drawable.noimagefound ).into( userImage );
                    if (data.email != null) {
                        mail.setText( data.email );
                    } else {
                        mail.setText( "Private" );
                    }
                    addhistroydatabasse( data.login,data.avatar_url );
          } else {
                    Intent intent = new Intent( getApplicationContext(), pagenotfound.class );
                    startActivity( intent );
                    finish();
                    Log.d( "mainactivity2", "string 3 " + data );

                }

            }

            @Override
            public void onFailure(Call<userinformationclass> call, Throwable t) {
                Toast.makeText( getApplicationContext(), "user not found", Toast.LENGTH_SHORT ).show();
            }
        } );


    }
    private void addhistroydatabasse(String loginname, URL avatarurl){
        gitopenhelper openhelper=gitopenhelper.getInstance( MainActivity.this );
        SQLiteDatabase database=openhelper.getWritableDatabase();
        ContentValues values=new ContentValues(  );
        values.put( contractclass.git.COLUMN_NAME,loginname );
        values.put( contractclass.git.COLUMN_URL, String.valueOf( avatarurl ) );
        database.insert( contractclass.git.TABLE_NAME,null,values );

    }
}

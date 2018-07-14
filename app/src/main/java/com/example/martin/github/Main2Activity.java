package com.example.martin.github;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    public static final String DATA_SEND_KEY = "jsondatasend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        searchbutton = findViewById( R.id.search_button );
        searchtext = findViewById( R.id.searchtext );
        gitimage = findViewById( R.id.imageView1 );
    }


    public void search(View view) {
        search = searchtext.getText().toString().trim();
        Intent intent=new Intent( getApplicationContext(),MainActivity.class );
        intent.putExtra( DATA_SEND_KEY,search );
        startActivity( intent );
      gitopenhelper openhelper=gitopenhelper.getInstance( Main2Activity.this );
        SQLiteDatabase database=openhelper.getWritableDatabase();
        ContentValues values=new ContentValues(  );
        values.put( contractclass.git.COLUMN_NAME,search );
        database.insert( contractclass.git.TABLE_NAME,null,values );
       searchtext.setText( "" );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate( R.menu.main_menu,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent=new Intent( getApplicationContext(),historyactivity.class );
        startActivity( intent );
        return super.onOptionsItemSelected( item );
    }
}

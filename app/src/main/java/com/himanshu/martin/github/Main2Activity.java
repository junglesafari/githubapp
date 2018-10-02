package com.himanshu.martin.github;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
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
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONObject;

import java.util.ArrayList;

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
    MaterialSearchView materialSearchView;
    public  static final int IMAGE_REQUEST_CODE=1;
    public  static final int IMAGE_RESULT_CODE=2;
    public static final String DATA_SEND_KEY = "jsondatasend";
    public static final String IMAGE_URL_SEND_KEY="imagesend";
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        searchbutton = findViewById( R.id.search_button );
        searchtext = findViewById( R.id.searchtext );
        gitimage = findViewById( R.id.imageView1 );
//        materialSearchView=findViewById( R.id.searchTextView );
//        gitopenhelper openhelper = gitopenhelper.getInstance( Main2Activity.this );
//        SQLiteDatabase database = openhelper.getReadableDatabase();
//        Cursor cursor = database.query( contractclass.git.TABLE_NAME, null, null, null, null, null, null, null );
//        while (cursor.moveToNext()) {
//            String loginname = cursor.getString( cursor.getColumnIndex( contractclass.git.COLUMN_NAME ) );
//            String avtarurl = cursor.getString( cursor.getColumnIndex( contractclass.git.COLUMN_URL ) );
//            Log.d( "historyactivity", "image url " + avtarurl );
//            pojoclassforfollower pojoclassforfollower = new pojoclassforfollower( loginname, avtarurl );
//            arrayList.add( pojoclassforfollower.getLogin() );
//        }
//      materialSearchView.setSuggestions( arrayList );
//
//        materialSearchView.setOnQueryTextListener( new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        } );
//
    }


    public void search(View view) {
        search = searchtext.getText().toString().trim();
        Intent intent=new Intent( getApplicationContext(),MainActivity.class );
        intent.putExtra( DATA_SEND_KEY,search );
        startActivity( intent );
        searchtext.setText( "" );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate( R.menu.main_menu,menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.history){
        Intent intent=new Intent( getApplicationContext(),historyactivity.class );
        startActivity( intent );}
        if(item.getItemId()==R.id.aboutus){
            Intent intent=new Intent( getApplicationContext(),aboutusactivity.class );
            startActivity( intent );
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
     if(requestCode==IMAGE_REQUEST_CODE){
         if(resultCode==IMAGE_RESULT_CODE){
             gitopenhelper openhelper=gitopenhelper.getInstance( Main2Activity.this );
             SQLiteDatabase database=openhelper.getWritableDatabase();
             ContentValues values=new ContentValues(  );
             values.put( contractclass.git.COLUMN_NAME,search );
             String url=data.getStringExtra(IMAGE_URL_SEND_KEY );
             Log.d( "Main2Activity",url );
             values.put( contractclass.git.COLUMN_URL,url );
             database.insert( contractclass.git.TABLE_NAME,null,values );


         }
     }





        super.onActivityResult( requestCode, resultCode, data );
    }
}

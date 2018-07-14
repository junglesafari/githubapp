package com.example.martin.github;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class historyactivity extends AppCompatActivity {
ListView listView;
ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_historyactivity );
        arrayList=new ArrayList<>(  );
listView=findViewById( R.id.historylistview );
gitopenhelper openhelper=gitopenhelper.getInstance( historyactivity.this );
        SQLiteDatabase database=openhelper.getReadableDatabase();
        Cursor cursor=database.query( contractclass.git.TABLE_NAME,null,null,null,null,null,null,null );
      while (cursor.moveToNext()){
          arrayList.add( cursor.getString( cursor.getColumnIndex( contractclass.git.COLUMN_NAME ) ));
      }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),R.layout.layoutforhistory,arrayList);
      listView.setAdapter( adapter );
    listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent=new Intent( getApplicationContext(),MainActivity.class );
            intent.putExtra( Main2Activity.DATA_SEND_KEY,arrayList.get( i ) );
            startActivity( intent );
        }
    } );
    }




}

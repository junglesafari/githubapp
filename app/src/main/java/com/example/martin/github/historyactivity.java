package com.example.martin.github;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class historyactivity extends AppCompatActivity {
    // ListView listView;
    RecyclerView recyclerView;
    ArrayList<pojoclassforfollower> arrayList;
    followersrecycleradaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_historyactivity );
        arrayList = new ArrayList<>();
        recyclerView = findViewById( R.id.historylistview );
        gitopenhelper openhelper = gitopenhelper.getInstance( historyactivity.this );
        SQLiteDatabase database = openhelper.getReadableDatabase();
        Cursor cursor = database.query( contractclass.git.TABLE_NAME, null, null, null, null, null, null, null );
        while (cursor.moveToNext()) {
            String loginname = cursor.getString( cursor.getColumnIndex( contractclass.git.COLUMN_NAME ) );
            String avtarurl = cursor.getString( cursor.getColumnIndex( contractclass.git.COLUMN_URL ) );
            Log.d( "historyactivity", "image url " + avtarurl );
            pojoclassforfollower pojoclassforfollower = new pojoclassforfollower( loginname, avtarurl );
            arrayList.add( pojoclassforfollower );
        }
        adaptor = new followersrecycleradaptor( historyactivity.this, arrayList, new repoclicklistener() {
            @Override
            public void onRepoClick(View view, int position) {
                Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                intent.putExtra( Main2Activity.DATA_SEND_KEY, arrayList.get( position ).getLogin() );
                startActivity( intent );
            }
        } );
        LinearLayoutManager manager = new LinearLayoutManager( getApplicationContext(), LinearLayoutManager.VERTICAL, false );
        recyclerView.addItemDecoration( new DividerItemDecoration( historyactivity.this, DividerItemDecoration.VERTICAL ) );
        recyclerView.setAdapter( adaptor );
        recyclerView.setLayoutManager( manager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper( new ItemTouchHelper.SimpleCallback( ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder fromVH, @NonNull RecyclerView.ViewHolder toVH) {
                int from = fromVH.getAdapterPosition();
                int to = toVH.getAdapterPosition();

                pojoclassforfollower post = arrayList.get( from );
                arrayList.remove( from );
                arrayList.add( to, post );

                adaptor.notifyItemMoved( from, to );
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                gitopenhelper gitopenhelper = com.example.martin.github.gitopenhelper.getInstance( getApplicationContext() );
                SQLiteDatabase database1 = gitopenhelper.getWritableDatabase();
                String[] selectargument = {arrayList.get( viewHolder.getAdapterPosition() ).getLogin()};
                database1.delete( contractclass.git.TABLE_NAME, contractclass.git.COLUMN_NAME + " = ?", selectargument );
                arrayList.remove( viewHolder.getAdapterPosition() );
                adaptor.notifyItemRemoved( viewHolder.getAdapterPosition() );

            }
        } );

        itemTouchHelper.attachToRecyclerView( recyclerView );


    }


}

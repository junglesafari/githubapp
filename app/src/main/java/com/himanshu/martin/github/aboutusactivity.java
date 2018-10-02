package com.himanshu.martin.github;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class aboutusactivity extends AppCompatActivity {
     CardView getdiscount;
     ImageView p1;
     ImageView p2;
     ImageView p3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_aboutusactivity );
        p1=findViewById( R.id.p1 );
        p2=findViewById( R.id.p2 );
        p3=findViewById( R.id.p3 );


        getdiscount=findViewById( R.id.gd );
        getdiscount.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( aboutusactivity.this,getdiscountactivity.class );
                Pair[] pairs=new Pair[3];
                pairs[0]=new Pair<View,String>(p1,"imagetransition");
                pairs[1]=new Pair<View,String>(p2,"cnt");
                pairs[2]=new Pair <View,String>(p3,"rct");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation( aboutusactivity.this,pairs );




                startActivity( intent,options.toBundle() );
            }
        } );









    }










}

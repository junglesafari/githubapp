package com.himanshu.martin.github;

import android.content.Intent;
import android.net.Uri;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.URI;

public class getdiscountactivity extends AppCompatActivity {
   TextView cnbutton;
    TextView rcbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_getdiscountactivity );
        cnbutton=findViewById( R.id.cnbutton );
        rcbutton=findViewById( R.id.rcbutton );

        cnbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(  );
                intent.setAction( Intent.ACTION_VIEW );
                Uri uri=Uri.parse("https://www.codingninjas.in/"  );
                intent.setData( uri );
                startActivity( intent );
            }
        } );
        rcbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(  );
                intent.setAction( Intent.ACTION_VIEW );
                Uri uri=Uri.parse("https://www.facebook.com/rcubedclub/"  );
                intent.setData( uri );
                startActivity( intent );
            }
        } );






    }
}

package com.example.martin.github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class pagenotfound extends AppCompatActivity {
ImageView pagenotfound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pagenotfound );

   pagenotfound=findViewById( R.id.imageView2 );
    }
}

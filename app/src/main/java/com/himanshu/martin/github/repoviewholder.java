package com.himanshu.martin.github;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class repoviewholder extends RecyclerView.ViewHolder {

    TextView repository;
    TextView repourl;
    TextView creationdate;
    TextView updationdate;
    TextView pusheddate;
    TextView watcherscount;
    TextView language;
    View itemview;



    public repoviewholder(@NonNull View itemView) {
        super( itemView );
         repository=itemView.findViewById( R.id.textView2 );
        repourl=itemView.findViewById( R.id.textView5 );
         creationdate=itemView.findViewById( R.id.textView9 );
         updationdate=itemView.findViewById( R.id.textView11 );
         pusheddate=itemView.findViewById( R.id.textView13 );
        watcherscount=itemView.findViewById( R.id.textView15 );
        language=itemView.findViewById( R.id.textView17 );
        this.itemview=itemView;
    }
}

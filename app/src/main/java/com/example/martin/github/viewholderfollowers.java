package com.example.martin.github;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class viewholderfollowers extends RecyclerView.ViewHolder {

 TextView followername;
    CircleImageView circleImageView;
    View itemview;
    public viewholderfollowers(@NonNull View itemView) {
        super( itemView );
        this.itemview=itemView;
        followername =itemView.findViewById( R.id.followertextview );
        circleImageView=itemView.findViewById( R.id.circularimageview );
    }
}

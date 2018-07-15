package com.example.martin.github;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class followersrecycleradaptor extends RecyclerView.Adapter<viewholderfollowers> {
    private ArrayList<pojoclassforfollower> arrayList;

    private Context context;
    private repoclicklistener listener;
    private LayoutInflater inflater;

    public followersrecycleradaptor(Context context, ArrayList<pojoclassforfollower> arrayList, repoclicklistener repoclicklistener) {
        this.arrayList = arrayList;
        this.context=context;
        listener=repoclicklistener;
    }


    @NonNull
    @Override
    public viewholderfollowers onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        inflater= (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        assert inflater != null;
        View output=inflater.inflate( R.layout.layoutforfollowers,viewGroup,false );
        return new viewholderfollowers(output);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholderfollowers viewholderfollowers, final int i) {
             viewholderfollowers.followername.setText( arrayList.get( i ).getLogin() );
        Picasso.with( context )
                .load( String.valueOf( arrayList.get( i ).getAvatar_url() ) )
                .fit()
                .placeholder( R.drawable.noimagefound )
                .into( viewholderfollowers.circleImageView );
        viewholderfollowers.itemview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRepoClick( view,viewholderfollowers.getAdapterPosition() );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return  this.arrayList.size();
    }
}

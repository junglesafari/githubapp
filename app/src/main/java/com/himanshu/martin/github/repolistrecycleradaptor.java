package com.himanshu.martin.github;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class repolistrecycleradaptor extends RecyclerView.Adapter<repoviewholder>  {

ArrayList<pojoclassforrepo> arrayList;
Context context;
LayoutInflater inflater;
repoclicklistener listener;
    public repolistrecycleradaptor(Context context,ArrayList<pojoclassforrepo> arrayList,repoclicklistener clicklistener) {
        this.arrayList=arrayList;
        this.context=context;
        this.listener=clicklistener;
    }

    @NonNull
    @Override
    public repoviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
inflater=(LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );
        View rowlayout=inflater.inflate( R.layout.layoutforrepos,viewGroup,false );
        return new repoviewholder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final repoviewholder repoviewholder, int i) {

        pojoclassforrepo data=arrayList.get( i );
        repoviewholder.repository.setText( data.getName() );
        repoviewholder.repourl.setText( data.getHtml_url() );
        repoviewholder.watcherscount.setText( data.getWatchers_count() );
        repoviewholder.language.setText( data.getLanguage() );
        repoviewholder.pusheddate.setText( data.getPushed_at() );
        repoviewholder.creationdate.setText( data.getCreated_at() );
        repoviewholder.updationdate.setText( data.getUpdated_at() );
        repoviewholder.itemview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRepoClick( view,repoviewholder.getAdapterPosition() );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

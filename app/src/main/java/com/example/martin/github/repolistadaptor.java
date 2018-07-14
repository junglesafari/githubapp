package com.example.martin.github;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Callback;

public class repolistadaptor extends ArrayAdapter {
    ArrayList<pojoclassforrepo> item;
LayoutInflater inflater;
    public repolistadaptor(@NonNull Context context, ArrayList<pojoclassforrepo> arrayList) {
        super( context,0,arrayList);
        inflater=(LayoutInflater)context.getSystemService( context.LAYOUT_INFLATER_SERVICE );
        this.item=arrayList;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View output=convertView;
      if(output==null){
          output=inflater.inflate( R.layout.layoutforrepos,parent,false );
          TextView repository=output.findViewById( R.id.textView2 );
          TextView repourl=output.findViewById( R.id.textView5 );
          TextView creationdate=output.findViewById( R.id.textView9 );
          TextView updationdate=output.findViewById( R.id.textView11 );
          TextView pusheddate=output.findViewById( R.id.textView13 );
          TextView watcherscount=output.findViewById( R.id.textView15 );
          TextView language=output.findViewById( R.id.textView17 );
       // repoviewholder viewholader=new repoviewholder();
//        viewholader.repository=repository;
//        viewholader.repourl=repourl;
//        viewholader.creationdate=creationdate;
//        viewholader.updationdate=updationdate;
//        viewholader.language=language;
//        viewholader.pusheddate=pusheddate;
//        viewholader.watcherscount=watcherscount;
//        output.setTag( viewholader );

        }
   repoviewholder viewholader=(repoviewholder) output.getTag();
   pojoclassforrepo data=item.get( position );
   viewholader.repository.setText( data.getName() );
   viewholader.repourl.setText( data.getHtml_url() );
   viewholader.watcherscount.setText( data.getWatchers_count() );
   viewholader.language.setText( data.getLanguage() );
   viewholader.pusheddate.setText( data.getPushed_at() );
   viewholader.creationdate.setText( data.getCreated_at() );
   viewholader.updationdate.setText( data.getUpdated_at() );



        return output;
    }
}

package com.example.martin.github;

import android.view.View;

public interface repoclicklistener {
    void onRepoClick(View view,int position);
    //we use same listener for both repo and followers activity;
}

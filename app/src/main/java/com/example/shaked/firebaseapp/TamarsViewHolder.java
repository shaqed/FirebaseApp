package com.example.shaked.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TamarsViewHolder extends RecyclerView.ViewHolder {


    public TextView header;
    public TextView subHeader;
    public TextView numberTextView; // = null

    public TamarsViewHolder(@NonNull View rowTemplateView) {
        super(rowTemplateView);

        header = rowTemplateView.findViewById(R.id.rowBigHeader);
        subHeader = rowTemplateView.findViewById(R.id.rowSubHeader);
        this.numberTextView = rowTemplateView.findViewById(R.id.rowNumber);

    }
}

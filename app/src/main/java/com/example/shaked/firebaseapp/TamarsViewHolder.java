package com.example.shaked.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class TamarsViewHolder extends RecyclerView.ViewHolder {


    public TextView header;
    public TextView subHeader;
    public CheckBox checkBox;

    public TamarsViewHolder(@NonNull View rowTemplateView) {
        super(rowTemplateView);

        header = rowTemplateView.findViewById(R.id.rowBigHeader);
        subHeader = rowTemplateView.findViewById(R.id.rowSubHeader);
        this.checkBox = rowTemplateView.findViewById(R.id.rowCheckbox);

    }
}

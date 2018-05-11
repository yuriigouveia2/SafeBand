package com.example.yurig.aparencia;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yurig.aparencia.R;

import java.util.ArrayList;

/**
 * Created by yurig on 06/04/2018.
 */

public class CustomListAdapter extends ArrayAdapter<String> {

    private Activity context;
    private ArrayList itemName;
    private Integer imgId;
    public CustomListAdapter(Activity context, ArrayList itemName, Integer imgId) {
        super(context, R.layout.list, itemName);

        this.context = context;
        this.itemName = itemName;
        this.imgId = imgId;
    }

    public View getView(int posicao, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imagem);

        txtTitle.setText(itemName.indexOf(posicao));
        imageView.setImageResource(imgId);
        return rowView;
    }
}

package com.fefeyo.kyotoibw.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fefeyo.kyotoibw.R;
import com.fefeyo.kyotoibw.items.Sake;

/**
 * Created by fefe on 2017/03/04.
 */

public class SakeListAdapter extends ArrayAdapter<Sake>{

    private LayoutInflater mInflater;

    public SakeListAdapter(Context context) {
        super(context, 0);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) convertView = mInflater.inflate(R.layout.sake_list_row, null);

        Sake sake = getItem(position);
        TextView name = (TextView)convertView.findViewById(R.id.name);
        name.setText(sake.getName());
        TextView cost = (TextView)convertView.findViewById(R.id.cost);
        cost.setText("¥" + Integer.toString(sake.getCost()));

        return convertView;
    }

}

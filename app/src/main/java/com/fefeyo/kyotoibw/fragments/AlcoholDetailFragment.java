package com.fefeyo.kyotoibw.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fefeyo.kyotoibw.R;

import java.util.List;

/**
 * Created by fefe on 2017/03/04.
 */

public class AlcoholDetailFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle arguments = getArguments();
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.sake_detail_dialog, null);
        TextView name = (TextView)content.findViewById(R.id.name);
        name.setText(arguments.getString("name"));
        TextView prefecture = (TextView)content.findViewById(R.id.pref);
        prefecture.setText(arguments.getString("prefecture"));

        boolean[] type = arguments.getBooleanArray("type");
        ImageView pure = (ImageView)content.findViewById(R.id.pure);
        pure.setAlpha(.1f);
        if(type[0]) pure.setAlpha(1.0f);
        ImageView nopure = (ImageView)content.findViewById(R.id.nopure);
        nopure.setAlpha(.1f);
        if(type[1]) nopure.setAlpha(1.0f);

        boolean[] style = arguments.getBooleanArray("style");
        ImageView type1 = (ImageView)content.findViewById(R.id.type1);
        type1.setAlpha(.1f);
        if(style[0]) type1.setAlpha(1.0f);
        ImageView type2 = (ImageView)content.findViewById(R.id.type2);
        type2.setAlpha(.1f);
        if(style[1]) type2.setAlpha(1.0f);
        ImageView type3 = (ImageView)content.findViewById(R.id.type3);
        type3.setAlpha(.1f);
        if(style[2]) type3.setAlpha(1.0f);
        ImageView type4 = (ImageView)content.findViewById(R.id.type4);
        type4.setAlpha(.1f);
        if(style[3]) type4.setAlpha(1.0f);
        ImageView type5 = (ImageView)content.findViewById(R.id.type5);
        type5.setAlpha(.1f);
        if(style[4]) type5.setAlpha(1.0f);

        TextView cost = (TextView)content.findViewById(R.id.cost);
        cost.setText("90mlあたり　¥" + arguments.getString("cost"));
        LinearLayout hashtags = (LinearLayout)content.findViewById(R.id.hashtag_wrapper);
        List<String> hashtagList = arguments.getStringArrayList("hashtags");
        for(String tag : hashtagList) {
            TextView text = (TextView) inflater.inflate(R.layout.hashtag, null);
            text.setText(tag);
            hashtags.addView(text);
        }
        builder.setView(content);
        builder.setPositiveButton("OK", null);

        return builder.create();
    }

}

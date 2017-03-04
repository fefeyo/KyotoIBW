package com.fefeyo.kyotoibw.store;

import android.app.Application;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fefeyo.kyotoibw.MainApplication;
import com.fefeyo.kyotoibw.R;
import com.fefeyo.kyotoibw.databinding.ActivityInsertPriceBinding;
import com.fefeyo.kyotoibw.items.Sake;

import java.util.ArrayList;
import java.util.List;

public class InsertPriceActivity extends AppCompatActivity {

    private ActivityInsertPriceBinding mBinding;
    private String price;
    private List<Sake> sakeList;
    private List<Sake> commitSakeList;
    private MainApplication application;
    private SoundPool soundPool;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_insert_price);
        setSupportActionBar(mBinding.toolbar);
        initSound();

        application = (MainApplication) getApplication();
        sakeList = application.getSakeList();
        commitSakeList = new ArrayList<>();
        changeState();
        price = "";

        mBinding.sakeName.setText("");
        mBinding.sakePrice.setText("0");
        mBinding.one.setOnClickListener(v -> {
            price += "1";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.twe.setOnClickListener(v -> {
            price += "2";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.three.setOnClickListener(v -> {
            price += "3";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.four.setOnClickListener(v -> {
            price += "4";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.five.setOnClickListener(v -> {
            price += "5";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.six.setOnClickListener(v -> {
            price += "6";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.seven.setOnClickListener(v -> {
            price += "7";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.eight.setOnClickListener(v -> {
            price += "8";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.nine.setOnClickListener(v -> {
            price += "9";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.zero.setOnClickListener(v -> {
            price += "0";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.wzero.setOnClickListener(v -> {
            price += "00";
            mBinding.sakePrice.setText(price);
            checkZero();
        });
        mBinding.sakeName.setText(sakeList.get(0).getName() + "の料金を入力してください");
        mBinding.buttonOk.setOnClickListener(v -> {
            soundPool.play(sound, 1F, 1F, 0, 0, 1F);
            int num = Integer.parseInt(price);
            sakeList.get(0).setCost(num);
            commitSakeList.add(sakeList.get(0));
            sakeList.remove(0);
            if (sakeList.isEmpty()) {
                application.setAllSake(commitSakeList);
                Intent intent = new Intent(InsertPriceActivity.this, CheckAlcoholsActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            mBinding.sakeName.setText(sakeList.get(0).getName() + "の料金を入力してください");
            price = "";
            mBinding.sakePrice.setText("0");
            changeState();
        });
    }

    private void checkZero() {
        if(Integer.parseInt(mBinding.sakePrice.getText().toString()) <= 0) {
            mBinding.sakePrice.setText("0");
            price = "";
        }
    }

    private void changeState() {
        if (sakeList.size() == 1) {
            mBinding.buttonOk.setText("確定");
        } else {
            mBinding.buttonOk.setText("次へ");
        }
    }

    private void initSound() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Android 5.0(Lolipop)より古いとき
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        } else {
            // Android 5.0(Lolipop)以降
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(2)
                    .build();
        }
        sound = soundPool.load(this, R.raw.next, 1);
    }
}

package com.fefeyo.kyotoibw.store;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fefeyo.kyotoibw.MainApplication;
import com.fefeyo.kyotoibw.R;
import com.fefeyo.kyotoibw.items.Sake;
import com.google.common.collect.Interner;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlcoholReaderActivity extends AppCompatActivity {

    private MainApplication application;
    private CompoundBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_reader_activity);

        application = (MainApplication) getApplication();

//        for (int i = 1; i < 4; i++) {
//            Sake sake = new Sake();
//            sake.setName("純粋無垢" + i);
//            sake.setPrefecture("和歌山");
//            sake.setType("純米大吟醸");
//            sake.setStyle("冷酒");
//            sake.addHashtag("すっきり");
//            sake.addHashtag("食前に");
//            application.setSake(sake);
//        }

//        findViewById(R.id.reader).setOnClickListener(v -> {
//            Intent intent = new Intent(AlcoholReaderActivity.this, InsertPriceActivity.class);
//            startActivity(intent);
//            finish();
//        });

        barcodeView = (CompoundBarcodeView) findViewById(R.id.qrview);
        barcodeView.setStatusText("日本酒のQRコードをスキャンします");
        barcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Log.d("読み取り成功！", result.getText());
                String[] datas = result.getText().split(",");
                Sake sake = new Sake();
                sake.setName(datas[0]);
                sake.setPrefecture(datas[1]);
                boolean[] type = new boolean[2];
                type[0] = Integer.parseInt(datas[2]) == 1 ? true : false;
                type[1] = Integer.parseInt(datas[3]) == 1 ? true : false;
                sake.setType(type);
                boolean[] style = new boolean[5];
                int count = 0;
                for (int i = 4; i < 9; i++) {
                    style[count] = Integer.parseInt(datas[i]) == 1 ? true : false;
                    count++;
                }
                sake.setStyle(style);
                for (int i = 11; i < datas.length; i++) {
                    sake.addHashtag(datas[i]);
                }
                application.addSake(sake);
//                initSampleData("純粋無垢", "和歌山", true, false, false, true, false, false, false, 700, new String[]{"すっきり"});
//                initSampleData("紀土", "和歌山", true, false, false, true, true, false, false, 700, new String[]{"すっきり"});
//                initSampleData("奥鹿", "大阪", true, false, false, false, true, false, false, 700, new String[]{"すっきり"});
//                initSampleData("星の降る夜", "京都", false, true, false, true, true, false, false, 900, new String[]{"すっきり"});
                Intent intent = new Intent(AlcoholReaderActivity.this, InsertPriceActivity.class);
                startActivity(intent);
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
    }

    private void initSampleData(String name, String prefecture, boolean pure, boolean nopure, boolean rock, boolean cold, boolean warm, boolean nurui, boolean hot, int cost, String[] hashtags) {
        Sake sake = new Sake();
        sake.setName(name);
        sake.setPrefecture(prefecture);
        sake.setType(new boolean[]{
                pure,
                nopure
        });
        sake.setStyle(new boolean[]{
                rock,
                cold,
                warm,
                nurui,
                hot
        });
        sake.setCost(cost);
        sake.setHashtags(Arrays.asList(hashtags));
        application.addSake(sake);
    }

    @Override
    public void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        barcodeView.pause();
    }
}

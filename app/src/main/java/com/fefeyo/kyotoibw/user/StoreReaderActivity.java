package com.fefeyo.kyotoibw.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fefeyo.kyotoibw.R;
import com.fefeyo.kyotoibw.items.Sake;
import com.fefeyo.kyotoibw.store.AlcoholReaderActivity;
import com.fefeyo.kyotoibw.store.InsertPriceActivity;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;

public class StoreReaderActivity extends AppCompatActivity {

    private CompoundBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_reader);

        barcodeView = (CompoundBarcodeView)findViewById(R.id.qrview);

        barcodeView.setStatusText("日本酒のQRコードをスキャンします");
        barcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Log.d("読み取り成功！", result.getText());
                Intent intent = new Intent(StoreReaderActivity.this, AlcoholListActivity.class);
                startActivity(intent);
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });

//        findViewById(R.id.reader).setOnClickListener(v -> {
//            Intent intent = new Intent(StoreReaderActivity.this, AlcoholListActivity.class);
//            startActivity(intent);
//        });
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

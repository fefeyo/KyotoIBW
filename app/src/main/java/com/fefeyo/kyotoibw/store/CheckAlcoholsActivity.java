package com.fefeyo.kyotoibw.store;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.fefeyo.kyotoibw.MainActivity;
import com.fefeyo.kyotoibw.MainApplication;
import com.fefeyo.kyotoibw.R;
import com.fefeyo.kyotoibw.adapters.SakeListAdapter;
import com.fefeyo.kyotoibw.databinding.ActivityCheckAlcoholsBinding;
import com.fefeyo.kyotoibw.fragments.AlcoholDetailFragment;
import com.fefeyo.kyotoibw.items.Sake;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckAlcoholsActivity extends AppCompatActivity {

    private ActivityCheckAlcoholsBinding mBinding;
    private SakeListAdapter mAdapter;
    private MainApplication application;
    private List<Sake> sakeList;
    private SoundPool soundPool;
    private int syousai, kettei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_alcohols);
        setSupportActionBar(mBinding.toolbar);
        initSound();
        mAdapter = new SakeListAdapter(getApplicationContext());
        mBinding.sakeList.setAdapter(mAdapter);
        SwipeMenuCreator creator = menu -> {
            SwipeMenuItem delete = new SwipeMenuItem(getApplicationContext());
            delete.setBackground(new ColorDrawable(Color.parseColor("#e74c3c")));
            delete.setWidth(200);
            delete.setTitle("削除");
            delete.setTitleSize(18);
            delete.setTitleColor(Color.WHITE);
            menu.addMenuItem(delete);
        };
        mBinding.sakeList.setMenuCreator(creator);
        mBinding.sakeList.setOnMenuItemClickListener((position, menu, index) -> {
            Sake sake = mAdapter.getItem(position);
            mAdapter.remove(sake);
            mAdapter.notifyDataSetChanged();

            return false;
        });

        application = (MainApplication) getApplication();
        initSampleData("純粋無垢", "和歌山", true, false, false, true, false, false, false, 700, new String[]{"すっきり"});
        initSampleData("紀土", "和歌山", true, false, false, true, true, false, false, 700, new String[]{"すっきり"});
        initSampleData("奥鹿", "大阪", true, false, false, false, true, false, false, 700, new String[]{"すっきり"});
        initSampleData("星の降る夜", "京都", false, true, false, true, true, false, false, 900, new String[]{"すっきり"});
        sakeList = application.getSakeList();
        mAdapter.addAll(sakeList);
        mAdapter.notifyDataSetChanged();

        mBinding.sakeList.setOnItemClickListener((parent, view, position, id) -> {
            soundPool.play(syousai, 1F, 1F, 0, 0, 1F);
            Sake sake = mAdapter.getItem(position);
            Bundle bundle = new Bundle();
            bundle.putString("name", sake.getName());
            bundle.putString("prefecture", sake.getPrefecture());
            bundle.putBooleanArray("type", sake.getType());
            bundle.putBooleanArray("style", sake.getStyle());
            bundle.putString("cost", Integer.toString(sake.getCost()));
            ArrayList<String> sakeTags = new ArrayList<>();
            sakeTags.addAll(sake.getHashtags());
            bundle.putStringArrayList("hashtags", sakeTags);
            AlcoholDetailFragment fragment = new AlcoholDetailFragment();
            fragment.setArguments(bundle);
            fragment.show(getFragmentManager(), null);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_items, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_complete:
                soundPool.play(kettei, 1F, 1F, 0, 0, 1F);
                MainApplication application = (MainApplication)getApplication();
                application.clearList();
                Intent intent = new Intent(CheckAlcoholsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
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
        syousai = soundPool.load(this, R.raw.syousai, 1);
        kettei = soundPool.load(this, R.raw.menukettei, 2);
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

}

package com.fefeyo.kyotoibw.user;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.fefeyo.kyotoibw.R;
import com.fefeyo.kyotoibw.adapters.SakeListAdapter;
import com.fefeyo.kyotoibw.fragments.AlcoholDetailFragment;
import com.fefeyo.kyotoibw.items.Sake;

import java.util.ArrayList;
import java.util.Arrays;

public class AlcoholListActivity extends AppCompatActivity {

    SakeListAdapter mAdapter;
    ListView listView;
    int syousai;
    SoundPool soundPool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_list);
        initSound();

        listView = (ListView)findViewById(R.id.listview);
        mAdapter = new SakeListAdapter(getApplicationContext());

        mAdapter.add(initSampleData("純粋無垢", "和歌山", true, false, false, true, false, false, false, 700, new String[]{"すっきり"}));
        mAdapter.add(initSampleData("紀土", "和歌山", true, false, false, true, true, false, false, 700, new String[]{"すっきり"}));
        mAdapter.add(initSampleData("奥鹿", "大阪", true, false, false, false, true, false, false, 700, new String[]{"すっきり"}));
        mAdapter.add(initSampleData("星の降る夜", "京都", false, true, false, true, true, false, false, 900, new String[]{"すっきり"}));
        mAdapter.add(initSampleData("秋鹿", "大阪", true, false, false, false, false, true, true, 900, new String[]{"すっきり"}));
        mAdapter.notifyDataSetChanged();

        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
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

    private Sake initSampleData(String name, String prefecture, boolean pure, boolean nopure, boolean rock, boolean cold, boolean warm, boolean nurui, boolean hot, int cost, String[] hashtags) {
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

        return sake;
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
    }
}

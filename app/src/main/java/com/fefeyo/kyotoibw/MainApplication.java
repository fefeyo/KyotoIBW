package com.fefeyo.kyotoibw;

import android.app.Application;

import com.fefeyo.kyotoibw.items.Sake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fefe on 2017/03/04.
 */

public class MainApplication extends Application{

    private List<Sake> commitSakeList;

    @Override
    public void onCreate() {
        super.onCreate();
        commitSakeList = new ArrayList<>();
    }

    public void clearList() {
        commitSakeList.clear();
    }

    public List<Sake> getSakeList() {
        return this.commitSakeList;
    }

    public void setAllSake(List<Sake> sakeList) {
        this.commitSakeList = sakeList;
    }

    public void addSake(Sake sake) {
        commitSakeList.add(sake);
    }

}

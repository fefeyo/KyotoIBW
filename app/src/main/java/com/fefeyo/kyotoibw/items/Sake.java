package com.fefeyo.kyotoibw.items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fefe on 2017/03/04.
 */

public class Sake {
    private String name;
    private String prefecture;
    private boolean[] type;
    private boolean[] style;
    private int cost;
    private List<String> hashtags;

    public Sake() {
        hashtags = new ArrayList<>();
        type = new boolean[2];
        style = new boolean[5];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public boolean[] getType() {
        return type;
    }

    public void setType(boolean[] type) {
        this.type = type;
    }

    public boolean[] getStyle() {
        return style;
    }

    public void setStyle(boolean[] style) {
        this.style = style;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void addHashtag(String tag) {
        tag = "#" + tag;
        hashtags.add(tag);
    }
}

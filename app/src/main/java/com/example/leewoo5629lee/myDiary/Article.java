package com.example.leewoo5629lee.myDiary;

import io.realm.RealmObject;

/**
 * Created by leewoo5629.lee on 2017-08-17.
 */

public class Article extends RealmObject {
    private String title;
    private String content;


    /*
    Realm 적용하면서 생략
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }*/


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

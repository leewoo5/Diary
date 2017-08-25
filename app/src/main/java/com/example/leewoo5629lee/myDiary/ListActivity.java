package com.example.leewoo5629lee.myDiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ListActivity extends AppCompatActivity {

    private ListView mListView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //final String[] sampleArray = {"hello", "this", "is", "test"};
        //Article[] articles = {
                               /* new Article("title1", "content1"),
                                new Article("title2", "content2"),
                                new Article("title3", "content3"),
                                new Article("title4", "content4")*/
        //};
        mListView = (ListView) findViewById(R.id.listView);
        mButton = (Button) findViewById(R.id.btn_toMain);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, articles);


        //데이터 가져오기
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Article> articles = realm.where(Article.class).findAll();

        final CustomAdapter adapter = new CustomAdapter(
                this,
                R.layout.list_row,
                //new ArrayList<Article>(Arrays.asList(articles))//array를 arrayList로 변경
                articles
        );

        articles.addChangeListener(new RealmChangeListener<RealmResults<Article>>() {
            @Override
            public void onChange(RealmResults<Article> articles) {
                adapter.notifyDataSetChanged();
            }
        });
        mListView.setAdapter(adapter);
        //리스트뷰의 아이템 클릭시 페이지 이동
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ReadActivity.class);
                intent.putExtra("title", articles.get(position).getTitle());
                intent.putExtra("content", articles.get(position).getContent());
                startActivity(intent);
            }
        });

        //메인페이지 이동 버튼 이벤트
        View.OnClickListener onClickListener_toMain = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        mButton.setOnClickListener(onClickListener_toMain);

    }
}

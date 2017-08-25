package com.example.leewoo5629lee.myDiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class ReadActivity extends AppCompatActivity {

    //화면에 배치한 UI 컴포넌트들을 사용하기 위해 변수를 정의함
    //구글에서는 멤버변수의 네이밍 규칙으로 m으로 시작하는 것을 권장
    private EditText mTitleEditText;
    private EditText mContentEditText;
    private Button mMoveToListPage;
    private Button mDelete;
    private String mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        //UI 컴포넌트들의 아이디로 각 객체를 찾아서 변수에 대입한다.
        mTitleEditText = (EditText) findViewById(R.id.titleEditText);
        mContentEditText = (EditText) findViewById(R.id.contentEditText);
        mMoveToListPage = (Button) findViewById(R.id.moveToListPage);
        mDelete = (Button) findViewById(R.id.deleteList);

        //메인화면 이동버튼
        View.OnClickListener moveToListPageListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadActivity.this, ListActivity.class);
                startActivity(intent);
            }
        };
        mMoveToListPage.setOnClickListener(moveToListPageListener);

        //아이템 삭제버튼
        View.OnClickListener deleteListListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                //Article delete
                final Article article = realm.where(Article.class).equalTo("title", mtitle).findFirst();
                article.deleteFromRealm();
                realm.commitTransaction();
            }
        };
        mDelete.setOnClickListener(deleteListListener);

        if (savedInstanceState == null) {
            String title = getIntent().getStringExtra("title");
            String content = getIntent().getStringExtra("content");
            mtitle = title;
            mTitleEditText.setText(title);
            mContentEditText.setText(content);

        }
    }
}

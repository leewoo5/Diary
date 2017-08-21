package com.example.leewoo5629lee.myDiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    //화면에 배치한 UI 컴포넌트들을 사용하기 위해 변수를 정의함
    //구글에서는 멤버변수의 네이밍 규칙으로 m으로 시작하는 것을 권장
    private EditText mTitleEditText;
    private EditText mContentEditText;
    private Button mButton;
    private String mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI 컴포넌트들의 아이디로 각 객체를 찾아서 변수에 대입한다.
        mTitleEditText = (EditText) findViewById(R.id.titleEditText);
        mButton = (Button) findViewById(R.id.button);
        mContentEditText = (EditText) findViewById(R.id.contentEditText);


        //각 UI 컴포넌트 객체들의 이벤트를 감지하기 위해 리스너를 달아준다.
        //클릭 리스너는 클릭 했을 때 발생하는 이벤트를 정의해줘야 한다.
        View.OnClickListener firstOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Realm.init(getApplicationContext());
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                //Article Addition
                /*String titleText = mTitleEditText.getText().toString();
                String contentText = mContentEditText.getText().toString();
                //mTextView.setText(titleText);
                mTitleEditText.setText("");
                mContentEditText.setText("");

                //transaction

                Article article = realm.createObject(Article.class);
                article.setTitle(titleText);
                article.setContent(contentText);
                realm.commitTransaction();*/

                //////////////////////////////////////////////////////////////////////////////////

                //Article delete
                final Article article = realm.where(Article.class).equalTo("title", mtitle).findFirst();
                article.deleteFromRealm();
                realm.commitTransaction();

                finish(); //activity 종료

            }
        };
        mButton.setOnClickListener(firstOnClickListener);

        if (savedInstanceState == null) {
            String title = getIntent().getStringExtra("title");
            String content = getIntent().getStringExtra("content");
            mtitle = title;
            mTitleEditText.setText(title);
            mContentEditText.setText(content);

        }
    }
}

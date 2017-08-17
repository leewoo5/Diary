package com.example.leewoo5629lee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //화면에 배치한 UI 컴포넌트들을 사용하기 위해 변수를 정의함
    //구글에서는 멤버변수의 네이밍 규칙으로 m으로 시작하는 것을 권장
    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI 컴포넌트들의 아이디로 각 객체를 찾아서 변수에 대입한다.
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mEditText = (EditText) findViewById(R.id.editText);

        //각 UI 컴포넌트 객체들의 이벤트를 감지하기 위해 리스너를 달아준다.
        //클릭 리스너는 클릭 했을 때 발생하는 이벤트를 정의해줘야 한다.
        View.OnClickListener firstOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
                mTextView.setText(text);
                mEditText.setText("");
            }
        };
        mButton.setOnClickListener(firstOnClickListener);

        if (savedInstanceState == null) {
           String text = getIntent().getStringExtra("item");
            mTextView.setText(text);
        }
    }
}

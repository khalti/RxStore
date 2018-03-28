package com.rxStoreImpl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.rxStore.RxStore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.btnAddOne)
    Button btnAddOne;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.btnAddTwo)
    Button btnAddTwo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RxStore.getInstance().get("add1").subscribe(o -> {
            if (o != null) {
                txt.setText(o + "");
            }
        });
        btnAddOne.setOnClickListener(view -> RxStore.getInstance().save("add1", Integer.parseInt(txt.getText().toString()) + 1));

        RxStore.getInstance().get("add2").subscribe(o -> {
            if (o != null) {
                txt2.setText(o + "");
            }
        });
        btnAddTwo.setOnClickListener(view -> RxStore.getInstance().save("add2", Integer.parseInt(txt2.getText().toString()) + 2));
    }
}

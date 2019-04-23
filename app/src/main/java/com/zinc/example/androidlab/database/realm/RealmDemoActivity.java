package com.zinc.example.androidlab.database.realm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.zinc.example.androidlab.R;
import com.zinc.example.androidlab.database.realm.RealmHelper.TestRealmHelper;

import rx.functions.Action1;

import static com.zinc.example.androidlab.app.CusApplication.SCHEMA_VERSION;

public class RealmDemoActivity extends AppCompatActivity {

    TestRealmHelper mRealmHelper;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_realm_demo);
        mRealmHelper = new TestRealmHelper();
        ((TextView)findViewById(R.id.tv_content_size_id)).setText("schema version: "+SCHEMA_VERSION);
        RxView.clicks(findViewById(R.id.btn_add_data_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                addData();
            }
        });

        RxView.clicks(findViewById(R.id.btn_query_data_id)).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(mContext, queryData()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addData(){
        mRealmHelper.addOrUpdateRealmBean();
    }

    private int queryData(){
        return mRealmHelper.queryInfo().size();
    }
}

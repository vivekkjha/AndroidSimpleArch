package com.mds.android.framework.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mds.android.R;
import com.mds.android.common.app.MdsApplication;

import mds.mdstoolkit.tasks.BGTask;
import mds.mdstoolkit.tasks.ICancelableTask;
import mds.mdstoolkit.tasks.IProgressWatcher;
import mds.mdstoolkit.tasks.Invoker;

public class MainActivity extends BaseActivity {

    ICancelableTask mICancelableTask;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initializeViews(Bundle bundle) {

        Button btn_get = (Button)findViewById(R.id.btn_get);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MdsApplication.getInstance().getEPrefs().setUserId(56);
                Toast.makeText(MainActivity.this,"Prefs value : " + MdsApplication.getInstance().getEPrefs().getUserId(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void doTask()
    {

      mICancelableTask =  Invoker.run(new BGTask<Void, Void>() {
            @Override
            public Void performOperation(Void... voids) throws Exception {
                return null;
            }

            @Override
            public void updateUI(Void aVoid) {

            }

          @Override
          public boolean handleErrorInUI(Exception t) {
              return true;
          }

      }, (IProgressWatcher) null);

    }

    private void doProgressiveTask()
    {
        mICancelableTask =  Invoker.run(new BGTask<Void, Void>() {
            @Override
            public Void performOperation(Void... voids) throws Exception {
                return null;
            }

            @Override
            public void updateUI(Void aVoid) {

            }

            @Override
            public boolean handleErrorInUI(Exception t) {
                return true;
            }

            @Override
            public String getDescription() {
                return  "Loading...data...";
            }
        }, new IProgressWatcher() {
            @Override
            public void onTaskBegins() {

            }

            @Override
            public void onTaskEnds() {

            }

            @Override
            public void onInfo(String msg) {

            }
        });

    }

    private void cancelTask()
    {
        if(mICancelableTask!=null)
            mICancelableTask.cancel();
    }
}

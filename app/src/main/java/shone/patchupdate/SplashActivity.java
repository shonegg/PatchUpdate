package shone.patchupdate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Author   Shone
 * Date     27/08/16.
 * Github   https://github.com/shonegg
 */
public class SplashActivity extends AppCompatActivity {


    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpToMain();
            }
        }, 2000);
    }

    private void jumpToMain() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}

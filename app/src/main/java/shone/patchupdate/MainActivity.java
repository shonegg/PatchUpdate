package shone.patchupdate;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sugar.patch.Contants;
import com.sugar.patch.Updater;
import com.sugar.util.AppUtils;

import java.io.File;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView versionTv;
    private Button checkBt;
    private RelativeLayout contentView;
    private RelativeLayout loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        versionTv = (TextView) findViewById(R.id.versionTv);
        String ver = AppUtils.getVersion(this);
        versionTv.setText(getResources().getString(R.string.version_name) + ver);

        checkBt = (Button) findViewById(R.id.checkBt);
        checkBt.setOnClickListener(this);

        contentView = (RelativeLayout) findViewById(R.id.content);
        loadingView = (RelativeLayout) findViewById(R.id.loading);

        checkFileDir();
        showContent();
    }

    private void showContent() {
        contentView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }


    private void hideContent() {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }


    private void checkFileDir() {
        String dir = Contants.rootDir;
        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }
    }


    public void startUpdate() {
        new UpdateTask(this).execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkBt: {
                startUpdate();
                break;
            }
        }
    }

    private class UpdateTask extends AsyncTask<Void, Void, Boolean> {

        private String rootDir;
        private String downPatchPath;
        private String newVersionPath;

        private WeakReference<Context> context;


        public UpdateTask(Context context) {
            this.context = new WeakReference<>(context);
            rootDir = Contants.rootDir;
            downPatchPath = Contants.downPatchPath;
            newVersionPath = Contants.newVersionPath;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (context.get() instanceof MainActivity) {
                ((MainActivity) context.get()).hideContent();
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //模拟更新,省掉下载过程,直接将补丁文件放到sd卡下面
            //合并
            try {
                String oldVersionPath = AppUtils.getOldVersionPath(context.get());
                Updater.applyPatch(oldVersionPath, newVersionPath, downPatchPath);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                AppUtils.install(context.get(), newVersionPath);
            }
            if (context.get() instanceof MainActivity) {
                ((MainActivity) context.get()).showContent();
            }
        }
    }
}

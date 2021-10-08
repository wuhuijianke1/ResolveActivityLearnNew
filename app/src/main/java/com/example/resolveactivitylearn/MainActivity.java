package com.example.resolveactivitylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

//TODO - used a listview template
public class MainActivity extends AppCompatActivity {
    TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loadApps();
    }

    private void loadApps() {
        infoTextView = findViewById(R.id.info1);
        PackageManager packageManager = getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        ComponentName resolveActivity = intent.resolveActivity(packageManager);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("best resolveActivity="+resolveActivity+"\n");

        //https://stackoverflow.com/questions/65629268/queryintentactivities-returning-empty-list-in-android-11
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        int infoSize = resolveInfos == null ? 0 : resolveInfos.size();
        stringBuilder.append("all resolveInfos size: ="+infoSize+"\n");
        for (ResolveInfo resolveInfo: resolveInfos) {
            if (getPackageName() != null && getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                stringBuilder.append("resolveInfo: ="+resolveInfo.activityInfo.name+"\n");
            }
        }
        stringBuilder.append("all resolveInfos **** end");

        infoTextView.setText(stringBuilder.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadApps();
    }
}
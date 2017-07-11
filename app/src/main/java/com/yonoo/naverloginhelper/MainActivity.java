package com.yonoo.naverloginhelper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "다른 앱 위에 그리기 권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "다른 앱 위에 그리기 권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("다른 앱 위에 그리기 허용 권한이 필요합니다. 권한을 허용해주세요")
                .setDeniedMessage("[설정] > [권한] 에서 권한을 허용할 수 있습니다.")
                .setPermissions(Manifest.permission.SYSTEM_ALERT_WINDOW)
                .check();

    }

    public void mStart(View v) {
        startService(new Intent(this, AlwaysTopServiceTouch.class));
    }

    public void mStop(View v) {
        stopService(new Intent(this, AlwaysTopServiceTouch.class));
    }

    public void mList(View v) {
        startActivity(new Intent(this, DBActivity.class));
    }
}


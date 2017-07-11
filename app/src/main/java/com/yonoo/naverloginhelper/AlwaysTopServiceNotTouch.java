package com.yonoo.naverloginhelper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class AlwaysTopServiceNotTouch extends Service {
	private View mView;
	private WindowManager mManager;

	@Override
	public void onCreate() {
		super.onCreate();

		LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = mInflater.inflate(R.layout.always_on_top_view_not_touch, null);

		WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				// ?? ??? ??? ??? ?????
				WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
				// ?? ???? ?? ????
				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
				PixelFormat.TRANSLUCENT); // ??

		mManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		mManager.addView(mView, mParams);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (mView != null) {
			mManager.removeView(mView);
			mView = null;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}

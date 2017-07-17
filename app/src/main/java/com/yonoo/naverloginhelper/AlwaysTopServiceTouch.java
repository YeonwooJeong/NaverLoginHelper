package com.yonoo.naverloginhelper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

public class AlwaysTopServiceTouch extends Service {
	private View mView;
	private WindowManager mManager;
	private WindowManager.LayoutParams mParams;

	private float mTouchX, mTouchY;
	private int mViewX, mViewY;

	private boolean isMove = false;
	public static MainActivity main = new MainActivity();
	Intent it = new Intent(this, DBActivity.class);

	private OnTouchListener mViewTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {

			switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					isMove = false;

					mTouchX = event.getRawX();
					mTouchY = event.getRawY();
					mViewX = mParams.x;
					mViewY = mParams.y;

					break;

				case MotionEvent.ACTION_UP:
					if (!isMove) {
//						Toast.makeText(getApplicationContext(), "???",
//								Toast.LENGTH_SHORT).show();
						main.startActivity(it);
					}

					break;

				case MotionEvent.ACTION_MOVE:
					isMove = true;

					int x = (int) (event.getRawX() - mTouchX);
					int y = (int) (event.getRawY() - mTouchY);

					final int num = 5;
					if ((x > -num && x < num) && (y > -num && y < num)) {
						isMove = false;
						break;
					}

					/**
					 * mParams.gravity? ?? ?? ??
					 *
					 * LEFT : x? +
					 *
					 * RIGHT : x? -
					 *
					 * TOP : y? +
					 *
					 * BOTTOM : y? -
					 */
					mParams.x = mViewX + x;
					mParams.y = mViewY + y;

					mManager.updateViewLayout(mView, mParams);

					break;
			}

			return true;
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();

		LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = mInflater.inflate(R.layout.always_on_top_view_touch, null);

		mView.setOnTouchListener(mViewTouchListener);

		mParams = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		mParams.gravity = Gravity.TOP | Gravity.LEFT;

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

/**
��ŷ
 **/

package com.lego.minddroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SplashMenuView extends View {

	int mScreenWidth;
	int mScreenHeight;
	int startButtonYStart;
	int tutorialButtonYStart;
	Activity splashMenuActivity;

	Resources res;
	Bitmap ic_splash_tutorial;
	Bitmap ic_splash_start;

	Bitmap logo_splash_minddroid;
	Bitmap mBackgroundImage;

	public SplashMenuView(Context context, Activity splashMenuActivity) {
		super(context);
		this.splashMenuActivity = splashMenuActivity;
		res = context.getResources();
	}

	private int calcImgHeight(float originalImageHeight, float originalImageWidth) {
		float screenWidth = mScreenWidth;
		return (int) (originalImageHeight * (screenWidth / originalImageWidth));
	}

	private float calcStartPos() {

		float remainingSpace;
		remainingSpace = mScreenHeight - logo_splash_minddroid.getHeight()  - ic_splash_start.getHeight()
				- ic_splash_tutorial.getHeight();
		float divider = remainingSpace / 5;
		startButtonYStart = (int) getStartButtonYPos(divider);
		return getStartButtonYPos(divider);
	}

	private float calcTutorialPos() {
		float remainingSpace;
		remainingSpace = mScreenHeight - logo_splash_minddroid.getHeight()  - ic_splash_start.getHeight()
				- ic_splash_tutorial.getHeight();

		float divider = remainingSpace / 5;
		tutorialButtonYStart = (int) getTutorialButtonYPos(divider);
		return getTutorialButtonYPos(divider);
	}

	public float getStartButtonYPos(float divider) {
		return (logo_splash_minddroid.getHeight() + ic_splash_start.getHeight() + (divider * 3));
	}

	public float getTutorialButtonYPos(float divider) {
		return (logo_splash_minddroid.getHeight() + (divider * 2));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(mBackgroundImage, 0, 0, null);
		canvas.drawBitmap(logo_splash_minddroid, 0, 0, null);
		canvas.drawBitmap(ic_splash_start, 0,mScreenHeight/5*4, null);
		canvas.drawBitmap(ic_splash_tutorial, 0,mScreenHeight/5*3, null);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mScreenHeight = h;
		mScreenWidth = w;
		setupBitmaps();
		invalidate();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			if (event.getY() > mScreenHeight/5*3&& event.getY() <=  mScreenHeight/5*3+ ic_splash_tutorial.getHeight()) {
				Intent playGame = new Intent(splashMenuActivity.getBaseContext(), MINDdroid.class);
				playGame.putExtra(SplashMenu.MINDDROID_ROBOT_TYPE, 1);
				splashMenuActivity.startActivity(playGame);
				splashMenuActivity.finish();
			} else if (event.getY() > mScreenHeight/5*4&& event.getY() <= mScreenHeight/5*4 + ic_splash_start.getHeight()) {
				splashMenuActivity.finish();
			}
		}
		return true;
	}

	private void setupBitmaps() {

		ic_splash_tutorial = BitmapFactory.decodeResource(res, R.drawable.ic_splash_tutorial);
		ic_splash_tutorial = Bitmap.createScaledBitmap(ic_splash_tutorial, mScreenWidth,
				calcImgHeight(ic_splash_tutorial.getHeight(), ic_splash_tutorial.getWidth()), true);

		ic_splash_start = BitmapFactory.decodeResource(res, R.drawable.ic_splash_start);
		ic_splash_start = Bitmap.createScaledBitmap(ic_splash_start, mScreenWidth,
				calcImgHeight(ic_splash_start.getHeight(), ic_splash_start.getWidth()), true);

		
		logo_splash_minddroid = BitmapFactory.decodeResource(res, R.drawable.logo_splash_minddroid);
		logo_splash_minddroid = Bitmap.createScaledBitmap(logo_splash_minddroid, mScreenWidth,
				calcImgHeight(logo_splash_minddroid.getHeight(), logo_splash_minddroid.getWidth()), true);

		mBackgroundImage = BitmapFactory.decodeResource(res, R.drawable.background_1);
		mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage, mScreenWidth, mScreenHeight, true);

	}

}

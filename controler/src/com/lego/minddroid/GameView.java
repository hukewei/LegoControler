/**
 *中欧
**/

package com.lego.minddroid;

import java.util.List;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private static final int SHORT_PRESS_MAX_DURATION = 750;

	public Bitmap bBitmap;

	class GameThread extends Thread {
		/** time between each redraw */
		private static final int REDRAW_SCHED = 100;
		private int ICON_MAX_SIZE;
		private int ICON_MIN_SIZE;

		private int GOAL_HEIGHT;
		private int GOAL_WIDTH;
		private static final int HAPTIC_FEEDBACK_LENGTH = 30;
		/**
		 * is tilt icon in goal
		 */
		boolean mInGoal = true;

		/**
		 * to notify users when leaving goal
		 */
		Vibrator mHapticFeedback;

		/** The drawable to use as the background of the animation canvas */
		private Bitmap mBackgroundImage;

	
	

	

		private Bitmap mActionDownButton;
		private Bitmap motora;

		private Bitmap motorb;

		private Bitmap motorc;
		/**
		 * Current height of the surface/canvas.
		 * 
		 * @see #setSurfaceSize
		 */
		private int mCanvasHeight = 1;

		/**
		 * Current width of the surface/canvas.
		 * 
		 * @see #setSurfaceSize
		 */
		private int mCanvasWidth = 1;

		/** Message handler used by thread to interact with TextView */
		private Handler mHandler;

		/** Used to figure out elapsed time between frames */
		private long mLastTime;

		/** Indicate whether the surface has been created & is ready to draw */
		private boolean mRun = false;

		/** Handle to the surface manager object we interact with */
		private SurfaceHolder mSurfaceHolder;

		/** X of motion indicator */
		private float mX;

		/** Y of motion indicator */
		private float mY;
		private int a,b,c;;
		/**
		 * mIconSize grows within target between ICON_MIN_SIZE and ICON_MAX_SIZE
		 */
		private int mGrowAdjust;

		/**
		 * time when haptic feedback will stop - needed to ensure we don't take
		 * tilt measurements while handset if vibrating
		 */
		//private long mFeedbackEnd = 0;

		/**
		 * track how long since we redrew screen
		 */
		long mElapsedSinceDraw = 0;

		/**
		 * track how long since we redrew screen
		 */
		long mElapsedSinceNXTCommand = 0;

		/**
		 * count how many times we took tilt readings in 100ms so we can average
		 * position
		 */
		int mAvCount = 0;
		/**
		 * time when tilt icon should change color
		 */
		long mNextPulse = 0;

		/* holder for current color in pulse effect* */
		Drawable mPulsingTiltIcon;

		/** was action button just pressed */
		boolean mActionPressed = false;

		/** */
		boolean mToNXT = false;

		/** buffers to hold tilt readings for averaging */
		float mNumAcX;
		float mNumAcY;

        /** digital filtering variables **/

	    public boolean longPressCancel;
		private Bitmap mActionButton;
		
		public GameThread(SurfaceHolder surfaceHolder, Context context, Vibrator vibrator, Handler handler) {
			// get handles to some important objects
			mHapticFeedback = vibrator;
			mSurfaceHolder = surfaceHolder;
			mHandler = handler;
			Resources res = context.getResources();
		
			motora = BitmapFactory.decodeResource(res, R.drawable.motora);
			motorb = BitmapFactory.decodeResource(res, R.drawable.motorb);
			motorc = BitmapFactory.decodeResource(res, R.drawable.motorc);
			// load background image as a Bitmap instead of a Drawable b/c
			// we don't need to transform it and it's faster to draw this way
		
			mActionDownButton = BitmapFactory.decodeResource(res, R.drawable.action_btn_down);
			mBackgroundImage = BitmapFactory.decodeResource(res, R.drawable.background_2);
	
		}



		/**
		 * Draws move indicator, button and background to the provided Canvas.
		 */
		private void doDraw(Canvas mCanvas) {
			if(mCanvas!=null){
	int www=mCanvas.getWidth()/3*2;
	int hhh=mCanvas.getHeight()/10*3+mCanvas.getHeight()/700;
	//Log.e("www",mCanvas.getWidth()+" "+www);
	//Log.e("hhh",mCanvas.getHeight()+" "+hhh);
				// draw the background
				mCanvas.drawBitmap(mBackgroundImage, 0, 0, null);
				//draw pressed action button
				mCanvas.drawBitmap(mActionDownButton, 0, mCanvasHeight - mActionButton.getHeight(), null);
				//draw icon in goal
				// draw the goal
				
				mCanvas.drawBitmap(motora, 0, 0, null);
				mCanvas.drawBitmap(motorb, mCanvasWidth/3, 0, null);
				mCanvas.drawBitmap(motorc, mCanvasWidth/3*2, 0, null);
				//mCanvas.drawBitmap(bssg, mCanvasWidth/11*2, mCanvasWidth/100*64, null);
				if(MINDdroid.bitmaphua==true)	
				{ //Matrix matrix = new Matrix(); 
				//matrix.postScale(mCanvas.getWidth()/480,mCanvas.getHeight()/762); //长和宽放大缩小的比例
			//	MINDdroid.bitmap=Bitmap.createScaledBitmap(MINDdroid.bitmap,width, height ,true);
				
			//	bBitmap = Bitmap.createBitmap(MINDdroid.bitmap,0,0,MINDdroid.bitmap.getWidth(),MINDdroid.bitmap.getHeight(),matrix,true);
			//	Log.e("MINDdroid.bitmap.getWidth()",MINDdroid.bitmap.getWidth()+"");
			//	Log.e("bBitmap.getWidth()",bBitmap.getWidth()+"");
				if(MINDdroid.suo==false)
					{//MINDdroid.bitmap=Bitmap.createScaledBitmap(MINDdroid.bitmap,hhh, www ,true);
					Matrix matrix = new Matrix();  
		    	       matrix.postRotate(-90, 10 + MINDdroid.bitmap.getWidth()/2, 10 + MINDdroid.bitmap.getHeight()/2);  
		    	         
		    	      Bitmap destBmp = Bitmap.createBitmap(MINDdroid.bitmap, 0, 0,  MINDdroid.bitmap.getWidth(),     MINDdroid.bitmap.getHeight(), 
		    	    		   
		    	                                            
		    	                                           matrix, true); 
		    	      destBmp=Bitmap.createScaledBitmap(destBmp,(int)(hhh*1.3), (int)(www*1.3) ,true);
					mCanvas.drawBitmap(destBmp,mCanvasWidth/15*3, mCanvas.getHeight()/20*6,null);
					bBitmap=destBmp;
					}
				else
					{
					
					
					
					bBitmap=Bitmap.createScaledBitmap(bBitmap,(int)(hhh*1.3), (int)(www*1.3) ,true);
					
					mCanvas.drawBitmap(bBitmap,mCanvasWidth/15*3, mCanvas.getHeight()/20*6,null);
					}
				
				}
			}
		
		}

		/**
		 * Starts the game, setting parameters for the current difficulty.
		 */
		public void doStart() {
			synchronized (mSurfaceHolder) {

			
				mY = (mCanvasHeight - motora.getHeight()) / 2+motora.getHeight();
				mX=(mCanvasHeight - motora.getHeight()) / 2;

			}
		}

		/**
		 * Pauses the animation.
		 */
		public void pause() {
			thread.setRunning(false);
			synchronized (mSurfaceHolder) {

			}
			boolean retry = true;
			getThread().setRunning(false);
			while (retry) {
				try {
					getThread().join();
					retry = false;
				} catch (InterruptedException e) {
				}
			}
			
		}

		/**
		 * Restores game state from the indicated Bundle. Typically called when
		 * the Activity is being restored after having been previously
		 * destroyed.
		 * 
		 * @param savedState
		 *            Bundle containing the game state
		 */
		public synchronized void restoreState(Bundle savedState) {
			synchronized (mSurfaceHolder) {

			}
		}

		@Override
		public void run() {
		    
		//	Log.d(TAG, "--run--");
			while (mRun) {
				
				// sleep some time
				try {
				    Thread.sleep(30);
				}
				catch (InterruptedException e) {
				}

				updateTime();
				updateMoveIndicator(mAccelX, mAccelY, mAccelZ);
			
				// is it time to update the screen?
				if (mElapsedSinceDraw > REDRAW_SCHED) {

					//is it time to update motor movement?
					if (mElapsedSinceNXTCommand > MINDdroid.UPDATE_TIME) {
						//calculate and send command to move motors							
						mActivity.updateMotorControl(a,b,c);
					}

					lockCanvasAndDraw();
                    
				}
			}
		}



		public void lockCanvasAndDraw() {
			Canvas c = null;
			try {
				c = mSurfaceHolder.lockCanvas(null);
				synchronized (mSurfaceHolder) {
					doDraw(c);

				}
			} finally {
				// do this in a finally so that if an exception is
				// thrown
				// during the above, we don't leave the Surface in an
				// inconsistent state
				if (c != null) {

					mSurfaceHolder.unlockCanvasAndPost(c);

					mElapsedSinceDraw = 0;// mLastTime set to current
											// moment in updateTime
				}
			}
		}

		/**
		 * Dump game state to the provided Bundle. Typically called when the
		 * Activity is being suspended.
		 * 
		 * @return Bundle with this view's state
		 */
		public Bundle saveState(Bundle map) {
			synchronized (mSurfaceHolder) {
				if (map != null) {

				}
			}
			return map;
		}

		/**
		 * Used to signal the thread whether it should be running or not.
		 * Passing true allows the thread to run; passing false will shut it
		 * down if it's already running. Calling start() after this was most
		 * recently called with false will result in an immediate shutdown.
		 * 
		 * @param b
		 *            true to run, false to shut down
		 */
		public void setRunning(boolean b) {
			mRun = b;
		}

		
		/**
		 * Sets the game mode. That is, whether we are running, paused, etc.
		 * 
		 * @see #setState(int, CharSequence)
		 * @param mode
		 *            one of the STATE_* constants
		 */
		public void setState(int mode) {
			synchronized (mSurfaceHolder) {
				setState(mode, null);
			}
		}

		/**
		 * Sets the game mode. That is, whether we are running, paused, in the
		 * failure state, in the victory state, etc.
		 * 
		 * @param mode
		 *            one of the STATE
		 * @param message
		 *            string to add to screen or null
		 */
		public void setState(int mode, CharSequence message) {

			synchronized (mSurfaceHolder) {

			}

		}

		/* Callback invoked when the surface dimensions change. */
		public void setSurfaceSize(int width, int height) {
			// synchronized to make sure these all change atomically
			synchronized (mSurfaceHolder) {
				mCanvasWidth = width;
				mCanvasHeight = height;
				float mAHeight = mActionDownButton.getHeight();
				float mAWidth = mActionDownButton.getWidth();
				float mBHeight = motora.getHeight();
				float mBWidth = motora.getWidth();
				mActionButton = Bitmap.createScaledBitmap(mActionDownButton, width, (Math.round((width * (mAHeight / mAWidth)))), true);
				mActionDownButton = Bitmap.createScaledBitmap(mActionDownButton, width, (Math.round((width * (mAHeight / mAWidth)))), true);
				// don't forget to resize the background image
				mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage, width, height, true);
				mBHeight=mBHeight/(int)mBWidth*width/3;
				mBWidth=width/3;
				
				motora = Bitmap.createScaledBitmap(motora,(int)mBWidth, (int)mBHeight, true);
				motorb = Bitmap.createScaledBitmap(motorb, (int)mBWidth, (int)mBHeight, true);
				motorc = Bitmap.createScaledBitmap(motorc, (int)mBWidth, (int)mBHeight, true);
				if(MINDdroid.bitmaphua)	
				{
				MINDdroid.bitmap=Bitmap.createScaledBitmap(MINDdroid.bitmap,width/10, height/10 ,true);
				
				}
				
			//	Log.e("height",height+"");
			//	Log.e("weight",width+"");
				int temp_ratio = mCanvasWidth / 64;
				GOAL_WIDTH = mCanvasWidth / temp_ratio;

				ICON_MAX_SIZE = (GOAL_WIDTH / 8) * 6;
				ICON_MIN_SIZE = (GOAL_WIDTH / 4);

				temp_ratio = mCanvasHeight / 64;
				GOAL_HEIGHT = mCanvasHeight / temp_ratio;

				
			}
		}

		/**
		 * Resumes from a pause.
		 */
		public void unpause() {
			// Move the real time clock up to now
			synchronized (mSurfaceHolder) {
				mLastTime = System.currentTimeMillis() + 100;
			}

		}

		private void updateTime() {// use for blinking
			long now = System.currentTimeMillis();

			// Do nothing if mLastTime is in the future.
			// This allows the game-start to delay the start
			// by 100ms or whatever.
			if (mLastTime > now)
				return;

			// double elapsed = (now - mLastTime) / 1000.0;
			long elapsed = now - mLastTime;
			//	elapsedSincePulse += elapsed;
			mElapsedSinceDraw += elapsed;
			mElapsedSinceNXTCommand += elapsed;
			mLastTime = now;

		}

		public void vibrate() {
			mHapticFeedback.vibrate(HAPTIC_FEEDBACK_LENGTH);
			//mFeedbackEnd = System.currentTimeMillis() + HAPTIC_FEEDBACK_LENGTH + 15;

		}

		void updateMoveIndicator(float mAcX, float mAcY,float mAcZ) {

		       


			a = (int)mAcX;

			
			b= (int)mAcY;
			c=(int)mAcZ;

		}


	}

	/** used for logging */
	private static final String TAG = GameView.class.getName();;

	private MINDdroid mActivity;

	/** The thread that actually draws the animation */
	private GameThread thread;



	/** orientation (tilt) readings */
	private float mAccelX = 0;
	private float mAccelY = 0;
	private float mAccelZ = 0; // heading
	/**time that action button was pressed - used to calc long or short press */
	long mTimeActionDown=0;
	 

	Context context;
	public GameView(Context context, MINDdroid uiActivity) {
		super(context);
		 
		mActivity = uiActivity;

		// register our interest in hearing about changes to our surface
		SurfaceHolder holder = getHolder();
		holder.setKeepScreenOn(true);
		holder.addCallback(this);
		  this.context=context;
		// create thread only; it's started in surfaceCreated()
		thread = new GameThread(holder, context, (Vibrator) uiActivity.getSystemService(Context.VIBRATOR_SERVICE), new Handler() {
			@Override
			public void handleMessage(Message m) {

			}
		});

		setFocusable(true); // make sure we get key events
	}

	public GameThread getThread() {
		return thread;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
	float y=event.getY();
	float x=event.getX();
	if(y<thread.mCanvasWidth/2){
	if(x<thread.mCanvasWidth/3)
		{mAccelX=-1*y/thread.mActionButton.getHeight()*108+126;
		if(mAccelX>80)
			mAccelX=80;
		if(mAccelX<-80)
			mAccelX=-80;if(mAccelZ==1)mAccelZ=0;
	}
		else if(x>thread.mCanvasWidth/3*2)
		{mAccelZ=-1*y/thread.mActionButton.getHeight()*108+126;
	if(mAccelZ>80)
		mAccelZ=80;
	if(mAccelZ<-80)
		mAccelZ=-80;}
	else
		{mAccelY=-1*y/thread.mActionButton.getHeight()*108+126;
	if(mAccelY>80)
		mAccelY=80;
	if(mAccelY<-80)
		mAccelY=-80;
	if(mAccelZ==1)mAccelZ=0;}}
	if(y>thread.mCanvasHeight - thread.mActionButton.getHeight())
	{	mAccelX=0;
	mAccelY=0;
	mAccelZ=1;
		//mActivity.actionButtonPressed();
	
	//Log.e("long","press");
	}	
	//Log.e("long",x+" "+y+" "+thread.mCanvasHeight+" "+thread.mActionButton.getHeight());
	
	return true;
	}



	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		getThread().setSurfaceSize(width, height);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	
		if (getThread().getState()!=Thread.State.TERMINATED){
		 
			getThread().setRunning(true);
	 
			getThread().start();
		} else{
			thread = new GameThread(holder, context , (Vibrator) mActivity.getSystemService(Context.VIBRATOR_SERVICE), new Handler() {
				@Override
				public void handleMessage(Message m) {

				}
			});
		
			getThread().setRunning(true);
			getThread().start();
		}
	 

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i (TAG,"surfaceDestroyed");
		boolean retry = true;
		getThread().setRunning(false);
		while (retry) {
			try {
				getThread().join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}



}


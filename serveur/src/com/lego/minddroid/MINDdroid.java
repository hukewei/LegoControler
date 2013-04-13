package com.lego.minddroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewParent;

import android.view.Window;
import android.view.WindowManager;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;

import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;









public class MINDdroid extends Activity implements BTConnectable, TextToSpeech.OnInitListener,Callback,OnClickListener {

	   public static final int UPDATE_TIME = 200;
	   public boolean  chuansong=false;
	    public static final int MENU_TOGGLE_CONNECT = Menu.FIRST;
	    public static final int MENU_QUIT = Menu.FIRST + 1;
	    public boolean jieshou=false;
	    private static final int REQUEST_CONNECT_DEVICE = 1000;
	    private static final int REQUEST_ENABLE_BT = 2000;
	    private BTCommunicator myBTCommunicator = null;
	    private boolean connected = false;
	    private ProgressDialog connectingProgressDialog;
	    private Handler btcHandler;
	    private Menu myMenu;
public String ceshi="0";
	    private Activity thisActivity;
	    private boolean btErrorPending = false;
	    private boolean pairing;
	    private static boolean btOnByUs = false;
	    private SurfaceView mSurfaceView = null;
		private SurfaceHolder mSurfaceHolder = null;
		private Camera mCamera = null;
		private boolean mPreviewRunning = false;
		private static String requestURL = "http://kekebox.com/lego/add.php";
		//连接相关
		private String picPath = null;
		private EditText remoteIP=null;
		private Button connect=null;
		private String remoteIPStr=null;
		byte[] rgbss;
		//视频数据
		private static final int width = 240;
		private static final int height = 160;
		private static final int numBands = 3;
		private byte[] byteArray = new byte[width * height * numBands];// 图像RGB数组
		private StreamIt streamIt=null;
		private Bitmap bmp;

		private ImageView imageView;
	int www,hhh;

	public boolean mIsRunning=true;
	    int mRobotType;
	    int motorLeft;
	    int motorb = BTCommunicator.MOTOR_B;
	   
	    int motorc = BTCommunicator.MOTOR_C;
	    int ceshu=1;
	    int motora = BTCommunicator.MOTOR_A;
	   
	    class stockThread extends Thread{
	    	private String message1="";
			private String message;
			private String mes="ssss";
			private boolean run=true;
			   DatagramSocket datagramSocket;
	            DatagramPacket datagramPacket;
			
	            int j=0;
	            int sa=0;
	            int sb=0;
	            int sc=0;
			
			public stockThread() { 
				
	        
	    	}
	    	public void run(){
	    	    
	            String uriAPI = "http://kekebox.com/lego/get.php"; //这是我测试的本地,大家可以随意改
	            /*建立HTTPost对象*/
	            HttpGet httpRequest = new HttpGet(uriAPI);
	    		
	    		  
	    		  
	    		            	while (jieshou==false) { 
	    		                    // 准备接收数据  
	    		            
	    		               
	    		     	            httpRequest = new HttpGet(uriAPI);
	    		     	            try   
	    		     	            {   
	    		     	            	
	    		     	            	long temps=System.currentTimeMillis();
	    		     	            	
	    		     	            	
	    		     	            	
	    		     	            	
	    		     	              /*发出HTTP request*/  
	    		     	              HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);   
	    		     	              /*若状态码为200 ok*/  
	    		     	              if(httpResponse.getStatusLine().getStatusCode() == 200)    
	    		     	              {   
	    		     	                /*取出响应字符串*/  
	    		     	                String strResult = EntityUtils.toString(httpResponse.getEntity());  
	    		     	                
	    		     	                /*删除多余字符*/  
	    		     	                strResult = eregi_replace("(/r/n|/r|/n|/n/r)","",strResult);
	    		     	          
	    		     	                String messs="";
	    		     	                int i=86;
	    		     	                while(strResult.charAt(i)!='<'){
	    		     	                messs=messs+strResult.charAt(i);
	    		     	                i=i+1;}
	    		     	                messs=messs+"ma";
	    		     	                i=i+9;
	    		     	                while(strResult.charAt(i)!='<'){
	    		     		                messs=messs+strResult.charAt(i);
	    		     		                i=i+1;}
	    		     		                messs=messs+"mb";
	    		     		                i=i+9;
	    		     		                while(strResult.charAt(i)!='<'){
	    		     			                messs=messs+strResult.charAt(i);
	    		     			                i=i+1;}
	    		     			                messs=messs+"mc";
	    		     			                int a=0;
	    		     			        		int b=0;		
	    		     			        		int c=0;
	    		     			        	 i=0;
	    		     			        	   Log.d("UDP Demo",mes); 
	    		     			        		int sign=1;
	    		     			        		char cc=messs.charAt(i);
	    		     			        		while (cc!='a')
	    		     			        		{	i++;	
	    		     			        		cc=messs.charAt(i);
	    		     			        		if (cc=='-')
	    		     			        			sign=-1;
	    		     			        		else if((int)cc>=48&&(int)cc<=57)
	    		     			        			a=((int)cc-48)+a*10;
	    		     			        		}
	    		     			        		a=a*sign;
	    		     			        		sign=1;
	    		     			        		while (cc!='b')
	    		     			        		{		i++;
	    		     			        		cc=messs.charAt(i);
	    		     			        		if (cc=='-')
	    		     			        			sign=-1;
	    		     			        		else if((int)cc>=48&&(int)cc<=57)
	    		     			        			b=((int)cc-48)+b*10;}
	    		     			        		b=b*sign;
	    		     			        		sign=1;
	    		     			        		while (cc!='c')
	    		     			        		{		i++;
	    		     			        		cc=messs.charAt(i);
	    		     			        		if (cc=='-')
	    		     			        			sign=-1;
	    		     			        		else if((int)cc>=48&&(int)cc<=57)
	    		     			        			c=((int)cc-48)+c*10;}
	    		     			        		c=c*sign;
	    		     			        	//   while((a+b+c)==0||(sa==a&&sb==b&&sc==c))
	    		     				         //   	yield();
	    		     				      //      sa=a;
	    		     				       //     sb=b;
	    		     				       //     sc=c;	
	    		     			        	
	    		     			                j=j+1;
	    		     			        		if (j<5)
	    			    			        		messs=message1+"第"+j+"次"+a+"a"+b+"b"+c+"c  ";
	    			    			        		else
	    			    			        			{messs="new"+a+"a"+b+"b"+c+"c";
	    			    			        			j=1;
	    			    			        			}	message1=messs;     
	    		     			                
	    		     			                
	    		     			                mes=messs;
	    		     			           //    Log.e("", ""+a+b+c);           
	    		     			            //  if( chuansong)		
	    				    			        		updateMotorControl(a,b,c);     
	    		     			                
	    		     			         while(temps>System.currentTimeMillis()-10);                
	    		     			                yield();
	    		     			                
	    		     			                
	    		     			                
	    		     	               
	    		     	              }   
	    		     	              else   
	    		     	              {   
	    		     	            	   Log.d("UDP Demo1","Error Response: "+httpResponse.getStatusLine().toString());   
	    		     	              }   
	    		     	            }   
	    		     	            catch (ClientProtocolException e)   
	    		     	            {    
	    		     	            	   Log.d("UDP Demo2",e.getMessage().toString());   
	    		     	              e.printStackTrace();   
	    		     	            }   
	    		     	            catch (IOException e)   
	    		     	            {    
	    		     	            	   Log.d("UDP Demo3",e.getMessage().toString());   
	    		     	              e.printStackTrace();   
	    		     	            }   
	    		     	            catch (Exception e)   
	    		     	            {    
	    		     	            	   Log.d("UDP Demo4",e.getMessage().toString());   
	    		     	              e.printStackTrace();    
	    		     	            }    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                    
	    		                 

	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    		                 
	    			        	
	    			        	
	    		            	 
	    		 }
		  
		    	
		    	
	    		
	    		

	    	
	    	
	    	
	    	
	    
	    }	   

	    		       	 public String eregi_replace(String strFrom, String strTo, String strTarget)  
	    			        {  
	    			          String strPattern = "(?i)"+strFrom;  
	    			          Pattern p = Pattern.compile(strPattern);  
	    			          Matcher m = p.matcher(strTarget);  
	    			          if(m.find())  
	    			          {  
	    			            return strTarget.replaceAll(strFrom, strTo);  
	    			          }  
	    			          else  
	    			          {  
	    			            return strTarget;  
	    			          }  
	    			        }  
	    			            
	    		    	
	    			}	
	    
	    
	    
	    

	    
	    
	    
	    int motorRight;
	    private boolean stopAlreadySent = false;
	    		


	    private List<String> programList;
	    private static final int MAX_PROGRAMS = 20;
	    private String programToStart;
	    private Toast reusableToast;
	    
	    // experimental TTS support
	    private TextToSpeech mTts;
 private final int TTS_CHECK_CODE = 9991;
	private TextView mTextView01;
	private stockThread thread;
	  private ArrayList<String> data;
	private Button bt1;
	private shipinThread thhread;

	//private EditText text2;
	//private EditText text1;
	//private EditText text3;

 /**
  * Asks if bluetooth was switched on during the runtime of the app. For saving 
  * battery we switch it off when the app is terminated.
  * @return true, when bluetooth was switched on by the app
  */
 public static boolean isBtOnByUs() {
     return btOnByUs;
 }

 /**
  * Sets a flag when bluetooth was switched on durin runtime
  * @param btOnByUs true, when bluetooth was switched on by the app
  */
 public static void setBtOnByUs(boolean btOnByUs) {
     MINDdroid.btOnByUs = btOnByUs;
 }

 /**
  * @return true, when currently pairing 
  */
 @Override
 public boolean isPairing() {
     return pairing;
 }

 /**
  * Called when the activity is first created. Inititializes all the
  * graphical views.
  */
 float wx;
 float wy;
private uploadThread thdhread;
 @Override
 public void onCreate(Bundle savedInstanceState) {
 	  super.onCreate(savedInstanceState);
	       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	       requestWindowFeature(Window.FEATURE_NO_TITLE);
	       setContentView(R.layout.aboutbox1280);
	   // mSurfaceView = (SurfaceView) this.findViewById(R.id.surface_camera);
		//	mSurfaceHolder = mSurfaceView.getHolder();
		//	mSurfaceHolder.addCallback(this);
		//	mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			
		//	mCamera = Camera.open();
		//	Camera.Parameters p = mCamera.getParameters();
			//p.setPreviewSize(320, 240);
		//	streamIt=new StreamIt();
		
		//	mCamera.setPreviewCallback(streamIt);

		//	mCamera.setParameters(p);
		//	try {
		//		mCamera.setPreviewDisplay(mSurfaceHolder);
		//	} catch (Exception ex) {
		//	}
			//mCamera.startPreview();
	//
		//	 text1=(EditText)this.findViewById(R.id.editText1);  
		//	 text2=(EditText)this.findViewById(R.id.editText2);  
		//	 text3=(EditText)this.findViewById(R.id.editText3);  
	       DisplayMetrics dm = new DisplayMetrics();  
	        getWindowManager().getDefaultDisplay().getMetrics(dm);  
	          
	   wx = dm.widthPixels/480;  
wy = dm.heightPixels/800;  
if(wy>1)
setContentView(R.layout.aboutbox1280);
else
	//if(wy==1)
	//setContentView(R.layout.aboutbox800);
	//else
		setContentView(R.layout.aboutbox854);
Log.e("",wx +" "+wy);
//AbsoluteLayout
     thisActivity = this;
     mSurfaceView = (SurfaceView) this.findViewById(R.id.surface_camera);
  //   LayoutParams params = mSurfaceView.GET;
     
  //   @SuppressWarnings("deprecation")
	//LinearLayout.LayoutParams new_params = new LinearLayout.LayoutParams((int)(320*wx), (int)(200*wy));
	//new_params.setMargins(0, 0, 0, 0);
	//LinearLayout.LayoutParams new_paramsm = new LinearLayout.LayoutParams((int)(320*wx), (int)(240*wy));
	//new_paramsm.setMargins(0, 0, 400, 300);
	//new_paramsm.setMargins(-1*dm.widthPixels/7, -1*dm.widthPixels/3, 0, 0);
   //  mSurfaceView.setLayoutParams(new_params);
    //  View linearLayout3 = this.findViewById(R.id.linearLayout3);
    //  new_params.setMargins(dm.widthPixels/5, 0, 0, dm.heightPixels/2);
      
   //   View text3 = this.findViewById(R.id.textView2);
    //  LinearLayout.LayoutParams new_paramst = new LinearLayout.LayoutParams((int)(480*wx), (int)(60*wy));
   //   text3.setLayoutParams(new_paramst);
   //   View linearLayout5 = this.findViewById(R.id.linearLayout5);
    //  LinearLayout.LayoutParams new_params5 = new LinearLayout.LayoutParams((int)(480*wx), (int)(120*wy)); 
    //  new_params5.setMargins(0, dm.heightPixels/10, 0, dm.heightPixels/4);
    //  linearLayout5.setLayoutParams(new_params5);
     // View linearLayout4 = this.findViewById(R.id.linearLayout4);
     // new_params.setMargins(0, dm.heightPixels/3, 0,dm.heightPixels/2);
    //  linearLayout3.setLayoutParams(new_params);
    // mSurfaceView.setPadding(width/6, height/10*3, width/6, height/10*4);

    // mSurfaceView.setPadding(100, 200, 300, 400);
//   Log.e("top",""+mSurfaceView.getHeight());
     // mSurfaceView.setPadding(width/6, height/10*3, width/6, height/10*4)；
     
   //  ViewParent view = mSurfaceView.getParent();
   //  mSurfaceView.setMinimumHeight(height);
    // mSurfaceView.setMinimumWidth(width);
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	//	mSurfaceView.setPadding(100, 200, 300, 400);
		
		   Log.e("top",""+mSurfaceView.getHeight());
		thhread=new shipinThread();
       thhread.start();
       thdhread=new uploadThread();
	
       thdhread.start();
		     
     bt1=(Button) findViewById(R.id.button1);
     LinearLayout.LayoutParams new_paramss = new LinearLayout.LayoutParams((int)(240*wx), (int)(60*wy));
     new_paramss.setMargins(dm.widthPixels/5, 0, 0, 0);
     
     
    // LayoutParams params1 =  bt1.getLayoutParams();
     
  //   @SuppressWarnings("deprecation")
//	AbsoluteLayout.LayoutParams new_paramsS = new AbsoluteLayout.LayoutParams((int)(200*wx), (int)(60*wy),(int)(150*wx), (int)(600*wy));
  bt1.setLayoutParams(new_paramss);
     bt1.setOnClickListener(new View.OnClickListener(){ 
     	 
         @Override 
         public void onClick(View view) { 
         //	int a=10;
         //	int b=10;
         //	int c=10;
         
         //	int i=text1.getText().length();
         //	for(int j=1;j<i;j++)
        // 	
        //  a=10*(int)text1.getText().charAt(0)-48;
      //   b=10*(int)text2.getText().charAt(0)-48;
         	//c=10*(int)text3.getText().charAt(0)-48;
         //	updateMotorControl(a,b,c);
         mTextView01 = (TextView) findViewById(R.id.textView1);
       //  LayoutParams params2 =  mTextView01.getLayoutParams();
         
   //      @SuppressWarnings("deprecation")
   // 	AbsoluteLayout.LayoutParams new_paramsS2 = new AbsoluteLayout.LayoutParams((int)(300*wx), (int)(120*wy),(int)(90*wx), (int)(90*wy));
     //    mTextView01.setLayoutParams(new_paramsS2);
     		mTextView01.setText(thread.mes);
     	//	 Intent intent=new Intent();
            //  intent.setClass(MINDdroid.this, AndroidVideo.class);
            //  startActivity(intent);
        
         } 
     }); 

    
   
     //reusableToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

     // experimental TTS support for the lejosMINDdroid project
     //mTts = new TextToSpeech(this,
     //    this  // TextToSpeech.OnInitListener
    //     );
 }

 /**
  * Initialization of the motor commands for the different robot types.
  */
 private void setUpByType() {

 }

 /**
  * Updates the menus and possible buttons when connection status changed.
  */


 /**
  * Creates a new object for communication to the NXT robot via bluetooth and fetches the corresponding handler.
  */
 private void createBTCommunicator() {
     // interestingly BT adapter needs to be obtained by the UI thread - so we pass it in in the constructor
     myBTCommunicator = new BTCommunicator(this, myHandler, BluetoothAdapter.getDefaultAdapter(), getResources());
     btcHandler = myBTCommunicator.getHandler();
 }

 /**
  * Creates and starts the a thread for communication via bluetooth to the NXT robot.
  * @param mac_address The MAC address of the NXT robot.
  */
 private void startBTCommunicator(String mac_address) {
     connected = false;        
     connectingProgressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.connecting_please_wait), true);

     if (myBTCommunicator != null) {
         try {
             myBTCommunicator.destroyNXTconnection();
         }
         catch (IOException e) { }
     }
     createBTCommunicator();
     myBTCommunicator.setMACAddress(mac_address);
     myBTCommunicator.start();
     chuansong=true;

 }

 /**
  * Sends a message for disconnecting to the communcation thread.
  */
 public void destroyBTCommunicator() {

     if (myBTCommunicator != null) {
         sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.DISCONNECT, 0, 0);
         myBTCommunicator = null;
     }

     connected = false;
   
 }


 /**
  * Gets the current connection status.
  * @return the current connection status to the robot.
  */
 public boolean isConnected() {
     return connected;
 }

 /**
  * Method for performing the appropriate action when the ACTION button is pressed shortly.
  */
 public void actionButtonPressed() {
 
    
    jieshou=true;
         // Wolfgang Amadeus Mozart "Zauberfloete - Der Vogelfaenger bin ich ja"
      

         // MOTOR ACTION: forth an back


 }

 /**
  * Method for performing the appropriate action when the ACTION button is long pressed.
  */
 public void actionButtonLongPress() {
 	jieshou=false;
     

 }

 /**
  * Starts a program on the NXT robot.
  * @param name The program name to start. Has to end with .rxe on the LEGO firmware and with .nxj on the 
  *             leJOS NXJ firmware.
  */   
 public void startProgram(String name) {
     // for .rxe programs: get program name, eventually stop this and start the new one delayed
     // is handled in startRXEprogram()
     if (name.endsWith(".rxe")) {
         programToStart = name;        
         sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.GET_PROGRAM_NAME, 0, 0);
         return;
     }
           
     // for .nxj programs: stop bluetooth communication after starting the program
     if (name.endsWith(".nxj")) {
         sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.START_PROGRAM, name);
         destroyBTCommunicator();
         return;
     }        

     // for all other programs: just start the program
     sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.START_PROGRAM, name);
 }

 /**
  * Depending on the status (whether the program runs already) we stop it, wait and restart it again.
  * @param status The current status, 0x00 means that the program is already running.
  */   
 public void startRXEprogram(byte status) {
     if (status == 0x00) {
         sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.STOP_PROGRAM, 0, 0);
         sendBTCmessage(1000, BTCommunicator.START_PROGRAM, programToStart);
     }    
     else {
         sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.START_PROGRAM, programToStart);
     }
 }        

 /**
  * Sends the motor control values to the communcation thread.
  * @param left The power of the left motor from 0 to 100.
  * @param rigth The power of the right motor from 0 to 100.
  */  
 

 
 public void updateMotorControl(int a,int b,int c) {


 sendBTCmessage(BTCommunicator.NO_DELAY, motora,a , 0);
  sendBTCmessage(BTCommunicator.NO_DELAY, motorb, b, 0);
 sendBTCmessage(BTCommunicator.NO_DELAY, motorc, c , 0);          
	// }


                         

 }

 /**
  * Sends the message via the BTCommuncator to the robot.
  * @param delay time to wait before sending the message.
  * @param message the message type (as defined in BTCommucator)
  * @param value1 first parameter
  * @param value2 second parameter
  */   
 void sendBTCmessage(int delay, int message, int value1, int value2) {
     Bundle myBundle = new Bundle();
     myBundle.putInt("message", message);
     myBundle.putInt("value1", value1);
     myBundle.putInt("value2", value2);
     Message myMessage = myHandler.obtainMessage();
     myMessage.setData(myBundle);
     //Log.e("", "第三方"); 
	if (myBTCommunicator != null) {
    
     if (delay == 0)
    	 
         btcHandler.sendMessage(myMessage);

     else
         btcHandler.sendMessageDelayed(myMessage, delay);}
 }

 /**
  * Sends the message via the BTCommuncator to the robot.
  * @param delay time to wait before sending the message.
  * @param message the message type (as defined in BTCommucator)
  * @param String a String parameter
  */       
 void sendBTCmessage(int delay, int message, String name) {
     Bundle myBundle = new Bundle();
     myBundle.putInt("message", message);
     myBundle.putString("name", name);
     Message myMessage = myHandler.obtainMessage();
     myMessage.setData(myBundle);

     if (delay == 0)
         btcHandler.sendMessage(myMessage);
     else
         btcHandler.sendMessageDelayed(myMessage, delay);
 }

 @Override
 public void onResume() {
     super.onResume();

 }

 @Override
 protected void onStart() {
     super.onStart();
  


 if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
} else {
  selectNXT();
}
     thread=new stockThread();
     thread.start();
     

     
     
     
     
     
     
     
 }

 @Override
 protected void onDestroy() {
     super.onDestroy();
     destroyBTCommunicator();
     jieshou=true;
     
     mIsRunning=false;
    this.finish();
 }

 @Override
 public void onPause() {

     destroyBTCommunicator();
     super.onStop();
 }

 @Override
 public void onSaveInstanceState(Bundle icicle) {
     super.onSaveInstanceState(icicle);

 }

 /**
  * Creates the menu items
  */



 /**
  * Handles item selections
  */
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     switch (item.getItemId()) {
         case MENU_TOGGLE_CONNECT:

             if (myBTCommunicator == null || connected == false) {
                 selectNXT();

             } else {
                 destroyBTCommunicator();
           
             }

             return true;
         case MENU_QUIT:
             destroyBTCommunicator();
             finish();

             if (btOnByUs)
                 showToast(R.string.bt_off_message, Toast.LENGTH_SHORT);

             SplashMenu.quitApplication();
             return true;
     }

     return false;
 }

 /**
  * Displays a message as a toast
  * @param textToShow the message
  * @param length the length of the toast to display
  */
 private void showToast(String textToShow, int length) {
     reusableToast.setText(textToShow);
     reusableToast.setDuration(length);
     reusableToast.show();
 }

 /**
  * Displays a message as a toast
  * @param resID the ressource ID to display
  * @param length the length of the toast to display
  */
 private void showToast(int resID, int length) {
     reusableToast.setText(resID);
     reusableToast.setDuration(length);
     reusableToast.show();
 }
 
 /**
  * Receive messages from the BTCommunicator
  */
 final Handler myHandler = new Handler() {
     @Override
     public void handleMessage(Message myMessage) {
         switch (myMessage.getData().getInt("message")) {
             case BTCommunicator.DISPLAY_TOAST:
                 showToast(myMessage.getData().getString("toastText"), Toast.LENGTH_SHORT);
                 break;
             case BTCommunicator.STATE_CONNECTED:
                 connected = true;
                 programList = new ArrayList<String>();
                 connectingProgressDialog.dismiss();
           
                 sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.GET_FIRMWARE_VERSION, 0, 0);
                 break;
             case BTCommunicator.MOTOR_STATE:

                 if (myBTCommunicator != null) {
                     byte[] motorMessage = myBTCommunicator.getReturnMessage();
                     int position = byteToInt(motorMessage[21]) + (byteToInt(motorMessage[22]) << 8) + (byteToInt(motorMessage[23]) << 16)
                                    + (byteToInt(motorMessage[24]) << 24);
                     showToast(getResources().getString(R.string.current_position) + position, Toast.LENGTH_SHORT);
                 }

                 break;

             case BTCommunicator.STATE_CONNECTERROR_PAIRING:
                 connectingProgressDialog.dismiss();
                 destroyBTCommunicator();
                 break;

             case BTCommunicator.STATE_CONNECTERROR:
                 connectingProgressDialog.dismiss();
             case BTCommunicator.STATE_RECEIVEERROR:
             case BTCommunicator.STATE_SENDERROR:

                 destroyBTCommunicator();
                 if (btErrorPending == false) {
                     btErrorPending = true;
                     // inform the user of the error with an AlertDialog
                     AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
                     builder.setTitle(getResources().getString(R.string.bt_error_dialog_title))
                     .setMessage(getResources().getString(R.string.bt_error_dialog_message)).setCancelable(false)
                     .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int id) {
                             btErrorPending = false;
                             dialog.cancel();
                             selectNXT();
                         }
                     });
                     builder.create().show();
                 }

                 break;

             case BTCommunicator.FIRMWARE_VERSION:

                 if (myBTCommunicator != null) {
                     byte[] firmwareMessage = myBTCommunicator.getReturnMessage();
                     // check if we know the firmware
                     boolean isLejosMindDroid = true;
                     for (int pos=0; pos<4; pos++) {
                         if (firmwareMessage[pos + 3] != LCPMessage.FIRMWARE_VERSION_LEJOSMINDDROID[pos]) {
                             isLejosMindDroid = false;
                             break;
                         }
                     }
                    
              
                     // afterwards we search for all files on the robot
                     sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.FIND_FILES, 0, 0);
                 }

                 break;

             case BTCommunicator.FIND_FILES:

                 if (myBTCommunicator != null) {
                     byte[] fileMessage = myBTCommunicator.getReturnMessage();
                     String fileName = new String(fileMessage, 4, 20);
                     fileName = fileName.replaceAll("\0","");


                     // find next entry with appropriate handle, 
                     // limit number of programs (in case of error (endless loop))
                     if (programList.size() <= MAX_PROGRAMS)
                         sendBTCmessage(BTCommunicator.NO_DELAY, BTCommunicator.FIND_FILES,
                                        1, byteToInt(fileMessage[3]));
                 }

                 break;
                 
             case BTCommunicator.PROGRAM_NAME:
                 if (myBTCommunicator != null) {
                     byte[] returnMessage = myBTCommunicator.getReturnMessage();
                     startRXEprogram(returnMessage[2]);
                 }
                 
                 break;
                 
             case BTCommunicator.SAY_TEXT:
                 if (myBTCommunicator != null) {
                     byte[] textMessage = myBTCommunicator.getReturnMessage();
                     // evaluate control byte 
                     byte controlByte = textMessage[2];
                     // BIT7: Language
                     if ((controlByte & 0x80) == 0x00) 
                         mTts.setLanguage(Locale.US);
                     else
                         mTts.setLanguage(Locale.getDefault());
                     // BIT6: Pitch
                     if ((controlByte & 0x40) == 0x00)
                         mTts.setPitch(1.0f);
                     else
                         mTts.setPitch(0.75f);
                     // BIT0-3: Speech Rate    
                     switch (controlByte & 0x0f) {
                         case 0x01: 
                             mTts.setSpeechRate(1.5f);
                             break;                                 
                         case 0x02: 
                             mTts.setSpeechRate(0.75f);
                             break;
                         
                         default: mTts.setSpeechRate(1.0f);
                             break;
                     }
                                                                                                     
                     String ttsText = new String(textMessage, 3, 19);
                     ttsText = ttsText.replaceAll("\0","");
                     showToast(ttsText, Toast.LENGTH_SHORT);
                     mTts.speak(ttsText, TextToSpeech.QUEUE_FLUSH, null);
                 }
                 
                 break;                    
                 
             case BTCommunicator.VIBRATE_PHONE:
                 if (myBTCommunicator != null) {
                     byte[] vibrateMessage = myBTCommunicator.getReturnMessage();
                     Vibrator myVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                     myVibrator.vibrate(vibrateMessage[2]*10);
                 }
                 
                 break;
         }
     }
 };

 private int byteToInt(byte byteValue) {
     int intValue = (byteValue & (byte) 0x7f);

     if ((byteValue & (byte) 0x80) != 0)
         intValue |= 0x80;

     return intValue;
 }

 void selectNXT() {
     Intent serverIntent = new Intent(this, DeviceListActivity.class);
     startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
 }

 @Override
 public void onActivityResult(int requestCode, int resultCode, Intent data) {
     switch (requestCode) {
         case REQUEST_CONNECT_DEVICE:

             // When DeviceListActivity returns with a device to connect
             if (resultCode == Activity.RESULT_OK) {
                 // Get the device MAC address and start a new bt communicator thread
                 String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                 pairing = data.getExtras().getBoolean(DeviceListActivity.PAIRING);
                 startBTCommunicator(address);
             }
             
             break;
             
         case REQUEST_ENABLE_BT:

             // When the request to enable Bluetooth returns
             switch (resultCode) {
                 case Activity.RESULT_OK:
                     btOnByUs = true;
                     selectNXT();
                     break;
                 case Activity.RESULT_CANCELED:
                     showToast(R.string.bt_needs_to_be_enabled, Toast.LENGTH_SHORT);
                     finish();
                     break;
                 default:
                     showToast(R.string.problem_at_connecting, Toast.LENGTH_SHORT);
                     finish();
                     break;
             }
             
             break;

         // will not be called now, since the check intent is not generated                
         case TTS_CHECK_CODE:
             if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                 // success, create the TTS instance
                 mTts = new TextToSpeech(this, this);
             } 
             else {
                 // missing data, install it
                 Intent installIntent = new Intent();
                 installIntent.setAction(
                     TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                 startActivity(installIntent);
             }
             
             break;                
     }
 }

 /**
  * Initializing of the TTS engine.
  */
 public void onInit(int status) {
     // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
     if (status == TextToSpeech.SUCCESS) {
         // Set preferred language to US english.
         // Note that a language may not be available, and the result will indicate this.
         int result = mTts.setLanguage(Locale.US);
         // Try this someday for some interesting results.
         if (result == TextToSpeech.LANG_MISSING_DATA ||
             result == TextToSpeech.LANG_NOT_SUPPORTED) {            
             // Language data is missing or the language is not supported.
    
         } 
     } else {
         // Initialization failed.
       
     }
 }
	
 public static Bitmap rawByteArray2RGBABitmap2(byte[] data, int width, int height) {  
     int frameSize = width * height;  
     int[] rgba = new int[frameSize];  
  
 	
	    for (int j = 0, yp = 0; j < height; j++) {
	        int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
	        for (int i = 0; i < width; i++, yp++) {
	            int y = (0xff & ((int)  data[yp])) - 16;
	            if (y < 0) y = 0;
	            if ((i & 1) == 0) {
	                v = (0xff &  data[uvp++]) - 128;
	                u = (0xff &  data[uvp++]) - 128;
	            }
	 
	            int y1192 = 1192 * y;
	            int r = (y1192 + 1634 * v);
	            int g = (y1192 - 833 * v - 400 * u);
	            int b = (y1192 + 2066 * u);
	 
	            if (r < 0) r = 0; else if (r > 262143) r = 262143;
	            if (g < 0) g = 0; else if (g > 262143) g = 262143;
	            if (b < 0) b = 0; else if (b > 262143) b = 262143;
	 
	            rgba[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
	       
	        
             }  }

     Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);  
     bmp.setPixels(rgba, 0 , width, 0, 0, width, height);  
     return bmp;  
 }
 class uploadThread extends Thread{
		private long startTime;
		private long endTime;
		private int diffTime;
	 public void run() {
			
			int i=0;
			
			while(mIsRunning)
			{startTime = System.currentTimeMillis(); 
			
			i++;
				picPath=Environment.getExternalStorageDirectory()+ "/temple"+"/temp.jpg";
				File file = new File(picPath);
				if(file!=null)
				uploadFile(file, requestURL); 
				Log.e("时间df",i+"ddsfd "+(System.currentTimeMillis()- 	startTime)); 	
			}
			
	 
	 
	 
	 }
	 
 }
	 class shipinThread extends Thread{
		private long startTime;
		private long endTime;
		private int diffTime;
		public void run() {
	
			int i=0;
			
			while(mIsRunning)
			{
			
			startTime = System.currentTimeMillis(); 
			
			i++;
			
			

		String pictureDir = "";
			
			FileOutputStream fos = null;
			
			BufferedOutputStream bos = null;
			
			ByteArrayOutputStream baos = null;
		//	  Log.e("sss", "try");
			long hhhhhh=System.currentTimeMillis();
			try {
				
			    baos = new ByteArrayOutputStream();
		
			    streamIt.bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
	
			    byte[] byteArray = baos.toByteArray();
			
			    String saveDir = Environment.getExternalStorageDirectory()
			
			            + "/temple";
			
			    File dir = new File(saveDir);
			   // Log.i("时间2",""+(System.currentTimeMillis()- hhhhhh));    
			  //  Log.e("sss", "dir");
			    if (dir.exists()) { dir.delete(); }
if (!dir.exists()) {dir.mkdir();}
File file = new File(saveDir, "temp.jpg");
			   // Log.e("sss", "file");
 file.delete();
 if (!file.exists()) { file.createNewFile(); }
fos = new FileOutputStream(file);
 bos = new BufferedOutputStream(fos);
bos.write(byteArray);
pictureDir = file.getPath();
} catch (Exception e) {
 e.printStackTrace();
} finally {if (baos != null) {
try {baos.close();
 } catch (Exception e) {
  e.printStackTrace();
 }
}

//Log.i("时间3",""+(System.currentTimeMillis()- hhhhhh)); 
if (bos != null) { try {  bos.close();
 } catch (Exception e) {e.printStackTrace(); }
 }
if (fos != null) { try {  fos.close();} catch (Exception e) { e.printStackTrace();
 } }}
//startTime = System.currentTimeMillis(); 		
picPath=Environment.getExternalStorageDirectory()+ "/temple"+"/temp.jpg";
File file = new File(picPath);


Log.i("时间4",""+(System.currentTimeMillis()- hhhhhh)); 
if(file!=null){
	//String request =uploadFile(file, requestURL);
}

Log.i("时间5",""+(System.currentTimeMillis()- hhhhhh)); 
endTime = System.currentTimeMillis();   diffTime = (int) (endTime - startTime);  
	        Log.i("shijiansd", ""+diffTime);
	        /** 确保每次更新时间为50帧 **/  
	        while (diffTime <= 50) {   diffTime = (int) (System.currentTimeMillis() - startTime);  
	yield();  }
  Log.e("时间",i+"dd "+(System.currentTimeMillis()- 	startTime)); 	
			}
		
		
	
	
	}
	 }
	private static final String TAG = "uploadFile";
	private static final int TIME_OUT = 10*1000;   //超时时间
	private static final String CHARSET = "utf-8"; //设置编码
	/**
	 * android上传文件到服务器
	 * @param file  需要上传的文件
	 * @param RequestURL  请求的rul
	 * @return  返回响应的内容
	 */
	public static String uploadFile(File file,String RequestURL)
	{
		String result = null;
		String  BOUNDARY =  UUID.randomUUID().toString();  //边界标识   随机生成
		String PREFIX = "--" , LINE_END = "\r\n"; 
		String CONTENT_TYPE = "multipart/form-data";   //内容类型
		long hhhhhh=System.currentTimeMillis();	
		try {
			URL url = new URL(RequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(300);
			conn.setConnectTimeout(300);
			conn.setDoInput(true);  //允许输入流
			conn.setDoOutput(true); //允许输出流
			conn.setUseCaches(false);  //不允许使用缓存
			conn.setRequestMethod("POST");  //请求方式
			conn.setRequestProperty("Charset", CHARSET);  //设置编码
			conn.setRequestProperty("connection", "keep-alive");   
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY); 
		//	Log.i("时间6",""+(System.currentTimeMillis()- hhhhhh)); 	
			if(file!=null)
			{
				/**
				 * 当文件不为空，把文件包装并且上传
				 */
				DataOutputStream dos = new DataOutputStream( conn.getOutputStream());
				StringBuffer sb = new StringBuffer();
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINE_END);
				/**
				 * 这里重点注意：
				 * name里面的值为服务器端需要key   只有这个key 才可以得到对应的文件
				 * filename是文件的名字，包含后缀名的   比如:abc.png  
				 */
				
				sb.append("Content-Disposition: form-data; name=\"form_data\"; filename=\""+file.getName()+"\""+LINE_END); 
				sb.append("Content-Type: image/jpeg; charset="+CHARSET+LINE_END);
				sb.append(LINE_END);
		//		Log.i("时间7",""+(System.currentTimeMillis()- hhhhhh)); 	
				dos.write(sb.toString().getBytes());
				//Log.e(TAG, sb.toString());
				InputStream is = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len=is.read(bytes))!=-1)
				{
			
					dos.write(bytes, 0, len);
				}
				is.close();
				dos.write(LINE_END.getBytes());
				byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
				dos.write(end_data);
				dos.flush();
			Log.i("时间8",""+(System.currentTimeMillis()- hhhhhh)); 
				/**
				 * 获取响应码  200=成功
				 * 当响应成功，获取响应的流  
				 */
				int res = conn.getResponseCode();  
			

				//	Log.e(TAG, "request success");
					InputStream input =  conn.getInputStream();
					StringBuffer sb1= new StringBuffer();
					int ss ;
					while((ss=input.read())!=-1)
					{
						sb1.append((char)ss);
					}
					result = sb1.toString();
				//	Log.e(TAG, "result : "+ result);
					Log.i("时间9",""+(System.currentTimeMillis()- hhhhhh)); 
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	Log.i("时间10",""+(System.currentTimeMillis()- hhhhhh)); 
		return result;
	}
	
	
	
class StreamIt implements Camera.PreviewCallback {
	public byte[] yuv420sp =null;
	private boolean t=true;
	  Bitmap bmp=null;
	  int i=1;
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub

		  Size size = camera.getParameters().getPreviewSize();          
		    try{  long temps=System.currentTimeMillis();
		    i++;
		        YuvImage image = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);  
		        if(image!=null){  
		            ByteArrayOutputStream stream = new ByteArrayOutputStream();  
		           // Log.e("temps",i+" "+(System.currentTimeMillis()-temps)); 
		            image.compressToJpeg(new Rect(0, 0, size.width, size.height), 100, stream);
		         //   Log.e("temps",i+" "+(System.currentTimeMillis()-temps)); 
		     
		            bmp = BitmapFactory.decodeByteArray(stream.toByteArray(), 0, stream.size()); 
		           // Log.e("temps",i+" "+(System.currentTimeMillis()-temps)); 
		           bmp = Bitmap.createScaledBitmap(bmp, 240,320, true);
		            Log.e("temps",i+" "+(System.currentTimeMillis()-temps)); 
		         //   Log.e("Sys","Error:");  
	                 stream.close();  
		        }  
		      //  Log.i("temps",i+" "+(System.currentTimeMillis()-temps)); 
		    }catch(Exception ex){  
		        Log.e("Sys","Error:"+ex.getMessage());  
	}}
}


@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	
}

@Override
public void surfaceChanged(SurfaceHolder holder, int format, int width,
		int height) {
	if (mPreviewRunning) {
		mCamera.stopPreview();
	}
	Log.e("wocap","1");
	Camera.Parameters p = mCamera.getParameters();
//p.setPreviewSize(width, height);
	//p.set("rotation", 90);
	streamIt=new StreamIt();
www=width;
hhh=height;
	mCamera.setPreviewCallback(streamIt);
	Log.e("wocap","2");
	mCamera.setParameters(p);
	Log.e("wocap","3");
	try {
		mCamera.setPreviewDisplay(holder);
	} catch (Exception ex) {
	}
 mCamera.setDisplayOrientation(90);
	mCamera.startPreview();
	
	mPreviewRunning = true;
}


public void surfaceCreated(SurfaceHolder holder) {
	//Log.e("wocap","1");
	 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
		   for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
		    CameraInfo info = new CameraInfo();
		    Camera.getCameraInfo(i, info);
		    if (info.facing == CameraInfo.CAMERA_FACING_BACK){//这就是前置摄像头，亲。
		    	mCamera = Camera.open(i);
		   // 	Log.e("wocap",""+i);
		    }
		   }
		  }
		  if (mCamera == null) {Log.e("wocap","3");
			mCamera = Camera.open();
		  } ;
}


public void surfaceDestroyed(SurfaceHolder holder) {
	mCamera.setPreviewCallback(null) ;
	 jieshou=true;
	mIsRunning=false;
	mCamera.stopPreview();
	mPreviewRunning = false;
	mCamera.release();
	
}

@Override
public void onConfigurationChanged(Configuration newConfig) {
	try {
		super.onConfigurationChanged(newConfig);
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
		}
	} catch (Exception ex) {
	}
}

}
/**
 * 中欧

 **/

package com.lego.minddroid;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.view.Menu;

import android.view.Window;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.lego.minddroid.GameView.GameThread;
import com.lego.minddroid.MINDdroid.stockThread;


public class MINDdroid extends Activity {
	 public static Bitmap bitmap=null;
	 public static boolean bitmaphua=false;
	 public static boolean hua=false;
	   public static final int UPDATE_TIME = 200;
	    public static final int MENU_TOGGLE_CONNECT = Menu.FIRST;
	    public static final int MENU_QUIT = Menu.FIRST + 1;
	    public static boolean fasong=false;
	    public static int kaiqi=-1;
public static String IPadres;
	public int ma=0;
	public int mb=0;
	public int mc=0;
	public long mt;
	    private GameView mView;
		static boolean suo;
	    int mRobotType;
	    int motorLeft;
	 
	    class stockThread extends Thread{  //  DatagramSocket s = null;
		private int sc=0;
		private int sb=0;
		private int sa=0; 
	    	public stockThread(){
	    	
	    		mt=System.currentTimeMillis();
	    		
	    		
	    	}
	    	public void run(){	
	    
	            String uriAPI = "http://kekebox.com/lego/get.php";
	            HttpGet httpRequest = new HttpGet(uriAPI);

	            while(fasong==false){

	            while((ma+mb+mc)==0||(sa==ma&&sb==mb&&sc==mc))
	            	yield();
	            if((ma+mb+mc)==1)
	           {ma=0;
	            mb=0;
	            mc=0;}
	           
	            	sa=ma;	            sb=mb;	            sc=mc;
	            int isid=(int)(Math.random()*100);
	            uriAPI = "http://kekebox.com/lego/insert.php?motor_A="+ma+"&motor_B="+mb+"&motor_C="+mc+"&sid="+isid;
	            httpRequest = new HttpGet(uriAPI);
	            try   
	            {   
	              HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);   
	              if(httpResponse.getStatusLine().getStatusCode() == 200)    
	              {   
	                String strResult = EntityUtils.toString(httpResponse.getEntity());  
	                strResult = eregi_replace("(/r/n|/r|/n|/n/r)","",strResult);

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
  
	    class bmpThread extends Thread{
	    	
	    	

			private String CodeImageUrl;
			private long startTime;
	
		
	   
	    	public void run(){
	    		
	    		while(fasong==false){
	    			
	    			try {
						bitmap=getCodeImage();
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}

	    	}
	    	

	    	private Bitmap getCodeImage() throws ClientProtocolException, IOException 
	    	   { startTime = System.currentTimeMillis(); 
	 
	    		CodeImageUrl="http://kekebox.com/lego/getimg_nb.php"; 
	    		  		

	    HttpClient client = new DefaultHttpClient(); 
	    	    HttpGet httprequest=new HttpGet(CodeImageUrl); 
	    	    httprequest.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,200); 
	    	    httprequest.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 200);
	    	   // httprequest.getHttpConnectionManager().getParams().setConnectionTimeout400); 
	    	  //  httprequest.getHttpConnectionManager().getParams().setSoTimeout(400);
	    	    HttpResponse httpResponse=client.execute(httprequest); 
	    	
	    	    long Timeuse = System.currentTimeMillis()-startTime; 
	    	 	  Log.e("请求",(int)(Timeuse)+""); 
	    	    if(httpResponse.getStatusLine().getStatusCode()==200) 
	    	    { suo=true;
	    	        byte[] data=EntityUtils.toByteArray(httpResponse.getEntity()); 
	    	        Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length); 
	    	  //      Log.i("height", bitmap.getHeight()+""); 
	    	        try {
	    	       bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(),bitmap.getHeight(), true);
	    	       suo=false;
	    	       bitmaphua=true; 
	    	        }
	    	        catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	    	     
	    	   Timeuse = System.currentTimeMillis()-startTime; 
	    	 	  Log.e("接受",(int)(Timeuse)+""); 
	    	  return bitmap; 
	    
	    	    } 
	    	    else 
	    	        return null; 
	    	     
	    	   } 
	    	
	    	
	    	

	    	
	    
	   	  
	    	
	    	
	    	
	    	

	    	
	    }
	    
	    
	    
	    
	    
	     MyHandler mHandler = null ;
	    int motorRight;
	    private boolean stopAlreadySent = false;
		private MINDdroid thisActivity;
		private Toast reusableToast;
		private stockThread thread;
		private bmpThread thread2;
	 	public String mesbb="19";

	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        thisActivity = this;

	        requestWindowFeature(Window.FEATURE_NO_TITLE);

	        mView = new GameView(getApplicationContext(), this);
	        mView.setFocusable(true);
	        setContentView(mView);

	    }  


    public void actionButtonPressed() {

         
    	ma=0;mb=0;mc=1;

    }


    public void actionButtonLongPress() {//fasong=false;
    ma=0;mb=0;mc=1;
       

    }

  
    public void updateMotorControl(int a, int b,int c) {

       
            // don't send motor stop twice
            if ((a == ma) && (b == mb)&&(c== mc)){
               kaiqi=-1;
            }
            else if((a == 0) && (b == 0)&&(c== 0))
            	kaiqi=-1;
            else{kaiqi=1;        
ma=a;
mb=b;
mc=c;}
         
       
       
    }



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
        thread = new stockThread();
        thread2 = new  bmpThread();
	       thread.start();
	      thread2.start();
	      fasong=false;
//Log.e("",fasong+"");
    }
    final Handler myHandler = new Handler();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        fasong=true;
        bitmaphua=false;
        Log.e("","Destroy");
       this.finish();
    }

    @Override
    public void onPause() {
        Log.e("","Pause");
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);

    }

    private class MyHandler extends Handler{             
        public MyHandler(Looper looper){
               super (looper);
        }
        @Override
        public void handleMessage(Message msg) { // 处理消息
            mesbb=msg. obj .toString();
            Log.i("msg", mesbb); 
        }       }     
   
}

/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program takes weight and Speed of object and compares this object in free fall to an object hitting 
a solid abject at a certain speed, then extrapolates the height that the object would have to fall from
to be equivalent

Main entry starting point for program, is the launcher.
shows a splash screen before main program is activated through an intent
Uses canvas to draw the splash to the screen.

*/





package com.jonathan.ass1speed;

import android.annotation.SuppressLint;
import android.app.Activity;  
import android.content.Context;
import android.content.Intent;  
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;  
import android.os.Handler;  
import android.os.Message;  
import android.view.View;
      
    @SuppressLint("HandlerLeak")
	public class Splash extends Activity { 
    	GraphicsView graphicsView;

        //for stopping the splash screen   
        private static final int STOPSPLASH = 0;  
        //time  in milliseconds for the  splash screen to be visible   
        private static final long SPLASHTIME = 3000;  
      
        //handler for splash screen  
        private Handler splashHandler = new Handler() {  
             @Override  
             public void handleMessage(Message msg) { 
            	 //if argument passed in msg.what is STOPFLASH =(0) then stop 
                  switch (msg.what) {  
                    case STOPSPLASH:  
                        //new intent to stop splash and call mainscreen activity  
                        Intent intent = new Intent(getApplicationContext(),   
                        		MainScreen.class);  
                        startActivity(intent);  
                            Splash.this.finish(); 
                        break;  
                  }  
                  super.handleMessage(msg);  
             }  
        };  
          
        @Override  
        public void onCreate(Bundle savedInstanceState) {  
            super.onCreate(savedInstanceState); 
            // create graphic view object from 
            graphicsView = new GraphicsView(this);
            //set the content view to the graphics view custom class object
    		setContentView(graphicsView);
             
            //Generating  a message then sending it to splash handler  
            Message msg = new Message();  
            msg.what = STOPSPLASH;  
            // set a delay so the splash screen will show for a certain period of time
            splashHandler.sendMessageDelayed(msg, SPLASHTIME);  
            
        } 
        
        //Graphics View class for splash screen display, using a canvas
        private class GraphicsView extends View{
    		public GraphicsView(Context context){
    			super(context);
    		}

    		@Override protected void onDraw(Canvas canvas) {
    			super.onDraw(canvas);

    			//set up x and y axis
    			int x = 0;
    			int y = 0;
    			Paint paint = new Paint();
    			paint.setStyle(Paint.Style.FILL);

    			// make the entire canvas black
    			paint.setColor(Color.BLACK);
    			canvas.drawPaint(paint);
    			
    			// draw the appname in a stroke style in red
    			paint.setStyle(Paint.Style.STROKE);
    			paint.setStrokeWidth(1);
    			paint.setColor(Color.RED);
    			paint.setAntiAlias(true);
    			paint.setTextSize(50);
    			canvas.drawText("Ass1Speed", 110, 75, paint);
    			
    			// change the x and y axis for new location
    			// for a serpate text display
    			x = 25;
    			y = 200;
    			paint.setColor(Color.GRAY);
    			paint.setTextSize(25);
    			String str2rotate = "Assigment 1 Mobile App Devlopement";
    			paint.setStyle(Paint.Style.FILL);
    			canvas.drawText(str2rotate, x, y, paint);

    			canvas.restore();



    		}
    	}    
        
        
    }  
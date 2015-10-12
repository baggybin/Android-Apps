/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address



Displays Previous Booking details using flings to see the different parts


*/










package com.jonathan.flight;

import java.util.UUID;



import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.text.Html;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import android.view.MotionEvent;

import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector.OnGestureListener;

import android.widget.TextView;
import android.widget.Toast;

import android.widget.ViewFlipper;

public class PreviousBooking extends Activity implements OnGestureListener {

	protected GestureDetector gestureScanner;

	protected ViewFlipper vFliffer;
	
	private TextView tv1, tv2, tv3;

	private static final int SWIPE_MIN = 120;

    private static final int SWIPE_THRESHOLD_SPEED = 200;
    
    private  String htmlTextVF1, htmlTextVF2,htmlTextVF3; 
    
    private Storage obj;
    
    private String uuid;
    

    
    
    ////////////menus
    
    
    private final int ID_DEFAULT=999;
    private final int ID_1=1, ID_2=2;
    private String[] choices = {"Start Again", "Change Date" };
    
    private static TextView bv; 
    
    

	@SuppressWarnings("deprecation")
	@Override

	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_previous_booking);
		
		
		  Intent i = getIntent();
			
		  obj = (Storage) i.getSerializableExtra("obj");
		
		


        bv = (TextView) findViewById(R.id.p_context_text);
        registerForContextMenu((View) bv);
		
		gestureScanner = new GestureDetector(this);


		vFliffer = (ViewFlipper) findViewById(R.id.p_pages);
		
		uuid = UUID.randomUUID().toString();

	      String htmlArrAirport;
	      if(obj.getSingle() == true)
	      {
	    	htmlArrAirport ="n/a";
	      }
	      else
	      {
	    	htmlArrAirport = obj.getArrAirport();
	      }
	      
	      String htmlReturnTime;
	      if(obj.getSingle() == true)
	      {
	    	  htmlReturnTime ="n/a";
	      }
	      else
	      {
	    	  htmlReturnTime = obj.getReturnTime();
	      }
	      
	      int totalPrice = obj.getAdultTotalPrice() + obj.getChildTotalprice();
	      
	      
	      String htmlReturnDate;
	      if(obj.getSingle() == true)
	      {
	    	  htmlReturnDate = "n/a";
	      }
	      else
	      {
	    	  htmlReturnDate = obj.getDay2()+ "/" + obj.getMonth2() + "/" + obj.getYr2();
	      }
		
		htmlTextVF1 = 
	    		"<body><h1>Booking</h1><p>This is your confirmation " +
	            "Number " + "<br/>" +
	            "<strong>" + uuid.substring(1, 16) + "<br/>" +
	            "</strong>Please have this for boarding.&nbsp;</p> <br/>" +
	            
	            "<p>Departure Airport" +
	            "<br/> <strong>" + obj.getDepAirport() +"</strong> <br/>"+
	            "Arrival Airport" +
	            "<br/> <strong>" + htmlArrAirport +"</strong> <br/>"+
	            "Departure Date <br/>"+ 
	            "<strong>"+ obj.getDay()+ "/" + obj.getMonth() + "/" + obj.getYr()+"</strong>"+
	            "<br>"+
	            "Return Date <br/>"+ 
	            "<strong>"+ htmlReturnDate+"</strong><br/>"+
	            "Departure Time <br/>"+ 
	            "<strong>"+ obj.getDepartTime()+"</strong>"+
	            "<br>"+
	            "Return Time <br/>"+ 
	            "<strong>"+ htmlReturnTime +"</strong>"+
	            "<br>"+
	            "Total Price <br/>"+ 
	            "<strong>"+ totalPrice +"</strong>"+
	            "<br>"+
	            "Booking Class <br/>"+ 
	            "<strong>"+ obj.getChoosenClass() +"</strong>"+
	            "<br>"+
	            "  </p>" +
	            
	            "<blockquote>Booking Confirmation from <a href=\"www.flight.com\">" +
	            "flight.com<a></blockquote></body>";
		
		obj.setHtmlTextVF1(htmlTextVF1);
	
      
      tv1 = (TextView) findViewById(R.id.p_vf_page1);      
      tv1.setText(Html.fromHtml(htmlTextVF1));
      
      
      
		htmlTextVF2 = 
				"<body><h1>Booking</h1><p>This is your confirmation " +
			            "Number " + "<br/>" +
			            "<strong>" + uuid.substring(1, 16) + "<br/>" +
			            "</strong>Please have this for boarding.&nbsp;</p> <br/>" +
			            
			            "<h2>Billing Address</h2><p>For <strong>"+obj.getNameCC()+"<strong/>"
			            + "<br/>"+
			            
	           
	            "<p> House" +
	            "<br/> <strong>" + obj.getHouse() +"</strong> <br/>"+
	            "Street" +
	            "<br/> <strong>" + obj.getStreet() +"</strong> <br/>"+
	            "Town <br/>"+ 
	            "<strong>"+ obj.getTown()+ "</strong>"+
	            "<br>"+
	            "County <br/>"+ 
	            "<strong>"+ obj.getTown()+"</strong><br/>"+    
	            
	            "  </p>" +
	            
	            "<blockquote>Booking Confirmation from <a href=\"www.flight.com\">" +
	            "flight.com<a></blockquote></body>";
      
      obj.setHtmlTextVF2(htmlTextVF2);
      
      
      
      tv2 =(TextView) findViewById(R.id.p_vf_page2);
      tv2.setText(Html.fromHtml(htmlTextVF2));
      
      
      
      
		htmlTextVF3 = 
				"<body><h1>Booking Conditions</h1><p>This is your confirmation " +
			            "Number " + "<br/>" +
			            "<strong>" + uuid.substring(1, 16) + "<br/>" +
			            "</strong>Please have this for boarding.&nbsp;</p> <br/>" +
			      
			            
	           
	            "<p> Your Contract with us" +
	            "<br/> <strong>" + "Our acceptance of your deposit or payment forms a contract between us and the party leader (first named passenger) acting on behalf of all passengers in the party. These booking conditions apply. You are deemed to have accepted these conditions and, where applicable, the insurance details unless you write to us by return recorded delivery post to state otherwise. No other conditions will apply to this contract unless confirmed by us to you in writing. " +"</strong> <br/>"+
	  
	            "  </p>" +
	            
	            "<blockquote>Booking Confirmation from <a href=\"www.flight.com\">" +
	            "flight.com<a></blockquote></body>";
      
		
		
		obj.setHtmlTextVF3(htmlTextVF3);
      tv3 =(TextView) findViewById(R.id.p_vf_page3);
      tv3.setText(Html.fromHtml(htmlTextVF3));
        
    
	}

	@Override

	public boolean onTouchEvent(MotionEvent me){

		return gestureScanner.onTouchEvent(me);

	}

	public boolean onDown(MotionEvent e){

		return true;

	}

	public boolean onFling(MotionEvent e1,MotionEvent e2,float X,float Y){

		try {

            if(e1.getX() > e2.getX() && Math.abs(e1.getX() - e2.getX()) 
            		> SWIPE_MIN && Math.abs(X) > SWIPE_THRESHOLD_SPEED) {

                Toast.makeText(this.getApplicationContext(), "<<<<<", Toast.LENGTH_SHORT).show();

                vFliffer.showPrevious();

            }else if (e1.getX() < e2.getX() && e2.getX() - e1.getX() 
            		> SWIPE_MIN && Math.abs(Y) > SWIPE_THRESHOLD_SPEED) {

                Toast.makeText(this.getApplicationContext(), ">>>>>", Toast.LENGTH_SHORT).show();

                vFliffer.showNext();

            }

        } catch (Exception e) {


        }

        return true;

		

	}

	public void onLongPress(MotionEvent e){
		
	}

	

	public boolean onScroll(MotionEvent e1,MotionEvent e2,
			float distanceX,float distanceY){

		return true;

	}

	public void onShowPress(MotionEvent e){
		
	}

	public boolean onSingleTapUp(MotionEvent e)
	{ return true;}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.booking_details, menu);
		return true;
	}
	
	
	
	
	
	
	
	 @Override
	 
	    public void onCreateContextMenu(ContextMenu menu, View v,
	            ContextMenuInfo menuInfo) {
	        super.onCreateContextMenu(menu, v, menuInfo);
	        System.out.println("in onCreateContextMenu");
	        
	        if(v.getId() == R.id.p_context_text) {
	        	
	            SubMenu textMenu = menu.addSubMenu("Back to");
	            textMenu.add(0, ID_1, 0, choices[0]);
	            textMenu.add(0, ID_2, 0, choices[1]);
	            menu.add(0, ID_DEFAULT, 0,  "Exit");
	            
	        }   
	    }

	    @Override
	    
	    public boolean onContextItemSelected(MenuItem item) {
	        switch(item.getItemId()) 
	        {
	        case ID_DEFAULT:
	        	
	            super.finish();
	        	return true;
	        	
	        case ID_1:
	        	
	        	startBeginning();
	        	return true;
	        	
	        case ID_2:
	        	
	        	startChangeDate();
	        	return true;
	        }
	        return super.onContextItemSelected(item);
	    }
	    
	
	
	
		private void startBeginning() {
			
			
		    Intent launchStart = new Intent(this, MainFlight.class);
		    startActivity(launchStart);

		    }
	

	
	
		private void startChangeDate() {
		
		
	    Intent launchDate = new Intent(this, Date.class);
	    startActivity(launchDate);

	    }
	
	
}




/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address



Displays Booking details using flings to see the different parts
also saves the Objects and So the Booking to Storage


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

public class BookingDetails extends Activity implements OnGestureListener {

	protected GestureDetector gestureScanner;

	protected ViewFlipper vFliffer;
	
	private TextView tv1, tv2, tv3;

	private static final int SWIPE_MIN = 120;

    private static final int SWIPE_THRESHOLD_SPEED = 200;
    
    
    // Strings to store HTML/strings to format and populate views
    
    private  String htmlTextVF1, htmlTextVF2,htmlTextVF3; 
    
    private Storage obj;
    
    // Uniquly generated number
    
    private String uuid;
    
	private VarObjectStore readWriteObj;

    
    
    ////////////menus
    
    
    private final int ID_DEFAULT=999;
    private final int ID_1=1, ID_2=2;
    private String[] choices  = {"Start Again",
    		"Change Date" };
    
    //= {getApplicationContext().getResources().getString(R.string.start_again),
    	//	getApplicationContext().getResources().getString(R.string.change_date) };
    
    private static TextView bv; /// does this need to be static>???? well works so leave for minute
    
    
    

	@SuppressWarnings("deprecation")
	@Override

	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booking_details);
		
		
		// object for writing to storage later
		
		readWriteObj = new VarObjectStore(this);
		
		
		
	

		
		// textview 
		
		
		
        bv = (TextView) findViewById(R.id.context_text);
        
        // register it for context menu on long click
        registerForContextMenu((View) bv);
		
        
        
		gestureScanner = new GestureDetector(this);

		// a view flipperView to move through the view for displaying
		
		vFliffer = (ViewFlipper) findViewById(R.id.pages);
		
		
		// generate the Random number for Unique 
		
		uuid = UUID.randomUUID().toString();

		
		  Intent i = getIntent();
			
	      obj = (Storage) i.getSerializableExtra("obj");
	      
	      
	      
	      // again some checks foe single or return trips
	      // for creating content for the textviews
	      
	      
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
		
	      
	      
	      // using HTML markup create the Conent for the views
	      
	      
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
		
		
		
		// store this
		obj.setHtmlTextVF1(htmlTextVF1);
	
        
		// get textview and set the content from HTML
		
        tv1 = (TextView) findViewById(R.id.vf_page1);      
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
        
        
        
        tv2 =(TextView) findViewById(R.id.vf_page2);
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
        tv3 =(TextView) findViewById(R.id.vf_page3);
        tv3.setText(Html.fromHtml(htmlTextVF3));
        
        
        
        
        
       //// Storage the Object to File 
        
        readWriteObj.setObject(obj);
        readWriteObj.writeStorage();

	}

	@Override
	
	// on touch for context menu activation
	

	public boolean onTouchEvent(MotionEvent me){

		return gestureScanner.onTouchEvent(me);

	}

	public boolean onDown(MotionEvent e){

		return true;

	}

	
	// on fling to move between views in viewFlipper
	
	public boolean onFling(MotionEvent e1,MotionEvent e2,float X,float Y){

		try {

            if(e1.getX() > e2.getX() && Math.abs(e1.getX() - e2.getX()) 
            		> SWIPE_MIN && Math.abs(X) > SWIPE_THRESHOLD_SPEED) {

                Toast.makeText(this.getApplicationContext(), "<<<<<", Toast.LENGTH_SHORT).show();
                
                // view flipper to previous view
                vFliffer.showPrevious();

            }else if (e1.getX() < e2.getX() && e2.getX() - e1.getX() 
            		> SWIPE_MIN && Math.abs(Y) > SWIPE_THRESHOLD_SPEED) {

                Toast.makeText(this.getApplicationContext(), ">>>>>", Toast.LENGTH_SHORT).show();
                
                // view flipper to next view

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
	
	
	
	
	
	/*
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.booking_details, menu);
		return true;
	}
	
	
	*/
	
	
	
	/// create the Context menu
	
	 @Override
	    public void onCreateContextMenu(ContextMenu menu, View v,
	            ContextMenuInfo menuInfo) {
	        super.onCreateContextMenu(menu, v, menuInfo);
	        System.out.println("in onCreateContextMenu"); // hold on text
	        
	        // if the id being pressed is the textview context_text
	        if(v.getId() == R.id.context_text) {
	        	
	        	// create and show the menu with choices
	            SubMenu textMenu = menu.addSubMenu("Back to");
	            textMenu.add(0, ID_1, 0, choices[0]);
	            textMenu.add(0, ID_2, 0, choices[1]);
	            
	            // and another with exit, which only goes back an Activity
	            
	            menu.add(0, ID_DEFAULT, 0,  "Exit");
	        }   
	    }

	    @Override
	    
	    
	    // depending on selection in context menu execute different things
	    
	    public boolean onContextItemSelected(MenuItem item) {
	        switch(item.getItemId()) {
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
	    
	
	
	// back to the beginning
		private void startBeginning() {
			
			
		    Intent launchStart = new Intent(this, MainFlight.class);
		    startActivity(launchStart);

		    }
	

	// bate to date activity
	
		private void startChangeDate() {
		
		
	    Intent launchDate = new Intent(this, Date.class);
	    launchDate.putExtra("obj", obj);
	    startActivity(launchDate);

	    }
	
	
}




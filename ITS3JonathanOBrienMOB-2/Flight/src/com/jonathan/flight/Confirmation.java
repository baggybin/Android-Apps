/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address


Shows a confirmation of the Selections made by the user
and calculates the price of the journey

also utilizes pending intent


*/



package com.jonathan.flight;





import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;

public class Confirmation extends Activity {

	
	// notification manager for getting the system service for notifications
	private NotificationManager mNManager;
	// gives an id for the notification
	private static final int NOTIFY_ID = 1001;
	
	
	
	// textviews for displaying data
	
		 private TextView outDepAir, outArrivalAir, inDepAir, inArrivalAir;
		 private TextView outDepTime, outDepDate;
		 private TextView inDepTime, inDepDate;
		 private TextView outClass, inClass;
		 private TextView tvAdultNumber,tVChildNumber, tvAdultPrice, tvChildrenPrice,
		 tvTotalPrice;
		 
		 private boolean emailCheckedBool;
		 
		 // toggle button for email
		 
		 private ToggleButton toggle;
		 private Button startNext;
		 
		 private int childPrice, adultprice = 0;
		 private int classMulitplier  = 1;
		 private String choosenClass;
		 private String [] airports;
		 
		 private int depAirpos, arrAirPos, distance;
		 
		 private boolean single;
		 Storage obj;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation);
		
		
		
		 Intent i = getIntent();
			
	      obj = (Storage) i.getSerializableExtra("obj");
		
	////// notification with Pending intent ////////////////////////////////////////////////////////////
		
			// create a string to id the notification service
			String ns = Context.NOTIFICATION_SERVICE; // toast shows "notification"
			// get the notification server adding to mnmanger for use
			mNManager = (NotificationManager) getSystemService(ns);
			@SuppressWarnings("deprecation")
			
			// create title message for notification, adding the current system time
			final Notification msg = new Notification(R.drawable.ic_launcher,
			"New Booking,"+ obj.getNameCC()+ ",Need Car hire?", 
			System.currentTimeMillis());
			
	

		
					
					
					
					
		 
	      single = obj.getSingle();
	      
	      
	      airports = obj.getAirports();
	      
	      
	      // create a fake distance to generate different prices
	      // by summing the difference between positions in the Array
	      
	      depAirpos = getDistance(obj.getDepAirport());
	      arrAirPos = getDistance(obj.getArrAirport());
	      
	      System.out.println("dep pos" + " =  " + depAirpos);
	      System.out.println("arr pos" + " =  " + arrAirPos);
	      
	      if (depAirpos > arrAirPos)
	      {
	    	distance = (depAirpos + 1) - arrAirPos;  
	      }
	      else
	      {
	    	  distance = (arrAirPos + 1) - depAirpos;
	      }
	      
	      
	  
	      // Generate price difference if diff Class chosen
	      // by creating a different multiplier
	      
	      
	      choosenClass  = obj.getChoosenClass();
	        if(choosenClass.equalsIgnoreCase("Business"))
	        {
	        classMulitplier = 2;	
	        }
	        else if (choosenClass.equalsIgnoreCase("First"))
	        {
	        classMulitplier = 4;
	        }
	      
	 	   
	        
	        /// set the Departure  and arrival
	        // Airports for outward journey leg
	        
	        outDepAir = (TextView) findViewById(R.id.textViewOutDep);
	        outDepAir.setText(obj.getDepAirport());
	      
	      
	        
	        outArrivalAir = (TextView)findViewById(R.id.textViewOutArr);
	        outArrivalAir.setText(obj.getArrAirport());
		  
	        
	      
	      
	      // display return leg if this has been selected  
	        
	      
	      inDepAir = (TextView) findViewById(R.id.textViewInDep);

	      if (single == false)
	      {
	      inDepAir.setText(obj.getArrAirport());
	      }
	      else
	      {
	      inDepAir.setText("");
	      }
	      
	      
	      
	      inArrivalAir = (TextView) findViewById(R.id.textViewInArr);
	      
	      if (single == false)
	      {
	      inArrivalAir.setText(obj.getDepAirport());
	      }
	      else
	      {
	      inArrivalAir.setText("");
	      }
	      
	      
	      
	      /// set the outward leg Times
	      
	      outDepTime = (TextView) findViewById(R.id.outDepTime);
	      outDepTime.setText(obj.getDepartTime());
	      
	      outDepDate = (TextView) findViewById(R.id.outDepDate);
	      outDepDate.setText(obj.getDay()+"/"+obj.getMonth()+"/" + obj.getYr());
	      
	      
	      // and the class of the journey
	      
	      outClass = (TextView) findViewById(R.id.outDepClass);
	      outClass.setText(obj.getChoosenClass());
	      
	      inClass = (TextView) findViewById(R.id.inDepClass);
	      
	      
	      
	      // display the Inbound journey leg Times if return has been selected
	      
	      inDepTime = (TextView) findViewById(R.id.inDepTime);
	      inDepDate = (TextView) findViewById(R.id.inDepDate);

	      if(single == false)
	      {
	      inDepTime.setText(obj.getReturnTime());
	      inDepDate.setText(obj.getDay2()+ "/" + obj.getMonth2() + "/" + obj.getYr2());
	      inClass.setText(obj.getChoosenClass());

	      }
	      else
	      {
	    	  inDepTime.setText("");
	    	  inDepDate.setText("");
	    	  inClass.setText("");
	      }
	      
	      
	      // Display the amount of adults and children 
	      
	      
	      tvAdultNumber = (TextView)findViewById(R.id.tvAdultNumber);
	      tvAdultNumber.setText(String.valueOf(obj.getAdultCount()));
	      
	      tVChildNumber =(TextView) findViewById(R.id.tVChildNumber);
	      tVChildNumber.setText(String.valueOf(obj.getChildCount()));
	      
	      
	      
	      /// display an total adult price
	      
	      tvAdultPrice = (TextView) findViewById(R.id.adult_price);
	      
	      if(obj.getAdultCount() > 0)
	      {
	    	  
	    // adult price 100 base * distance between array elements
	    	  // * by number of adults * class selection multiplier
	    	  
	      adultprice = (100 * distance) * (obj.getAdultCount()) * classMulitplier;
	      tvAdultPrice.setText(String.valueOf(adultprice));
	      obj.setAdultTotalPrice(adultprice);
	      }
	      else
	      {
	    	  tvAdultPrice.setText("n/a");
	      }
	      
	      
	      
	      /// display for children number and prices
	      
	      tvChildrenPrice =(TextView) findViewById(R.id.children_price);
	      if (obj.getChildCount() > 0)
	      {
	      childPrice = (40 * distance) * (obj.getChildCount()) * classMulitplier;
	      tvChildrenPrice.setText(String.valueOf(childPrice));
	      obj.setChildTotalprice(childPrice);
	      }
	      else
	      {
	    	  tvChildrenPrice.setText("n/a");
	      }
	      
	      
	      tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
	      tvTotalPrice.setText(String.valueOf((adultprice + childPrice)));
	      
	      
	      
	      
	      ////////////////////////Buttons //////////////////////////////////////////////////////////
	      
	      
	      
	      // toggle button to start email setting Boolean value to use later
	      
	      toggle=(ToggleButton)findViewById(R.id.toggleButtonEmail);
	        toggle.setOnClickListener(new OnClickListener() {
	              public void onClick(View v) {
	               // TODO Auto-generated method stub
	              if(toggle.isChecked())
	              {
	               Toast.makeText(getApplicationContext(), "Email Confirmation Enabled",
	            		   Toast.LENGTH_SHORT).show();
	               emailCheckedBool = true;
		           	
	               }
	                else
	                {
	                  Toast.makeText(getApplicationContext(), "Email Confirmation Disabled",
	                		  Toast.LENGTH_SHORT).show();
	                  emailCheckedBool = false;
	                 }
	                 }
	                 });
	      
	        
	        
	        
	        Button  backButton = (Button) findViewById(R.id.button2);
	        backButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                //return information to calling activity
	            	// get the sent intent
	                //Intent i = getIntent();
	               
	                //setResult(RESULT_OK, i);
	                finish();
	            }
	        });
	        
	        
	        
	        
	        /// starts the next activity 
	        
	        
	        startNext = (Button) findViewById(R.id.btnNextConfirm);        
	        startNext.setOnClickListener(new View.OnClickListener() 
	        {
	            @SuppressWarnings("deprecation")
				public void onClick(View v) 
	            {    
	            	
	            	
	            	// if email button toggled on send email
	            	
	            	// if email was working on Emualtor i would have set up
	            	// the email to receive all previous details
	            	// jut left as "email contents will go here"
	            	if (emailCheckedBool == true)
	            	{
	            	String[] to = {"jonathan.obrien@mycit.ie", "jonathanoddball@gmail.com"};   
	                String[] cc = {"Support@flight.net"};           
	            	sendEmail(to, cc,getApplicationContext().getResources().getString(R.string.support),
	                "Email Contents will go here");
	            	}
	            	
	            	
	            	// for the pending intent
	            	
					Context context = getApplicationContext();
					
					// messages to be added to the notification 
					CharSequence contentTitle = "Thank You For Youre Booking";
					CharSequence contentText = "Recommened Service for Car-Hire";
					
					
					// an intent to open a webpage
					Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri
							.parse("http://www.autoeurope.ie/"));
					
					// the pending intent to flag the new task 
					PendingIntent intent = PendingIntent.getActivity(Confirmation.this, 0, msgIntent,
							Intent.FLAG_ACTIVITY_NEW_TASK);
					
					msg.defaults |= Notification.DEFAULT_SOUND;
					msg.flags |= Notification.FLAG_AUTO_CANCEL;

					msg.setLatestEventInfo(context, contentTitle, contentText,
							intent);
					
					
					mNManager.notify(NOTIFY_ID, msg);
					
					
					// start next activty 
					
					startBookingDetails();
					
					
					
	            	
	            }
					
				
	        });
		
		
		
	}
	
	
	// send the email
	
	   private void sendEmail(String[] emailAddresses, String[] cCopies, 
			    String subject, String message)
			    {     
			        Intent emailIntent = new Intent(Intent.ACTION_SEND);
			        emailIntent.setData(Uri.parse("mailto:")); 
			        String[] to = emailAddresses;   
			        String[] cc = cCopies; 
			        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);   
			        emailIntent.putExtra(Intent.EXTRA_CC, cc);
			        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			        emailIntent.putExtra(Intent.EXTRA_TEXT, message);        
			        emailIntent.setType("message/rfc822");   
			        startActivity(Intent.createChooser(emailIntent, "Email"));
			    }
	
	
	   
	   // get position of Airport in the array 
	public int getDistance(String air)
	{
	    int index = -1;
	      
	      for (int i1 = 0; (i1 < airports.length) && (index == -1); i1++)
	      {
	        
	       if(airports[i1].equalsIgnoreCase(air))
	       {
	    	index = i1;
	       }
	       
	         
	      }
	      
	      return index;
	      
	}
	
	
	
	
	// start the next activity
	
	private void startBookingDetails() {
		
	
	    Intent launchBookingDetails = new Intent(this, BookingDetails.class);
	    launchBookingDetails.putExtra("obj", obj);
	    startActivity(launchBookingDetails);

	    }
	
	
	
/*	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirmation, menu);
		return true;
	}
	
	*/
	
	
	

}

/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address


Takes in Home address

but creates the activity screen programatically 
Including the edit texts and buttons





*/





package com.jonathan.flight;

import android.os.Bundle;
import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PassangerAddressEntry2 extends Activity {
	
	private Storage obj;

	 EditText ed, ed2, ed3, ed4;
	 Button but1;
	 String house, street, town, county;
	 
	 String temp;
	 


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_passanger_address_entry2);
		
		
		 setTitle(getApplicationContext().getResources().getString(R.string.home_add_entry_form));
		
		

		  Intent i = getIntent();
	
	      obj = (Storage) i.getSerializableExtra("obj");
	 	 
	      /// for getting the keyboard, for jumping from one edit txt to next later on , if
	      /// remember correctly
	      
	      
	      
	    	 final InputMethodManager imm = (InputMethodManager)getSystemService(
	    		      Context.INPUT_METHOD_SERVICE);
	    	 
	         
	         ed = new EditText(this);  
	         
	         
	         // create relative layout parameters 
	         
	         RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,  
	                 LayoutParams.WRAP_CONTENT);  
	         
	         // add parameter rule to align to left of parent
	         
	         params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);  
	         //  add layout parameters for edittext
	         
	         ed.setLayoutParams(params); 
	         
	         // give it an ID number
	         ed.setId(11);
	         
	         // place a hint text into it
	         ed.setHint(getApplicationContext().getResources().getString(R.string.house_name_no_apt)); 
	         
	         
	         // add a listener to it for text entry
	         
	         ed.setOnKeyListener(new OnKeyListener() {
	     		// @Override
	     		public boolean onKey(View v, int keyCode, KeyEvent event) {
	     			// input the number when enter is hit
	     			if ((event.getAction() == KeyEvent.ACTION_DOWN)
	     					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
	     				
	     				
	     				//v.setNextFocusDownId(12);
	     				ed2.requestFocus();
	     				return true;
	     			}
	     			return false;
	     		}
	     	});
	         
	     
	         /// second edit text
	         
	         
	         
	         ed2 = new EditText(this);
	         
	         
	         // new layout parameters with wrap content set
	         
	         RelativeLayout.LayoutParams paramsEdBelowFirst = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,  
	                 LayoutParams.WRAP_CONTENT);  
	         
	         // align parent left
	         paramsEdBelowFirst.addRule(RelativeLayout.ALIGN_PARENT_LEFT); 
	         
	         
	         // align it below ID 11 which is the first edititext
	         
	         paramsEdBelowFirst.addRule(RelativeLayout.BELOW,11);
	        
	         
	         // set the edittext layout paramters
	         
	         ed2.setLayoutParams(paramsEdBelowFirst);  
	         
	         // assign it an ID
	         
	         ed2.setId(12);
	         
	         // set its hint
	         
	         ed2.setHint(getApplicationContext().getResources().getString(R.string.street)); 
	         
	         ed2.setOnKeyListener(new OnKeyListener() {
	     		// @Override
	     		public boolean onKey(View v, int keyCode, KeyEvent event) {
	     			// input the number when enter is hit
	     			if ((event.getAction() == KeyEvent.ACTION_DOWN)
	     					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
	     				
	     				ed3.requestFocus();
	     				return true;
	     			}
	     			return false;
	     		}
	     	});
	         
	         
	         
	         // edit text again
	         
	         
	         ed3 = new EditText(this);
	         
	         RelativeLayout.LayoutParams paramsEdBelowSecond = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,  
	                 LayoutParams.WRAP_CONTENT);  
	         paramsEdBelowSecond.addRule(RelativeLayout.ALIGN_PARENT_LEFT); 
	         
	         
	         /// align it below the last one
	         
	         paramsEdBelowSecond.addRule(RelativeLayout.BELOW,12);
	        
	         ed3.setLayoutParams(paramsEdBelowSecond);  
	         
	         
	         // give it an id
	         
	         ed3.setId(13);
	         ed3.setHint(getApplicationContext().getResources().getString(R.string.town_city)); 
	         ed3.setOnKeyListener(new OnKeyListener() {
	     		// @Override
	     		public boolean onKey(View v, int keyCode, KeyEvent event) {
	     			// input the number when enter is hit
	     			if ((event.getAction() == KeyEvent.ACTION_DOWN)
	     					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
	     				
	     				ed4.requestFocus();
	     				return true;
	     			}
	     			return false;
	     		}
	     	});
	         
	         
	         
	         
	         // SAME idea again
	         
	         
	         
	         
	         
	          ed4 = new EditText(this);
	         
	         RelativeLayout.LayoutParams paramsEdBelowThird= new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,  
	                 LayoutParams.WRAP_CONTENT);  
	         paramsEdBelowThird.addRule(RelativeLayout.ALIGN_PARENT_LEFT); 
	         paramsEdBelowThird.addRule(RelativeLayout.BELOW,13);
	        
	         ed4.setLayoutParams(paramsEdBelowThird);  
	         ed4.setId(14);
	         ed4.setHint(getApplicationContext().getResources().getString(R.string.county)); 
	         
	         
	         ed4.setOnKeyListener(new OnKeyListener() {
	     		// @Override
	     		public boolean onKey(View v, int keyCode, KeyEvent event) {
	     			// input the number when enter is hit
	     			if ((event.getAction() == KeyEvent.ACTION_DOWN)
	     					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {

	     				
	     				but1.setFocusableInTouchMode(true);
	     				but1.requestFocus();
	     				
	     				return true;
	     			}
	     			return false;
	     		}
	     	});
	         
	         
	         
	         
	         // add a new button
	         
	         
	         
	         but1 = new Button(this);  
	         RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,  
	                 LayoutParams.WRAP_CONTENT);  
	         
	         // Center the Button horizontally on the screen
	         
	         params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
	         
	         // place it below the edit texts
	         
	         params2.addRule(RelativeLayout.BELOW,14);
	         
	         // set its parameters
	         but1.setLayoutParams(params2);  
	         but1.setText(getApplicationContext().getResources().getString(R.string.submit));  
	         
	         // assignment of ID
	         
	         but1.setId(1001);
	         
	         
	         // add an onlick listener to the Button
	         
	 		but1.setOnClickListener(new View.OnClickListener() 
	         {
	             public void onClick(View v) 
	             {    
	             	try{
	             		
	             		
	             	// if pressed pull in all the Edit texts Currently held strings
	             		
	             		
	             	house = ed.getText().toString(); 
	             	street = ed2.getText().toString(); 
	             	town = ed3.getText().toString(); 
	             	county = ed4.getText().toString(); 
	             	System.out.print(house +" xxxx " + street + " xxxxx " + town + " xxxxx " + county +" x xxx" );
	             	obj.setHouse(house);
	             	obj.setStreet(street);
	             	obj.setTown(town);
	             	obj.setCounty(county);

	             	
	             	ed.setText("");
	             	ed2.setText("");
	             	ed3.setText("");
	             	ed4.setText("");

	             	// hide the Keyboard
	             	
	             	
	             	imm.hideSoftInputFromWindow(but1.getWindowToken(), 0);            	
	             	}
	             	catch(Exception e)
	             	{
	             	System.out.println("error storing house ect");	
	             	}
	             }
	 				
	 			
	         });
	         
	         
	         
	         //  add button to start next activity 
	 		
	         Button butBottomRight = new Button(this);  
	         RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,  
	         LayoutParams.WRAP_CONTENT);  
	         
	         // align it to the bottom and to the right
	         
	         params3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
	         params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

	         butBottomRight.setLayoutParams(params3);
	         butBottomRight.setText("Next");  
	         butBottomRight.setId(1002); 
	         
		 		butBottomRight.setOnClickListener(new View.OnClickListener() 
		         {
		             public void onClick(View v) 
		             {    
		            	 startConfirmation();
		     
		             }
		 				
		 			
		         });
		         
	         
	         
	         
	         
	         // button to go back an activity
		 		
		 	// align it to bottom and the left
		 		
	         
	         
	         Button butBottomLeft = new Button(this);  
	         RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,  
	         LayoutParams.WRAP_CONTENT);  
	         params4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
	         params4.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

	         butBottomLeft.setLayoutParams(params4);
	        // but2.setPadding(0, 20, 0, 20);
	         butBottomLeft.setText(getApplicationContext().getResources().getString(R.string.back));  
	         // give the button an id that we know  
	         butBottomLeft.setId(1003); 
	         
	         
	         
	     	butBottomLeft.setOnClickListener(new View.OnClickListener() 
	         {
	             public void onClick(View v) 
	             {    
	            	 finish();
	     
	             }
	 				
	 			
	         });
	         
	         
	     	
	     	
	     	
	     	
	     	
	     	
	     	
	         // create the layout
	     	// and add the views to it
	         
	         RelativeLayout layout1 = new RelativeLayout(this);  
	         layout1.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));  
	         layout1.addView(ed); 
	         layout1.addView(ed2);
	         layout1.addView(ed3);
	         layout1.addView(ed4);
	         layout1.addView(but1);
	         layout1.addView(butBottomRight);
	         layout1.addView(butBottomLeft);
	         setContentView(layout1);
	         
	 }
		
		
	public void onResume()
	{
	super.onResume();

	//obj.setHouse("");
	//obj.setTown("");
	//obj.setStreet("");
	//obj.setCounty("");
	}
	
	
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		town= savedInstanceState.getString("town");
		street= savedInstanceState.getString("street");
		county= savedInstanceState.getString("county");
		house= savedInstanceState.getString("house");
		
	    obj = (Storage) savedInstanceState.getSerializable("obj");
		
	}


	//  used to restore values when to program is stopped and put through its activity cycle again
	// such as when a change orientation occurs
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//outState.putDouble("speed", speed);
		super.onSaveInstanceState(outState);

	outState.putString("town",town);
	outState.putString("street",street);
	outState.putString("county",county);
	outState.putString("house",house);


	outState.putSerializable("obj", obj);



	}

/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.passanger_address_entry2, menu);
		return true;
	}
	
	
	*/
	

	private void startConfirmation() {
		
		// check and make sure requirements are met before starting next activity
	
		
		if (house != null && street != null && town != null && county != null)
		{
	    Intent launchConfirmation = new Intent(this, Confirmation.class);
	    launchConfirmation.putExtra("obj", obj);
	    startActivity(launchConfirmation);
		}
	    
	    
		else
		{
			Toast.makeText(PassangerAddressEntry2.this,
					getApplicationContext().getResources().getString(R.string.please_enter_required),
					Toast.LENGTH_SHORT).show();
		}
	    
	    
	    
	    }
	
	
	
	
	
	

}

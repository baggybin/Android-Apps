/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address

// This class is the main activity that takes in the user input
is the starting point of the program, is the launcher.

also allows to view previous booking which was stored

////--
*/



package com.jonathan.flight;





import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainFlight extends Activity {
	
	private static final int DATE_CODE = 1010;
     
	
	
	// Main variable storage object and one for reading in previous booking
	private Storage storeObj, prevStoreobj;
	
	
	// object to read in previous booking i/o
	private VarObjectStore readWriteObj;
	
	
	
	
	
	// variable and constants for Options menu
	private final int GROUP_PREV=1;
	private final int MENU_ADD=1;
	private boolean prevExist = false;
	
	
	// variables for choice selections
	
	private String departAirport;
	private String arrivalAirport;
	private String choosenClass;
	private boolean single= true;
	private int adultCount,childCount;
	
	// buttons and edit texts
	private Button btnNext;
	private EditText adultEditCount;
	private EditText childEditCount;
	
	
	// array of airports for spinners
	private String[] airports =  {"cork","Belfast","london",
			"Cardif","Paris","Amsterdam","Frankfurt","Sydney",
			"Tokyo","Johannesburg"};
	
	//string array for Class Spinner
	
	private String[] flightClass =  {"Economy","Business","First"};
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_flight);
		
	////////// storage io Objects///////////////////////////////////////////////////////////////////////

		storeObj = new Storage();
		prevStoreobj = new Storage();
		storeObj.setAirports(airports);
		readWriteObj = new VarObjectStore(this);
		
		
		// read in previous stored object if exits
		readWriteObj.readStorage();
		
		
		// check if previous object stored exists, then set boolean for options menu
		// will activate / deactivate menu part
		if (readWriteObj.checkIfStored() == true)
		{
			prevStoreobj = readWriteObj.getStorageObject();
			prevExist = true;
		}
		
		
	/////// Spinners //////////////////////////////////////////////////////////////////////////////////////
		
		
		// set up spinners for selection 
		// of depart and arrival airports
		// + for Class of flight
		
		
		
		/// spinner for Departing airport
			   Spinner s1 = (Spinner) findViewById(R.id.spinner_airport_1);
		        
			   
			   
			   // string array adapter to use String array to fill spinners
			   
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		            android.R.layout.simple_spinner_item, airports);
		        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		        s1.setAdapter(adapter);
		        s1.setOnItemSelectedListener(new OnItemSelectedListener()
		        {
		        	
		        	@Override
		            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
		            {
		        		//store string at this position 
		        		
		        		departAirport = (String) arg0.getItemAtPosition(arg2);
		        		
		        		// add to storage variable
                        storeObj.setDepAirport(departAirport);
             
		            }

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {} 
		        });        
			
		        
		        
		       /// spinner for arrival airport 
		        
		        Spinner s2 = (Spinner) findViewById(R.id.spinner_airport_2);
		        

		 // again set to the first adapter
		        s2.setAdapter(adapter);
		        s2.setOnItemSelectedListener(new OnItemSelectedListener()
		        {
		        	@Override
		            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
		            {
		        	// store the airport (string) at selected position 
		             arrivalAirport  = (String) arg0.getItemAtPosition(arg2);  
		             storeObj.setArrAirport(arrivalAirport);
		            }

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {} 
		        }); 
		        
		        
		        //a second array adapter to fill Class selection Spinner
		        
		        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
			            android.R.layout.simple_spinner_item, flightClass);
			        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			        
			        
		        Spinner s3 = (Spinner) findViewById(R.id.spinner_class);

				 // set spinner s3 to second adapter
		        s3.setAdapter(adapter2);
		        s3.setOnItemSelectedListener(new OnItemSelectedListener()
		        {
		        	@Override
		            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
		            {
		             choosenClass  = (String) arg0.getItemAtPosition(arg2);  
		             storeObj.setChoosenClass(choosenClass);
		            }

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {} 
		        });        
			
	/////// Spinners //////////////////////////////////////////////////////////////////////////////////////
			
			

	    ///// Text Edit Views////////////////////////////////////////////////////////////////////////////////
		adultEditCount = (EditText) findViewById(R.id.editTextAdult);

	    adultEditCount.setOnKeyListener(new OnKeyListener() {
			// @Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// input the number when enter is hit
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// take in the numbers for use and store them
					try{
						adultCount = Integer.valueOf(adultEditCount.getText().toString());
						storeObj.setAdultCount(adultCount);
					}
					catch(Exception e){
						adultCount = 0;
					}
					//show toast notification  
					Toast.makeText(MainFlight.this,
							getApplicationContext().getResources().getString(R.string.entered_adults) + adultCount,
							Toast.LENGTH_SHORT).show();
					adultEditCount.setText(""); // cleared afterwards 
					
					//release focus so doesn't jump to next, then requires touch selection
					// hides the keyboard
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(v.getWindowToken(), 0); 
					adultEditCount.setFocusable(false);
					adultEditCount.setFocusableInTouchMode(true);
					return true;
				}
				return false;
			}
		});
		
	    
	    childEditCount = (EditText) findViewById(R.id.editTextChildren);

	    childEditCount.setOnKeyListener(new OnKeyListener() {
			// @Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// input the number when enter is hit
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// take in the text for use
					try{
						childCount = Integer.valueOf(childEditCount.getText().toString());
						storeObj.setChildCount(childCount);
						}
					catch(Exception e){
						childCount = 0;
					}
					//show toast notification  
					Toast.makeText(MainFlight.this,
							getApplicationContext().getResources().getString(R.string.entered_children)
							+ childCount,
							Toast.LENGTH_SHORT).show();
					childEditCount.setText(""); // cleared afterwards
					
					//release focus so doesn't jump to next, then requires touch selection
					// hides the keyboard
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(v.getWindowToken(), 0); 
					childEditCount.setFocusable(false);
					childEditCount.setFocusableInTouchMode(true);
					return true;
				}
				return false;
			}
			
		});
	    
	       
	    
	    
///// Text Edit Views/////////////////////////////////////////////////////////////////////////////////	
		
		
		
		
		
		

	        
	        
	        
	        
/////// RadioButton ///////////////////////////////////////////////////////////////////////////////////
	    
	    // create radioGroup for selecting if its a Single or Return Journey
	        
	        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rdbGp1);        
	        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        {
	            public void onCheckedChanged(RadioGroup group, int checkedId) {                              
	                RadioButton rb1 = (RadioButton) findViewById(R.id.rdb1); 
	                if (rb1.isChecked()) 
	                {
	                single = true;
	                storeObj.setSingle(single);
	                }
	                else
	                {
	                single = false;	
	                storeObj.setSingle(single);

	                }
	                
	                ; 
	                
	                //System.out.println("Single =" + single + " id " + checked);
	                
	                } 
	                
	            
	        });	
		
	  /////// RadioButton ///////////////////////////////////////////////////////////////////////////////////

	        
	        
	        
	        
////////////////Button////////////////////////////////////////////////////////////////////////////////
	        
	        
	        //starts the next activity Date Entry
	        
	    	btnNext = (Button) findViewById(R.id.btnToDate);        
			btnNext.setOnClickListener(new View.OnClickListener() 
	        {
	            public void onClick(View v) 
	            {    
	            startDate();
	            	
	            }
					
				
	        });
			
			
////////////////Button////////////////////////////////////////////////////////////////////////////////
		
	
			
			
			
	}

	
	
////////////////OverRidden methods////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	// Options menu creation
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu from the XML file
		getMenuInflater().inflate(R.menu.main_flight, menu);
		
		// adds a Group menu part which can be enabled or disabled
		// depending on APP state, whether a Booking has been stored before or not
		menu.add(GROUP_PREV, MENU_ADD, 0, "Previous").setIcon(R.drawable.previous);;
		return true;
	}
	
	
	
	 @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	    	
	    	
	    	// if Previous booking Storage Exists then make that
		 	// menu part visible or invisible 
	        if(prevExist == true) 
	        { 
	            menu.setGroupVisible(GROUP_PREV, true);
	        } 
	        else 
	        {
	            menu.setGroupVisible(GROUP_PREV, false);            
	        }
	     
	        return super.onPrepareOptionsMenu(menu);
	    }

	 
	 // listener activating methods when the menu items
	 // are clicked, in this case one from OncreateOptionsMenu menu.add() method
	 // and the other from the XML files identifier
	 
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch(item.getItemId()) {
	        case MENU_ADD:
	        	startPreviousBooking();
	        case R.id.system_exit:
	        	System.exit(0);
	            return true;

	        }
	        return super.onOptionsItemSelected(item);
	    }
	
	
	
	@Override
	   // restores the variables stored in the onSaveinstanceState method
		// when the activity is started again
		public void onRestoreInstanceState(Bundle savedInstanceState) {
			super.onRestoreInstanceState(savedInstanceState);
			//weight = savedInstanceState.getDouble("weight");
			departAirport= savedInstanceState.getString("departAirport");
			arrivalAirport = savedInstanceState.getString("arrivalAirport");
			choosenClass = savedInstanceState.getString("choosenClass");
			single = savedInstanceState.getBoolean("single");
			adultCount = savedInstanceState.getInt("adultCount");
			childCount = savedInstanceState.getInt("childCount");
			
		}
		
		
		//  used to restore values when to program is stopped and put through its activity cycle again
		// such as when a change orientation occurs
		@Override
		protected void onSaveInstanceState(Bundle outState) {
			//outState.putDouble("speed", speed);
		outState.putString("departAirport",departAirport);
		outState.putString("arrivalAirport",arrivalAirport);
		outState.putString("choosenClass", choosenClass);
		outState.putBoolean("single", single);
		outState.putInt("adultCount", adultCount);
		outState.putInt("childCount", childCount);
		super.onSaveInstanceState(outState);
		
	
		
		}	
	
	public void onResume()
	{
	// i was using these but seems a little pointless for this app
	
		
		
	//storeObj.setAdultCount(0);
	//storeObj.setChildCount(0);
	//storeObj.setChoosenClass("Economy");
	//storeObj.setSingle(true);
	//storeObj.setDepAirport("cork");
	//storeObj.setArrAirport("cork");
	
	super.onResume();
	}
	
	
////////////////////////Custom methods///////////////////////////////////////////////////////////////////
		
		private void startDate() {
	        
			
			// intent for starting next activity 
			
			Intent launchDate = new Intent(this, Date.class);
			
			// make sure everything required has been input
			// and stored before launching next
			
			if (adultCount > 0 || (childCount > 0 && adultCount > 0))

			{
				//readWriteObj.setObject(storeObj);
		       // readWriteObj.writeStorage();
		        
		       // launchDate.putExtra("single", single);
		       // launchDate.putExtra("departairport", departAirport);
		       // launchDate.putExtra("arrivalairport", arrivalAirport);
		        //launchDate.putExtra("airports", airports);  
		       // launchDate.putExtra("adultcount", adultCount);
		       // launchDate.putExtra("childcount", childCount);
		       // launchDate.putExtra("choosenclass", choosenClass);  
		        launchDate.putExtra("obj", storeObj); 
		       // launchDate.putExtra("adultEditCount", adultEditCount);
		        startActivityForResult(launchDate, DATE_CODE);
			}
			else
			{
				// show toast of requirements 
				Toast.makeText(MainFlight.this,
						getApplicationContext().getResources().getString(R.string.at_least_one_adult),
						Toast.LENGTH_SHORT).show();	
			}
			
			
	        }
	
		
		
		
		// method to start an activity to show previous
		// booking with the  object read in from file
		

		private void startPreviousBooking() {
			
			
		    Intent launchPrevious = new Intent(this, PreviousBooking.class);
		    launchPrevious.putExtra("obj", prevStoreobj);
		    startActivity(launchPrevious);

		    }
	
		
		

}

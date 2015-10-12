/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program takes weight and Speed of object and compares this object in free fall to an object hitting 
a solid abject at a certain speed, then extrapolates the hieght that the object would have to fall from
to be equivilant

// This class is the main activity that takes in the user input
is the sarting point of the program 

//
*/



package com.jonathan.ass1speed;



import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
 
public class MainScreen extends Activity {

	private static final int COMP_CODE = 1010;
	private EditText speedResult;
	private EditText weightResult;
	private double speed,weight;
	private int count = 1 ;
	private Button sendEmail;
	private double height;
	private double height2;
	private double car = 1500;
	private double motorbike = 200;
	private double truck = 2500;
	private double bus = 9000;
	String FILENAME = "file";
	
	
	
    private static Context mContext;




    // for formating the doubles to two decimal places for results
	private DecimalFormat df = new DecimalFormat("0.0");

	// arraylist for storage of array passed back from Comp Activity then 
	// sent back again to append to it
	// though i could have implemented this using the persistent storage method
	
	private ArrayList <String> array =   new ArrayList <String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
		   mContext = this;
    

 
	

			// array for choices in the spinner
			//private static final String[] vehicles ={};
		
	    String[] vehicles =  {getApplicationContext().getResources().getString(R.string.vchoice),
	    		getApplicationContext().getResources().getString(R.string.car),
	    		getApplicationContext().getResources().getString(R.string.MotorBike),
	    		getApplicationContext().getResources().getString(R.string.truck),
	    		getApplicationContext().getResources().getString(R.string.Bus)};
		
	    ///// Text Edit Views////////////////////////////////////////////////////////////////////////////////
	        
		speedResult = (EditText) findViewById(R.id.text_speed);
	    speedResult.setOnKeyListener(new OnKeyListener() {
			// @Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// input the number when enter is hit
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// take in the text for use
					try{
					speed = Double.valueOf(speedResult.getText().toString());
					}
					catch(Exception e){
						speed = 0;
					}
					//String enteredSpeed = "@string/enteredSpeed";
					//show toast notification  
					Toast.makeText(MainScreen.this, 
							getApplicationContext().getResources().getString(R.string.entered_speed) + speed,
							Toast.LENGTH_SHORT).show();
					speedResult.setText(""); // cleared afterwards 
					return true;
				}
				return false;
			}
		});
		
	    //again take in double
		weightResult = (EditText) findViewById(R.id.text_weight);
	    weightResult.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					try{
					weight = Double.valueOf(weightResult.getText().toString());
					}catch(Exception e){
						weight = 0;
					}
					Toast.makeText(MainScreen.this,
					getApplicationContext().getResources().getString(R.string.entered_weight) + weight,
					Toast.LENGTH_SHORT).show();
					weightResult.setText(""); // cleared after return pressed
					return true;
				}
				return false;
			}
		});
	    
	    ///// Text Edit Views/////////////////////////////////////////////////////////////////////////////////
	
	    
	    
	    
	   
	    ///Spinner ///////////////////////////////////////////////////////////////////////////////////////////
	   
	    // used for preset weight class
	    // Creates the Array adapter to connect the vehicle array contents to the spinner
	    // uses separate XML layout for the Spinner than this activities layout
		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
				R.layout.spinner_entry, vehicles);
		
		// creates a spinner and connects it to the XML layout
		final Spinner pickvehicle = (Spinner) findViewById(R.id.spinner);
		
		// Connects the to use a drop down view again using seperate XML layout
		mAdapter.setDropDownViewResource(R.layout.spinner_entry);
		// set the spinner to use the adapter
		pickvehicle.setAdapter(mAdapter);
		
		
		// OnitemSlected listener, listening for selection on the spinner
		pickvehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                View view, int pos, long id) {
            	pickvehicle.setSelection(0);	

            	//was executing this on APP loading and entering the first item i spinner
            	//only want after someone picks spinner item
            	if(count > 1 )
            	{
            	
            	// stores the selection, id from the pos integer number
                String v = (String) parent.getItemAtPosition(pos);
                
                // checks which selections and based on that sets the weight for calc
                
                if (v.equalsIgnoreCase(getApplicationContext().getResources().getString(R.string.car)))
                {
                weight = car;
                }
                if (v.equalsIgnoreCase(getApplicationContext().getResources().getString(R.string.truck)))
                {
                weight = truck;		
                }
                if(v.equalsIgnoreCase(getApplicationContext().getResources().getString(R.string.MotorBike)))
                {
                weight = motorbike;	
                }
                if (v.equalsIgnoreCase(getApplicationContext().getResources().getString(R.string.Bus)))
                {
                weight = bus;
                }
                if(v.equalsIgnoreCase(getApplicationContext().getResources().getString(R.string.vchoice)))
                {
                	
                }
                else{
                // message to use to id that item was selected
            	Toast.makeText(getApplicationContext(), 
            	v +getApplicationContext().getResources().getString(R.string.selected)
                + weight +" KG", Toast.LENGTH_SHORT).show();	
                }
                
            	}
            	// increments counter so this only run after second time
            	// may have to check if this is relevant and causing the bug where item stays selected
            	count++;
            	
          
            }

            public void onNothingSelected(AdapterView<?> parent) {
              
            }

        });
		///spinner  //////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		//// other Buttons ///////////////////////////////////////////////////////////////////////////////////////	

		
		 
		// sets up a button with listener to send a support email 
			sendEmail = (Button) findViewById(R.id.email);        
			sendEmail.setOnClickListener(new View.OnClickListener() 
	        {
	            public void onClick(View v) 
	            {    
	            // create these when clicked and send to sendEmail method
	            	String[] to = {"jonathan.obrien@mycit.ie", "jonathanoddball@gmail.com"};   
	                String[] cc = {"Support@ass1speed.net"};           
	            	sendEmail(to, cc,getApplicationContext().getResources().getString(R.string.support),
	                getApplicationContext().getResources().getString(R.string.support_for));
	            }
					
				
	        });
		
	
		// button to start calculation Activity when clicked by calling calc method
	       Button startButton = (Button) findViewById(R.id.calcbutton);
	        startButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                startCalc();
	            }
	        });
	        
	        
	       // button to show the previous height results from persistent storage
	        // by starting a dialog notification 
	       Button  previousButton = (Button) findViewById(R.id.previous);
	       previousButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                dialog();
	            }
	        });
	       
	        // Exits the program
		 Button  exitButton = (Button) findViewById(R.id.exit_button);
		 exitButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                System.exit(0);
	            }
	        });
		 
	    	//// other Buttons ///////////////////////////////////////////////////////////////////////////////
	        
	    
	   	
 ////////End oF OnCreate Method
	} 
	

	
	
	//////// overridden SuperClass Methods //////////////////////////////////////////////////////////////////
	
	
	// When a called activity return backed to this the caller, with a result code and result + data
    @Override
    protected void onActivityResult(int requestCode,
            int resultCode, Intent intentData) {
    	
    	// checks for calling code and compares to this activity Code o verify
    	// it was this activity that made the request and that the result is ok.
    	
        if (requestCode == COMP_CODE && resultCode == RESULT_OK) {
       // if so take in the arraylist, and height from the bundle and store in local variables
        	array = intentData.getStringArrayListExtra("array");
        	height = intentData.getDoubleExtra("height", -1);
        }
        super.onActivityResult(requestCode, resultCode, intentData);
    }
		
		
		
    // When program is resumed it will call the readheight method to store value in height2 variable 
    // this was stored in the Comp java Activity
	@Override
	protected void onResume() {
		super.onResume();
		 readHeight();

	}

	
	@Override
   // restores the variables stored in the onSaveinstanceState method
	// when the activity is started again
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		weight = savedInstanceState.getDouble("weight");
		speed = savedInstanceState.getDouble("speed");
		array = savedInstanceState.getStringArrayList("array");
		height = savedInstanceState.getDouble("height");

	}
	
	
	//  used to restore values when to program is stopped and put through its activity cycle again
	// such as when a change orientation occurs
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putDouble("speed", speed);
		outState.putDouble("weight", weight);
		outState.putStringArrayList("array", array);
		outState.putDouble("height", height);
		super.onSaveInstanceState(outState);
	
	}	
	
	
	
	//////// overridden SuperClass Methods //////////////////////////////////////////////////////////////////
	
	
	
	
	
	
  // method to use a filestream to read in the stored height value from storage
// this result was stored in the Comp Activity class where the calculation is done
	
	private void readHeight() {
		{

			ObjectInputStream inputStream = null;
			try {

				// Android get file input stream
				FileInputStream fis = openFileInput(FILENAME);

				// Construct the ObjectInputStream object
				inputStream = new ObjectInputStream(fis);
				
				// value stored in the height2 variable read in from stream
				// from the file
				// was using the original height variable to begin with
				// but was complicated getting it overwritten when like a screen orientation change
				// happened with null values and other errors
				// so was easier to use separate variable and write to file in 	Comp class
				
				height2 = inputStream.readDouble();
           // catch errors from the try block above// reading in the file
				// such as if the file didnt exist 
			} catch (EOFException ex) { 
				
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
				
			} catch (IOException ex) {
				ex.printStackTrace();
				
			} finally {
				// Close the ObjectInputStream
				try {
					// if open then close
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}
	
	
	
	
	
	
	
	//////// Custom methods /////////////////////////////////////////////////////////////////////////////////
	

		// creates an explicit intent to start the Comp Activity screen
		// passing it the speed, weight vars for storage
		
	private void startCalc() {
        Intent launchCalc = new Intent(this, Comp.class);
        
        //making sure there was input
       
        if(weight == 0.0 && speed == 0.0)
        {
        	 Toast.makeText(MainScreen.this,
      				getApplicationContext().getResources().getString(R.string.zero_error),
             		Toast.LENGTH_LONG).show();  
        	
        }
        else
        {
        //passing information to launched activity
        launchCalc.putExtra("speed", speed);
        launchCalc.putExtra("weight", weight);
        
        // array is passed back to activity so that next string result can be opened
        // so that it can be used to create the listview previous results display
        launchCalc.putStringArrayListExtra("array", array);
        
        // starts the Activity for a result, giving the Code to id the called activity on result
        startActivityForResult(launchCalc, COMP_CODE);
        }
       
    }
	
	
	// creates implicit intent to create and send an email using any email app on the device
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
	
    // creates the dialog popup which displays the previous result
	public void dialog(){
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setMessage(getApplicationContext().getResources().getString(R.string.previous_result_was) 
		+ df.format(height2) + 
		getApplicationContext().getResources().getString(R.string.meters));
		
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE,
				getApplicationContext().getResources().getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
		
					}
				});

		dialog.show();	
	}
    
	
	//////// Custom methods /////////////////////////////////////////////////////////////////////////////////
	
	   public static Context getContext(){
	        return mContext;
	    }
			


	

}

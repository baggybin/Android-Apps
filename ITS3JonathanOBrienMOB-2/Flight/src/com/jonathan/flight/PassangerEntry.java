/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address


Takes in either a Visa or MasterCard number and name
uses fragments to display edit texts

also uses a progress Dialog for Authorization


		// got Fragments from the Support4Demos from Google android API
		// Heavily modified

*/




package com.jonathan.flight;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PassangerEntry extends FragmentActivity {
    

	
	// handler for progress Dialog + Pro Dialog
	
    Handler handler;
    ProgressDialog progressDialog;
    
	private int masterNumber = 0;
	private int visaNumber = 0;
	private String nameCC;
	private Storage obj;

	private EditText editCreditName;
    //OnDataPass dataPasser;
	
	private Button nextButton,backButton;

	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passanger_entry);
        
        
         handler = new Handler();

        
		 Intent i = getIntent();
		 obj = (Storage) i.getSerializableExtra("obj");
		 
		 
		// got Fragments from the Support4Demos from Google android API
		// Heavily modified
		 
        
        // The content view embeds two fragments; now retrieve them and attach
        // their "hide" button.
		 
		 // start a fragment manager to handle the fragments
		 
        FragmentManager fm = getSupportFragmentManager();
        
        
        /// add listener to show or hide the fragments
        
        addShowHideListener2(R.id.frag1hide, fm.findFragmentById(R.id.fragment1));
        addShowHideListener2(R.id.frag2hide, fm.findFragmentById(R.id.fragment2));
        
        // This allows for changes to the fragments, such as animations
        // when they change state
        
        
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft2.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        
        // I set them both to Hide themselves first
        // so that only when one CC system is chosen does that
        // fragment appear
        
        
        ft2.hide(fm.findFragmentById(R.id.fragment1));

        ft2.hide(fm.findFragmentById(R.id.fragment2));
        
        
        // commit this transaction hiding the fragments
        
        ft2.commit();
        
    
        
        
        
        
        
        
 ////////////////////////////////edit texts ////////////////////////////////////
        
        // take in credit card name
        
        
    	editCreditName = (EditText) findViewById(R.id.credit_name_entry);

    	editCreditName.setOnKeyListener(new OnKeyListener() {
    		// @Override
    		public boolean onKey(View v, int keyCode, KeyEvent event) {
    			// input the number when enter is hit
    			if ((event.getAction() == KeyEvent.ACTION_DOWN)
    					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
    				// take in the text for use
    				try{
    					nameCC = (editCreditName.getText().toString());
    					obj.setNameCC(nameCC);
    					System.out.println("name strored " + nameCC);
    				}
    				catch(Exception e){
    					System.out.println("there has been an eception enter name cc");
    					nameCC = "";
    				}
    				//String enteredSpeed = "@string/enteredSpeed";
    				//show toast notification  
    				Toast.makeText(PassangerEntry.this,
    						getApplicationContext().getResources().getString(R.string.entered_name)
    						+ nameCC,
    						Toast.LENGTH_SHORT).show();
    				editCreditName.setText(""); // cleared afterwards 
    				return true;
    			}
    			return false;
    		}
    	});
    	
    	
    	
    	 ////////////////////////////////Buttons ////////////////////////////////////
 	
    	
    	
    	// back button
    	
        backButton = (Button) findViewById(R.id.btn_credit_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //return information to calling activity
            	// get the sent intent
                //Intent i = getIntent();
               
                //setResult(RESULT_OK, i);
                finish();
            }
        });
        
        
        
        // next activity
        
        nextButton = (Button) findViewById(R.id.btn_credit_next1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                
             
                startPassAddress();
            }
        });
      
        
        
        
 }

	
	//////// End of oncreate method
	

   // Add listener to a button to view or hide fragment
	
	
    
    void addShowHideListener2(int buttonId, final Fragment fragment) {
    	
    	// starts both buttons depending on there is
    	// same as both fragments
    	
        final Button button = (Button)findViewById(buttonId);


        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	
            // when clicked start a fragment transactions showing of hiding the
            	// fragment depending on its current state
            	
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                
                
                if (fragment.isHidden()) {
                    ft.show(fragment);
                    button.setText("Hide");
                } else {
                    ft.hide(fragment);
                    button.setText("Show");
                }
                
                // commit the transaction to make it occur
                ft.commit();
  
            }
        });    
        
    }

    ///////////////////////////////////////// Fragments ///////////////////////////////////////////////
  
    
    // First fragment
    
    public static class FirstFragment extends Fragment {
        TextView mTextView;
        EditText visaCard;
        int visaNumber;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	// create the edittext and textview contents of the fragment
        	// using an XML layout file called labeled_text_edit
        	
            View v = inflater.inflate(R.layout.labeled_text_edit, container, false);
            View tv = v.findViewById(R.id.msg);
            
            // set the textview to display this
            
            ((TextView)tv).setText("Please Enter Card no.");
            visaCard = (EditText)v.findViewById(R.id.saved);

             // on on key listener for the Edit-text in the fragment
            
    	    visaCard.setOnKeyListener(new OnKeyListener() {
    			// @Override
    			public boolean onKey(View v, int keyCode, KeyEvent event) {
    				// input the number when enter is hit
    				if ((event.getAction() == KeyEvent.ACTION_DOWN)
    						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
    					
    					
    					// when enter pressed try and store the Number
    					try{
    					int visaNumber=Integer.valueOf(visaCard.getText()
    					.toString());
    					
    					// get access to the Main Class Activity for context
    					// so that methods from the Main activity class
    					// can be called and referenced from within the fragment
    					// in this case calling methods to pass the integer numbers
    					// back to PassangerEnty Activity
    					
    					PassangerEntry fActivity= (PassangerEntry)getActivity();
    					fActivity.setVisaNumber(visaNumber);
    					fActivity.setObjVisaNumber(visaNumber);

    					}
    					
    					// catch errors and set to zero if so
    					catch(Exception e)
    					{
    						visaNumber = 0;	
    						System.out.println(e);
    						
    					}
    					//passData(masterNumber);
    					
    					// reset text numbers
    					visaCard.setText(""); // cleared afterwards 
    					return true;
    				}
    				return false;
    			}
    	    
    		});
    	       	    
            ((TextView)v.findViewById(R.id.saved)).setSaveEnabled(true);
			return v;
        }
    	    
        
      
    }
    
    
    
    /// second fragment which would have same comment as the first

    public static class SecondFragment extends Fragment {
    	EditText masterCard;
    	int masterNumber;
    	//OnDataPass dataPasser;        
      
        /*
        public void onAttach(Activity a) {
            super.onAttach(a);
            dataPasser = (OnDataPass) a;
        }
        
        public void passData(int data) {
            dataPasser.onDataPass(data);
        }
        */    

        
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.labeled_text_edit, container, false);
            View tv = v.findViewById(R.id.msg);
            ((TextView)tv).setText("Please Enter Card no.");
            
            
            masterCard = (EditText)v.findViewById(R.id.saved);

    	    masterCard.setOnKeyListener(new OnKeyListener() {
    			// @Override
    			public boolean onKey(View v, int keyCode, KeyEvent event) {
    				// input the number when enter is hit
    				if ((event.getAction() == KeyEvent.ACTION_DOWN)
    						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
    					
    					try{
    					 masterNumber=Integer.valueOf(masterCard.getText()
    					.toString());
    					
    					PassangerEntry fActivity= (PassangerEntry)getActivity();
    					fActivity.setMasterNumber(masterNumber);
    					fActivity.setObjMasterNumber(masterNumber);

    					
    					}
    					catch(Exception e)
    					{
    						masterNumber = 0;	
    						
    					}
    					//passData(masterNumber);
    					masterCard.setText(""); // cleared afterwards 

    					return true;
    				}
    				return false;
    			}
    		});   
   
           ((TextView)v.findViewById(R.id.saved)).setSaveEnabled(true);
            return v;
        }
    }
    
    
    
    
    
    
    
    
    
    /* public void onDataPass(int data) {
    Log.d("LOG","hello " + data);
 }
 
 */
    
    
    // part of main class and will be called from fragment to store data
    
    public void setMasterNumber(int m)
    {
    // Id to show which visa or master method will call the 
    	// dialog with the progress authorisation 
    	
	int id = 0;

	
	// simple check to make sure something decent entered
	
	if (m > 1000)
	{
		
	// if so start the Progress Dilog and pass context int
		
	startAuthDialog(id);

	// store the number
	
	masterNumber = m;
	
	
	
	}
	else
	{
		
		// else state that not authorised
		
		
		Toast.makeText(PassangerEntry.this,
				getApplicationContext().getResources().getString(R.string.invalid_not_auth),
				Toast.LENGTH_SHORT).show();	
	}
}


// same for this one
   // but for visa
    

public void setVisaNumber(int v)
{
	int id = 1;
	
	if (v > 1000)
	{
	visaNumber = v;
	startAuthDialog(id);

	}
	else
	{
		Toast.makeText(PassangerEntry.this,
				getApplicationContext().getResources().getString(R.string.invalid_not_auth),
				Toast.LENGTH_SHORT).show();
	}
}
 


/// not required i think

public void setObjVisaNumber(int v)
{
	obj.setVisaNumber(v);
}

public void setObjMasterNumber(int m)
{
	obj.setMasterNumber(m);
}

/*
    public interface OnDataPass {
        public void onDataPass(int data);
    }   
    */
public void onRestoreInstanceState(Bundle savedInstanceState) {
	super.onRestoreInstanceState(savedInstanceState);
	nameCC= savedInstanceState.getString("nameCC");
	visaNumber = savedInstanceState.getInt("visaNumber");
	masterNumber = savedInstanceState.getInt("masterNumber");
    obj = (Storage) savedInstanceState.getSerializable("obj");
	
}


//  used to restore values when to program is stopped and put through its activity cycle again
// such as when a change orientation occurs
@Override
protected void onSaveInstanceState(Bundle outState) {
	//outState.putDouble("speed", speed);
	super.onSaveInstanceState(outState);

outState.putString("nameCC",nameCC);
outState.putInt("visaNumber", visaNumber);
outState.putInt("masterNumber", masterNumber);
outState.putSerializable("obj", obj);



}	

public void onResume()
{
super.onResume();

//obj.setMasterNumber(0);
//obj.setVisaNumber(0);
//obj.setNameCC(obj.getNameCC());

}


/// start the next Activity 




private void startPassAddress() {
	
	// check everything required is input
	
	if ((masterNumber != 0 || visaNumber != 0) && (nameCC !=""))
	{
    Intent launchPassAddress = new Intent(this, PassangerAddressEntry2.class);
    launchPassAddress.putExtra("obj", obj);
    startActivity(launchPassAddress);
	}
    
    
	else
	{
		Toast.makeText(PassangerEntry.this,"Please Enter Required Details",
				Toast.LENGTH_SHORT).show();
	}  
	
	
	
    
    }



	/// Authorization progress Dialog

	private void startAuthDialog(final int id) {
	 
    // display progress
    progressDialog = ProgressDialog.show(this, "Authorisation", "Please Wait");

    // start task in an new thread
    Thread thread = new Thread() {

        public void run () {
        	
        	
/// waste some CPU Cycles for time
        	
            for ( int i = 0; i < 100000000; i++ ) {
               
            }

            // after  the task has finished running
            
            handler.post(new Runnable() 
            {
                @Override
                public void run()
                {

                    // remove the progress Dialog 
                    progressDialog.dismiss();
                    
                    
                    // display toast authorizations depending on which 
                    // method called, visa or Master
                    
                    if(id == 1)
                    {
                	Toast.makeText(PassangerEntry.this,
                			getApplicationContext().getResources().getString(R.string.visacard_auth),
                			Toast.LENGTH_LONG).show();
                    }
                    if(id == 0)
                    {
                    	//show toast notification  
                    	Toast.makeText(PassangerEntry.this,
                    			getApplicationContext().getResources().getString(R.string.mastercard_auth),
                    			Toast.LENGTH_LONG).show();
                    }
                    

                }
            });

        }

    };

    thread.start();
}




    
}

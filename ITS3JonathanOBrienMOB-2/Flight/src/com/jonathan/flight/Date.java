/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address




takes in the dates for the flights, disables certain aspects if
a single was chosen previously.


*/





package com.jonathan.flight;

import java.util.Calendar;



import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


public class Date extends Activity{
	
	private Storage obj;

	
	
	
	private CheckBox checkBox2,checkBox,checkBox3,checkBox4;

    ///   previous variable that ids if it was return of single flight
	
	private boolean single;
  
	//private TimePicker timePicker;
	private DatePicker datePicker;
	
	// ints for date-pickers entries 
	
    private int yr, month, day, yr2, month2, day2;
    
    // date for current day for date picker
    private int dYr, dMonth, dDay;
    
    
    // created strings from ints
    private String departTime;
    private String returnTime;
    
    
    // dialogs for showing date pickers
    
    static final int DATE_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID2= 1;
    
    Button btnNext;
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date);
		
		
		
		/// read in Obj that was sent in Intent
		
		  Intent i = getIntent();
	        
	        //departAirport = i.getStringExtra("departairport");
	        //arrivalAirport =i.getStringExtra("arrivalairport");
	       // single = i.getBooleanExtra("single", true);
	        //choosenClass = i.getStringExtra("choosenclass");
	       // adultCount = i.getIntExtra("adultcount", -1);
	       //childCount = i.getIntExtra("childcount", -1);
	        //airports = i.getStringArrayExtra("airports");
	       obj = (Storage) i.getSerializableExtra("obj");

	       single = obj.getSingle();

	      
	       
// get todays date
	        
	        Calendar today = Calendar.getInstance();
	        dYr = today.get(Calendar.YEAR);
	        dMonth = today.get(Calendar.MONTH);
	        dDay = today.get(Calendar.DAY_OF_MONTH); 
	        
	        
/////////////////////////// Buttons ///////////////////////////////////////////////////////////////////// 
	        
	        
	        /// button to show date entry one
	        
	        Button  dateButton = (Button) findViewById(R.id.date_btn_1);
	        dateButton.setOnClickListener(new View.OnClickListener() {
		            @SuppressWarnings("deprecation")
					public void onClick(View view) {
		            	showDialog(DATE_DIALOG_ID);
		            }
		        });
	        
	        // button for date entry return
	        // if single if false activate listener on this 
	        
	        if (single == false)
	        {
			 Button  dateButton2 = (Button) findViewById(R.id.date_btn_2);
			 dateButton2.setOnClickListener(new View.OnClickListener() {
		            @SuppressWarnings("deprecation")
					public void onClick(View view) {
		            	showDialog(DATE_DIALOG_ID2);
		            }
		        });
	        }
	        
	        
	        // button to go back to previous Activity
	        
	        Button backButton = (Button) findViewById(R.id.back_date);
	        backButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                //return information to calling activity
	            	// get the sent intent
	                Intent i = getIntent();
	               
	                setResult(RESULT_OK, i);
	             
	                finish();
	            }
	        });
	        
	        
	        // button to forward to next activity 
	        
	        btnNext = (Button) findViewById(R.id.btn_next_date);        
			btnNext.setOnClickListener(new View.OnClickListener() 
	        {
	            public void onClick(View v) 
	            {    
	            	startCredit();	            	
	            }
					
				
	        });
	        

/////////////////---CheckBox---//////////////////////////////////////////////////////////////////////////////
	        
	        
			
			// set up check boxs for time entry
			
	         checkBox = (CheckBox) findViewById(R.id.chkd_7am);
	        checkBox.setOnCheckedChangeListener(listener);
	        
	         checkBox2 = (CheckBox) findViewById(R.id.chkd_7pm);
	        checkBox2.setOnCheckedChangeListener(listener);
	
	    
	     
	        
	         checkBox3 = (CheckBox) findViewById(R.id.chkr_7am);
	        checkBox3.setOnCheckedChangeListener(listener2);
	        
	        checkBox4 = (CheckBox) findViewById(R.id.chkr_7pm);
	        checkBox4.setOnCheckedChangeListener(listener2);
	        
	        
	        /// disable returns if single true

		      if (single == true)
		      {
		      	checkBox3.setEnabled(false);
		       	checkBox4.setEnabled(false);
		      }
		        
	  
	        
		
	}
	
//////////////////////////////overridden methods /////////////////////////////////////////////	
    
	
	
	
	/////////////// Create Dialogs for Date input///////
    @Override    
    protected Dialog onCreateDialog(int id) 
    {
    	
    	// add the Dialogs with the Date inputs
        switch (id) {
            case DATE_DIALOG_ID2: 
            	return new DatePickerDialog(this, mDateSetListener2, dYr, dMonth, dDay);
            case DATE_DIALOG_ID: 
                return new DatePickerDialog(this, mDateSetListener, dYr, dMonth, dDay);
        }
        return null;    
    }
 
    
    
    // date picker one
    // take in the input and store as integer values
    
    private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() 
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                    int dayOfMonth) 
            {
            	yr = year;
                month = monthOfYear;
                day = dayOfMonth;
                Toast.makeText(getBaseContext(), 
                        "You have selected : " + (month + 1) +
                        "/" + day + "/" + year,
                        Toast.LENGTH_SHORT).show();
                obj.setTimes1(yr, month, day);
            }
        };
        
        
        // date picker two
        
        private DatePickerDialog.OnDateSetListener mDateSetListener2 =
                new DatePickerDialog.OnDateSetListener() 
                {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                            int dayOfMonth) 
                    {
                    	yr2 = year;
                        month2 = monthOfYear;
                        day2 = dayOfMonth;
                        Toast.makeText(getBaseContext(), 
                                "You have selected : " + (month + 1) +
                                "/" + day + "/" + year,
                                Toast.LENGTH_SHORT).show();
                        obj.setTimes2(yr2, month2, day2);
                    }
                };
   
         
  //////////// Listeners for checkboxs////////
                
    	        private OnCheckedChangeListener listener = new OnCheckedChangeListener() {
    	        	 
    	        	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
    	        	if(isChecked){
    	        	switch(arg0.getId())
    	        	  {
    	        	
    	        	
    	        	/// Disable on when other is selected
    	        	
    	        	    case R.id.chkd_7am:
    	        	         checkBox.setChecked(true);
    	        	         checkBox2.setChecked(false);
    	        	         departTime=getApplicationContext().getResources().getString(R.string.seven_am);
    	        	         obj.setDepartTime(departTime);
    	        	        
    	        	         break;
    	        	    case R.id.chkd_7pm:
    	        	         checkBox2.setChecked(true);
    	        	         checkBox.setChecked(false);
    	        	         departTime =getApplicationContext().getResources().getString(R.string.seven_pm);
    	        	         obj.setDepartTime(departTime);
    	        	         break;
    	        	
    	        	  }
    	        	}
    	        	 
    	        	}
    	        	};
    	       
    	        	// Listener for checkboxs for Return 
    	        	// if single is false
    	        	
    	        	
    	        	  private OnCheckedChangeListener listener2 = new OnCheckedChangeListener() {
    	    	        	 
    	    	        	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
    	    	        	if(isChecked){
    	    	        	switch(arg0.getId())
    	    	        	  {
    	    	        	    
    	    	        	
    	    	        	// disable one if other is selected and vice-versa
    	    	        	   case R.id.chkr_7am:
    	    	        		 
    	    	        		
    	    	        		  checkBox3.setChecked(true);
    		        	         checkBox4.setChecked(false);
    		        	         returnTime=getApplicationContext().getResources().getString(R.string.seven_am);
    		        	         obj.setReturnTime(returnTime);
    		        	         break;

    	    	        	   case R.id.chkr_7pm:
    	    	        		 checkBox4.setChecked(true);
    	   	        	         checkBox3.setChecked(false);
                                 returnTime = getApplicationContext().getResources().getString(R.string.seven_pm);
                                 obj.setReturnTime(returnTime);
    	    	        	     break;
    	    	        	  }
    	    	        	}
    	    	        	 
    	    	        	}
    	    	        	};
    	    	        	
 
    	    	        	
    	    	      public void onRestoreInstanceState(Bundle savedInstanceState) {
    	    	        	super.onRestoreInstanceState(savedInstanceState);

    	    	        	    obj = (Storage) savedInstanceState.getSerializable("obj");
    	    	        		yr = savedInstanceState.getInt("yr");
    	    	        		month = savedInstanceState.getInt("month");
    	    	        		day = savedInstanceState.getInt("day");
    	    	        		yr2 = savedInstanceState.getInt("yr2");
    	    	        		month2 = savedInstanceState.getInt("month2");
    	    	        		day2 = savedInstanceState.getInt("day2");
    	    	        		single = savedInstanceState.getBoolean("single");
    	    	        		departTime = savedInstanceState.getString("departTime");
    	    	        		returnTime= savedInstanceState.getString("returnTime");
    	    	        	
    	    	        		
    	    	        	}


    	    	        	//  used to restore values when to program is stopped and put through its activity cycle again
    	    	        	// such as when a change orientation occurs
    	    	        	@Override
    	    	      protected void onSaveInstanceState(Bundle outState) {
    	    	        	outState.putInt("yr", yr);
    	    	        	outState.putInt("month", month);
    	    	        	outState.putInt("day", day);
    	    	        	outState.putInt("yr2", yr2);
    	    	        	outState.putInt("month2", month2);
    	    	        	outState.putInt("day2", day2);
    	    	        	outState.putInt("yr", yr);
    	    	        	outState.putBoolean("single", single);
    	    	        	
    	    	        	outState.putString("departTime", departTime);
    	    	        	outState.putString("returnTime", returnTime);

    	    	        	outState.putSerializable("obj", obj);
    	    	        	super.onSaveInstanceState(outState);



    	    	        	}	
                
                
     public void onResume()
     {
      
    	 super.onResume();
    	 

//obj.setYr(0);
//obj.setMonth(0);
//obj.setDay(0);
//obj.setYr2(0);
//obj.setMonth2(0);
//obj.setDay2(0);
//obj.setDepartTime("");
//obj.setReturnTime("");
    	 
     }

     
     /*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date, menu);
		return true;
	}
	
	*/
	
     
     
     
	
	
	////////////////////custom methods //////////////////////////////////////////////////////
	
	
     
     
     /// method to start next activity
     // doing some checks to make sure
     // everything has been entered that is required
     // checks based also on if single or return
	
	private void startCredit() {
		
		
		// if single true and required variables entered
		// or if single false and required variables entered
		
		// then create Intent and launch the next Activity
		
		if (((single == true) && 
				((yr + month + day) > 0)&&
				(departTime != null))
				|| 
				((single == false)&& 
				((yr2 + day2 + month2) > 0)&& 
				((yr + month + day) > 0) && 
				(returnTime != null)))
			
		{
        Intent launchCredit = new Intent(this, PassangerEntry.class);
        launchCredit.putExtra("obj", obj);
        startActivity(launchCredit);
		}
		else
		{
			
			///else display toast about requirements not being met
			
			Toast.makeText(Date.this,
					getApplicationContext().getResources().getString(R.string.please_enter_required),
					Toast.LENGTH_SHORT).show();
		}
		
        
        
        
        }
       
	
	

}

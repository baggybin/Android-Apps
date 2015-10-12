/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10



This class is the class which displays and stores the calcualtions and 
there results 


*/



package com.jonathan.ass1speed;




import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;





import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class Comp extends Activity {

	private double speed;
	private double weight;
	private double height;
	private double vel;
	private double kinetic;
	private DecimalFormat df = new DecimalFormat("0.0");
	String FILENAME = "file";
	//static flag to track screen orientation changes/ well oncreate() restarts
	// had to use a static integer rather than instancesave restore because would get reset to
	// zero. not sure if it may be an issue that i was implementing both on pause and on instance save
	static int flag;  

	
	// notification manager for getting the system service for notifications
	private NotificationManager mNManager;
	// gives an id for the notification
	private static final int NOTIFY_ID = 1001;
	
	// listview for listview
	private ListView resultsListView;
	
	// Declare an array to store data to fill the list
	private ArrayList <String> array =   new ArrayList <String>();
	
	// an adapter to connect the arraylist to the listview
	private ArrayAdapter<String> arrayAdapter;

   // private Dialogs d = new Dialogs();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comp);
		ImageView view = new ImageView(this);
		view.setImageResource(R.drawable.ic_launcher);
         
		
		////// notification with Pending intent ////////////////////////////////////////////////////////////
		
		// create a string to id the notification service
		String ns = Context.NOTIFICATION_SERVICE; // toast shows "notification"
		// get the notification server adding to mnmanger for use
		mNManager = (NotificationManager) getSystemService(ns);
		@SuppressWarnings("deprecation")
		
		// create title message for notification, adding the current system time
		final Notification msg = new Notification(R.drawable.ic_launcher,
		getApplicationContext().getResources().getString(R.string.new_event), 
		System.currentTimeMillis());
		
		// the button the activate the notification 
		Button notifyButton = (Button) findViewById(R.id.notification);
		
		notifyButton.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				// get general info about where the app is running
				Context context = getApplicationContext();
				
				// messages to be added to the notification 
				CharSequence contentTitle = getApplicationContext().getResources().getString(R.string.compare_online);
				CharSequence contentText = getApplicationContext().getResources().getString(R.string.Spat_calc);
				
				// create a pending intent to access a website
				Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.angio.net/personal/climb/speed"));
				PendingIntent intent = PendingIntent.getActivity(Comp.this, 0, msgIntent,
						Intent.FLAG_ACTIVITY_NEW_TASK);
				
				msg.defaults |= Notification.DEFAULT_SOUND;
				msg.flags |= Notification.FLAG_AUTO_CANCEL;

				msg.setLatestEventInfo(context, contentTitle, contentText,
						intent);
				
				
				mNManager.notify(NOTIFY_ID, msg);
			}
		});
		
		////// notification with Pending intent ////////////////////////////////////////////////////////////

		
		
		
		
		
		
		
		
		
		/// data passing and Calculations//////////////////////////////////////////////////////////////////
		
		
		
		// get the intent Bundle given by the calling Activity MainScreen
		// take the values from it and store them 
		// including the array that being passed back and forth
        Intent i = getIntent();
        
        speed = i.getDoubleExtra("speed", -1);
        weight = i.getDoubleExtra("weight", -1);
        if(i.getStringArrayListExtra("array") != null)
        {
        array = i.getStringArrayListExtra("array");	
        	
        }
        
        
        
        
       System.out.println("the flagmarker is now " + flag);
        // if values have been passed do some calculations

       if(flag < 1)
       {
        // call custom class Calculations constructor passing speed and weight
        Calculation calc = new Calculation(speed,weight);
        // get the height equivalent from the Calculation object
        height = calc.calc();
        
        // get the time to of hitting the ground
        vel = calc.freefallTime();
        
        // get the energy from this impact
        kinetic = calc.getKinetic();
        
        // set static flag that on screen recreation this control block wont be executed
        // again  
      
         flag++;
         
         array.add(speed+"KMh: "+" "+ weight +"KG: "+df.format(height) +"M: " + df.format(kinetic)+
         		getApplicationContext().getResources().getString(R.string.joules));
        // call dialog to display info
        dialog();
        }
     
       
       
        
		/// data passing and Calculations//////////////////////////////////////////////////////////////////

        
        
        
        
        
        
        ////// back return Button//////////////////////////////////////////////////////////////////////////
        
        Button backButton = (Button) findViewById(R.id.end_calc);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //return information to calling activity
            	// get the sent intent
                Intent i = getIntent();
                // add current variables of this activity to it
                // the array and the height
                i.putExtra("height", height);
                i.putExtra("array", array);
                // set the result in the intent to ok
                setResult(RESULT_OK, i);
                
                //reset the flag so next time this activity loaded it will display new diagol and append
                //results
                flag = 0;
                
                // finish with this activity
                finish();
            }
        });
		
        ////// back return Button//////////////////////////////////////////////////////////////////////////
 
        
        
        
        
        
        
        
        
        ////////// Listview //////////////////////////////////////////////////////////////////////////     
        
        
        // create listview for displaying previous results
		resultsListView = (ListView) findViewById(R.id.resultsListView);
		
		//Add string to arraylist for use in the listview

       
        //resource - The resource ID for a layout file containing a layout to use when instantiating views. 
        //textViewResourceId - The id of the TextView within the layout resource to be populated  
        //From the third parameter, you plugged the data set to adapter
        
        //create an array adapter to connect the arraylist to array for use
        // use the custom XML layout cause otherwise text was too large
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.mylist, array);
        //plug arraylist to listview with adapter
        resultsListView.setAdapter(arrayAdapter);	
        
        ////////// Listview /////////////////////////////////////////////////////////////////////////////    
       
		
	}
	
	
	/////////////// Overridden Methods//////////////////////////////////////////////////////////////////
	
	
	// save height data when activity ends during the  onpuase method ()
	@Override
	protected void onPause() {
		// Save our data here
		super.onPause();
		writeHeight();
	}
	
	
	///  retire the height value when activity gets recreated
	protected void onresume()
	{
		super.onResume();
		readHeight();
	}

	
	// restore variables saved by the onsaveinstancestate method
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		weight = savedInstanceState.getDouble("weight");
		speed = savedInstanceState.getDouble("speed");
		array = savedInstanceState.getStringArrayList("array");
		//flag = savedInstanceState.getInt("flag");
		
		//System.out.println("the flagmarker in on restore" + flagMarker);
		System.out.println("the flag in on restore is" + flag);
	}
	
	// save variables when the activity is interrupted 
	// used to reclaim resources 
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putStringArrayList("array", array);
		outState.putDouble("weight", weight);
		outState.putDouble("speed", speed);
		System.out.println("the flag in onsave is" + flag);
		//pass flag on activity recreation
		//outState.putInt("flag", flag);

		super.onSaveInstanceState(outState);

		
	}	
	

	
	/////////////// Overridden Methods//////////////////////////////////////////////////////////////////
	
	
	
	
	
	//// File Input Output Methods///////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	// method to write the result to a file for persistent storage
	// that can be accessed by the calling activity + after when it has been killed
	
	private void writeHeight() {
		ObjectOutputStream outputStream = null;

		try {

			// create Android FileOutputStream, private file for this app only
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);

			// Construct the output stream
			outputStream = new ObjectOutputStream(fos);
			outputStream.writeDouble(height);
			//System.out.println("height written to " + FILENAME + "  " + height); 
			
		} catch (FileNotFoundException ex) {
			System.out.println("file not found");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("i/o exception");
			ex.printStackTrace();
		} finally {
			// Close the ObjectOutputStream
			try {
				System.out.println("closing " + FILENAME);

				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	

	// read in the stored file double variable
	private void readHeight() {
		{

			ObjectInputStream inputStream = null;
			try {

				// Android get file input stream
				FileInputStream fis = openFileInput(FILENAME);

				// Construct the ObjectInputStream object
				inputStream = new ObjectInputStream(fis);

				height = inputStream.readDouble();

			} catch (EOFException ex) { 
				
				System.out.println("End of file reached.");

			} catch (FileNotFoundException ex) {
				System.out.println("FileNotFoundException");
				ex.printStackTrace();
			} catch (IOException ex) {
				System.out.println("IOException");
				ex.printStackTrace();
			} finally {
				// Close the ObjectInputStream
				System.out.println("closing...");
				try {
					if (inputStream != null) {
						inputStream.close();
						System.out.println("...closed");
					}
				} catch (IOException ex) {
					System.out.println("...IOException");
					ex.printStackTrace();
				}
			}
		}

	}
	
	
	
	
	
	//// File Input Output Methods///////////////////////////////////////////////////////////////////////////


	
	
	//////// Dialogs /////////////////////////////////////////////////////////////////////////////////////
	
	
			//set up nested dialogs to display information on activity start
	
			public void dialog(){
			AlertDialog dialog = new AlertDialog.Builder(this).create();
			dialog.setMessage(getApplicationContext().getResources().getString(R.string.equiv_free_fall)
					+ df.format(height) + 
					getApplicationContext().getResources().getString(R.string.meters) );
			
			
			// starts dialog to show the time till impact from that height
			dialog.setButton(DialogInterface.BUTTON_NEUTRAL,
					getApplicationContext().getResources().getString(R.string.time) ,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog2();	
						}
					});
			
			 // starts the dialog to display the energy on impact
			dialog.setButton(DialogInterface.BUTTON_POSITIVE,
					getApplicationContext().getResources().getString(R.string.energy) ,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog3();	
						}
					});
                
			// just exits main dialog doing nothing
			dialog.setButton(DialogInterface.BUTTON_NEGATIVE,
					getApplicationContext().getResources().getString(R.string.ok) ,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
			
						}
					});

			dialog.show();	
		}
		// the dialog to display time till impact
		public void dialog2(){
			AlertDialog dialog2 = new AlertDialog.Builder(this).create();
			dialog2.setMessage(getApplicationContext().getResources().getString(R.string.time_till_impact)
					+ df.format(vel) +
					getApplicationContext().getResources().getString(R.string.seconds) );
		
			dialog2.setButton(DialogInterface.BUTTON_NEUTRAL, 
					getApplicationContext().getResources().getString(R.string.ok),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});

			dialog2.show();	
		}
		//dialog to show energy at impact
		public void dialog3(){
			AlertDialog dialog3 = new AlertDialog.Builder(this).create();
			dialog3.setMessage(getApplicationContext().getResources().getString(R.string.energy_at_impact) 
		  + df.format(kinetic) + 
		  getApplicationContext().getResources().getString(R.string.joules) );
		
			dialog3.setButton(DialogInterface.BUTTON_NEUTRAL,
					getApplicationContext().getResources().getString(R.string.ok) ,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});

			dialog3.show();	
		}
		//////// Dialogs /////////////////////////////////////////////////////////////////////////////////////


}

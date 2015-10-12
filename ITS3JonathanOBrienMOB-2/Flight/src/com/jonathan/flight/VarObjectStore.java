/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address

This class is used to Read and Write in Objects to storage

//
*/






package com.jonathan.flight;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;



public class VarObjectStore extends Activity {
	String FILENAME = "object_file";
	Storage  storageObject = new Storage();
    Context context;
    
    
    public VarObjectStore()
    {
    	
    }
    
    
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		System.out.println("started ...");

	}
	
	
	public VarObjectStore(Context c)
	{
	context = c;	
	}
	
	
	// return the read in object
	public Storage getStorageObject()
	{
		return storageObject;
		
	}
	
	// object to write into Storage fILE
	public VarObjectStore(Storage s)
	{
		storageObject = s;
	}
	
	public void setObject(Storage obj)
	{
		storageObject = obj;
	}
	
	
	/// write object to storage
	
	
	public void writeStorage() {

		ObjectOutputStream outputStream = null;

		try {

			// create Android FileOutputStream, private file for this app only
			// passed context from MainFlight used here
			FileOutputStream fos = context.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);

			// Construct the output stream
			outputStream = new ObjectOutputStream(fos);

			// Create some objects to write to file
		
			outputStream.writeObject(storageObject); // write obect
			System.out.println("object1 written to " + FILENAME); // confirm on console that it worked

			

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
	
	
	
	// method for checking to see if there is an object stored
public boolean checkIfStored() {
		
		boolean exist = false;

		ObjectInputStream inputStream = null;
		System.out.println("chechking objects...");
		try {

			// Android get the file input stream
			FileInputStream fis = context.openFileInput(FILENAME);

			// Construct the ObjectInputStream object
			inputStream = new ObjectInputStream(fis);

			Object obj = null;
			
			// check to see if there is and object 
			if ((obj = inputStream.readObject()) != null)
			{
				exist = true;	
			}
		}
		catch(Exception ex)
		{}
		return exist;
	
}
	
	
	
	
	
	
	
	
	
	
	// Read objects back in
	public void readStorage() {
		

		ObjectInputStream inputStream = null;
		System.out.println("Reading objects...");
		try {

			// Android get file input stream
			FileInputStream fis = context.openFileInput(FILENAME);

			// Construct the ObjectInputStream object
			inputStream = new ObjectInputStream(fis);

			Object obj = null;
			
	

			while ((obj = inputStream.readObject()) != null) { // start reading in objects

				if (obj instanceof Storage) { // check it's the correct object type
                storageObject = (Storage) obj;
				}

			}

		} catch (EOFException ex) { // This exception will be caught when EOF is
									// reached
			System.out.println("End of file reached.");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException");
			ex.printStackTrace();
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

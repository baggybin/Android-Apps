
/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

standard java class, that does the calculations for the main APP

takes the values of speed and weight and calculates the equivalent height that would be to fall from
in free fall

also returns the energy produced on impact

*/

package com.jonathan.ass1speed;

public class Calculation {
	
	private double speed;
	private double weight;
	private double velocity;
	private double kinetic;
	private double height;
	
	Calculation(double s, double w)
	{
	speed = s;
	weight = w;
				
	}
	
	public double freefallTime()
	{
		// gives the time it will take to impact the ground from this height
    double timeImpact =(double) Math.sqrt(height/4.9);
	return timeImpact;	
		
	}
	public double getKinetic()
	{
		return kinetic;
	}
	public double getWeight()
	{
		return weight;
	}
	
	public double calc()
	{
  
		
		////// forgot that mass will have no bearing in this
		/// calculation apart from if it is null only the energy will change on impact
		//energy = (1/2)mass * velocity  to power of 2
		
		velocity = (speed/3600)* 1000;
		velocity = (velocity) * velocity;
		kinetic = (0.5 * weight) * velocity;
						
		//height = energy / (mass * gravity)

		height = kinetic/(weight*9.6);
		
			
	return height;	
	}
	

}

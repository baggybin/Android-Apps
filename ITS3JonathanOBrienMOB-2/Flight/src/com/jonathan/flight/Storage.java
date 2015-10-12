/*
Name - Jonathan O'Brien
Email - jonathan.obrien@mycit.ie
version - 10

About -- program is flight booking system which takes user choice
of airports, single or return, no of adults and children and Class
to create a booking and have variable prices depending on these choices
Also inputs Credit card details and home address



used for Storing variables which can be accessed an manipulated from one
place


//
*/








package com.jonathan.flight;

import java.io.Serializable;

// make it implement serializable so that it can be stored

public class Storage implements Serializable {

	private String departAirport;
	private String arrivalAirport;
	private String choosenClass;
	private boolean single= true;
	private int adultCount,childCount;
	private int adultTotalPrice, childTotalprice;
    private int yr, month, day, yr2, month2, day2;
    private String departTime;
    private String returnTime;
    
    
    private String htmlTextVF1,htmlTextVF2,htmlTextVF3;
    
	private int masterNumber;
	private int visaNumber;
	private String nameCC;
	
	private String house;
 	private String street ;
 	private String town ;
 	private String county;


	
	private String[] airports;
	
	
	public Storage()
	{}
	
	public Storage(String d, String a,String c, boolean s, int ac, int cc, String [] air)
	{
    departAirport = d;
    arrivalAirport = a;
    choosenClass = c;
    single = s;
    adultCount =ac;
    childCount = cc;
    airports = air;
		
	}
	
	
	

	public String getDepAirport()
	{
	return departAirport;	
	}
	
	public String getArrAirport()
	{
		return arrivalAirport;
	}
	
	public String getChoosenClass()
	{
		return choosenClass;
	}
	
	public boolean getSingle()
	{
		return single;
	}
	
	public int getAdultCount()
	{
	
		return adultCount;
	}
	
	public int getChildCount()
	{
		return childCount;
	}
	
	public String [] getAirports()
	{
		return airports;
	}
	
	
	//////////////////////// Mutators ///////////////////////////////////////////////////////////////
	
	public void setDepAirport(String  d)
	{
	departAirport = d;	
	}
	
	public void setArrAirport(String a)
	{
		arrivalAirport = a;
	}
	
	public void setChoosenClass(String c)
	{
	choosenClass = c;
	}
	
	public void setSingle(boolean s)
	{
	 single = s;
	}
	
	public void setAdultCount(int ac)
	{
	
	adultCount = ac;
	}
	
	public void setChildCount(int cc)
	{
		childCount = cc;
	}
	
	public void setAirports(String [] air)
	{
		airports = air;
	}
	
	public void setTimes1(int y, int m, int d)
	{
	setYr(y);
	setMonth(m);
	setDay(d);
		
	}
	
	public void setTimes2(int y2, int m2, int d2)
	{
		setYr2(y2);
		setMonth2(m2);
		setDay2(d2);
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYr() {
		return yr;
	}

	public void setYr(int yr) {
		this.yr = yr;
	}

	public int getYr2() {
		return yr2;
	}

	public void setYr2(int yr2) {
		this.yr2 = yr2;
	}

	public int getDay2() {
		return day2;
	}

	public void setDay2(int day2) {
		this.day2 = day2;
	}

	public int getMonth2() {
		return month2;
	}

	public void setMonth2(int month2) {
		this.month2 = month2;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getDepartTime() {
		return departTime;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public int getMasterNumber() {
		return masterNumber;
	}

	public void setMasterNumber(int masterNumber) {
		this.masterNumber = masterNumber;
	}

	public int getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(int visaNumber) {
		this.visaNumber = visaNumber;
	}

	public String getNameCC() {
		return nameCC;
	}

	public void setNameCC(String nameCC) {
		this.nameCC = nameCC;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public int getChildTotalprice() {
		return childTotalprice;
	}

	public void setChildTotalprice(int childTotalprice) {
		this.childTotalprice = childTotalprice;
	}

	public int getAdultTotalPrice() {
		return adultTotalPrice;
	}

	public void setAdultTotalPrice(int adultTotalPrice) {
		this.adultTotalPrice = adultTotalPrice;
	}

	public String getHtmlTextVF1() {
		return htmlTextVF1;
	}

	public void setHtmlTextVF1(String htmlTextVF1) {
		this.htmlTextVF1 = htmlTextVF1;
	}

	public String getHtmlTextVF3() {
		return htmlTextVF3;
	}

	public void setHtmlTextVF3(String htmlTextVF3) {
		this.htmlTextVF3 = htmlTextVF3;
	}

	public String getHtmlTextVF2() {
		return htmlTextVF2;
	}

	public void setHtmlTextVF2(String htmlTextVF2) {
		this.htmlTextVF2 = htmlTextVF2;
	}
	
	
	
	
	
	
	
}

package com.ninza.hrm.api.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * This class consists of generic methods to use java utilities
 * @author neera
 */
public class JavaFileUtility {
	/**
	 * This method will generate random number and return value to caller
	 * @return
	 */
	public int getRandomNumber()
	{
		Random r=new Random();
		int randomNum=r.nextInt(5000);
		return randomNum;
	}
	/**
	 * This method will generate random string and return value to caller
	 * @return
	 */
	public String getRandomString()
	{
		String s="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String s1="";int n=7;
		for(int i=0;i<n;i++)
		{
			s1=s1+s.charAt((int) (Math.random()*s.length()));
		}
		return s1;
	}
	/**
	 * This method will get current system date in 'YYYYMMDD' format
	 * @return
	 */
	public String getSysDateInYYYYMMDD()
	{
		Date d=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String reqFormat=sim.format(d);
		return reqFormat;
	}
	/**
	 * This method will get current system date in 'DDMMYYYY' format
	 * @return
	 */
	public String getSysDateInDDMMYYYY()
	{
		Date d=new Date();//17/10/2025
		SimpleDateFormat sim=new SimpleDateFormat("dd/MM/yyyy");
		String reqFormat=sim.format(d);
		return reqFormat;
	}
	/**
	 * This method will get date of provided number of days prior to system date in 'YYYYMMDD' format
	 * @param days
	 * @return
	 */
	public String getRequiredDateYYYYMMDD(int days)
	{
		Date d=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		sim.format(d);
		Calendar c=sim.getCalendar();
		c.add(Calendar.DAY_OF_MONTH, days);
		String reqDate=sim.format(c.getTime());
		return reqDate;
	}
}

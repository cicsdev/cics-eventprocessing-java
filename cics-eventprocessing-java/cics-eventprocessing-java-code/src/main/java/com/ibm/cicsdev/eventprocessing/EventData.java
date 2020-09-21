package com.ibm.cicsdev.eventprocessing;

public class EventData
{
	private String email;
	private String account;
	
	public EventData()
	{
		this(null, null);
	}
	
	public EventData(String email, String account)
	{
		this.email = email;
		this.account = account;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getAccount()
	{
		return account;
	}
	
	public void setAccount(String account)
	{
		this.account = account;
	}
	
	@Override
	public String toString() {
		return "email: " + this.email + ", account: " + this.account;
	}
}

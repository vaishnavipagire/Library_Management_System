package com.data;

import java.util.Date;

public class Transaction 
{
	 User user;
	 Book book;
     Date date;
	 String type; 

	    public Transaction(User user, Book book, String type) 
	    {
	        this.user = user;
	        this.book = book;
	        this.type = type;
	        this.date = new Date();
	    }

	    @Override
	    public String toString() 
	    {
	        return user.name + " " + type + " " + book.title + " on " + date;
	    }
}

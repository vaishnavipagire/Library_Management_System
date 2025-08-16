package com.data;

  public class Book 
{
	 int id;
	 String title, author;
     boolean available;
     
	public Book(int id, String title, String author) 
	{
		this.id = id;
		this.title = title;
		this.author = author;
		this.available = available;
	}

	@Override
    public String toString()
	{
        return id + " - " + title + " by " + author + (available ? " (Available)" : " (Checked Out)");
    }
 }
  

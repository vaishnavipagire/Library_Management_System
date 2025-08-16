package com.data;

public class User 
{
	int userId;
    String name;

    public User(int userId, String name) 
    {
        this.userId = userId;
        this.name = name;
    }

    @Override
    public String toString() 
    {
        return userId + " - " + name;
    }
}

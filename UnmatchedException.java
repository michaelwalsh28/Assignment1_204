
/*
 * Michael Walsh
 * June 9, 2024
 * Professor Tsai
 * Assignment 1 Password Checker
 */

public class UnmatchedException extends Exception
{
	public UnmatchedException()
	{
		super("The passwords do not match");
	}
	public UnmatchedException(String message)
	{
		super(message);
	}
}

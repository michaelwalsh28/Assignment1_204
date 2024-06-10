/*
 * Michael Walsh
 * June 9, 2024
 * Professor Tsai
 * Assignment 1 Password Checker
 */

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordCheckerUtility 
{
	ArrayList<String> passwords = new ArrayList<>();
	
	PasswordCheckerUtility()
	{
		
	}
	
	//compare passwords with thrown exception
	public static void comparePasswords​(String password, String passwordConfirm) throws UnmatchedException
	{
		if (!password.equals(passwordConfirm))
		{
			throw new UnmatchedException("Passwords do not match");
		}
	}
	
	// use above method to return true or false depending on comparability
	public static boolean comparePasswordsWithReturn​(String password, String passwordConfirm)
	{
		try {
			comparePasswords​(password, passwordConfirm);
			return true;
		}
		catch (UnmatchedException e)
		{
			return false;
		}
	}
	
	
	//make sure the password is of valid length
	public static boolean isValidLength​(String password) throws LengthException
	{
		if (password.length() > 5)
		{
			return true;
		}
		throw new LengthException("Password must be at least 6 characters long");
	}
	
	
	// using regular expressions find if the password has uppercase letter
	public static boolean hasUpperAlpha​(String password) throws NoUpperAlphaException
	{
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
		{
			throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
		}
		else {
			return true;
		}
	}
	
	
	// using regular expressions find if the password has lowercase letter
	static boolean hasLowerAlpha​(String password) throws NoLowerAlphaException
	{
		Pattern pattern = Pattern.compile("[a-z]");
		Matcher matcher = pattern.matcher(password);
		
		if (!matcher.find())
		{
			throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
		}
		else {
			return true;
		}
	}
	
	
	// using regular expressions make sure the password has a digit
	static boolean hasDigit​(String password) throws NoDigitException
	{
		Pattern pattern = Pattern.compile("\\d");
		Matcher matcher = pattern.matcher(password);
		
		if (!matcher.find())
		{
			throw new NoDigitException("The password must contain at least one digit");
		}
		else {
			return true;
		}
		
	}
	
	// using regular expressions make sure the password has a special character
	static boolean hasSpecialChar​(String password) throws NoSpecialCharacterException
	{
		Pattern pattern = Pattern.compile("\\W");
		Matcher matcher = pattern.matcher(password);
		
		if (!matcher.find())
		{
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		}
		else {
			return true;
		}
	}
	
	
	// using regular expressions make sure the password does not have more than 2 of the same characters repeated
	static boolean NoSameCharInSequence​(String password) throws InvalidSequenceException
	{
		Pattern pattern = Pattern.compile("(.)\\\1");
		Matcher matcher = pattern.matcher(password);
		
		if (matcher.find())
		{
			throw new InvalidSequenceException("The password cannot contain more than two of the same character in a sequence");
		}
		else {
			return true;
		}
	}
	
	
	// iterate through each exception to find whether the password is valid or not
	// the validity is in order of the exceptions below
	// i.e. if a password doesnt have a digit, but it is only 5 character, the length will be the reason for the invalid password
	public static boolean isValidPassword​(String password) throws LengthException, 
				NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException,
				InvalidSequenceException, WeakPasswordException
	{
		try
		{
			isValidLength​(password);
		}
		catch (LengthException e)
		{
			throw new LengthException(e.getMessage());
		}
		
		try 
		{
			hasUpperAlpha​(password);
		}
		catch (NoUpperAlphaException e)
		{
			throw new NoUpperAlphaException(e.getMessage());
		}
		
		try
		{
			hasLowerAlpha​(password);
		}
		catch (NoLowerAlphaException e)
		{
			throw new NoLowerAlphaException(e.getMessage());
		}
		
		try
		{
			hasDigit​(password);
		}
		catch (NoDigitException e)
		{
			throw new NoDigitException(e.getMessage());
		}
		
		try
		{
			hasSpecialChar​(password);
		}
		catch (NoSpecialCharacterException e)
		{
			throw new NoSpecialCharacterException(e.getMessage());
		}
		
		try 
		{	
			NoSameCharInSequence​(password);
		}
		catch (InvalidSequenceException e)
		{
			throw new InvalidSequenceException(e.getMessage());
		}
		
		return true;
	}
	
	
	// find if the valid password is inbetween 6 and 9 characters in length
	static boolean hasBetweenSixAndNineChars​(String password)
	{
		return password.length() >= 6 && password.length() <= 9;
	}
	
	
	// using the above method, if the password is valid and between 6 and 9 characters
	// the password is "OK but weak"
	public static boolean isWeakPassword​(String password) throws LengthException, 
	NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException,
	InvalidSequenceException, WeakPasswordException
	{
		try 
		{
			if (isValidPassword​(password) && hasBetweenSixAndNineChars​(password))
			{
				return true;
			}
		}
		catch (WeakPasswordException e)
		{
			throw new WeakPasswordException(e.getMessage());
		}
		return false;
	}
	
	// iterate through the array of passwords to find the invalid ones and the reason they are invalid
	// using the isValidPassword method defined above.
	public static ArrayList<String> getInvalidPasswords​(ArrayList<String> passwords)
	{
		ArrayList<String> invalid = new ArrayList<>();
		for (int i = 0; i < passwords.size(); i++)
		{
			try {
				
				if (!isValidPassword​(passwords.get(i)))
				{
					invalid.add(passwords.get(i));
				}
			}
			catch (Exception e)
			{
				invalid.add(passwords.get(i) + " " + e.getMessage());
			}
		}
		return invalid;
	}
}

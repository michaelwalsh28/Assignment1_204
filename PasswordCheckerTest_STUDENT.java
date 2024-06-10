
/*
 * Michael Walsh
 * June 9, 2024
 * Professor Tsai
 * Assignment 1 Password Checker
 */


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	
	ArrayList<String> passwords;
	String password1, password2, password3, password4, password5; 

	@Before
	public void setUp() throws Exception 
	{
		password1 = "michaelwalsh040";
		password2 = "MichaelWalsh$040";
		password3 = "Michael";
		password4 = "mike";
		password5 = "MMMichael$58";
		
		String[] p = {password1, password2, password3, password4};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception 
	{
		password1 = null;
		password2 = null;
		password3 = null;
		password4 = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword​(password1));
		}
		catch(LengthException e)
		{
			assertTrue("Successfully threw a lengthExcepetion", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides lengthException",true);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword​(password1));
		}
		catch (LengthException e)
		{
			assertTrue("Successfully threw a LengthException", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides LengthException", true);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword​(password4));
		}
		catch (LengthException e)
		{
			assertTrue("Successfully threw a LengthException", true); 
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides LengthException", true);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword​(password1));
		}
		catch (NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException", false);
		}
		
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword​(password2));
		}
		catch (NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a NoUpperAlphaException", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException", false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword​(password1));
		}
		catch (NoLowerAlphaException e)
		{
			assertTrue("Successfully threw a NoLowerAlphaException", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoLowerAlphaException", true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword​(password2));
		}
		catch (NoUpperAlphaException e)
		{
			assertTrue("Successfully threw a WeakPasswordException", false);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides WeakPasswordException", false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword​(password5));
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw an InvalidSequenceExcepetion",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some other exception besides an InvalidSequenceException",false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword​(password3));
		}
		catch (NoDigitException e)
		{
			assertTrue("Successfully threw a NoDigitException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException", false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords​(passwords);

		try {
			assertTrue(PasswordCheckerUtility.isValidPassword​("Michael!23"));
		} catch (LengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoUpperAlphaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoLowerAlphaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoDigitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSpecialCharacterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSequenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WeakPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords​(passwords);
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), password1);
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
	}
	
}

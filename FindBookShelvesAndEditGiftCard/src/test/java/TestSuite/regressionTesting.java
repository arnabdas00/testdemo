package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.Base;
import Pages.BookShelves;

public class regressionTesting extends Base {
	@Test
	public void regressionTest() throws IOException, InterruptedException {
		
		//Creating new object of class BookShelves
		BookShelves rg = new BookShelves();
		
		//Creating report for Regression Test
		ExtentTest regTest = report.createTest("Urbanladder Test 2", "Functionalities of urbanladder website");
		regTest.log(Status.INFO, "Starting Regression Test");
		
		String title = rg.open();
		
		//Validate the title of the page: https://www.urbanladder.com/
		if (title.contains("Urban Ladder")) {
			regTest.pass("Successfully navigated to website");
		} else {
			regTest.fail("Failed to navigate to website");
		}

		rg.scrolldown(500);

		String ar = rg.bookShelve();
		//To validate the Bookshelf button
		if (ar.contains("Bookshelf")) {
			regTest.pass("Successfully clicked on Bookshelves");

		} else {
			regTest.fail("Unable to click on Bookshelves");
		}

		//To validate the exclude out of stock check box
		
		if(rg.excludeOutOfStock().contains("In Stock Only"))
		{
			regTest.pass("Successfully clicked on exclude out of stock checkbox");
		}
		else 
		{
		regTest.fail("Unable to click on exclude out of stock checkbox");
		}
		Thread.sleep(3000);
		
		//Validate the selection of storage type
		
		if(rg.storagetype().contains("Open"))
		{
			regTest.pass("Successfully selected storege type");
			
		} 
		else {
			regTest.fail("Unable to select storege type");
		}
		
		
        Thread.sleep(2000);
		//To validate the selection of price range 
		try {
			rg.selectprice();
		} catch (Exception e) {
			regTest.fail("Unable to select price range");
		}
		regTest.pass("Successfully selected the price range");

		Thread.sleep(5000);
		
		//To validate the method for displaying the name and price of bookshelves(1st 3)
		try {
			rg.getNameAndPrice();
		} catch (Exception e) {
			regTest.fail("Unable to display name and price of 1st three bookshelf");
		}
		regTest.pass("Successfully displayed name and price of 1st three bookshelf");

		Thread.sleep(3000);
		rg.scrollUp(100);
		
		//Validate the navigation on collections 
		if(rg.collection().contains("Collections"))
		{
			regTest.pass("Successfully navigated to collections menu");
		}
		else 
		{
			regTest.fail("Unable to navigate to collections menu");
		}
		
		
		//Validate the method to display the menu and sublists under collections 
		try {
			rg.collectionList();
		} catch (Exception e) {
			regTest.fail("Unable to display the Menu and sublists under Collections tab");
		}
		regTest.pass("Successfully displayed the Menu and sublists under Collections tab");
		Thread.sleep(3000);
		rg.scrolldown(200);
		Thread.sleep(3000);
		rg.scrollUp(500);
		
        //To validate the method to click on gift card
		if(rg.giftcard().contains("Gift Cards"))
		{
			
			regTest.pass("Successfully selected type of gift card");
		}
		else
		{
			regTest.fail("Unable to select type of gift card");
		}
		
		//To validate the method to click on birth-day gift card
		if(rg.birthday().contains("Birthday"))
		{
			
			regTest.pass("Successfully selected birthday gift card");
		}
		else
		{
			regTest.fail("Unable to select birthday gift card");
		}
		
		//To validate the method to fill details (amount and date)
		try {
			rg.fillgiftcard();
		} catch (Exception e) {
			regTest.fail("Unable to fill the details in giftcard");
		}
		regTest.pass("Successfully filled the details in giftcard");

		//To validate the method to fill details
		try {
			regTest.addScreenCaptureFromPath(rg.setGiftCardDetails("Jaya Bisht", "jayabisht@gmail.com",
					"Ravinder Bisht", "ravijrgmail.com", "9876543210", "Blessings"));
		} catch (Exception e) {
			regTest.fail("Unable to capture error message");
		}
		regTest.pass("Successfully captured error message");
		regTest.log(Status.INFO, "Ending Regression Test");

	}

}

package TestSuite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.Base;
import Pages.BookShelves;

public class FindBookShelvesTest extends Base{
	@Test
	public void smokeTests() throws FileNotFoundException, IOException, InterruptedException {
		
		//Creating new object of Class BookShelves
		BookShelves bs = new BookShelves();
		ExtentTest smokeTest = report.createTest("Urbanladder Test 1","Functionalities of urbanladder website");
		
		//Creating report for smoke test
		smokeTest.log(Status.INFO, "Starting Smoke Test");
		
		String title=bs.open();
		//Validate the title of the page: https://www.urbanladder.com/
		if(title.contains("Urban Ladder")) 
		{
	       smokeTest.pass("Successfully navigated to website");
		}
		else 
		{
		   smokeTest.fail("Failed to navigate to website");
		}
		bs.scrolldown(500);
		
		//Validate the navigation on collections 
		if(bs.collection().contains("Collections"))
		{
			smokeTest.pass("Successfully navigated to collections menu");
		}
		else 
		{
			smokeTest.fail("Unable to navigate to collections menu");
		}
		Thread.sleep(3000);
		
		//Validate the method to display the menu and sublists under collections 
		try {
		    bs.collectionList();
		}catch(Exception e)
		{
			smokeTest.fail("Unable to display the Menu and sublists under Collections tab");
		}
		smokeTest.pass("Successfully displayed the Menu and sublists under Collections tab");
		smokeTest.log(Status.INFO, "Ending Smoke Test");
		
		}
	
	   

}

package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;

public class BookShelves extends Base {

	public static By bsicon = By.xpath("//a[@href='/bookshelf?src=explore_categories']");
	public static By excos = By.id("filters_availability_In_Stock_Only");
	public static By stype = By.xpath("//li[@data-group='storage type']");
	public static By typeopen = By.id("filters_storage_type_Open");
	public static By price = By.xpath("//li[@data-group='price']");
	public static By sprice = By.xpath("//div[@class='noUi-handle noUi-handle-upper']");
	public static By bsname = By.xpath("//div[@class='product-title product-title-sofa-mattresses']//span");
	public static By bsprice = By.xpath("//div[@class='price-number']//span");
	public static By pointer = By.xpath("(//div[@class='productbox'])[3]");
	public static By logclose = By.linkText("Close");
	public static By col = By.xpath("//span[text()[normalize-space()='Collections']]");
	public static By gift = By.linkText("Gift Cards");
	public static By mouse = By
			.xpath("//body/div[@id='app-container']/div[1]/main[1]/section[1]/section[1]/ul[1]/li[3]");
	public static By m_click = By.xpath("(//button[text()='Choose this'])[3]");
	public static By fill = By.name("amount_select");
	public static By mon = By.xpath(
			"//body/div[@id='app-container']/div[1]/main[1]/section[1]/section[2]/div[1]/section[2]/div[4]/select[1]");
	public static By days = By.xpath(
			"//body/div[@id='app-container']/div[1]/main[1]/section[1]/section[2]/div[1]/section[2]/div[4]/select[2]");
	public static By next = By.xpath("(//button[@type='button'])[8]");
	public static By R_name = By.name("recipient_name");
	public static By R_email = By.name("recipient_email");
	public static By C_name = By.name("customer_name");
	public static By C_email = By.name("customer_email");
	public static By C_num = By.name("customer_mobile_number");
	public static By com = By.name("message");
	public static By confirm = By.xpath("//button[@type='submit']");
	public static String spath;
	public static WebDriverWait wait= new WebDriverWait(d,10);
	public static By vStock =By.xpath("//li[@data-option-key='In Stock Only']//span[1]");
	public static By vOpen =By.xpath("//li[@data-filter-name='storage_type']//span[1]");
	
	// Method to open the webapplication "https://www.urbanladder.com/" in browser and get the title of the page
	public String open() {
		d.get(baseUrl);
		return d.getTitle();

	}
	
    // Method to scroll down the page
	public void scrolldown(int a) {
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("window.scrollTo(0," + a + ")");
	}
	
	// Method to scroll up the page
	public void scrollUp(int b) {
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("window.scrollTo(" + b + ",0)");
	}
    
	// Method to click on the bookshelf and get the title of the page
	public String bookShelve() throws InterruptedException {
		d.findElement(bsicon).click();
		Thread.sleep(2000);
		return d.getTitle();
	}
	
	// Method to exclude item which are out of stock
	public String excludeOutOfStock() {
		d.findElement(excos).click();
		wait.until(ExpectedConditions.visibilityOf(d.findElement(vStock)));
		return d.findElement(vStock).getText();
	}
	
	// Method to select the storage type of the bookshelf
	public String storagetype() {
		WebElement mpointer = d.findElement(stype);
		Actions action = new Actions(d);
		wait.until(ExpectedConditions.visibilityOf(mpointer));
		action.moveToElement(mpointer).perform();
		wait.until(ExpectedConditions.visibilityOf(d.findElement(typeopen)));
		d.findElement(typeopen).click();
		wait.until(ExpectedConditions.visibilityOf(d.findElement(vOpen)));
		return d.findElement(vOpen).getText();

	}
    
	// Method to select price range of bookshelf
	public void selectprice() {
		WebElement mpointer = d.findElement(price);
		WebElement slider = d.findElement(sprice);
		Actions action = new Actions(d);
		action.moveToElement(mpointer).perform();
		int l = 210;
		action.dragAndDropBy(slider, -l, 0).perform();
		
	}
    
	// Method to get the Name and price of the 1st three bookshelf displayed
	public void getNameAndPrice() {
		WebElement mpointer = d.findElement(pointer);
		wait.until(ExpectedConditions.visibilityOf(mpointer));
		Actions action = new Actions(d);
		action.moveToElement(mpointer).perform();
		List<WebElement> name = d.findElements(bsname);
		List<WebElement> pr = d.findElements(bsprice);
		System.out.println("****** Name and Price of the 1st three bookshelves ******");
		System.out.println("__________________________________________________________");
		System.out.println("");
		for (int j = 0; j < 3; j++) {
			System.out.println("Name : " + name.get(j).getText());

			String iprice = pr.get(j).getText();
			int k = Integer.parseInt(iprice.replaceAll("[^0-9]", ""));

			System.out.println("Price : " + k);

			System.out.println("");
		}
		d.findElement(logclose).click();

	}
    
	// Method to navigate cursor to collectios menu
	public String collection() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("window.scrollTo(100,0)");
		WebElement c = d.findElement(col);
		wait.until(ExpectedConditions.visibilityOf(c));
		Actions action = new Actions(d);
		action.moveToElement(c).perform();
		return c.getText();
	}

	// Method to display the menu and sublists under collections 
	public void collectionList() {
		System.out.println("****** Menu and sublists under collections tab ******");
		System.out.println("");
		for (int i = 1; i < 4; i++) {
			WebElement headerList = d
					.findElement(By.xpath("(//ul[@class='inline-list left'])[10]/li[" + i + "]/div[1]/a[1]"));
			wait.until(ExpectedConditions.visibilityOf(headerList));
			System.out.println(headerList.getText());
			System.out.println("----------------");
			for (int j = 1; j < 7; j++) {
				WebElement subList = d.findElement(By
						.xpath("(//ul[@class='inline-list left'])[10]/li[" + i + "]/ul[1]/li[" + j + "]/a[1]/span[1]"));
				wait.until(ExpectedConditions.visibilityOf(subList));
				System.out.println(subList.getText());
			}
			System.out.println("");
		}
	}

	// Method to click on the gift card
	public String giftcard() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(d.findElement(gift)));
		d.findElement(gift).click();
		Thread.sleep(2000);
		return d.getTitle();
	}

	// Method to navigate cursor to the birthday gift card
	public String birthday() {
		WebElement b = d.findElement(mouse);
		Actions action = new Actions(d);
		action.moveToElement(b).perform();
		d.findElement(m_click).click();
		return b.getText();
	}

	// Method to fill(amount and date) the data for gift card
	public void fillgiftcard() throws InterruptedException {
		d.findElement(fill).sendKeys("10000");

		WebElement month = d.findElement(mon);
		Select select = new Select(month);
		select.selectByValue("8/2021");
		WebElement day = d.findElement(days);
		wait.until(ExpectedConditions.visibilityOf(day));
		Select select1 = new Select(day);
		select1.selectByValue("17");

		d.findElement(next).click();
	}

	// Method to fill necessary data of sender and recipient
	public String setGiftCardDetails(String name, String email, String cName, String cEmail, String cNum,
			String cMessage) throws InterruptedException, IOException {
		d.findElement(R_name).sendKeys(name);
		d.findElement(R_email).sendKeys(email);

		d.findElement(C_name).sendKeys(cName);
		d.findElement(C_email).sendKeys(cEmail);
		d.findElement(C_num).sendKeys(cNum);

		d.findElement(com).sendKeys(cMessage);
		d.findElement(confirm).click();
        Thread.sleep(3000);
		spath = takescreenshot("Error.png");
		return spath;

	}

}

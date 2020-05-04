package example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class NewTest {
	
    String url = "https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_distinct";
    String driverPath = "C:\\Users\\rocio\\chromedriver.exe";
    WebDriver driver;
    
    @Test
    public void task1() {
	  
	   System.setProperty("webdriver.chrome.driver",driverPath);
	   driver = new ChromeDriver();
	   driver.get(url);
	   WebDriverWait wait = new WebDriverWait(driver,10);
	   
	   //check if 'SQL Statement:' element is present
	   boolean element1Present = !driver.findElements(By.xpath("//*[text()='SQL Statement:']")).isEmpty();
	   assertTrue(element1Present,"The element 'SQL Statement:' is not present");
	   
	   //check if 'Result:' element is present
	   boolean element2Present = !driver.findElements(By.xpath("//*[text()='Result:']")).isEmpty();
	   assertTrue(element2Present,"The element 'Result:' is not present");
	   
	   //check if 'The Try-SQL Editor' element is present
	   boolean element3Present = !driver.findElements(By.xpath("//*[contains(@style,'text-align:center;font')]")).isEmpty();
	   assertTrue(element3Present,"The element 'The Try-SQL Editor' is not present");
	   
	   //check if 'advertisement' element is present
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'google_ads')]")));
	   boolean element4Present = !driver.findElements(By.xpath("//div[contains(@id,'google_ads')]")).isEmpty();
	   assertTrue(element4Present,"The element 'advertisement' is not present");
	   
	   //check if 'database' element is present
	   boolean element5Present = !driver.findElements(By.id("dbInfo")).isEmpty();
	   assertTrue(element5Present,"The element 'database' is not present");
	   
	   driver.close();	   
  }

  @Test
  public void task2() {
	  
	   System.setProperty("webdriver.chrome.driver",driverPath);
	   driver = new ChromeDriver();
	   driver.get(url);
	   
	   String PictureText= "Click \"Run SQL\" to execute the SQL statement above.\n" + 
	   		"W3Schools has created an SQL database in your browser.\n" + 
	   		"The menu to the right displays the database, and will reflect any changes.\n" + 
	   		"Feel free to experiment with any SQL statement.\n" + 
	   		"You can restore the database at any time.";
	   String DefaultText = driver.findElement(By.id("divResultSQL")).getText();
	   
	   //check that the text displayed is the same as in the picture
	   assertEquals(PictureText, DefaultText, "The default text displayed in result field is not the same as in the picture");
	   
	   driver.close();	   
  }
  
  @Test
  public void task3() throws InterruptedException {
	  
	   System.setProperty("webdriver.chrome.driver",driverPath);
	   driver = new ChromeDriver();
	   driver.get(url);
	   WebDriverWait wait = new WebDriverWait(driver,10);
	   
	   //write SQL code 
	   //this piece of code does not run correctly because the SQL statement is not changed properly
	   //I am still finding solutions
	   WebElement element = driver.findElement(By.xpath("//*[@id='textareaCodeSQL']"));
	   JavascriptExecutor javascript = (JavascriptExecutor) driver;
	   javascript.executeScript("arguments[0].style.display='block';",element);
	   element.clear();
	   element.sendKeys("CREATE TABLE TableRocio (int param1, char param2);");
	   //javascript.executeScript("arguments[0].style.display='none';",element);
	   
       //run SQL
       driver.findElement(By.xpath("//*[@type='button'][text()='Run SQL »']")).click();
       
       //check if new table is created correctly
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("yourDB")));
       boolean newTablePresent = !driver.findElements(By.xpath("//table//tr[10]")).isEmpty();
       assertTrue(newTablePresent,"New table is not created correctly");
       
       //check if number of records of new table is 0
       String recordsNewTable = driver.findElement(By.xpath("//table//tr[10]//td[2]")).getText();
       assertEquals(recordsNewTable,'0',"Records of new table is not 0");
       
	   driver.close();	   
  }
  
  @Test
  public void task4() throws InterruptedException {
	  
	   System.setProperty("webdriver.chrome.driver",driverPath);
	   driver = new ChromeDriver();
	   driver.get(url);
	   WebDriverWait wait = new WebDriverWait(driver,10);
	   
	   //write SQL code 
	   //this piece of code does not run correctly because the SQL statement is not changed properly
	   //I am still finding solutions
	   WebElement element = driver.findElement(By.xpath("//*[@id='textareaCodeSQL']"));
	   JavascriptExecutor javascript = (JavascriptExecutor) driver;
	   javascript.executeScript("arguments[0].style.display='block';",element);
	   element.clear();
	   element.sendKeys("SELECT * FROM [Customers] WHERE Country='Spain';");
	   //javascript.executeScript("arguments[0].style.display='none';",element);
	   
	   //Time to write the SQL code manually. This is for my test because the automating testing of the writing code does not work
	   Thread.sleep(5000);
	   
       //Run SQL
       driver.findElement(By.xpath("//*[@type='button'][text()='Run SQL »']")).click();
       
       //check if number of records is 5
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divResultSQL']/div/div")));
       String records = driver.findElement(By.xpath("//*[@id='divResultSQL']/div/div")).getText();
       assertEquals(records,"Number of Records: 5","Number of records is not 5");
       
	   driver.close();	   
  }
  
  @Test
  public void task5() throws InterruptedException {
	  
	   System.setProperty("webdriver.chrome.driver",driverPath);
	   driver = new ChromeDriver();
	   driver.get(url);
	   WebDriverWait wait = new WebDriverWait(driver,10);
	   
	   //write SQL code 
	   //this piece of code does not run correctly because the SQL statement is not changed properly
	   //I am still finding solutions
	   WebElement element = driver.findElement(By.xpath("//*[@id='textareaCodeSQL']"));
	   JavascriptExecutor javascript = (JavascriptExecutor) driver;
	   javascript.executeScript("arguments[0].style.display='block';",element);
	   element.clear();
	   element.sendKeys("SELECT Notes FROM Employees WHERE BirthDate BETWEEN '1925-01-01' AND '1930-12-31';");
	   //javascript.executeScript("arguments[0].style.display='none';",element);
	   
	   //Time to write the SQL code manually. This is for my test because the automating testing of the writing code does not work
	   Thread.sleep(5000);
	   
       //Run SQL
       driver.findElement(By.xpath("//*[@type='button'][text()='Run SQL »']")).click();
       
       //check if notes is 'An old chum.'
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"divResultSQL\"]/div/table/tbody/tr[2]/td")));
       String notes = driver.findElement(By.xpath("//*[@id=\"divResultSQL\"]/div/table/tbody/tr[2]/td")).getText();
       assertEquals(notes,"An old chum.","Notes is not 'An old chum'");
       
	   driver.close();	   
  }
  
  @Test
  public void task6() throws InterruptedException {
	  
	   System.setProperty("webdriver.chrome.driver",driverPath);
	   driver = new ChromeDriver();
	   driver.get(url);
	   WebDriverWait wait = new WebDriverWait(driver,10);		  
	      		  
	   //write first SQL code 
	   //this piece of code does not run correctly because the SQL statement is not changed properly
	   //I am still finding solutions
	   WebElement element = driver.findElement(By.xpath("//*[@id='textareaCodeSQL']"));
	   JavascriptExecutor javascript = (JavascriptExecutor) driver;
	   javascript.executeScript("arguments[0].style.display='block';",element);
	   element.clear();
	   element.sendKeys("SELECT MAX(Quantity) FROM [OrderDetails];");
	   //javascript.executeScript("arguments[0].style.display='none';",element);
	   
	   //Time to write the code manually
	   Thread.sleep(5000);
	   
	   //Run first SQL
       driver.findElement(By.xpath("//*[@type='button'][text()='Run SQL »']")).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divResultSQL']/div/table/tbody/tr[2]/td")));
       String maxQuantity = driver.findElement(By.xpath("//*[@id='divResultSQL']/div/table/tbody/tr[2]/td")).getText();
       
       //write second SQL code 
	   element.clear();
	   element.sendKeys("SELECT ProductID FROM [OrderDetails] where Quantity = "+maxQuantity+";");
       
       //Time to write the code manually
	   Thread.sleep(5000);
	   
	   //Run second SQL
       driver.findElement(By.xpath("//*[@type='button'][text()='Run SQL »']")).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divResultSQL']/div/table/tbody/tr[2]/td")));
       String productID = driver.findElement(By.xpath("//*[@id='divResultSQL']/div/table/tbody/tr[2]/td")).getText();
       
       //write third SQL code 
	   element.clear();
	   element.sendKeys("SELECT ProductName FROM [Products] WHERE ProductID = "+productID+";");
       
       //Time to write the code manually
	   Thread.sleep(5000);
	   
	   //Run third SQL
       driver.findElement(By.xpath("//*[@type='button'][text()='Run SQL »']")).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divResultSQL']/div/table/tbody/tr[2]/td")));
       String productName = driver.findElement(By.xpath("//*[@id='divResultSQL']/div/table/tbody/tr[2]/td")).getText();
       
       //check if maxQuantity and productName is the expected ones: 'Longlife Tofu' and the quantity 100
       assertEquals(maxQuantity,"100","The quantity is not 100");
       assertEquals(productName,"Longlife Tofu","The product is not LongLife Tofu");
       
	   driver.close();	   
  }
  
  @Test
  public void task7() throws InterruptedException {
	  
	   System.setProperty("webdriver.chrome.driver",driverPath);
	   driver = new ChromeDriver();
	   driver.get(url);
	   WebDriverWait wait = new WebDriverWait(driver,10);
	   
	   //write SQL code 
	   WebElement element = driver.findElement(By.xpath("//*[@id='textareaCodeSQL']"));
	   JavascriptExecutor javascript = (JavascriptExecutor) driver;
	   javascript.executeScript("arguments[0].style.display='block';",element);
	   element.clear();
	   element.sendKeys("SELECT EmployeeID FROM [Orders] WHERE OrderDate = '1997-01-30';");
	   //javascript.executeScript("arguments[0].style.display='none';",element);
	   
	   //Time to write the SQL code manually. This is for my test because the automating testing of the writing code does not work
	   Thread.sleep(3000);
	   
       //Run SQL
       driver.findElement(By.xpath("//*[@type='button'][text()='Run SQL »']")).click();
       
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='divResultSQL']/div/div")));
       String records = driver.findElement(By.xpath("//*[@id='divResultSQL']/div/div")).getText();
       int size = records.length();
       
       String ultimo = null;
       if (size==20) {
    	   ultimo = records.substring(size-1);
       }
       else if (size==21) {
    	   ultimo = records.substring(size-2);
       }
       else {
    	   ultimo = records.substring(size-3); 
       }
       
       int numberRecords = Integer.parseInt(ultimo);
       
       String employeeId = null;
       for(int i = 1; i <= numberRecords; i = i + 1)
       {
    	   employeeId= driver.findElement(By.xpath("//*[@id=\"divResultSQL\"]/div/table/tbody/tr['\" + i+1 + \"']/td")).getText();
    	   assertEquals(employeeId,"4","The employee ID is not the number 4");
       }
       
	   driver.close();	   
  }
}
package pagepkg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class reduceimagepage {
	WebDriver driver;
	By loginlink=By.xpath("//*[@id=\"menu-overlay\"]/div[3]/a[1]");
	By user=By.id("email_field");
	By pass=By.id("form_pwd_field");
	By login=By.xpath("//*[@id=\"contact_form\"]/div[3]/div[1]/div/div/button");
	By image=By.xpath("//*[@id=\"dropzone-container-offline\"]/div[2]/button");

	
	public reduceimagepage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void login() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(loginlink).click();
		
		File f=new File("D:\\reduceimage.xlsx");
		FileInputStream fi=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sh=wb.getSheet("Sheet1");
		System.out.println(sh.getLastRowNum());
		
		for(int i=1; i<=sh.getLastRowNum(); i++)
		{
			String username = sh.getRow(i).getCell(0).getStringCellValue();
			System.out.println("Username : "+username);
			String password=sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println("Password :"+password);
			
			driver.findElement(user).clear();
			driver.findElement(user).sendKeys(username);
			driver.findElement(pass).clear();
			driver.findElement(pass).sendKeys(password);
			driver.findElement(login).click();
			
			String act=driver.getPageSource();
			System.out.println(act);
			String exp="harisreehari6";
			if(act.contains(exp))
			{
				System.out.println("Passed");
			}
			else
			{
				System.out.println("Failed");
			}
			
		}
	}
	
	public void upload() throws Exception
	{
		driver.findElement(image).click();
		fileUpload("D:\\screenshot.png");
	}
	public void fileUpload(String p) throws AWTException
	{
		// to copy path to clipboard
		StringSelection strsele = new StringSelection(p);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strsele, null);
		// to paste into sys window
		Robot robot=new Robot();
		robot.delay(3000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		
	}
	public void screenscroll()
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		driver.findElement(By.xpath("/html/body/div[4]/div[3]/p/a")).click();
		// find contact us link and click
	}
	public static String screenshotMethod(WebDriver driver,String screenshotname) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination="./Screenshot/"+screenshotname +".png";
		FileHandler.copy(src, new File(destination));
		return destination;
	}

}

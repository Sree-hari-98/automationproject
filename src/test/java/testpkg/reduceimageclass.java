package testpkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pagepkg.reduceimagepage;

public class reduceimageclass {
	WebDriver driver;
	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
	}
	@BeforeMethod
	public void urlloading()
	{
		driver.get("https://www.reduceimages.com/");
		driver.manage().window().maximize();
	}
	@Test
	public void reduceimagetest() throws Exception
	{
		reduceimagepage ob=new reduceimagepage(driver);
		ob.login();
		ob.upload();
		ob.screenscroll();
		
	}

}

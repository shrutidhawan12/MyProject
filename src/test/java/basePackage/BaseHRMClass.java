package basePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.TimeUtils;


public class BaseHRMClass 
{
	//browser info   WebDriver driver= new FirefoxDriver();
	//The classes that will inherit this base class will use firefoxDriver and if the class wants to use the chromedriver.
	//Everything needs to be done in base class, if base class fails, every other class will fail dependent on it.
	//URL info
	//USername and password.   (Common information)
	public static Properties prop=new Properties(); //The object of the properties file/class is created to read the content of the Config.properties
	public static WebDriver driver;
	
	//These variables are declared as public and static, so that it can be used using a class-name in any method.
    
	//Step 1- To create constructor of this class
	public BaseHRMClass() 
	{
		try 
		{
		FileInputStream file=new FileInputStream("C:\\Users\\cmakk\\eclipse-workspace\\HRmanagement\\src\\test\\java\\environmentvariables\\Config.properties");                       //to read input from the file
	//To copy the path of the config.properties, right click on the file and click properties to copy the url of the file from location box.
	//FileInputStream is used to read the data from the file.
		prop.load(file);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException a)     //iF data cannot be read from the file, this exception can be thrown.
		{
			a.printStackTrace();
		}
	}	
		//In case of files, try and catch block should be used as If file not found, so it can handle exceptions before compiling it
	
	//Step 2
		public static void initiation()
		{
			//driver path ---according to the type of driver mentioned in the file config.properties, the entry should be done here.
			//if(browser=chrome use--> webdriver.chrome.driver)
			//else if(browser=firefox--> use-->webdriver.gecko.driver)
			//so in this we are creating a code that doesn't depend on a single browser, instead that depends on the value in config file
			String browsername=prop.getProperty("browser");    //The browser should be in the same case as mentioned in the config.properties file.
			//maximize pageload,implicit,getting url
			if (browsername.equals("Firefox"))
			{
				System.setProperty("WebDriver.gecko.driver","geckodriver.exe");
				driver=new FirefoxDriver();
			}
			else if (browsername.equals("chrome"))
			{
				System.setProperty("WebDriver.chrome.driver","chrome.exe");
				driver=new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(TimeUtils.timepage, TimeUnit.SECONDS); //this can be also separated as it can be changed
			//Thus a separate package called Utility can be created wherein the the timeunit value can be stored in a variable.
			driver.get(prop.getProperty("url"));
			
			
			
		}
	
		
	}
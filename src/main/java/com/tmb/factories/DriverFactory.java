/**
 * 
 */
package com.tmb.factories;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tmb.driver.DriverManager;
import com.tmb.enums.ConfigProperties;
import com.tmb.utils.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;

/**
 * Jan 26, 2021 
 * @author Amuthan Sakthivel
 * @version 1.0
 * @since 1.0
 */
public final class DriverFactory {

	private DriverFactory() {}

	/**
	 * 
	 * @author Amuthan Sakthivel
	 * Mar 20, 2021
	 * @param browser
	 * @param version
	 * @return
	 * @throws MalformedURLException
	 * TODO Remove hardcoded value of grid url
	 * @throws InterruptedException 
	 */
	public static WebDriver getDriver(String browser,String version) throws MalformedURLException, InterruptedException {

		WebDriver driver=null;
		System.out.println("in getDriver method");
		
		String runmode = PropertyUtils.get(ConfigProperties.RUNMODE);
		if(browser.equalsIgnoreCase("chrome")) {
			if(runmode.equalsIgnoreCase("remote")) {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("chrome");
				cap.setVersion(version);
				driver =new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), cap);
				driver.manage().window().maximize();
				Thread.sleep(8000);
			}
			else {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				Thread.sleep(8000);
			}
		}
		else if(browser.equalsIgnoreCase("firefox")) {

			if(runmode.equalsIgnoreCase("remote")) {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("firefox");
				cap.setVersion(version);
				driver =new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), cap);
				driver.manage().window().maximize();
				Thread.sleep(8000);
			} else {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				Thread.sleep(8000);
			}
		}
		else if(browser.equalsIgnoreCase("edge")) {

			if(runmode.equalsIgnoreCase("remote")) {					
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("browserName", "MicrosoftEdge");
				//capabilities.setVersion(version);
				driver =new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), capabilities);
				driver.manage().window().maximize();
				Thread.sleep(8000);
				
			} else {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().window().maximize();
				Thread.sleep(8000);
			}
		}
		return driver;
	}

}

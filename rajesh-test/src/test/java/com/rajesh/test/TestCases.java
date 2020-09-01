package com.rajesh.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases {

	TestFunctions tFunctions = new TestFunctions();

	/*
	 * Can you write a program that prints the numbers 1,2,3,4,5,6,7,8,9,10 and test
	 * it with unit tests
	 */

	@Test
	public void test01() {

		int[] intArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		// Keep current System.out with us
		PrintStream oldOut = System.out;

		// Create a ByteArrayOutputStream so that we can get the output
		// from the call to print
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// Change System.out to point out to our stream
		System.setOut(new PrintStream(baos));

		tFunctions.printNumbers(intArray);

		// Reset the System.out
		System.setOut(oldOut);

		// Our baos has the content from the print statement
		String output = new String(baos.toByteArray());

		// Assert output
		Assert.assertTrue(output.contains("1"), "Function did not print 1");
		Assert.assertTrue(output.contains("2"), "Function did not print 2");
		Assert.assertTrue(output.contains("3"), "Function did not print 3");
		Assert.assertTrue(output.contains("4"), "Function did not print 4");
		Assert.assertTrue(output.contains("5"), "Function did not print 5");
		Assert.assertTrue(output.contains("6"), "Function did not print 6");
		Assert.assertTrue(output.contains("7"), "Function did not print 7");
		Assert.assertTrue(output.contains("8"), "Function did not print 8");
		Assert.assertTrue(output.contains("9"), "Function did not print 9");
		Assert.assertTrue(output.contains("10"), "Function did not print 10");
		Assert.assertFalse(output.contains("11"), "Function printed 11");

		System.out.println(output);
	}

	/*
	 * Can you write a program that calculates the area of a triangle, and test this
	 * program
	 */

	@Test
	public void test02() {

		float t1Area = tFunctions.triangleArea(5, 7);
		Assert.assertEquals(t1Area, 17.5f, "Area calculated is incorrect");

		float t2Area = tFunctions.triangleArea(7, 9);
		Assert.assertEquals(t2Area, 31.5f, "Area calculated is incorrect");
	}

	/*
	 * Can you write a simple selenium program that loads the hcl page
	 * https://www.hcl.com/ then walks to
	 * https://www.hcl.com/leadership/hcl-corporation and validates that the Founder
	 * and Chairman is still on the web page
	 */

	@Test
	public void test03() {

		String url, currentUrl;

		System.out.println("Starting test03");
		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

// sleep for 1 seconds
		sleep(1000);

// maximize browser window
		driver.manage().window().maximize();

// Open test page
		url = "https://www.hcl.com/";
		driver.get(url);
		System.out.println("Page is opened.");

//	Verify test page
		currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, url, "Current url is not same as expected url");

// sleep for 1 seconds
		sleep(1000);

		WebElement wwaLink = driver.findElement(By.xpath("//*[contains(text(), 'Who we are ')]"));
		wwaLink.click();

		sleep(1000);

		WebElement leadershipLink = driver.findElement(By.xpath("//a[@class='semi-bold'][contains(., 'Leadership')]"));
		leadershipLink.click();

		WebElement verifyChairmanLink = driver.findElement(
				By.xpath("//span[@class='designation-inner']/p[contains(., 'Chairman - Shiv Nadar Foundation')]"));

//		verifyChairmanLink.isDisplayed();
		Assert.assertEquals(verifyChairmanLink.isDisplayed(), true, "Founder Chairman element not displayed in page");

//		Close browser
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

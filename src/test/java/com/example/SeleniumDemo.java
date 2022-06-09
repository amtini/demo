package com.example;

import static org.junit.Assert.*;
import com.example.Instruction.Login;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDemo {
    protected WebDriver driver;
    protected Actions actions;
    protected SleepClass sleepClass = new SleepClass();
    protected Integer time = 1500;
    Login loginClass = new Login();
    LocalFunctions functions = new LocalFunctions();


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

   @After  
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /*
        "Do1Disable" Test Steps:
            1- Login
            2- Click on "DO1!" Button
            3- Check both buttons state according to the steps.

            Expected Result: DO1! disable, DO2! enable
    */
    @Test
    public void Do1Disable(){
        loginClass.main(driver);
        sleepClass.sleep(time);
        driver.findElement(By.xpath("/html/body/div[1]/button[1]")).click();
        boolean do1BoolValue = driver.findElement(By.xpath("/html/body/div[1]/button[1]")).isEnabled();
        boolean do2BoolValue = driver.findElement(By.xpath("/html/body/div[1]/button[2]")).isEnabled();

        assertFalse("Expected False for the state of the DO1! button",do1BoolValue);
        assertTrue("Expected True for the state of the DO2! button",do2BoolValue);
    }


    /*
        "Do1Enable" Test Steps:
            1- Login
            2- Click on "DO1!" Button
            3- Click on "DO2!" Button
            4- Check both buttons state according to the steps.

            Expected Result: DO1! enable, DO2! disable
    */
    @Test
    public void Do1Enable(){
        loginClass.main(driver);
        sleepClass.sleep(time);
        driver.findElement(By.xpath("/html/body/div[1]/button[1]")).click();
        sleepClass.sleep(time);
        driver.findElement(By.xpath("/html/body/div[1]/button[2]")).click();
        sleepClass.sleep(time);
        boolean do1BoolValue = driver.findElement(By.xpath("/html/body/div[1]/button[1]")).isEnabled();
        boolean do2BoolValue = driver.findElement(By.xpath("/html/body/div[1]/button[2]")).isEnabled();

        assertTrue("Expected True for the state of the DO1! button",do1BoolValue);
        assertFalse("Expected False for the state of the DO2! button",do2BoolValue);
    }


    /*
        For this few Font Size tests, i will need to check 3 case scenario,
        increase the font, decrease the font, and increase and decrease the font (only if the increment and decrement value are the same).
        "IncreaseFont" Test Steps:
            1- Login
            2- Save font size
            3- Click on DownArrow button
            4- Save new font size
            5- Compare new font size with the old one

            Expected Result: oldFontSize lower than newFontSize
    */
    @Test
    public void IncreaseFont(){
        loginClass.main(driver);
        sleepClass.sleep(time);
        int oldFontSize = functions.PixelToInteger(driver.findElement(By.id("textFontSize")).getCssValue("font-size"));
        driver.findElement(By.id("btnIncreaseFont")).click();
        sleepClass.sleep(time);
        int newFontSize = functions.PixelToInteger(driver.findElement(By.id("textFontSize")).getCssValue("font-size"));
        assertTrue("Expected oldFontSize be lower than newFontSize",oldFontSize < newFontSize);
    }


    /*
        "DecreaseFont" Test Steps:
            1- Login
            2- Save font size
            3- Click on DownArrow button
            4- Save new font size
            5- Compare new font size with the old one

            Expected Result: oldFontSize higher than newFontSize (only if the increment and decrement value are the same)
    */
    @Test
    public void DecreaseFont(){
        loginClass.main(driver);
        sleepClass.sleep(time);
        int oldFontSize = functions.PixelToInteger(driver.findElement(By.id("textFontSize")).getCssValue("font-size"));
        driver.findElement(By.id("btnDecreaseFont")).click();
        sleepClass.sleep(time);
        int newFontSize = functions.PixelToInteger(driver.findElement(By.id("textFontSize")).getCssValue("font-size"));
        assertTrue("Expected oldFontSize be greater than newFontSize",oldFontSize > newFontSize);
    }

    /*
        "Increase and Decrease Font Size" Test Steps:
            1- Login
            2- Save font size
            3- Click on DownArrow button
            4- Click on UpArrow button
            5- Save new font size
            6- Compare the old font size with the new one

            Expected Result: oldFontSize higher than newFontSize
    */
    @Test
    public void IncAndDecFont(){
        loginClass.main(driver);
        sleepClass.sleep(time);
        int oldFontSize = functions.PixelToInteger(driver.findElement(By.id("textFontSize")).getCssValue("font-size"));
        driver.findElement(By.id("btnDecreaseFont")).click();
        driver.findElement(By.id("btnIncreaseFont")).click();
        int newFontSize = functions.PixelToInteger(driver.findElement(By.id("textFontSize")).getCssValue("font-size"));
        assertEquals("Expected oldFontSize be the same as newFontSize",oldFontSize, newFontSize);
    }

    /*
        "Change Color" Test Steps:
            1- Login
            2- Save BackGround color
            3- Write color name in TextBox
            4- Click on "SET BACKGROUND COLOR!" button
            5- Save new BackGround color
            4- Compare new color with the old one

            Expected Result: oldColor is different from new color, and also new color has to be equal to the rgb value of the input color.
    */
    @Test
    public void ChangeColor(){
        loginClass.main(driver);
        sleepClass.sleep(time);
        String oldColor = driver.findElement(By.id("formToColorize")).getCssValue("background-color");
        //yellow rgba color is rgba(255,255,0,1)
        String yellowStringRGBA = "rgba(255, 255, 0, 1)";
        driver.findElement(By.xpath("//*[@id='bgColor']")).sendKeys("yellow");
        driver.findElement(By.id("btnSetBgColor")).click();
        String newColor = driver.findElement(By.id("formToColorize")).getCssValue("background-color");

        assertNotEquals("Expected oldColor not be the same as newColor",oldColor, newColor);
        assertEquals("Expected yellow RGBA color be the same as newColor",yellowStringRGBA, newColor);
    }
}
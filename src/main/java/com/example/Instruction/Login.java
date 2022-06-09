package com.example.Instruction;

import com.example.SleepClass;
import com.example.Domain.UserLogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

    public void main(WebDriver driver) {
        //Initiating your chromedriver
        SleepClass sleepClass = new SleepClass();
        Integer time = 5000;

        WebDriverManager.chromedriver().setup();

        driver.get("https://practis.co.il/automation/");

        sleepClass.sleep(time);

        UserLogin userLogin = new UserLogin("admin","admin");

        driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).clear();
        driver.findElement(By.xpath("/html/body/div/div/form/input[1]")).sendKeys(userLogin.userName);
        driver.findElement(By.xpath("/html/body/div/div/form/input[2]")).clear();
        driver.findElement(By.xpath("/html/body/div/div/form/input[2]")).sendKeys(userLogin.userName);
        driver.findElement(By.xpath("/html/body/div/div/form/button")).click();

    }
}
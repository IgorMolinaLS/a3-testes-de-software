/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mavenproject1;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.edge.EdgeDriver;

/**
 *
 * @author Igor Molina
 */
public class FirstScriptTest {

    WebDriver driver;
    UlifeUser user;
    String operation = "Calendário";

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void isLogin() {
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser(driver, operation);
        user.enterLoginPage();
        assertTrue(user.getLoginButton().isEnabled());
    }

    @Test
    public void isLoggedIn() {
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser(driver, operation);
        user.enterLoginPage();
        user.doLogin();
        assertFalse(user.getButtonList().isEmpty());
    }

    @Test
    public void desiredOperationExists() {
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser(driver, operation);
        Boolean operationExists = false;

        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();

        List<WebElement> optionList = this.driver.findElements(By.className("nw"));

        for (int i = 0; i < optionList.size(); i++) {
            if (optionList.get(i).getText().contains(user.operation)) {
                operationExists = true;
            }
        }
        assertEquals(true, operationExists);
    }
    
    @Test
    public void dateExists() throws InterruptedException{
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser(driver, operation);

        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();
        user.selectOperation(); 
        Thread.sleep(1000);
        String day = "3";
        String month = "Junho";
        String date = user.getVideo(day, month);
        assertEquals(day + " " + month.toUpperCase(), date);
       
    }
    

}

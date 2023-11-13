/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

/**
 *
 * @author 12522224744
 */
public class UlifeLogin {

    private String login;
    private String password;
    private WebDriver driver;

    public UlifeLogin() {
        //Adicione seus dados nas variáveis antes de iniciar o programa!
        this.login = "12522224744@ulife.com.br";
        this.password = "050504";
        
        this.driver = new EdgeDriver();
    }

    public UlifeLogin(String login, String password, WebDriver driver) {
        this.login = login;
        this.password = password;
        this.driver = driver;
    }
    
    public UlifeLogin(WebDriver driver) {
        this.login = "12522224744@ulife.com.br";
        this.password = "050504";
        this.driver = driver;
    }

    public void EnterLoginPage(String url) {
        this.driver.get(url);
        this.driver.manage().window().maximize();
        this.driver.getWindowHandle();
    }

    public void DoLogin() {

        WebElement loginText = this.driver.findElement(By.id("txtLogin"));;
        WebElement passwordText = this.driver.findElement(By.id("txtPassword"));
        WebElement loginButton = getLoginButton();
        

        if (this.login == "" || this.password == "") {
            System.out.println("Por favor, adicione login e senha no programa!");
            driver.quit();
            System.exit(0);
        }

        loginText.sendKeys(this.login);

        passwordText.sendKeys(this.password);

        loginButton.click();

        try {
            WebElement menuOptionSelector = this.driver.findElement(By.className("uOrgSelected"));
            menuOptionSelector.click();
        } catch (Exception ex) {
            System.out.println("Login inválido! Por favor cheque seus dados.");
            driver.quit();
            System.exit(0);
        }
    }
    
    public WebElement getLoginButton(){
        return this.driver.findElement(By.id("ctl00_b_imbLogin"));
    }
    
    
       
}

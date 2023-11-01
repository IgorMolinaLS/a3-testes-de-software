package com.mycompany.mavenproject1;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Mavenproject1 {

    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.ulife.com.br/login.aspx");
        driver.manage().window().maximize();

        driver.getWindowHandle();

        WebElement loginText = driver.findElement(By.id("txtLogin"));
        WebElement passwordText = driver.findElement(By.id("txtPassword"));
        WebElement loginButton = driver.findElement(By.id("ctl00_b_imbLogin"));
        
        //Adicionar seu login e senha nas strings abaixo
        loginText.sendKeys("");
        passwordText.sendKeys("");

        loginButton.click();

        WebElement menuOptionSelector = driver.findElement(By.className("uOrgSelected"));
        menuOptionSelector.click();

        /*  
        TODO: Adicionar lógica de pegar sempre o Campus, e não programa de nivelamento
        
        */
        WebElement campusSelectorButton = driver.findElement(By.xpath("//div[2]/label/div/div/div/ul/li[2]"));
        campusSelectorButton.click();

        WebElement financeButton = driver.findElement(By.xpath("//div[2]/label/div[2]/div[2]/div/ul/li[14]"));
        financeButton.click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        try { Thread.sleep (10000); } catch (InterruptedException ex) {}
        WebElement selectTicketButton = driver.findElement(By.id("aba_boleto"));
        //Se não funcionar, usar xpath:    
        //driver.findElement(By.xpath("//div/div/main/div/main/div/div/div[2]/div[2]/div/div[4]/div[4]/div/div[2]/div/div[3]"));
        selectTicketButton.click();
        
        WebElement generateTicket = driver.findElement(By.id("gerar_codigo_barra"));
        System.out.println(generateTicket.getText());
        
        driver.quit();
    }
}

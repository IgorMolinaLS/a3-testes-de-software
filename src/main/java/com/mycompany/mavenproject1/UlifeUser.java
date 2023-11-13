/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author 12522224744
 */
public class UlifeUser {

    private String login;
    private String password;
    private String campusName;
    private WebDriver driver;
    private String url;
    public String operation;

    public UlifeUser() {
        this.login = "12522224744@ulife.com.br";
        this.password = "050504";
        this.campusName = "Paulista";
        this.driver = new EdgeDriver();
        this.url = "https://www.ulife.com.br/login.aspx";
        this.operation = "Minha Carreira";
    }

    public UlifeUser(String login, String password, String campusName, WebDriver driver, String operation) {
        this.login = login;
        this.password = password;
        this.campusName = campusName;
        this.driver = driver;
        this.url = "https://www.ulife.com.br/login.aspx";
        this.operation = "Minha Carreira";
    }
    
    public UlifeUser(WebDriver driver, String operation) {
        this.login = "12522224744@ulife.com.br";
        this.password = "050504";
        this.campusName = "Paulista";
        this.driver = driver;
        this.url = "https://www.ulife.com.br/login.aspx";
        this.operation = "Minha Carreira";
    }


    public void enterLoginPage() {
        this.driver.get(this.url);
        this.driver.manage().window().maximize();
        this.driver.getWindowHandle();
    }

    public void doLogin() {

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

    public void selectCampus() {
        List<WebElement> buttonList = getButtonList();

        try {
            for (int i = 0; i < buttonList.size(); i++) {
                if (buttonList.get(i).getText().contains(this.campusName)) {
                    buttonList.get(i).click();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Informações de Campus não encontradas!");
            driver.quit();
            System.exit(0);
        }
    }

    public List<WebElement> getButtonList() {
        return this.driver.findElements(By.className("ng-binding"));
    }

    public WebElement getLoginButton() {
        return this.driver.findElement(By.id("ctl00_b_imbLogin"));
    }

    public void selectOperation() {
        List<WebElement> optionList = this.driver.findElements(By.className("nw"));

        for (int i = 0; i < optionList.size(); i++) {
            
            if (optionList.get(i).getText().contains(this.operation)) {
                optionList.get(i).click();
                break;
            }
            if (i == optionList.size() - 1) {
                if (optionList.get(i).getText() != this.operation) {
                    System.out.println("Opção desejada não encontrada! Por favor cheque se ela está disponível");
                    this.driver.quit();
                    System.exit(0);
                }
            }
        }
    }
    
    public void getBarCode(){
        ArrayList<String> tabs = new ArrayList<String>(this.driver.getWindowHandles());
        this.driver.switchTo().window(tabs.get(1));
        int segundos = 1000;

        try {
            //Dependendo da potência da máquina, aumentar a quantidade de segundos
            Thread.sleep(10 * segundos);
            WebElement selectTicketButton = this.driver.findElement(By.id("aba_boleto"));
            selectTicketButton.click();

            WebElement generateTicket = this.driver.findElement(By.id("gerar_codigo_barra"));
            if (generateTicket.isEnabled()) {
                generateTicket.click();
            }

            WebElement copyToClipboard = this.driver.findElement(By.id("input-183"));
            Actions act = new Actions(this.driver);
            act.click(copyToClipboard).perform();
            copyToClipboard.sendKeys(Keys.CONTROL + "a");
            copyToClipboard.sendKeys(Keys.CONTROL + "c");

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            String barCodeText = (String) clipboard.getData(DataFlavor.stringFlavor);
            System.out.println("O código de barras do seu boleto é: " + barCodeText);

            this.driver.quit();
            System.exit(0);

        } catch (Exception ex) {
            System.out.println("Botão de gerar boletos inexistente! Você deve estar com as contas em dia!");
            this.driver.quit();
            System.exit(0);
        }
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    
    
}

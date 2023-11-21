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
        this.url = "https://www.ulife.com.br/login.aspx";
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
            endDriver();
        }

        loginText.sendKeys(this.login);

        passwordText.sendKeys(this.password);

        loginButton.click();

        try {
            WebElement menuOptionSelector = this.driver.findElement(By.className("uOrgSelected"));
            menuOptionSelector.click();
        } catch (Exception ex) {
            System.out.println("Login inválido! Por favor cheque seus dados.");
            endDriver();
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
            endDriver();
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
                    this.endDriver();
                }
            }
        }
    }

    public String getBarCode() {
        ArrayList<String> tabs = new ArrayList<String>(this.driver.getWindowHandles());
        this.driver.switchTo().window(tabs.get(1));
        int segundos = 1000;

        try {
            //Dependendo da potência da máquina, aumentar a quantidade de segundos
            Thread.sleep(10 * segundos);
            WebElement selectTicketButton = this.driver.findElement(By.id("aba_boleto"));
            selectTicketButton.click();

            WebElement generateTicket = this.driver.findElement(By.id("gerar_codigo_barra"));
            System.out.println(generateTicket);
            if (generateTicket.isEnabled()) {
                generateTicket.click();
            }
            
            List<WebElement> inputsList = this.driver.findElements(By.xpath("//input[@type='text']"));
            

            WebElement copyToClipboard = inputsList.get(inputsList.size() - 1);
            Actions act = new Actions(this.driver);
            act.click(copyToClipboard).perform();
            copyToClipboard.sendKeys(Keys.CONTROL + "a");
            copyToClipboard.sendKeys(Keys.CONTROL + "c");

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            String barCodeText = (String) clipboard.getData(DataFlavor.stringFlavor);
            System.out.println("O código de barras do seu boleto é: " + barCodeText);
            return barCodeText;
            
        } catch (Exception ex) {
            System.out.println("Botão de gerar boletos inexistente! Você deve estar com as contas em dia!");
            return null;
        }
    }

    public WebElement getMonth(String month) {
        List<WebElement> monthList = this.driver.findElements(By.xpath("//div[@class = 'ltTop ltTitleSmall']"));

        for (int i = 0; i < monthList.size(); i++) {
            if (monthList.get(i).getText().contains(month.toUpperCase())) {

                WebElement Button = monthList.get(i).findElement(By.className("ng-hide"));

                if (Button.getAttribute("class").contains("img iconCollapseAll ng-hide")) {
                    monthList.get(i).click();
                }
                return monthList.get(i);
            }
            if (i == monthList.size() - 1) {
                if (!monthList.get(i).getText().equals(month)) {
                    return null;
                }
            }
        }
        return null;
    }

    public String getVideo(String date, String month) {
        WebElement monthElement = getMonth(month);

        if (monthElement != null) {

            WebElement monthParent = monthElement.findElement(By.xpath("parent::*"));
            List<WebElement> dayList = monthParent.findElements(By.xpath(".//strong[@class = 'black fBold fb ng-binding']"));

            for (int i = 0; i < dayList.size(); i++) {
                if (dayList.get(i).getText().equals(date)) {
                    WebElement dayParent = dayList.get(i).findElement(By.xpath("parent::*"));
                    WebElement dayDiv = dayParent.findElement(By.xpath("parent::*"));
                    WebElement videoLink = dayDiv.findElement(By.xpath(".//div[@class = 'fRight argt lhn pm']"));
                    videoLink.click();
                    String[] monthArray = monthElement.getText().split(" ");
                    return dayList.get(i).getText() + " " + monthArray[0];

                }

            }
        }
        System.out.println("Mês inexistente");
        return monthElement.getText();
    }

    public void endDriver(WebDriver driver) {
        driver.close();
        driver.quit();
        System.exit(0);
    }

    public void endDriver() {
        this.driver.close();
        this.driver.quit();
        System.exit(0);
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}

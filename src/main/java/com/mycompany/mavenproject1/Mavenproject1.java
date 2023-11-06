package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

//clip
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class Mavenproject1 {
    /*
    TODO: 
        -Isolar funcionalidade de gerar boleto a partir da escolha da operação desejada
        -Criar array com funcionalidades tratadas
        -Criar e isolar novas funcionalidades
     */
    public static void main(String[] args) throws Exception {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.ulife.com.br/login.aspx");
        driver.manage().window().maximize();
        driver.getWindowHandle();

        //Adicione seus dados nas variáveis antes de iniciar o programa!
        String login = "seuRaAqui@ulife.com.br";
        String password = "suaSenhaAqui";
        String campus = "Paulista";
        String desiredOperation = "Extrato Financeiro";

        WebElement loginText = driver.findElement(By.id("txtLogin"));
        WebElement passwordText = driver.findElement(By.id("txtPassword"));
        WebElement loginButton = driver.findElement(By.id("ctl00_b_imbLogin"));

        if (login == "" || password == "") {
            System.out.println("Por favor, adicione login e senha no programa!");
            driver.quit();
            System.exit(0);
        }

        loginText.sendKeys(login);
        passwordText.sendKeys(password);

        loginButton.click();

        try {
            WebElement menuOptionSelector = driver.findElement(By.className("uOrgSelected"));
            menuOptionSelector.click();
        } catch (Exception ex) {
            System.out.println("Login inválido! Por favor cheque seus dados.");
            driver.quit();
            System.exit(0);
        }

        List<WebElement> buttonList = driver.findElements(By.className("ng-binding"));
        try {
            for (int i = 0; i < buttonList.size(); i++) {
                if (buttonList.get(i).getText().contains(campus)) {
                    buttonList.get(i).click();
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Informações de Campus não encontradas!");
            driver.quit();
            System.exit(0);
        }

        List<WebElement> optionList = driver.findElements(By.className("nw"));

        for (int i = 0; i < optionList.size(); i++) {
            if (optionList.get(i).getText().contains(desiredOperation)) {
                optionList.get(i).click();
                break;
            }
            if (i == optionList.size() - 1) {
                if (optionList.get(i).getText() != desiredOperation) {
                    System.out.println("Opção desejada não encontrada! Por favor cheque se ela está disponível");
                    driver.quit();
                    System.exit(0);
                }
            }
        }

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        int segundos = 1000;

        try {
            //Dependendo da potência da máquina, aumentar a quantidade de segundos
            Thread.sleep(10 * segundos);
            WebElement selectTicketButton = driver.findElement(By.id("aba_boleto"));
            selectTicketButton.click();

            WebElement generateTicket = driver.findElement(By.id("gerar_codigo_barra"));
            if (generateTicket.isEnabled()) {
                generateTicket.click();
            }
            
            WebElement copyToClipboard = driver.findElement(By.id("input-183"));
            Actions act = new Actions(driver);
            act.click(copyToClipboard).perform();
            copyToClipboard.sendKeys(Keys.CONTROL + "a");
            copyToClipboard.sendKeys(Keys.CONTROL + "c");

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            String barCodeText = (String) clipboard.getData(DataFlavor.stringFlavor);
            System.out.println("O código de barras do seu boleto é: " + barCodeText);

            driver.quit();
            System.exit(0);
            
        } catch (Exception ex) {
            System.out.println("Botão de gerar boletos inexistente! Você deve estar com as contas em dia!");
            driver.quit();
            System.exit(0);
        }
    }
}
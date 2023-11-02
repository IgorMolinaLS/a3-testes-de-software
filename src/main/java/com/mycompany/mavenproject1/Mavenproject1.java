package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Mavenproject1 {

    /*
    TODO: 
          
     */
    public static void main(String[] args) throws Exception {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.ulife.com.br/login.aspx");
        driver.manage().window().maximize();
        driver.getWindowHandle();

        //Adicione seus dados nas variáveis antes de iniciar o programa!
        String login = "";
        String password = "";
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

        WebElement menuOptionSelector = driver.findElement(By.className("uOrgSelected"));
        menuOptionSelector.click();

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
        }

        List<WebElement> optionList = driver.findElements(By.className("nw"));

        for (int i = 0; i < optionList.size(); i++) {
            if (optionList.get(i).getText().contains(desiredOperation)) {
                optionList.get(i).click();
                break;
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
            System.out.println(generateTicket.getText());

            driver.quit();
        } catch (Exception ex) {
            System.out.println("Botão de gerar boletos inexistente! Você deve estar com as contas em dia!");
            driver.quit();
        }
    }
}

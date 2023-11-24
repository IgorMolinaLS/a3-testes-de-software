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
    String login = "12522165805@ulife.com.brs";
    String pass = "Rere81018101#s";
    String campus = "Paulistas";
    String operation = "Extrato Financeiros";


    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void isLoginPage() {
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.setCampusName(campus);
        user.setOperation(operation);
        user.enterLoginPage();
        assertTrue(user.getLoginButton().isEnabled());
    }

    @Test
    public void isLoggedIn() {
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.setCampusName(campus);
        user.setOperation(operation);
        user.enterLoginPage();
        user.doLogin();
        assertFalse(user.getButtonList().isEmpty());
    }

    @Test
    public void desiredOperationExists() {
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.setCampusName(campus);
        user.setOperation(operation);
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

    //Video aula
    @Test
    public void dateExists() throws InterruptedException {
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.setCampusName(campus);
        user.setOperation("Calendário");

        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();
        user.selectOperation();
        Thread.sleep(1000);
        String day = "3";
        String month = "Novembro";
        String date = user.getRecordedClass(day, month);
        assertEquals(day + " " + month.toUpperCase(), date);
    }

    //Extrato Financeiro
    @Test
    public void barCodeExists() {
        driver = new EdgeDriver();
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.setCampusName(campus);
        user.setOperation(operation);

        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();
        user.selectOperation();
        String barCode = user.getBarCode();
        assertTrue(barCode != null);
    }
}

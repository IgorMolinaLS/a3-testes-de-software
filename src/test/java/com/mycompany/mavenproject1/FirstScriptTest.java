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

    WebDriver driver = new EdgeDriver();
    
    String login = "12522224744@ulife.com.br";
    String pass = "050504";
    String campus = "Paulista";
    String operation = "Extrato Financeiro";


    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void isLoginPage() {
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.enterLoginPage();
        assertTrue(user.getLoginButton().isEnabled());
    }

    @Test
    public void isLoggedIn() {
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.enterLoginPage();
        user.doLogin();
        assertFalse(user.getButtonList().isEmpty());
    }

    @Test
    public void desiredOperationExists() throws InterruptedException {
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
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin("12522165805@ulife.com.br");
        user.setPassword("Rere81018101#");
        user.setCampusName(campus);
        user.setOperation("Extrato Financeiro");

        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();
        user.selectOperation();
        String barCode = user.getBarCode();
        assertTrue(barCode != null);
    }
    
    @Test
    public void editPageExists(){
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.setCampusName(campus);
        user.setOperation("Atualizar celular");

        user.enterLoginPage();
        user.doLogin();
        WebElement editButton = user.editProfile();
        assertTrue(editButton != null);
    }
    
    @Test
    public void numberIsCorrect() throws InterruptedException{
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.setCampusName(campus);
        user.setOperation("Atualizar celular");

        user.enterLoginPage();
        user.doLogin();
        user.editProfile();
        String numberToCompare = user.updatePhoneNumber("55", "11994096827");
        
        Thread.sleep(1000);
        List<WebElement> updatedNumbers = driver.findElements(By.xpath("//em[@class='ng-binding']"));
        assertEquals(numberToCompare, updatedNumbers.get(updatedNumbers.size() - 1).getText());
    }
    
    @Test
    public void emailIsCorrect() throws InterruptedException{
        UlifeUser user = new UlifeUser();
        user.setDriver(driver);
        user.setLogin(login);
        user.setPassword(pass);
        user.setCampusName(campus);
        user.setOperation("Atualizar Email");

        user.enterLoginPage();
        user.doLogin();
        user.editProfile();
        String emailToCompare = user.updateEmail("thi-correia@uol.com.br");
        
        Thread.sleep(1000);
        WebElement updatedEmail = driver.findElement(By.xpath("//em[@class='lhs ng-binding']"));

        assertEquals(emailToCompare, updatedEmail.getText());
    }
}

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
public class CampusSelector {
    private String campusName;
    private WebDriver driver;
    
    public CampusSelector(){
        this.campusName = "Paulista";
    }
    
    public CampusSelector(String campusName, WebDriver driver){
        this.campusName = campusName;
        this.driver = driver;
    }
    
    public void SelectCampus(String campusName){
        List<WebElement> buttonList = getButtonList();

        try {
            for (int i = 0; i < buttonList.size(); i++) {
                if (buttonList.get(i).getText().contains(campusName)) {
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
    
    public List<WebElement> getButtonList(){
        return this.driver.findElements(By.className("ng-binding"));
    }
            
            
}

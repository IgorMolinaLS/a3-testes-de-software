package com.mycompany.mavenproject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
public class Mavenproject1 {

    /*
    TODO: 
        -Criar array com funcionalidades tratadas
        -Criar e isolar novas funcionalidades
        -A partir de Comunicação não é possível pegar o texto dos itens
     */
    public static void main(String[] args) throws Exception {

        UlifeUser user = new UlifeUser();
        WebDriver driver = new EdgeDriver();

        user.setDriver(driver);
        user.setLogin("12522165805@ulife.com.br");
        user.setPassword("Rere81018101#");
        user.setCampusName("Paulista");
        user.setOperation("Extrato Financeiro");

        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();

        switch (user.getOperation()) {

            case "Calendário":
                user.selectOperation();
                Thread.sleep(1000);
                ;
                String a = user.getVideo("3", "Novembro");
                System.out.println(a);
                break;

            case "Extrato Financeiro":
                user.selectOperation();
                user.getBarCode();
                break;
        } 
    }
}

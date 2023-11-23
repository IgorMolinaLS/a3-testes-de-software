package com.mycompany.mavenproject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Mavenproject1 {

    //Por algum motivo, operações abaixo de Comunicação não são acessáveis através do texto dos itens
    public static void main(String[] args) throws Exception {

        UlifeUser user = new UlifeUser();
        WebDriver driver = new EdgeDriver();

        user.setDriver(driver);
        user.setLogin("12522165805@ulife.com.br");
        user.setPassword("Rere81018101#");
        user.setCampusName("Paulista");
        user.setOperation("Mensagem");

        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();
        user.selectOperation();

        switch (user.getOperation()) {
            case "Calendário":
                Thread.sleep(1000);
                String a = user.getVideo("3", "Novembro");
                System.out.println(a);
                break;

            case "Extrato Financeiro":
                user.getBarCode();
                break;

            case "Mensagem":
                //user.sendMessage();
                break;
        }
    }
}

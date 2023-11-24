package com.mycompany.mavenproject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Mavenproject1 {

    //Por algum motivo, operações abaixo de Comunicação não são acessáveis através do texto dos itens
    public static void main(String[] args) throws Exception {

        UlifeUser user = new UlifeUser();
        WebDriver driver = new EdgeDriver();

        user.setDriver(driver);
        user.setLogin("12522192856@ulife.com.br");
        user.setPassword("040101");
        user.setCampusName("Paulista");
        user.setOperation("Extrato Financeiro");

        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();

        switch (user.getOperation()) {
            case "Calendário":
                user.selectOperation();
                user.getRecordedClass("3", "Novembro");
                break;

            case "Extrato Financeiro":
                user.selectOperation();
                user.getBarCode();
                break;

            case "Atualizar celular":
                user.editProfile();
                user.updatePhoneNumber("11970228098");
                break;
            case "Atualizar Email":
                user.editProfile();
                user.updateEmail("igoramil452@gmail.com");
                break;
        }
    }
}

package com.mycompany.mavenproject1;

public class Mavenproject1 {

    /*
    TODO: 
        -Criar array com funcionalidades tratadas
        -Criar e isolar novas funcionalidades
        -A partir de Comunicação não é possível pegar o texto dos itens
     */
    public static void main(String[] args) throws Exception {
        
        UlifeUser user = new UlifeUser();
        
        user.enterLoginPage();
        user.doLogin();
        user.selectCampus();
        user.setOperation("PlayList");
        
        user.selectOperation();
        user.getBarCode(); 
   }
}

package com.distribuida.principal;

import com.distribuida.entities.Cliente;

public class ClientePrincipal {

    public static void main(String[] args){

        Cliente cliente = new Cliente(1,"1701234567","Juan","Taipe","Av. por ahi y mas alla.", "0987654321","jtaipe@correo.com");

        System.out.println(cliente.toString());


    }

}

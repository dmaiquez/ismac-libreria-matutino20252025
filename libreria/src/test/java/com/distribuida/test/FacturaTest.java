package com.distribuida.test;

import com.distribuida.entities.Cliente;
import com.distribuida.entities.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTest {

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setUp(){

        factura = new Factura();

        cliente = new Cliente(1,"1701234567","Juan","Taipe","Av. por ahi.","098754321","jtaipe@correo.com");

        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        // inyección de dependencias
        factura.setCliente(cliente);
    }


    @Test
    public void testFacturaConstructorAndGetters(){
        assertAll("Validar datos factura",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("FAC-0001", factura.getNumFactura()),
              //  () -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal()),
                () -> assertEquals("Juan", factura.getCliente().getNombre())
        );

    }

    @Test
    public void testFacturaToString(){
        String str = factura.toString();
        assertAll("Validar datos factura",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-0001")),
                //() -> assertTrue(str.contains("new Date()")),
                () -> assertTrue(str.contains("100.0")),
                () -> assertTrue(str.contains("15.0")),
                () -> assertTrue(str.contains("115.0")),
                () -> assertTrue(str.contains("Juan")), // parte del objeto cliente - Inyección de dependencias
                () -> assertTrue(str.contains("098754321"))  // parte del objeto cliente - Inyección de dependencias
        );


    }




}

package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ClienteRepositorioTestIntegracion {

    @Autowired //anotación para inyectar dependencias
    private ClienteRepository clienteRepository;

    @Test
    public void findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for(Cliente item: clientes){
            System.out.println(item.toString());
        }

    }

    @Test
    public void findOne(){
        Optional<Cliente> cliente = clienteRepository.findById(39);
        assertTrue(cliente.isPresent(), "El cliente con id = 39, deberia existir");
        System.out.println(cliente.toString());
    }


    @Test
    public void save(){
        Cliente cliente = new Cliente(0,"1701234567","Juan","Taipe","Av. por ahi.","0987654321", "jtaipe@correo.com");
        clienteRepository.save(cliente);
        assertNotNull(cliente.getIdCliente(),"El cliente guardado debe tener un id.");
        assertEquals("1701234567", cliente.getCedula());
        assertEquals("Juan", cliente.getNombre());
    }

    @Test
    public void update(){
        Optional<Cliente> cliente = clienteRepository.findById(40);

        assertTrue(cliente.isPresent(), "El cliente con id=40 debe de existir para ser actualizado.");

        cliente.orElse(null).setCedula("22222222222");
        cliente.orElse(null).setNombre("Juan22");
        cliente.orElse(null).setApellido("Taipe22");
        cliente.orElse(null).setDireccion("Direccion22");
        cliente.orElse(null).setTelefono("09992222222");
        cliente.orElse(null).setCorreo("jtaipe222@correo.com");

        Cliente clienteActualizado = clienteRepository.save(cliente.orElse(null));

        assertEquals("Juan22", clienteActualizado.getNombre());
        assertEquals("Taipe22", clienteActualizado.getApellido());
    }

    @Test
    public void delete(){
        if(clienteRepository.existsById(39)){
            clienteRepository.deleteById(39);
        }
        assertFalse(clienteRepository.existsById(39),"El id=39, debería haberse eliminado.");
    }

}

package com.simaoneto.service;

import com.simaoneto.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {
    private ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
    }

    public void save(){
        this.clientesRepository.save();
    }


}

package com.api.portal.service.impl;

import com.api.portal.dao.ClientDao;
import com.api.portal.dto.ClientDTO;
import com.api.portal.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;



    @Override
    public List<ClientDTO> findAll() {
        List<ClientDTO> listClients = new ArrayList<ClientDTO>();

        listClients = Optional.ofNullable(clientDao.findAll()).orElse(new ArrayList<ClientDTO>());

        return listClients;
    }

   

    @Override
    public void update(ClientDTO client) {
        clientDao.update(client);
    }

    @Override
    @Transactional
    public void deleteClientInCascade(String item) {
        log.info("void deleteClientInCascade");
            

        log.info("Before deleting client "+item);
        clientDao.delete(Integer.parseInt(item));

    }
}

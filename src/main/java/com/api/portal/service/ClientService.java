package com.api.portal.service;

import com.api.portal.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> findAll();

    void update(ClientDTO client);

    void deleteClientInCascade(String item);
}



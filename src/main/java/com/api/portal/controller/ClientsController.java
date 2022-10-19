package com.api.portal.controller;

import com.api.portal.dto.*;
import com.api.portal.service.ClientService;
import com.api.portal.util.Utils;
import com.api.portal.util.Validations;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@Api(tags = { "Client" }, value = "CRUD Operations for Clients")
@RequestMapping(value = "/client-management")
public class ClientsController {


    public enum StatusEnum {
        OK,
        NO_OK
    }

    @Autowired
    private ClientService clientService;


    @GetMapping(value = "/clients")
    @ApiOperation(value = "List all the Clients included in the system", httpMethod = "GET")
    public ResponseEntity<Object> getAllClients() {


        List<ClientDTO> listClients = new ArrayList<ClientDTO>();
      

        try {
            listClients = clientService.findAll();
        }catch (Exception ex){

            log.error("Error querying client data. Cause: "+ex.getMessage());
            return new ResponseEntity<Object>(new ErrorDTO(new Date(),ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

      
        return new ResponseEntity<Object>(listClients, HttpStatus.OK);
    }

    @GetMapping(value = "/clients/{id}")
    @ApiOperation(value = "Get clients by Id", httpMethod = "GET")
    public ResponseEntity<Object> getClientById(@PathVariable(value = "id") String id) {

            ClientDTO clientDTO = new ClientDTO();

        try {

          //  clientDTO = clientService.findByID(id);

        }catch (Exception ex){

            log.error("Error querying client data. Cause: "+ex.getMessage());
            return new ResponseEntity<Object>(new ErrorDTO(new Date(),ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(clientDTO, HttpStatus.OK);
    }

    @PostMapping(value ="/clients",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Insert new Track", httpMethod = "POST")
    public ResponseEntity<Object> insertNewTrack(@RequestParam Map<String, String> body){

        ClientDTO newClient = new ClientDTO();

        try{
            if (Validations.validateEmptyValues(body)){
                log.error("Bad Request. All the fields need to be populated");
                return new ResponseEntity<Object>(new ErrorDTO(new Date(),"Bad Request. All the fields need to be populated", HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);

            }

            newClient.setName(body.get("name"));
            newClient.setWebsite(body.get("website"));
            newClient.setClientManager(body.get("clientManager"));
 


        }catch(Exception ex){
            log.error("Error setting the new Client Object. Cause: "+ex.getMessage());
            return new ResponseEntity<Object>(new ErrorDTO(new Date(),ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);

        }

        try {

            if(body.get("id") == null){

                newClient.setRegistrationDate(new Date());
                //clientService.save(newClient);

            }else{

                newClient.setId(Integer.parseInt(body.get("id")));
                clientService.update(newClient);
            }

        }catch (Exception ex){

            log.error("Error saving Client. Cause: "+ex.getMessage());
            return new ResponseEntity<Object>(new ErrorDTO(new Date(),ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Product", httpMethod = "DELETE")
    @DeleteMapping(value = "/clients/{ids}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "ids") String ids) {


        List<String> ListClientsToDelete = Utils.splitHelper(ids);


        try {
            for (String item : ListClientsToDelete) {

               clientService.deleteClientInCascade(item);

            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<Object>(new ErrorDTO(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    public static boolean validateRequestData(ClientDTO client) {

        boolean flagResult = false;

        if (client.getName() == null || client.getName().equals((""))){
            flagResult = true;
        }

        if (client.getWebsite() == null || client.getWebsite().equals((""))){
            flagResult = true;
        }

        if (client.getClientManager() == null || client.getClientManager().equals((""))){
            flagResult = true;
        }

        return flagResult;

    }

}

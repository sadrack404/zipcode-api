package br.com.hub.correio.controller;

import br.com.hub.correio.exception.NoContentException;
import br.com.hub.correio.exception.NotReadyException;
import br.com.hub.correio.model.Adress;
import br.com.hub.correio.repository.AdressStatusRepository;
import br.com.hub.correio.repository.SetupRepository;
import br.com.hub.correio.service.CorreioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorreiosController {
    @Autowired
    private CorreioService service;

    @Autowired
    private AdressStatusRepository adressStatusRepository;
    @Autowired
    private SetupRepository setupRepository;

    @GetMapping("/status")
    public String getCorreios() {
        return "Status: " + this.service.getStatus();
    }

    @GetMapping("/zipcode/{zipcode}")
    public Adress getAdress(@PathVariable("zipcode") String zipcode) throws NotReadyException, NoContentException {
        return this.service.getAdressByzipcode(zipcode);
    }

}

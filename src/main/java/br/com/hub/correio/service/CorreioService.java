package br.com.hub.correio.service;

import br.com.hub.correio.CorreioApplication;
import br.com.hub.correio.exception.NoContentException;
import br.com.hub.correio.exception.NotReadyException;
import br.com.hub.correio.model.Adress;
import br.com.hub.correio.model.AdressStatus;
import br.com.hub.correio.model.Status;
import br.com.hub.correio.repository.AdressRepository;
import br.com.hub.correio.repository.AdressStatusRepository;
import br.com.hub.correio.repository.SetupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CorreioService {

    private static Logger logger = LoggerFactory.getLogger(CorreioApplication.class);

    @Value("${setup.on.start.up}")
    private boolean setupOnStartUp;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private AdressStatusRepository adressStatusRepository;
    @Autowired
    private SetupRepository setupRepository;

    public Status getStatus() {
        return this.adressStatusRepository
                .findById(AdressStatus.DEFAULT_ID)
                .orElse(AdressStatus.builder().status(Status.NEED_SETUP).build()).getStatus();
    }

    public Adress getAdressByzipcode(String zipcode) throws NoContentException, NotReadyException {
        if (!this.getStatus().equals(Status.READY)) {
            throw new NotReadyException();
        }
        return adressRepository.findById(zipcode).orElseThrow(NoContentException::new);
    }

    private void saveStatus(Status status) {
        this.adressStatusRepository.save(AdressStatus.builder()
                .id(AdressStatus.DEFAULT_ID)
                .status(status)
                .build());
    }

    @EventListener(ApplicationStartedEvent.class)
    protected void setupOnStartUp() {

        if (!setupOnStartUp)
            return;

        try {
            this.setup();
        } catch (Exception exec) {
            CorreioApplication.close(999);
            logger.error(".setupOnStartUp () - Exception", exec);
        }
    }

    public void setup() throws Exception {
        logger.info(".............");
        logger.info(".............");
        logger.info("...... SETUP RUNNING");
        logger.info(".............");
        logger.info(".............");

        if (this.getStatus().equals(Status.NEED_SETUP)) {
            this.saveStatus(Status.SETUP_RUNNING);

            try {
                this.adressRepository.saveAll(this.setupRepository.getFromOrigin());
            } catch (Exception e) {
                this.saveStatus(Status.NEED_SETUP);
                throw e;
            }

            this.saveStatus(Status.READY);
        }

        logger.info(".............");
        logger.info(".............");
        logger.info("...... SERVICE READY");
        logger.info(".............");
        logger.info(".............");
    }


}

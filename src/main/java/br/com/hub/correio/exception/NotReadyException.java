package br.com.hub.correio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Serviço está em instalação. Favor aguarde 3 a 5 minutos")
public class NotReadyException extends Exception {

}

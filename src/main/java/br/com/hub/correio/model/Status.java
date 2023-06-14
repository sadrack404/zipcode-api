package br.com.hub.correio.model;

public enum Status {
    NEED_SETUP, //PRECISA BAIXAR O CSV DOS CORREIOS
    SETUP_RUNNING, //ESTÁ BAIXANDO /SALVANDO NO BANCO
    READY; //CORREIOS BAIXADOS COM SUCESSO
}

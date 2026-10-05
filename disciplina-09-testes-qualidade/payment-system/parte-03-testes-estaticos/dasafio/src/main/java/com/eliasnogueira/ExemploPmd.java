package com.eliasnogueira;

public class ExemploPmd {

    private int contador;

    public void processar() {
        int valor = 42;
        try {
            executarOperacao();
        } catch(Exception e) {
            // nenhum tratamento
        }
    }

    private void executarOperacao() throws Exception { }

    private void metodoInutil() { }
}

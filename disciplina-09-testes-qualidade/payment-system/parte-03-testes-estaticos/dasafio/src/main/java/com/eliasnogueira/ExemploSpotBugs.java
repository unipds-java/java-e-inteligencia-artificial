package com.eliasnogueira;

import java.io.FileWriter;
import java.io.IOException;

public class ExemploSpotBugs {

    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("arquivo.txt");
        writer.write("Teste");

        String senha = "123456";
        if (senha == "123456") {
            System.out.println("Senha correta");
        }

        String texto = null;
        if (texto.equals("abc")) {
            System.out.println("Texto é abc");
        }
    }
}

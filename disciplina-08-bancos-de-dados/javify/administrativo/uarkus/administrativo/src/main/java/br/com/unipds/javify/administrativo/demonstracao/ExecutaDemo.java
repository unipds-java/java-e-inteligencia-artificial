package br.com.unipds.javify.administrativo.demonstracao;

import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
@IfBuildProfile("demo")
public class ExecutaDemo {

    private final AcessoJDBC acessoJdbc;
    private final DemoJpa demoJpa;

    public ExecutaDemo(AcessoJDBC acessoJdbc, DemoJpa demoJpa) {
        this.acessoJdbc = acessoJdbc;
        this.demoJpa = demoJpa;
    }


    public void run(@Observes StartupEvent evento) throws Exception {
        acessoJdbc.executar();
        demoJpa.buscaEndereco();
        demoJpa.cadastraEndereco();
        demoJpa.atualizaEndereco();
        demoJpa.buscarAssinaturaComPlano();
        demoJpa.removerCartaoVencido();
        demoJpa.testarConsultas();
    }

}

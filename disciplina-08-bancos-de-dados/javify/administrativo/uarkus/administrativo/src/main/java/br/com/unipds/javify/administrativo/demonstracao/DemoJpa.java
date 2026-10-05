package br.com.unipds.javify.administrativo.demonstracao;

import br.com.unipds.javify.administrativo.domain.Assinatura;
import br.com.unipds.javify.administrativo.domain.Endereco;
import br.com.unipds.javify.administrativo.domain.Usuario;
import br.com.unipds.javify.administrativo.repository.AssinaturaRepository;
import br.com.unipds.javify.administrativo.repository.EnderecoRepository;
import br.com.unipds.javify.administrativo.repository.UsuarioRepository;
import br.com.unipds.javify.administrativo.repository.projection.ResumoUsuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DemoJpa {

    private final EnderecoRepository enderecoRepository;
    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;

    public DemoJpa(EnderecoRepository enderecoRepository, AssinaturaRepository assinaturaRepository, UsuarioRepository usuarioRepository) {
        this.enderecoRepository = enderecoRepository;
        this.assinaturaRepository = assinaturaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void buscaEndereco(){
        System.out.println("Endereço com Jakarta Data: " + enderecoRepository.findByCodigoPostal("01508000").orElse(null));
    }

    @Transactional
    public void cadastraEndereco() {
        var e = new Endereco();
        e.setCodigoPostal("25950000");
        e.setLogradouro("Rua Amaral");
        e.setBairro("Tijuca");
        enderecoRepository.save(e);
    }

    @Transactional
    public void atualizaEndereco() {
        var e = enderecoRepository.findByCodigoPostal("25950000").orElseThrow();
        System.out.println("Antes: " + e);
        e.setLogradouro("Rua Não Amaral Não Sincronizada");
        System.out.println("Depois: " + e);
        System.out.println("---");
    }

    @Transactional
    public void buscarAssinaturaComPlano(){
        List<Assinatura> assinaturas = assinaturaRepository.buscarAssinaturasComPlano();

        for (Assinatura assinatura: assinaturas) {
            System.out.printf("%s -  %s  \n", assinatura.getId(), assinatura.getPlano().getNome() );
        }
    }

    @Transactional
    public void removerCartaoVencido() {
        var assinatura = assinaturaRepository.buscarComCartoes(3);
        System.out.println("Cartões: " + assinatura.get().getCartoes());
        if (!assinatura.get().getCartoes().isEmpty()) {
            assinatura.get().getCartoes().remove(0);
            System.out.println("Cartões: " + assinatura.get().getCartoes());
        }
    }

    @Transactional
    public void testarConsultas() {
        for (Usuario u : usuarioRepository.findByTitularTrue()) {
            System.out.println("Titular: " + u.getNome() + " (" + u.getEmail() + ")");
        }

        for (Usuario u : usuarioRepository.buscarPorNome("ei")) {
            System.out.println("Usuario encontrado por nome: " + u.getNome() + " (" + u.getEmail() + ")");
        }

        long total = usuarioRepository.contarTitularesAtivos();
        System.out.println("Total usuários ativos: " + total);

        for (ResumoUsuario u : usuarioRepository.listaResumoTitulares()) {
            System.out.println("Resumo: " + u.nome() + " (" + u.email() + ")");
        }
    }

}

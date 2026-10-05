package br.com.unipds.javify.administrativo.service;

import br.com.unipds.javify.administrativo.domain.Assinatura;
import br.com.unipds.javify.administrativo.domain.Endereco;
import br.com.unipds.javify.administrativo.domain.Usuario;
import br.com.unipds.javify.administrativo.dto.*;
import br.com.unipds.javify.administrativo.repository.AssinaturaRepository;
import br.com.unipds.javify.administrativo.repository.EnderecoRepository;
import br.com.unipds.javify.administrativo.repository.PlanoRepository;
import br.com.unipds.javify.administrativo.repository.UsuarioRepository;
import br.com.unipds.javify.administrativo.repository.UsuarioTelefoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final PlanoRepository planoRepository;
    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioTelefoneRepository usuarioTelefoneRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          EnderecoRepository enderecoRepository,
                          PlanoRepository planoRepository,
                          AssinaturaRepository assinaturaRepository,
                          UsuarioTelefoneRepository usuarioTelefoneRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
        this.planoRepository = planoRepository;
        this.assinaturaRepository = assinaturaRepository;
        this.usuarioTelefoneRepository = usuarioTelefoneRepository;
    }

    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.listarComRelacionamentos().stream()
                .map(this::toResponseResumido)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        var usuario = usuarioRepository.buscarComRelacionamentos(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));
        return toResponseCompleto(usuario);
    }

    public UsuarioResponse criar(UsuarioRequest request) {
        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenhaHash(request.senhaHash());
        usuario.setTitular(request.titular());
        usuario.setDataNascimento(request.dataNascimento());
        vincularEndereco(usuario, request.endereco());
        vincularAssinatura(usuario, request.planoId(), request.assinaturaId());
        return toResponseCompleto(usuarioRepository.insert(usuario));
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenhaHash(request.senhaHash());
        usuario.setTitular(request.titular());
        usuario.setDataNascimento(request.dataNascimento());
        vincularEndereco(usuario, request.endereco());
        vincularAssinatura(usuario, request.planoId(), request.assinaturaId());
        return toResponseCompleto(usuarioRepository.update(usuario));
    }

    public void remover(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));
        usuarioRepository.delete(usuario);
    }

    private void vincularEndereco(Usuario usuario, EnderecoRequest request) {
        var endereco = enderecoRepository.findById(request.codigoPostal())
                .orElseGet(() -> {
                    var novo = new Endereco();
                    novo.setCodigoPostal(request.codigoPostal());
                    novo.setLogradouro(request.logradouro());
                    novo.setBairro(request.bairro());
                    return enderecoRepository.insert(novo);
                });
        usuario.setEndereco(endereco);
    }

    private void vincularAssinatura(Usuario usuario, Integer planoId, Integer assinaturaId) {
        if (planoId != null) {
            var plano = planoRepository.findById(planoId)
                    .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado: " + planoId));
            var assinatura = new Assinatura();
            assinatura.setPlano(plano);
            assinatura.setStatusAtiva(true);
            usuario.setAssinatura(assinaturaRepository.insert(assinatura));
            return;
        }
        if (assinaturaId != null) {
            var assinatura = assinaturaRepository.buscarComPlanoPorId(assinaturaId)
                    .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada: " + assinaturaId));
            usuario.setAssinatura(assinatura);
            return;
        }
        usuario.setAssinatura(null);
    }

    private UsuarioResponse toResponseResumido(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.isTitular(),
                usuario.getDataNascimento(),
                toEnderecoResponse(usuario.getEndereco()),
                toAssinaturaResponse(usuario.getAssinatura()),
                List.of()
        );
    }

    private UsuarioResponse toResponseCompleto(Usuario usuario) {
        var telefones = usuarioTelefoneRepository.findByUsuarioId(usuario.getId()).stream()
                .map(t -> new UsuarioTelefoneResponse(t.getId(), t.getUsuario().getId(), t.getNumero(), t.getTipo().name()))
                .toList();
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.isTitular(),
                usuario.getDataNascimento(),
                toEnderecoResponse(usuario.getEndereco()),
                toAssinaturaResponse(usuario.getAssinatura()),
                telefones
        );
    }

    private EnderecoResponse toEnderecoResponse(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoResponse(
                endereco.getCodigoPostal(),
                endereco.getLogradouro(),
                endereco.getBairro()
        );
    }

    private AssinaturaResponse toAssinaturaResponse(Assinatura assinatura) {
        if (assinatura == null) {
            return null;
        }
        var plano = assinatura.getPlano();
        var planoResponse = plano != null ? new PlanoResponse(
                plano.getId(),
                plano.getNome(),
                plano.getPreco(),
                plano.isPossuiPropagandas(),
                plano.getLimiteMembros(),
                plano.isModoOffline()
        ) : null;
        return new AssinaturaResponse(
                assinatura.getId(),
                planoResponse,
                assinatura.isStatusAtiva()
        );
    }
}

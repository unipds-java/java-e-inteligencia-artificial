package br.com.unipds.javify.administrativo.service;

import br.com.unipds.javify.administrativo.domain.Endereco;
import br.com.unipds.javify.administrativo.dto.EnderecoRequest;
import br.com.unipds.javify.administrativo.dto.EnderecoResponse;
import br.com.unipds.javify.administrativo.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoResponse> listarTodos() {
        return enderecoRepository.listarTodos().stream()
                .map(this::toResponse)
                .toList();
    }

    public EnderecoResponse buscarPorCodigoPostal(String codigoPostal) {
        return enderecoRepository.findById(codigoPostal)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado: " + codigoPostal));
    }

    public EnderecoResponse criar(EnderecoRequest request) {
        var endereco = new Endereco();
        endereco.setCodigoPostal(request.codigoPostal());
        endereco.setLogradouro(request.logradouro());
        endereco.setBairro(request.bairro());
        return toResponse(enderecoRepository.insert(endereco));
    }

    public EnderecoResponse atualizar(String codigoPostal, EnderecoRequest request) {
        var endereco = enderecoRepository.findById(codigoPostal)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado: " + codigoPostal));
        endereco.setLogradouro(request.logradouro());
        endereco.setBairro(request.bairro());
        return toResponse(enderecoRepository.update(endereco));
    }

    public void remover(String codigoPostal) {
        var endereco = enderecoRepository.findById(codigoPostal)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado: " + codigoPostal));
        enderecoRepository.delete(endereco);
    }

    EnderecoResponse toResponse(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoResponse(
                endereco.getCodigoPostal(),
                endereco.getLogradouro(),
                endereco.getBairro()
        );
    }
}

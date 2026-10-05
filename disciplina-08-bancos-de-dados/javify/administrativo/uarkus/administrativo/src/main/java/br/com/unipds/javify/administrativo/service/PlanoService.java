package br.com.unipds.javify.administrativo.service;

import br.com.unipds.javify.administrativo.domain.Plano;
import br.com.unipds.javify.administrativo.dto.PlanoRequest;
import br.com.unipds.javify.administrativo.dto.PlanoResponse;
import br.com.unipds.javify.administrativo.repository.PlanoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<PlanoResponse> listarTodos() {
        return planoRepository.listarTodos().stream()
                .map(this::toResponse)
                .toList();
    }

    public PlanoResponse buscarPorId(Integer id) {
        return planoRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado: " + id));
    }

    public PlanoResponse criar(PlanoRequest request) {
        var plano = new Plano();
        plano.setNome(request.nome());
        plano.setPreco(request.preco());
        plano.setPossuiPropagandas(request.possuiPropagandas());
        plano.setLimiteMembros(request.limiteMembros());
        plano.setModoOffline(request.modoOffline());
        return toResponse(planoRepository.insert(plano));
    }

    public PlanoResponse atualizar(Integer id, PlanoRequest request) {
        var plano = planoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado: " + id));
        plano.setNome(request.nome());
        plano.setPreco(request.preco());
        plano.setPossuiPropagandas(request.possuiPropagandas());
        plano.setLimiteMembros(request.limiteMembros());
        plano.setModoOffline(request.modoOffline());
        return toResponse(planoRepository.update(plano));
    }

    public void remover(Integer id) {
        var plano = planoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado: " + id));
        planoRepository.delete(plano);
    }

    private PlanoResponse toResponse(Plano plano) {
        return new PlanoResponse(
                plano.getId(),
                plano.getNome(),
                plano.getPreco(),
                plano.isPossuiPropagandas(),
                plano.getLimiteMembros(),
                plano.isModoOffline()
        );
    }
}

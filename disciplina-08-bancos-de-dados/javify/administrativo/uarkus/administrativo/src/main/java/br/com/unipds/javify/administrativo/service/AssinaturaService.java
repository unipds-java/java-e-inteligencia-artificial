package br.com.unipds.javify.administrativo.service;

import br.com.unipds.javify.administrativo.domain.Assinatura;
import br.com.unipds.javify.administrativo.dto.AssinaturaRequest;
import br.com.unipds.javify.administrativo.dto.AssinaturaResponse;
import br.com.unipds.javify.administrativo.dto.PlanoResponse;
import br.com.unipds.javify.administrativo.repository.AssinaturaRepository;
import br.com.unipds.javify.administrativo.repository.PlanoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final PlanoRepository planoRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository, PlanoRepository planoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.planoRepository = planoRepository;
    }

    public List<AssinaturaResponse> listarTodas() {
        return assinaturaRepository.buscarAssinaturasComPlano().stream()
                .map(this::toResponse)
                .toList();
    }

    public AssinaturaResponse buscarPorId(Integer id) {
        return assinaturaRepository.buscarComPlanoPorId(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada: " + id));
    }

    public AssinaturaResponse criar(AssinaturaRequest request) {
        var plano = planoRepository.findById(request.planoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado: " + request.planoId()));
        var assinatura = new Assinatura();
        assinatura.setPlano(plano);
        assinatura.setStatusAtiva(request.statusAtiva());
        return toResponse(assinaturaRepository.insert(assinatura));
    }

    public AssinaturaResponse atualizar(Integer id, AssinaturaRequest request) {
        var assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada: " + id));
        var plano = planoRepository.findById(request.planoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado: " + request.planoId()));
        assinatura.setPlano(plano);
        assinatura.setStatusAtiva(request.statusAtiva());
        return toResponse(assinaturaRepository.update(assinatura));
    }

    public void remover(Integer id) {
        var assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada: " + id));
        // O cascade do @OneToMany nao vale em Jakarta Data: a remocao dos cartoes e explicita.
        assinaturaRepository.removerCartoesDaAssinatura(id);
        assinaturaRepository.delete(assinatura);
    }

    private AssinaturaResponse toResponse(Assinatura assinatura) {
        var plano = assinatura.getPlano();
        var planoResponse = new PlanoResponse(
                plano.getId(),
                plano.getNome(),
                plano.getPreco(),
                plano.isPossuiPropagandas(),
                plano.getLimiteMembros(),
                plano.isModoOffline()
        );
        return new AssinaturaResponse(
                assinatura.getId(),
                planoResponse,
                assinatura.isStatusAtiva()
        );
    }
}

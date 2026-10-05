package br.com.unipds.javify.administrativo.service;

import br.com.unipds.javify.administrativo.domain.CartaoCredito;
import br.com.unipds.javify.administrativo.dto.CartaoCreditoRequest;
import br.com.unipds.javify.administrativo.dto.CartaoCreditoResponse;
import br.com.unipds.javify.administrativo.repository.AssinaturaRepository;
import br.com.unipds.javify.administrativo.repository.CartaoCreditoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class CartaoCreditoService {

    private final CartaoCreditoRepository cartaoCreditoRepository;
    private final AssinaturaRepository assinaturaRepository;

    public CartaoCreditoService(CartaoCreditoRepository cartaoCreditoRepository, AssinaturaRepository assinaturaRepository) {
        this.cartaoCreditoRepository = cartaoCreditoRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    public List<CartaoCreditoResponse> listarTodos() {
        return cartaoCreditoRepository.listarTodos().stream()
                .map(this::toResponse)
                .toList();
    }

    public CartaoCreditoResponse buscarPorId(Integer id) {
        return cartaoCreditoRepository.buscarPorId(id)
                .map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Cartão de crédito não encontrado: " + id));
    }

    public List<CartaoCreditoResponse> listarPorAssinatura(Integer assinaturaId) {
        return cartaoCreditoRepository.findByAssinaturaId(assinaturaId).stream()
                .map(this::toResponse)
                .toList();
    }

    public CartaoCreditoResponse criar(CartaoCreditoRequest request) {
        return criar(request.assinaturaId(), request);
    }

    public CartaoCreditoResponse criar(Integer assinaturaId, CartaoCreditoRequest request) {
        var assinatura = assinaturaRepository.findById(assinaturaId)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada: " + assinaturaId));
        var cartao = new CartaoCredito();
        cartao.setAssinatura(assinatura);
        cartao.setNomeTitular(request.nomeTitular());
        cartao.setUltimosQuatroDigitos(request.ultimosQuatroDigitos());
        cartao.setTokenGateway(request.tokenGateway());
        cartao.setValidade(request.validade());
        return toResponse(cartaoCreditoRepository.insert(cartao));
    }

    public CartaoCreditoResponse atualizar(Integer id, CartaoCreditoRequest request) {
        var cartao = cartaoCreditoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cartão de crédito não encontrado: " + id));
        var assinatura = assinaturaRepository.findById(request.assinaturaId())
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada: " + request.assinaturaId()));
        cartao.setAssinatura(assinatura);
        cartao.setNomeTitular(request.nomeTitular());
        cartao.setUltimosQuatroDigitos(request.ultimosQuatroDigitos());
        cartao.setTokenGateway(request.tokenGateway());
        cartao.setValidade(request.validade());
        return toResponse(cartaoCreditoRepository.update(cartao));
    }

    public void remover(Integer id) {
        var cartao = cartaoCreditoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cartão de crédito não encontrado: " + id));
        cartaoCreditoRepository.delete(cartao);
    }

    private CartaoCreditoResponse toResponse(CartaoCredito cartao) {
        return new CartaoCreditoResponse(
                cartao.getId(),
                cartao.getAssinatura().getId(),
                cartao.getNomeTitular(),
                cartao.getUltimosQuatroDigitos(),
                cartao.getTokenGateway(),
                cartao.getValidade()
        );
    }
}

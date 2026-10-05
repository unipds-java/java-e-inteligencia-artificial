package br.com.unipds.javify.administrativo.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {

    private static final String PROBLEM_JSON = "application/problem+json";

    @ServerExceptionMapper
    public RestResponse<ProblemDetail> handleEntityNotFound(EntityNotFoundException ex) {
        return naoEncontrado(ex);
    }

    @ServerExceptionMapper
    public RestResponse<ProblemDetail> handleValidation(ConstraintViolationException ex) {
        return dadosInvalidos(ex);
    }

    @ServerExceptionMapper
    public RestResponse<ProblemDetail> handleMessageNotReadable(JsonProcessingException ex) {
        return corpoInvalido();
    }

    @ServerExceptionMapper
    public RestResponse<ProblemDetail> handleDataIntegrity(org.hibernate.exception.ConstraintViolationException ex) {
        return conflitoDeDados();
    }

    @ServerExceptionMapper
    public RestResponse<ProblemDetail> handleGeneric(Exception ex) {
        // As excecoes atravessam o interceptador de transacao, entao podem chegar embrulhadas.
        for (Throwable causa = ex; causa != null; causa = causa.getCause()) {
            if (causa instanceof EntityNotFoundException entityNotFound) {
                return naoEncontrado(entityNotFound);
            }
            if (causa instanceof ConstraintViolationException violacao) {
                return dadosInvalidos(violacao);
            }
            if (causa instanceof org.hibernate.exception.ConstraintViolationException) {
                return conflitoDeDados();
            }
            if (causa instanceof JsonProcessingException) {
                return corpoInvalido();
            }
            if (causa.getCause() == causa) {
                break;
            }
        }

        if (ex instanceof WebApplicationException webApplicationException) {
            int status = webApplicationException.getResponse().getStatus();
            ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
            problem.setTitle(webApplicationException.getResponse().getStatusInfo().getReasonPhrase());
            return resposta(problem);
        }

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), ex.getMessage());
        problem.setTitle("Erro interno no servidor");
        return resposta(problem);
    }

    private RestResponse<ProblemDetail> naoEncontrado(EntityNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                Response.Status.NOT_FOUND.getStatusCode(), ex.getMessage());
        problem.setTitle("Recurso não encontrado");
        return resposta(problem);
    }

    private RestResponse<ProblemDetail> dadosInvalidos(ConstraintViolationException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(Response.Status.BAD_REQUEST.getStatusCode());
        problem.setTitle("Dados inválidos");

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.put(nomeDoCampo(violation.getPropertyPath()), violation.getMessage());
        }
        problem.setProperty("errors", errors);
        return resposta(problem);
    }

    private RestResponse<ProblemDetail> corpoInvalido() {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                Response.Status.BAD_REQUEST.getStatusCode(), "Corpo da requisição inválido");
        problem.setTitle("Requisição inválida");
        return resposta(problem);
    }

    private RestResponse<ProblemDetail> conflitoDeDados() {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                Response.Status.CONFLICT.getStatusCode(),
                "Violação de integridade de dados. Verifique valores únicos ou relacionamentos obrigatórios.");
        problem.setTitle("Conflito de dados");
        return resposta(problem);
    }

    /**
     * O caminho da violacao vem como "criar.request.nome"; o cliente so precisa de "nome".
     */
    private String nomeDoCampo(Path propertyPath) {
        String ultimoNo = null;
        for (Path.Node node : propertyPath) {
            ultimoNo = node.getName();
        }
        return ultimoNo != null ? ultimoNo : propertyPath.toString();
    }

    private RestResponse<ProblemDetail> resposta(ProblemDetail problem) {
        return RestResponse.ResponseBuilder.<ProblemDetail>create(problem.getStatus())
                .entity(problem)
                .type(PROBLEM_JSON)
                .build();
    }
}

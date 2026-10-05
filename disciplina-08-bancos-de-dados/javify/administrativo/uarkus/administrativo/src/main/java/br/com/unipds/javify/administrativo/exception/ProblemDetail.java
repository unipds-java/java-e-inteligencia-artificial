package br.com.unipds.javify.administrativo.exception;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Representacao RFC 7807 das respostas de erro. O Quarkus nao traz um equivalente
 * ao org.springframework.http.ProblemDetail, entao o formato e reproduzido aqui.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetail {

    private URI type = URI.create("about:blank");
    private String title;
    private int status;
    private String detail;

    private final Map<String, Object> properties = new LinkedHashMap<>();

    private ProblemDetail(int status, String detail) {
        this.status = status;
        this.detail = detail;
    }

    public static ProblemDetail forStatus(int status) {
        return new ProblemDetail(status, null);
    }

    public static ProblemDetail forStatusAndDetail(int status, String detail) {
        return new ProblemDetail(status, detail);
    }

    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    public URI getType() {
        return type;
    }

    public void setType(URI type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

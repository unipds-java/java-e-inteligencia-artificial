package dev.ia;

import dev.langchain4j.data.message.UserMessage;
import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InjectionGuard implements InputGuardrail {

    @Inject
    PromptSecurityExpert securityExpert;

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        if (securityExpert.isAttack(userMessage.singleText())) {
            return failure("Sua mensagem foi bloqueada por conter instruções não permitidas.");
        }
        return InputGuardrailResult.success();
    }
}
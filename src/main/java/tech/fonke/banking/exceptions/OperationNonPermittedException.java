package tech.fonke.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNonPermittedException extends RuntimeException {

    // Le message d'erreurs que l'on souhaite afficher
    private final String errorMsg;

    // Le code l'opération non permise
    private final String operationId;

    // La source du message d'erreurs
    private final String source;

    // La dépendance lié au message d'erreurs
    private final String dependency;

}
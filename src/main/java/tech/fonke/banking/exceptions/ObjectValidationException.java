package tech.fonke.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public class ObjectValidationException extends RuntimeException{

    //  Ensemble des violations de validation.
    private final Set<String> violations;

    //  Source des violations de validation.
    private final String violationSource;
}

package tech.fonke.banking.validators;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;


import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {

    // Création d'une instance de ValidatorFactory pour obtenir un validateur
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    // Obtention du validateur à partir de la factory
    private final Validator validator = factory.getValidator();

    /**
     * Méthode pour valider un objet de type générique T.
     * Si des violations de contraintes sont trouvées, une exception ObjectValidationException est levée.
     *
     * @param objectToValidate l'objet à valider
    // * @throws ObjectValidationException si des violations de contraintes sont trouvées
     */
    public void validate(T objectToValidate) {

        // Validation de l'objet et récupération des violations de contraintes
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);

        // Si des violations sont trouvées, on lance une exception
        if (!violations.isEmpty()) {
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            // Lancement de l'exception avec les messages d'erreur et le nom de la classe de l'objet
       //     throw new ObjectValidationException(errorMessages, objectToValidate.getClass().getName());
        }
    }

}

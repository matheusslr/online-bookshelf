package br.com.crudspring.onlinebookshelf.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ISBNValidator implements ConstraintValidator<ISBNContraints, Long> {
    public boolean isValid(Long isbnNumber, ConstraintValidatorContext cxt) {
        String isbnStringConverted = isbnNumber.toString();
        int sum = 0;
        int aux = 1;

        if (isbnStringConverted.length() != 10) {
            return false;
        }

        for (int i = isbnStringConverted.length() - 1; i != 0; i--) {
            int number = Character.getNumericValue(isbnStringConverted.charAt(i));
            sum += number * aux;
            aux++;
        }

        if (sum % 11 == 0) {
            return true;
        }

        return true;
    }
}

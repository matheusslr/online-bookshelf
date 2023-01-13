package br.com.crudspring.onlinebookshelf.annotations;

import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ISBNValidator implements ConstraintValidator<ISBNContraints, Long> {

    @Override
    public void initialize(ISBNContraints isbnNumber) {
    }

    @Override
    public boolean isValid(Long isbnNumber, ConstraintValidatorContext cxt) {

        if (isbnNumber == null) {
            return false;
        }

        String isbnStringConverted = isbnNumber.toString();

        if (isbnStringConverted.length() == 10) {
            return checkISBN10(isbnStringConverted);
        } else {
            return false;
        }
    }

    private boolean checkISBN10(String isbnNumber) {
        int sum = 0;
        int aux = 1;

        for (int i = isbnNumber.length(); i != 0; i--) {
            int number = Character.getNumericValue(isbnNumber.charAt(i - 1));
            sum += number * aux;
            aux++;
        }

        if (sum % 11 == 0) {
            return true;
        }

        return false;
    }

}

package br.com.crudspring.onlinebookshelf.annotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.validation.ConstraintValidatorContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Test for ISBN Validator Annotation")
public class ISBNValidatorTest {

    @Autowired
    private ISBNValidator isbnValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    @Test
    @DisplayName("Validated ISBN when Format is Correct")
    public void isValid_ValidateISBN_WhenFormatIsCorrect(){
        Long isbnValid = createValidISBN();
        Long isbnInvalid = createInvalidISBN();

        assertTrue(isbnValidator.isValid(isbnValid, constraintValidatorContext));
        assertFalse(isbnValidator.isValid(isbnInvalid, constraintValidatorContext));
    }

    private Long createValidISBN(){
        Long isbnNumber = 1259060977L;
        return isbnNumber;
    }

    private Long createInvalidISBN(){
        Long isbnNumber = 1259062577L;
        return isbnNumber;
    }
}

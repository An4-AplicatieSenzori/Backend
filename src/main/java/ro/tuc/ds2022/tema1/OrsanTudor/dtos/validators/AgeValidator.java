package ro.tuc.ds2022.tema1.OrsanTudor.dtos.validators;

import org.springframework.stereotype.Component;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.validators.annotation.AgeLimit;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


//Clasa pentru validare:
@Component
public class AgeValidator implements ConstraintValidator<AgeLimit, Integer>
{
    //Limit age;
    private int ageLimit;

    //O limita, default 120;
    @Override
    public void initialize(AgeLimit constraintAnnotation) {
        this.ageLimit = constraintAnnotation.limit();
    }

    //Return true if input > limita, deci este prost asa;
    @Override
    public boolean isValid(Integer inputAge, ConstraintValidatorContext constraintValidatorContext) {
        return inputAge > ageLimit;
    }
}


















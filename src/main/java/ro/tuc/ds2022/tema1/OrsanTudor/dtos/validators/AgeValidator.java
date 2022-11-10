package ro.tuc.ds2022.tema1.OrsanTudor.dtos.validators;

import org.springframework.stereotype.Component;
import ro.tuc.ds2022.tema1.OrsanTudor.dtos.validators.annotation.AgeLimit;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//FUNCTII IMPORTANTE PENTRU VALIDATOARE:
//DAI VALOAREA ANUME, FOLOSESTI VALOAREA PENTRU VALIDITATE;
//initialize   / isValid!
//(Annotation) / (Object, Context);
//Obiectul T poate fi si lista? Idk;

//Clasa pentru validare:
//Javax Validation!
//(limit = 18) => CE II DAI LA CONSTRAINTANNOTATION!
//Poate primi n parametrii, si tu ii iei pe rand?
@Component
public class AgeValidator implements ConstraintValidator<AgeLimit, Integer>
{
    //Limit age;
    private int ageLimit; //Aici primeste implicit 0?

    //O limita, default 120;
    //Din interfata annotation definita, iei valorile date ca parametrii,
    //si executi logica cu ele (In aceasta clasa, de validator);
    //No return!
    @Override
    public void initialize(AgeLimit constraintAnnotation) {
        this.ageLimit = constraintAnnotation.limit();
    }

    //Return true if input > limita, deci este prost asa;
    //Integer aici? Pentru isValid?
    //Return boolean, ori valid ori nu!
    @Override
    public boolean isValid(Integer inputAge, ConstraintValidatorContext constraintValidatorContext) {
        //System.out.println("Age " + inputAge + " is not valid!"); //Care este age-ul implicit: 0! (Integer!!!)
        return inputAge > ageLimit;
    }
}


















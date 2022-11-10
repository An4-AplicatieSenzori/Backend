package ro.tuc.ds2022.tema1.OrsanTudor.dtos.validators.annotation;

import ro.tuc.ds2022.tema1.OrsanTudor.dtos.validators.AgeValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//Multe annotari;
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {AgeValidator.class})

//(limit = 18)
//Interfata cu metodele:
public @interface AgeLimit
{
    //Nu este atribut / field, ci este
    //defapt o FUNCTIE, ce returneaza
    //un default, aici un int 120!
    //Varsta default;
    int limit() default 120; //Limita daca nu se da!
    //int limitNew() default 240;

    //Mesaj pentru cand nu se potriveste;
    //Apare daca nu dai age limit bun la request!
    String message() default "Age does not match the required adult limit!";

    //???
    Class<?>[] groups() default {};

    //???
    Class<? extends Payload>[] payload() default {};
}












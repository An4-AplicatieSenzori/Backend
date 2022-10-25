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

//Interfata cu metodele:
public @interface AgeLimit
{
    //Varsta default;
    int limit() default 120;

    //Mesaj pentru cand nu se potriveste;
    String message() default "Age does not match the required adult limit!";

    //?
    Class<?>[] groups() default {};

    //?
    Class<? extends Payload>[] payload() default {};
}












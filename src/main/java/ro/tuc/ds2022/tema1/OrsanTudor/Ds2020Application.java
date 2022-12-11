package ro.tuc.ds2022.tema1.OrsanTudor; //Toate au package;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.validation.annotation.Validated;
//SQL?
import java.util.TimeZone;

//Pentru booting;
//De ce validated aici;
@SpringBootApplication
@Validated
@EnableRabbit
public class Ds2020Application extends SpringBootServletInitializer
{
    //Configure:
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Ds2020Application.class);
    }

    //Main:
    public static void main(String[] args) {
        //Timezone + run;
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Ds2020Application.class, args);
        System.out.println("Finalul programului!");
    }
}











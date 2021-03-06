package hello;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "controller")
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        Map<String, Object> map = new HashMap<>();
        map.put("SERVER_CONTEXT_PATH", "/uniquiz");
        application.setDefaultProperties(map);
        application.run(args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }


           /* Uncomment for testing
            UserService service = new UserService();
            service.createUser("test","LEL","test","test");

            System.out.println(new UserRepository().findOne("test").get().getPassword());
            */
        };
    }

}
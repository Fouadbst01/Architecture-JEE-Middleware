package presentation;

import dao.IDao;
import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationInjection {
    public static void main(String[] args) {
        // packeg de base de mon application est com
        ApplicationContext context = new AnnotationConfigApplicationContext("metier","dao");
        IMetier metier = context.getBean(IMetier.class);

        System.out.println("Dynamic Injection (Spring) -> Annotation version :");
        System.out.println(metier.Calculate());
    }
}

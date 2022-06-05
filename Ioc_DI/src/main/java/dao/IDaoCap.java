package dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("dao1")
public class IDaoCap implements IDao{
    @Override
    public double getData() {
        System.out.println("Capture version");
        return Math.random()*100;
    }
}

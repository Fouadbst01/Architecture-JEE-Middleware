package dao;

import org.springframework.stereotype.Component;

@Component("daoDB")
public class IDaoDB implements IDao{
    @Override
    public double getData() {
        System.out.println("Data base version");
        return Math.random()*100;
    }
}

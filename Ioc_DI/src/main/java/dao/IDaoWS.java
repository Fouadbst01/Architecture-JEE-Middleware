package dao;

import org.springframework.stereotype.Component;

@Component("daoWS")
public class IDaoWS implements IDao{
    @Override
    public double getData() {
        System.out.println("Web Service Version");
        return Math.random()*100;
    }
}

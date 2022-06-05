package dao;

import annotation.MyComponent;

@MyComponent(Name = "dao2")
public class IDaoWS implements IDao{
    public IDaoWS() {
    }

    @Override
    public double getDate() {
        System.out.println("version web services");
        return 300;
    }
}

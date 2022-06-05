package dao;

import annotation.MyComponent;

@MyComponent(Name = "dao")
public class IDaoDB implements IDao{
    public IDaoDB() {
    }

    @Override
    public double getDate() {
        System.out.println("version DB");
        return 100;
    }
}

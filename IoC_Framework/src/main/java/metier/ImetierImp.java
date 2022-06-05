package metier;

import dao.IDao;
import annotation.Inject;
import annotation.MyComponent;

@MyComponent(Name = "metier")
public class ImetierImp implements Imetier{

    @Inject
    private IDao dao;

    @Override
    public double Calculate() {
        double d =dao.getDate();
        System.out.println("value = "+d);
        return d;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}

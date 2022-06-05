package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IMetierImpl implements IMetier{

    private IDao dao;

    public IMetierImpl(IDao dao1) {
        this.dao = dao1;
    }

    @Override
    public double Calculate() {
        double res = dao.getData()*12+100+Math.sin(15.2)*10;
        return res;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}

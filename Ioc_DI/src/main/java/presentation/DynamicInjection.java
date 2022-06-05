package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicInjection {
    /*public static void main(String[] args) {
        try {
            Scanner sn = new Scanner(new File("./Config.txt"));

            String MetierClassNmae = sn.next();
            Class MetierClass = Class.forName(MetierClassNmae);
            IMetier metier = (IMetier) MetierClass.newInstance();

            String DaoClassname = sn.next();
            Class DaoClass = Class.forName(DaoClassname);
            IDao Dao = (IDao) DaoClass.newInstance();

            Method method = MetierClass.getMethod("setDao",IDao.class);
            method.invoke(metier,Dao);

            System.out.println(metier.Calculate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}

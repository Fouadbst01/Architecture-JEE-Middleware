package annotation;

import exceptions.PackageNotFoundException;
import metier.Imetier;
import metier.ImetierImp;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class AnnotationScanner {
    private String[] packegName;
    public HashMap<String,Object> map =new HashMap();
    private List<Class> classList = new ArrayList<>();

    public AnnotationScanner(String ...packegs) throws PackageNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
       ClassLoader classLoader= AnnotationScanner.class.getClassLoader();
        for (String PackegName:packegs) {
            File packDirectory = new File("src/main/java/"+PackegName);
            if(!packDirectory.exists())
                throw new PackageNotFoundException(PackegName);
            else{
                File[] ClassFiles = packDirectory.listFiles();
                for (File ClassFile:ClassFiles) {
                    String packClassNam = PackegName+"."+ClassFile.getName().replaceAll(".java","");
                    Class Myclass = classLoader.loadClass(packClassNam);
                    Annotation  annotation = Myclass.getAnnotation(MyComponent.class);
                    classList.add(Myclass);
                    if(annotation!=null){
                        String s = ((MyComponent)annotation).Name();
                        String objNam= s.equals("")?packClassNam:s;
                        map.put(objNam,Myclass.newInstance());
                    }
                }
            }
        }
        //chercher les fields de la Class ou il faut injecter des Objects
        for (Class myClass:classList) {
            Field [] fields = myClass.getDeclaredFields();
            for (Field field:fields) {
                //pour chaque filed de la class en cherche s'elle est tager par l'annotation Inject
                Annotation annotation = field.getAnnotation(Inject.class);
                if(annotation!=null){
                    //les champs de notre class sont prives ce ligne permet de rendre ces champs accessible
                    //eviter une exeption de type IllegalAccessException
                    field.setAccessible(true);
                    Object o1 = getBean(myClass);
                    Object value = getBean(field.getType());
                    field.set(o1,value);
                }
            }
        }
    }

    /*public Object getBean(String s){
        return map.get(s);
    }*/
    public <T> T getBean(Class<T> cla){
        for (Object r :map.values()){
            if(cla.isInterface()){
                for(Class myInterfac : r.getClass().getInterfaces()){
                    if(myInterfac==cla) {
                        System.out.println("yes");
                        return cla.cast(r);
                    }
                }
            }else{
                if(cla.isInstance(r)){
                    System.out.println(cla.cast(r));
                    return cla.cast(r);
                }
            }
        }
        throw new RuntimeException("Bean "+cla.getSimpleName()+" Not Fouad");
    }
}

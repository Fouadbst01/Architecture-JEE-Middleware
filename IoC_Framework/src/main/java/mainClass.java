import annotation.AnnotationScanner;
import exceptions.PackageNotFoundException;
import metier.ImetierImp;

public class mainClass {
    public static void main(String[] args) throws Exception {
        AnnotationScanner annotationScanner = new AnnotationScanner("metier", "dao");
        ImetierImp var =annotationScanner.getBean(ImetierImp.class);
        var.Calculate();
    }
}

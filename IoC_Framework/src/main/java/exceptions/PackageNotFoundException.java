package exceptions;

public class PackageNotFoundException extends Exception {
    private String PackegeName;

    public PackageNotFoundException(String packegeName) {
        PackegeName = packegeName;
    }

    @Override
    public String getMessage() {
        return "Packege : "+PackegeName+" Not Found";
    }
}

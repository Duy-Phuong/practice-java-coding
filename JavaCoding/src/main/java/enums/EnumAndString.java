package enums;

// https://stackoverflow.com/questions/35140408/how-to-get-value-from-a-java-enum
enum Constants {
    YES("Y"), NO("N");
    private String value;

    public String getResponse() {
        return value;
    }

    Constants(String value){
        this.value = value;
    }

    // You can override this method instead of call System.out.println(Constants.YES.getResponse());
    @Override
    public String toString() {
        return value;
    }
}

public class EnumAndString{
    public static void main(String[] args){
        System.out.println(Constants.YES.getResponse());
        System.out.println(Constants.NO.getResponse());
        // Using toString
        System.out.println(Constants.YES);
        System.out.println(Constants.NO);

//        Return Exception
//        Constants constants = Constants.valueOf("sdf");
//        System.out.println(constants);
    }
}
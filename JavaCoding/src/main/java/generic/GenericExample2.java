package generic;

// We use < > to specify Parameter type
class TestGeneric<T, U>
{
    T obj1;  // An object of type T
    U obj2;  // An object of type U

    // constructor
    TestGeneric(T obj1, U obj2)
    {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    // To print objects of T and U
    public void print()
    {
        System.out.println(obj1);
        System.out.println(obj2);
    }
}

public class GenericExample2 {
    public static void main (String[] args)
    {
        TestGeneric<String, Integer> obj =
                new TestGeneric<String, Integer>("GfG", 15);

        obj.print();
    }
}

package Practice;

public class LambdaExp {
     public static void main(String[] args) {
        System.out.println("My system starts..");
        

        // We need to create a interface and a impl mclass to use a functional interface
        MyInter myInter = new MyinterImpl();
        myInter.sayHello();

        // method 2
        // we can sreate an anonymous class for implementing a functional interface without creating a impl class

        MyInter i = new MyInter() {
            // we are making obj of child class of MyInter not obj of the interface
            @Override
            public void sayHello() {
                System.out.println("this is first anonymous class");
                //throw new UnsupportedOperationException("Unimplemented method 'sayHello'");
            }
            
        };

        // Call the method
        i.sayHello();

        // Method 3 
        // use Lambda Expression

        MyInter i2 = () -> {
            System.out.println("This is first time I am using Lambda");
        };

        i2.sayHello();

        //if there is one line only u can remove {} and you can also remove 
        // return statement and Type Reference 
        // java will automatically detect the type of arguments

        SumInterface sumInterface = (a,b) -> a + b;
        

        System.out.println(sumInterface.sum(5, 6));
        System.out.println(sumInterface.sum(10, 12));

        String s = "I am using lambda now";

        LengthInter l = str -> str.length();

        System.out.println("Length of the string is : " + l.getLength(s));

     }
}

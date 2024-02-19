package ExceptionHandling;

class HelloWorld{

     public static void main(String []args){
        int [] array ={1,2,3};
        try
        {
            int i=5/1;
            
        }
        catch (ArithmeticException e)
        {
            System.err.println("error in calculation");
        }
        try
        {
            array[10]=5;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.err.println("error in array");
        }
        
     }
}
package Assignment.Assignment6Exception;
/* Task: Implement a custom exception named InvalidAgeException that gets thrown when a user tries
 to register with an age less than 18. Demonstrate the usage of try, catch, finally, and throw
 statements.
 Steps:
 1. Create a custom exception class named InvalidAgeException.
 2. In the registration process, check if the age is less than 18.
 2. In the registration process, check if the age is less than 18.
 4. Demonstrate the use of try-catch-finally and handle the exception appropriately */

 /*1. Create a custom exception class named InvalidAgeException. */
class  InvalidAgeException extends Exception{
    public InvalidAgeException(String message){
        super(message);
    }
}
/*2. In the registration process, check if the age is less than 18.
*3. In the registration process, check if the age is less than 18.*/
class RegistrationProcess{
    public void registerUser(String name,int age)throws InvalidAgeException{
        if(age<18){
            throw new InvalidAgeException("Age must be 18 or above for registration.");
        }else{
            System.out.println("User" + name+ " registered successsfully");
        }

    }
}

public class ExceptionDemo {
    public static void main(String[] args) {
        RegistrationProcess registrationProcess= new RegistrationProcess();

        try {
            registrationProcess.registerUser("Ajinkya", 23);
        } catch (InvalidAgeException e) {
            // TODO: handle exception
            System.out.println("Refistration failed :" + e.getMessage());
        }
        finally{
        System.out.println("Registration process completed.");
        }

    }
    
}

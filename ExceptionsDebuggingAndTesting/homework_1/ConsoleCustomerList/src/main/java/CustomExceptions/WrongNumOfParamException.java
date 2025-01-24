package CustomExceptions;

public class WrongNumOfParamException extends RuntimeException {

    public WrongNumOfParamException(String msg){
        super(msg);
        
    }
}

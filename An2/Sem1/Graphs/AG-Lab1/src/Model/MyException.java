package Model;
/**
 * 
 * @author mihai
 *
 */
public class MyException extends Exception{

	private static final long serialVersionUID = 1L;

	public MyException(){
		
	}
	
	public MyException(String ex){
		super(ex);
	}
	
}
package response;

public class Response{
	private String message;
	private int value;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return
			"Response{" +
			"message = '" + message + '\'' +
			",value = '" + value + '\'' +
			"}";
		}
}

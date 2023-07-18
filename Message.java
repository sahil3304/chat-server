import java.io.*;

public class Message implements Serializable{
  private String timeStamp;
  private String message;
  public Message(String timeStamp, String message){
  	this.timeStamp = timeStamp;
  	this.message = message;
  }
  
  public String getTimeStamp(){
     return this.timeStamp;
  }
  
  public String getMessage(){
     return this.message;
  }
}

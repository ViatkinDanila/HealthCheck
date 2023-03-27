public class UrlParsingException extends RuntimeException{
    private String message;
    public UrlParsingException(Exception e){
    }
    public UrlParsingException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}

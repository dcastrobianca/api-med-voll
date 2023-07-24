package med.voll.api.infra.exception;

public class AppointmentValidationException extends RuntimeException{
    public AppointmentValidationException(String message){
        super(message);
    }
}

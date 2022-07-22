import java.time.LocalDate;

public class SMS extends Service implements ServiceType{

    public SMS(ServiceType serviceType, LocalDate createdDate, State state) {
        super(serviceType, createdDate, state);
    }
}

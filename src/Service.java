import java.time.LocalDate;
import java.util.Objects;

public class Service {
    private String ID;
    private String serviceType;
    private LocalDate createdDate;
    private State state;

    public Service(String serviceType, LocalDate createdDate, State state) {
        ID=GenerateId.Service.getId();
        this.serviceType = serviceType;
        this.createdDate = createdDate;
        this.state = state;
    }

    public String getID() {
        return ID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public State getState() {
        return state;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return  String.format("%S : %s - %s - %S",ID,serviceType,createdDate,state);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Service){
            Service s=(Service)o;
            return s.getID().equals(ID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

}

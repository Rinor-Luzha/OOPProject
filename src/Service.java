

import java.time.LocalDate;
import java.util.Objects;

public class Service {
    private String ID;
    private ServiceType serviceType;
    /*private ServiceType serviceType=new ServiceType() {
        @Override
        public boolean getSimCard() {
            return true;
        }

        @Override
        public boolean getVoice() {
            return true;
        }
    };*/
    private LocalDate createdDate;
    private State state;

    public Service(ServiceType serviceType,LocalDate createdDate, State state){
        ID=GenerateId.Service.getId();
        this.serviceType=serviceType;

        this.createdDate = createdDate;
        this.state = state;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getID() {
        return ID;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public State getState() {
        return state;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
      //  return  String.format("%S : %s - %s - %S",ID,serviceType,createdDate,state);

        return String.format("Service with ID:'%S', type:%s created on '%s' " +
                "and with state %s",ID,serviceType.getClass().getSimpleName(),createdDate,state);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Service s){
            return s.getID().equals(ID);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }


}

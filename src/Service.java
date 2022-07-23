

import exceptions.ServiceException;

import java.time.LocalDate;
import java.util.Objects;

public class Service {
    private String idNumber;
    private ServiceType serviceType;
    private LocalDate createdDate;
    private State state;

    public Service(ServiceType serviceType,LocalDate createdDate, State state)throws ServiceException {
        idNumber=GenerateId.Service.getId();
        this.serviceType=serviceType;
        if(createdDate.isAfter(LocalDate.now())){
            throw new ServiceException("Data e krijimit per servisin eshte dhene gabimisht!");
        }
        this.createdDate = createdDate;
        this.state = state;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getIdNumber() {
        return idNumber;
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
        return String.format("Service with ID:'%S', type:%s created on '%s' " +
                "and with state %s",idNumber,serviceType.getClass().getSimpleName(),createdDate,state);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Service s){
            return s.getIdNumber().equals(idNumber);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber);
    }


}

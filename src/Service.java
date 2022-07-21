import java.time.LocalDate;
import java.util.Objects;

public class Service {
    private String ID;
    private ServiceType serviceType=new ServiceType() {
        @Override
        public boolean getSimCard() {
            return true;
        }

        @Override
        public boolean getVoice() {
            return true;
        }
    };
    private LocalDate createdDate;
    private State state;

    public Service( LocalDate createdDate, State state) {
        ID=GenerateId.Service.getId();
        this.createdDate = createdDate;
        this.state = state;
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
        return "Service with ID:'" + ID +
                "', created on '" + createdDate +
                "', with state: "+state+(serviceType.getSimCard()?" Has SimCard, ":"")
                +(serviceType.getVoice()?" Has Voice, ":"")+(serviceType.getSMS()?" Has SMS, ":"")+
                (serviceType.getData()?" Has Data, ":"");
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

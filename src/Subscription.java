import java.time.LocalDate;
import java.util.List;

public abstract class Subscription implements ServiceType{

    private String idNumber;
    private String phoneNumber;
    private LocalDate createdDate;
    private State state;

    List<Service> services;

    public Subscription(String phoneNumber, LocalDate createdDate, State state) throws SubscriptionException{
        this.idNumber = GenerateId.Subscription.getId();
        if(!phoneNumber.matches("(\\+3834)(4|5|6)(\\d{5})")){
            throw new SubscriptionException("Phone number is not correct!");
        }
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.state = state;
    }

    public String getId() {
        return idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean getSimCard() {
        return true;
    }

    @Override
    public boolean getVoice() {
        return true;
    }

    public boolean equals(Object o){
        if(o instanceof Subscription s){
            return s.idNumber.equals(idNumber);
        }
        return false;
    }


    @Override
    public String toString() {
        return idNumber+" with phone number '" + phoneNumber +
                "', created on '" + createdDate +
                "', with state: "+state+"\n\t"+(getSimCard()?" Has SimCard\n\t":"")
                +(getVoice()?" Has Voice\n\t":"")+(getSMS()?" Has SMS\n\t":"")+
                (getData()?" Has Data\n\t":"");
    }
}

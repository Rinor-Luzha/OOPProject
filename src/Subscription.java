import java.util.Date;

public class Subscription {

    private long id;
    private String phoneNumber;
    private Date createdDate;
    private State state;


    public Subscription(long id, String phoneNumber, Date createdDate, State state) {
        this.id = id;
        //TODO: Conditons for phone number
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public State getState() {
        return state;
    }



    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdDate=" + createdDate +
                ", state=" + state +
                '}';
    }
}

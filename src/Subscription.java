import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscription {

    private String idNumber;
    private String phoneNumber;
    private LocalDate createdDate;
    private State state;

    private List<Service> services = new ArrayList<>();



    public Subscription(String phoneNumber, LocalDate createdDate, State state) throws SubscriptionException{
        this.idNumber = GenerateId.Subscription.getId();
        if(!phoneNumber.matches("(\\+3834)(4|5|6)(\\d{5})")){
            throw new SubscriptionException("Phone number is not correct!");
        }
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.state = state;
        services.add(new Service(new SMS(),LocalDate.now(),State.ACTIVE));
        services.add(new Service(new Voice(),LocalDate.now(),State.ACTIVE));

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


    public List<Service> getServices() {
        return services;
    }

    public boolean addService(Service s){
        //Mos lejo me i shtu dy service te tnjejtit lloj
        //p.sh dy Data ose dy SMS.
        for (Service s1 : services) {
            if (s1.getServiceType().getClass().equals(s.getServiceType().getClass())) {
                System.out.println("Slejohet kjo "+s);
                return false;
            }
        }
        if(!services.contains(s)) {
            services.add(s);
            return true;
        }
        return false;
    }

    public boolean equals(Object o){
        if(o instanceof Subscription s){
            return s.idNumber.equals(idNumber);
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder(idNumber+" with phone number '" + phoneNumber +
                "', created on '" + createdDate +
                "', with state: "+state+", has the following services: \n");
        for(Service s:services) {
            sb.append("\t").append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}

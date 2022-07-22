import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SubscriptionException, ContactException {
        Customer c1=new Customer(Customer.CustomerType.INDIVIDUAL, LocalDate.now(),State.ACTIVE);
        Customer c2=new Customer(Customer.CustomerType.BUSINESS, LocalDate.now(),State.DEACTIVE);

       Subscription s = new Subscription("+3834491534",LocalDate.now(),State.INACTIVE) {
       };

        Contract c=new Contract(Contract.ContractType.POSTPAID, LocalDate.now(),State.ACTIVE);
        c.writeSubscription(s);
        c1.writeContract(c);
        c1.writeContract(c);
        System.out.println(c1);



    }
}

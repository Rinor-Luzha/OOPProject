import javax.sound.sampled.FloatControl;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SubscriptionException, ContactException {
        Subscription s=new Subscription("+3834512342",LocalDate.now(),State.ACTIVE);
        Subscription s1=new Subscription("+3834512342",LocalDate.now(),State.ACTIVE);

        Customer c1=new Customer(Customer.CustomerType.BUSINESS,LocalDate.now(),State.INACTIVE);


        Contract co=new Contract(Contract.ContractType.POSTPAID,LocalDate.MAX,State.INACTIVE);

        co.writeSubscription(s);
        co.writeSubscription(s1);

        System.out.println(s);
        System.out.println(s1);
        System.out.println(c1);

        c1.writeContract(co);
        c1.writeContract(co);
        System.out.println(c1);




    }
}

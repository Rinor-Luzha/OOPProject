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

        System.out.println(c2);

        Contact co1=new Contact(c1,"abc","abc",'M',LocalDate.now(),LocalDate.now(),State.ACTIVE);
        Contact co2=new Contact(c,LocalDate.now(),State.ACTIVE);
        Contact co3=new Contact(s,LocalDate.now(),State.ACTIVE);
        Contact co4=new Contact(c2,"sda", LocalDate.now(),State.ACTIVE);

        System.out.println(co1);
        System.out.println(co2);
        System.out.println(co3);
        System.out.println(co4);


    }
}

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws SubscriptionException, ContactException, IOException {
        Subscription s=new Subscription("+3834512342",LocalDate.now(),State.ACTIVE);
        Subscription s1=new Subscription("+3834512342",LocalDate.now(),State.ACTIVE);

        Customer c1=new Customer(Customer.CustomerType.BUSINESS,LocalDate.now(),State.INACTIVE);
        Customer c2=new Customer(Customer.CustomerType.INDIVIDUAL,LocalDate.now(),State.ACTIVE);
        Customer c3=new Customer(Customer.CustomerType.BUSINESS,LocalDate.now(),State.INACTIVE);


        Contract co=new Contract(Contract.ContractType.POSTPAID,LocalDate.MAX,State.INACTIVE);





        co.writeSubscription(s);
        co.writeSubscription(s1);

        System.out.println(s);
        System.out.println(s1);
        System.out.println(c1);

        c1.writeContract(co);
        c1.writeContract(co);
        System.out.println(c1);

        ArrayList<Customer> customers=new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        ArrayList<Contract> contracts=new ArrayList<>();
        contracts.add(co);
        ArrayList<Object> subscriptions=new ArrayList<>();
        subscriptions.add(s);
        subscriptions.add(s1);


        TelecomServiceImplementation t1=new TelecomServiceImplementation();


        t1.create(customers,"C:\\Users\\Lenovo\\Desktop\\OOPProject\\out\\production\\OOPProject\\");

        t1.create(contracts,"C:\\Users\\Lenovo\\Desktop\\OOPProject\\out\\production\\OOPProject\\");
        t1.create(subscriptions,"C:\\Users\\Lenovo\\Desktop\\OOPProject\\out\\production\\OOPProject\\");


    }
}

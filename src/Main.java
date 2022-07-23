import exceptions.ContactException;
import exceptions.ProductException;
import exceptions.SubscriptionException;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws SubscriptionException, ContactException, IOException, ProductException {
//        Subscription s=new Subscription("+3834512342",LocalDate.now(),State.ACTIVE);
//        Subscription s1=new Subscription("+3834512342",LocalDate.now(),State.ACTIVE);
//
//        Customer c1=new Customer(Customer.CustomerType.BUSINESS,LocalDate.now(),State.INACTIVE);
//        Customer c2=new Customer(Customer.CustomerType.INDIVIDUAL,LocalDate.now(),State.ACTIVE);
//        Customer c3=new Customer(Customer.CustomerType.BUSINESS,LocalDate.now(),State.INACTIVE);
//
//
//        Contract co=new Contract(Contract.ContractType.POSTPAID,LocalDate.MAX,State.INACTIVE);
//
//        Product p1=new Product("voice",24.2,LocalDate.parse("2021-12-03"),LocalDate.parse("2022-12-03"));
//        p1.addServiceType(new Voice());
//
//
//        s.purchaseProduct(p1);
//
//        co.writeSubscription(s);
//        co.writeSubscription(s1);
//
//        System.out.println(s);
//        System.out.println(s1);
//        System.out.println(c1);
//
//        c1.writeContract(co);
//        c1.writeContract(co);
//        System.out.println(c1);
//
//        ArrayList<Customer> customers=new ArrayList<>();
//        customers.add(c1);
//        customers.add(c2);
//        customers.add(c3);
//
//        ArrayList<Contract> contracts=new ArrayList<>();
//        contracts.add(co);
//        ArrayList<Object> subscriptions=new ArrayList<>();
//        subscriptions.add(s);
//        subscriptions.add(s1);
//
//


        TelecomServiceImplementation t1=new TelecomServiceImplementation("C:\\Users\\rinor\\OneDrive\\Desktop\\");

//
//        t1.create(customers);
//
//        t1.create(contracts);
//        t1.create(subscriptions);

//        System.out.println(t1.findByID("CUST_1"));
//        t1.delete("CUST_1");
//        System.out.println(t1.findByID("CUST_1"));
//
//        System.out.println(t1.findAll("CusTomer"));


    }
}

package main;

import exceptions.ContactException;
import exceptions.ProductException;
import exceptions.SubscriptionException;
import telecomService.TelecomServiceImplementation;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws SubscriptionException, ContactException, IOException, ProductException {
//        main.Subscription s=new main.Subscription("+3834512342",LocalDate.now(),main.State.ACTIVE);
//        main.Subscription s1=new main.Subscription("+3834512342",LocalDate.now(),main.State.ACTIVE);
//
//        main.Customer c1=new main.Customer(main.Customer.CustomerType.BUSINESS,LocalDate.now(),main.State.INACTIVE);
//        main.Customer c2=new main.Customer(main.Customer.CustomerType.INDIVIDUAL,LocalDate.now(),main.State.ACTIVE);
//        main.Customer c3=new main.Customer(main.Customer.CustomerType.BUSINESS,LocalDate.now(),main.State.INACTIVE);
//
//
//        main.Contract co=new main.Contract(main.Contract.ContractType.POSTPAID,LocalDate.MAX,main.State.INACTIVE);
//
//        main.Product p1=new main.Product("voice",24.2,LocalDate.parse("2021-12-03"),LocalDate.parse("2022-12-03"));
//        p1.addServiceType(new main.ServiceTypes.Voice());
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
//        ArrayList<main.Customer> customers=new ArrayList<>();
//        customers.add(c1);
//        customers.add(c2);
//        customers.add(c3);
//
//        ArrayList<main.Contract> contracts=new ArrayList<>();
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

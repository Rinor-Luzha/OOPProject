package main;

import exceptions.*;
import main.ServiceTypes.SMS;
import main.ServiceTypes.Voice;
import telecomService.TelecomServiceImplementation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SubscriptionException, ContactException, IOException, ProductException, ServiceException, CustomerException, ContractException {
//        main.Subscription s=new main.Subscription("+3834512342", LocalDate.parse("2021-10-02"),main.State.ACTIVE);
//        main.Subscription s1=new main.Subscription("+3834512342",LocalDate.parse("2021-10-02"),main.State.ACTIVE);
//
//        main.Customer c1=new main.Customer(main.Customer.CustomerType.BUSINESS,LocalDate.parse("2021-10-02"),main.State.INACTIVE);
//        main.Customer c2=new main.Customer(main.Customer.CustomerType.INDIVIDUAL,LocalDate.parse("2021-10-02"),main.State.ACTIVE);
//        main.Customer c3=new main.Customer(main.Customer.CustomerType.BUSINESS,LocalDate.parse("2021-10-02"),main.State.INACTIVE);
//
//
//        main.Contract co=new main.Contract(main.Contract.ContractType.POSTPAID,LocalDate.parse("2021-10-02"),main.State.INACTIVE);
//
//        main.Product p1=new main.Product("voice",24.2,LocalDate.parse("2021-12-03"),LocalDate.parse("2022-12-03"));
//        p1.addServiceType(new main.ServiceTypes.Voice());
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
//        ArrayList<main.Contract> contracts=new ArrayList<>();
//        contracts.add(co);
//        ArrayList<Object> subscriptions=new ArrayList<>();
//        subscriptions.add(s);
//        subscriptions.add(s1);
//
//
//        c1.purchaseProduct(p1);
//        Product p2=new Product("spo di",21.3,LocalDate.parse("2021-10-02"),LocalDate.parse("2022-10-02"));
//        p2.addServiceType(new Voice());
//        p2.addServiceType(new SMS());
//        c1.purchaseProduct(p2);
//        Product p3=new Product("spo di",21.3,LocalDate.parse("2021-10-02"),LocalDate.parse("2022-10-02"));
//        c1.purchaseProduct(p3);
//        c2.purchaseProduct(p3);
//        System.out.println(c1);
//        System.out.println(c2);
//
//        TelecomServiceImplementation t1=new TelecomServiceImplementation("C:\\Users\\rinor\\OneDrive\\Desktop\\");
//
//
//        t1.create(customers);
//
//        t1.create(contracts);
//        t1.create(subscriptions);




    }
}

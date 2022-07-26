package main;

import exceptions.*;
import main.ServiceTypes.*;
import telecomService.TelecomServiceImplementation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class Main {


    public static void main(String[] args) throws SubscriptionException, ContactException, IOException, ProductException, ServiceException, CustomerException, ContractException {
        main.Subscription s=new main.Subscription("+3834512342", LocalDate.parse("2021-10-02"),main.State.ACTIVE);
//        main.Subscription s1=new main.Subscription("+3834512342",LocalDate.parse("2021-10-02"),main.State.ACTIVE);
//
        main.Customer c1=new main.Customer(Customer.CustomerType.INDIVIDUAL,LocalDate.parse("2021-10-02"),main.State.INACTIVE);
//        main.Customer c2=new main.Customer(main.Customer.CustomerType.INDIVIDUAL,LocalDate.parse("2021-10-02"),main.State.ACTIVE);
//        main.Customer c3=new main.Customer(main.Customer.CustomerType.BUSINESS,LocalDate.parse("2021-10-02"),main.State.INACTIVE);
//
//
//        main.Contract co=new main.Contract(main.Contract.ContractType.POSTPAID,LocalDate.parse("2021-10-02"),main.State.INACTIVE);
//
//        Product p1=new Product("P1",24.2,LocalDate.parse("2021-12-03"),LocalDate.parse("2022-07-26"));
//        p1.addServiceType(new main.ServiceTypes.Voice());
//
//
//        co.writeSubscription(s);
//        co.writeSubscription(s1);
//
//        System.out.println(s);
//        System.out.println(s1);
//        System.out.println(c1);
//
//        c1.writeContract(co);
//
//        System.out.println(c1);
//        Product p2=new Product("P2",4,LocalDate.parse("2021-10-02"),LocalDate.parse("2022-08-03"));
//        Product p3=new Product("P3",21.3,LocalDate.parse("2021-10-02"),LocalDate.parse("2022-08-04"));
//        p2.addServiceType(new Voice());
//        p2.addServiceType(new SMS());
//        p3.addServiceType( new SMS());
//        p3.addServiceType(new Voice());
//        Contract contract2=new Contract(Contract.ContractType.POSTPAID,LocalDate.now(),State.ACTIVE);
//        Contract contract3=new Contract(Contract.ContractType.PREPAID,LocalDate.now(),State.ACTIVE);
//        c2.writeContract(contract2);
//        c3.writeContract(contract3);
//        Subscription s2=new Subscription("+3834512345",LocalDate.now(),State.INACTIVE);
//        Subscription s3=new Subscription("+3834512345",LocalDate.now(),State.INACTIVE);
//        contract2.writeSubscription(s2);
//        contract3.writeSubscription(s3);
//
//
//
//
//        ArrayList<Customer> customers=new ArrayList<>();
//        customers.add(c1);
//        customers.add(c2);
//        customers.add(c3);
//
//        ArrayList<main.Contract> contracts=new ArrayList<>();
//        contracts.add(co);
//        contracts.add(contract2);
//        contracts.add(contract3);
//
//        ArrayList<Object> subscriptions=new ArrayList<>();
//        subscriptions.add(s);
//        subscriptions.add(s1);
//        subscriptions.add(s2);
//        subscriptions.add(s3);
//
//        System.out.println("----------------");
//        System.out.println(c1.purchaseProduct(p1));
//        System.out.println(c1.purchaseProduct(p2));
//        System.out.println(c2.purchaseProduct(p1));
//        System.out.println(c2.purchaseProduct(p2));
//        System.out.println(c2.purchaseProduct(p3));
//        System.out.println("------------------");
//
//
//        System.out.println(c1);
//        System.out.println(c2);

        TelecomServiceImplementation t1=new TelecomServiceImplementation("C:\\Users\\rinor\\OneDrive\\Desktop\\");
//        Customer c, String name, String lastName, char gender, LocalDate dateOfBirth, LocalDate createdDate, State state) th

//        t1.create(customers);
//
//        t1.create(contracts);
//        t1.create(subscriptions);

//        t1.cheaperThanFive();
//        t1.specificProduct("PROD_2");
//        t1.expiresInNextTen();
    }

}

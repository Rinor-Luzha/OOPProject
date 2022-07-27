package main;


import exceptions.*;
import main.Contract.ContractType;
import main.ServiceTypes.*;
import telecomService.TelecomServiceImplementation;


import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;




public class Main {


  public static void main(String[] args) {
    try {
      Subscription subscription1 = new Subscription("+3834512345", LocalDate.parse("2021-10-02"), main.State.ACTIVE);
      Subscription subscription2 = new Subscription("+3834554321", LocalDate.parse("2022-03-20"), main.State.ACTIVE);
      Subscription subscription3 = new Subscription("+3834554123", LocalDate.parse("2022-03-20"), main.State.ACTIVE);
      Subscription subscription4 = new Subscription("+3834512121", LocalDate.parse("2022-03-22"), main.State.ACTIVE);
      Subscription subscription5 = new Subscription("+3834512456", LocalDate.parse("2022-03-23"), main.State.ACTIVE);

      Customer customer1 = new Customer(Customer.CustomerType.INDIVIDUAL, LocalDate.parse("2022-04-15"), State.ACTIVE);
      Customer customer2 = new Customer(Customer.CustomerType.INDIVIDUAL, LocalDate.parse("2021-05-02"), main.State.ACTIVE);
      Customer customer3 = new Customer(Customer.CustomerType.BUSINESS, LocalDate.parse("2021-06-02"), main.State.ACTIVE);
      Customer customer4 = new Customer(Customer.CustomerType.INDIVIDUAL, LocalDate.parse("2021-06-05"), main.State.ACTIVE);
      Customer customer5 = new Customer(Customer.CustomerType.BUSINESS, LocalDate.parse("2021-06-07"), main.State.ACTIVE);

      Contract contract1 = new main.Contract(ContractType.POSTPAID, LocalDate.parse("2021-07-03"), main.State.ACTIVE);
      Contract contract2 = new Contract(ContractType.POSTPAID, LocalDate.parse("2022-01-01"), State.ACTIVE);
      Contract contract3 = new Contract(ContractType.PREPAID, LocalDate.parse("2022-02-02"), State.ACTIVE);
      Contract contract4 = new Contract(ContractType.POSTPAID, LocalDate.parse("2022-03-02"), State.ACTIVE);
      Contract contract5 = new Contract(ContractType.PREPAID, LocalDate.parse("2022-03-03"), State.ACTIVE);

      Product product1 = new Product("Product1", 13.2, LocalDate.parse("2022-07-03"), LocalDate.parse("2022-07-31"));
      Product product2 = new Product("Product2", 4, LocalDate.parse("2022-07-04"), LocalDate.parse("2022-08-03"));
      Product product3 = new Product("Product3", 21.3, LocalDate.parse("2022-07-04"), LocalDate.parse("2022-08-04"));
      Product product4 = new Product("Product4", 11.3, LocalDate.parse("2022-07-04"), LocalDate.parse("2022-08-04"));
      Product product5 = new Product("Product5", 2.3, LocalDate.parse("2022-07-10"), LocalDate.parse("2022-08-04"));

      subscription1.addService(new Service(new SimCard(),LocalDate.parse("2022-07-03"),State.ACTIVE));
      subscription2.addService(new Service(new SimCard(),LocalDate.parse("2022-06-15"),State.ACTIVE));
      subscription3.addService(new Service(new Data(),LocalDate.parse("2022-02-11"),State.INACTIVE));
      subscription4.addService(new Service(new Data(),LocalDate.parse("2021-05-18"),State.INACTIVE));


      product1.addServiceType(new Voice());
      product1.addServiceType(new SMS());
      product2.addServiceType(new Voice());
      product2.addServiceType(new SMS());
      product2.addServiceType(new SimCard());
      product3.addServiceType(new Voice());
      product3.addServiceType(new SMS());
      product4.addServiceType(new Voice());
      product4.addServiceType(new SMS());
      product4.addServiceType(new Data());
      product5.addServiceType(new Voice());
      product5.addServiceType(new SMS());

      contract1.writeSubscription(subscription1);
      contract2.writeSubscription(subscription2);
      contract3.writeSubscription(subscription3);
      contract4.writeSubscription(subscription4);
      contract5.writeSubscription(subscription5);

      customer1.writeContract(contract1);
      customer2.writeContract(contract2);
      customer3.writeContract(contract3);
      customer4.writeContract(contract4);
      customer5.writeContract(contract5);


      customer1.purchaseProduct(product1);
      customer1.purchaseProduct(product2);
      customer2.purchaseProduct(product1);
      customer2.purchaseProduct(product2);
      customer2.purchaseProduct(product3);
      customer3.purchaseProduct(product4);
      customer3.purchaseProduct(product5);
      customer4.purchaseProduct(product5);


      ArrayList<Customer> customers = new ArrayList<>();
      customers.add(customer1);
      customers.add(customer2);
      customers.add(customer3);
      customers.add(customer4);
      customers.add(customer5);

      ArrayList<main.Contract> contracts = new ArrayList<>();
      contracts.add(contract1);
      contracts.add(contract2);
      contracts.add(contract3);
      contracts.add(contract4);
      contracts.add(contract5);

      ArrayList<Object> subscriptions = new ArrayList<>();
      subscriptions.add(subscription1);
      subscriptions.add(subscription2);
      subscriptions.add(subscription3);
      subscriptions.add(subscription4);
      subscriptions.add(subscription5);


      TelecomServiceImplementation t1 = new TelecomServiceImplementation("C:\\Users\\rinor\\OneDrive\\Desktop\\");

      t1.create(customers);

      t1.create(contracts);
      t1.create(subscriptions);

//            t1.cheaperThanFive();
//            t1.specificProduct("PROD_5");
//            t1.expiresInNextTen();
//
      t1.listByCostumerType(Customer.CustomerType.INDIVIDUAL.toString());

    } catch (SubscriptionException | ServiceException | CustomerException | ContractException | ProductException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

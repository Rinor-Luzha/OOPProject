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
import java.util.List;
import java.util.stream.Collectors;



public class Main {


    public static void main(String[] args) throws SubscriptionException, ContactException, IOException, ProductException, ServiceException, CustomerException, ContractException {
        main.Subscription s=new main.Subscription("+3834512342", LocalDate.parse("2021-10-02"),main.State.ACTIVE);
        main.Subscription s1=new main.Subscription("+3834512342",LocalDate.parse("2021-10-02"),main.State.ACTIVE);

        main.Customer c1=new main.Customer(main.Customer.CustomerType.BUSINESS,LocalDate.parse("2021-10-02"),main.State.INACTIVE);
        main.Customer c2=new main.Customer(main.Customer.CustomerType.INDIVIDUAL,LocalDate.parse("2021-10-02"),main.State.ACTIVE);
        main.Customer c3=new main.Customer(main.Customer.CustomerType.BUSINESS,LocalDate.parse("2021-10-02"),main.State.INACTIVE);


        main.Contract co=new main.Contract(main.Contract.ContractType.POSTPAID,LocalDate.parse("2021-10-02"),main.State.INACTIVE);

        Product p1=new Product("P1",24.2,LocalDate.parse("2021-12-03"),LocalDate.parse("2022-12-03"));
        p1.addServiceType(new main.ServiceTypes.Voice());


        co.writeSubscription(s);
        co.writeSubscription(s1);

        System.out.println(s);
        System.out.println(s1);
        System.out.println(c1);

        c1.writeContract(co);

        System.out.println(c1);
        Product p2=new Product("P2",4,LocalDate.parse("2021-10-02"),LocalDate.parse("2022-10-02"));
        Product p3=new Product("P3",21.3,LocalDate.parse("2021-10-02"),LocalDate.parse("2022-10-02"));
        p2.addServiceType(new Voice());
        p2.addServiceType(new SMS());
        p3.addServiceType( new SMS());
        p3.addServiceType(new Voice());
        Contract contract2=new Contract(Contract.ContractType.POSTPAID,LocalDate.now(),State.ACTIVE);
        Contract contract3=new Contract(Contract.ContractType.PREPAID,LocalDate.now(),State.ACTIVE);
        c2.writeContract(contract2);
        c3.writeContract(contract3);

        contract2.writeSubscription(new Subscription("+3834512345",LocalDate.now(),State.INACTIVE));
        contract3.writeSubscription(new Subscription("+3834512345",LocalDate.now(),State.INACTIVE));




        ArrayList<Customer> customers=new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        ArrayList<main.Contract> contracts=new ArrayList<>();
        contracts.add(co);
        ArrayList<Object> subscriptions=new ArrayList<>();
        subscriptions.add(s);
        subscriptions.add(s1);

        System.out.println("----------------");
        System.out.println(c1.purchaseProduct(p1));
        System.out.println(c1.purchaseProduct(p2));
        System.out.println(c2.purchaseProduct(p1));
        System.out.println(c2.purchaseProduct(p2));
        System.out.println(c2.purchaseProduct(p3));
        System.out.println("------------------");


        System.out.println(c1);
        System.out.println(c2);

        TelecomServiceImplementation t1=new TelecomServiceImplementation("C:\\Users\\Lenovo\\Desktop\\OOPProject\\out\\production\\OOPProject\\");


        t1.create(customers);

        t1.create(contracts);
        t1.create(subscriptions);

        cheaperThanFive();

        t1.specificProduct("PROD_3");


    }

    //Metodat per filters

    public static void cheaperThanFive() throws IOException {
        File file=new File("C:\\Users\\Lenovo\\Desktop\\OOPProject\\out\\production\\OOPProject\\Product.txt");

        List<Product> records= Files.readAllLines(file.toPath()).stream()
                .map(row->row.split(","))
                .filter(array->array.length==6)
                .filter(array->Double.parseDouble(array[2])<5)
                .map(array-> {
                    try {
                        return toProduct(array);
                    } catch (ProductException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        
        records.forEach(System.out::println);

    }







    private static Product toProduct(String[] tokens)throws ProductException{
        String idNumber=tokens[0];
        String name=tokens[1];
        double price=Double.parseDouble(tokens[2]);
        LocalDate fromDateTime=LocalDate.parse(tokens[3]);
        LocalDate toDateTime=LocalDate.parse(tokens[4]);
        List<ServiceType> serviceTypes=new ArrayList<ServiceType>();
        String servicesString=tokens[5].substring(1, tokens[5].length()-1);
        String[] services=servicesString.split(";");
        for(String service:services){
            switch (service){
                case "Data"->serviceTypes.add(new Data());
                case "SimCard"->serviceTypes.add(new SimCard());
                case "SMS"->serviceTypes.add(new SMS());
                case "Voice"->serviceTypes.add(new Voice());
            }
        }
        return Product.queryFileProduct(idNumber,name,price,fromDateTime,toDateTime,serviceTypes);
    }

}

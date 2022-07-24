package main;

import exceptions.ServiceException;
import exceptions.SubscriptionException;
import main.ServiceTypes.SMS;
import main.ServiceTypes.ServiceType;
import main.ServiceTypes.Voice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Subscription {

    private String idNumber;
    private String phoneNumber;
    private LocalDate createdDate;
    private State state;

    private List<Service> services;
    private List<Product> products;

    public Subscription(String phoneNumber, LocalDate createdDate, State state) throws SubscriptionException, ServiceException {
        this.idNumber = GenerateId.Subscription.getId();
        if(!phoneNumber.matches("(\\+3834)(4|5|6)(\\d{5})")){
            throw new SubscriptionException("Phone number is not correct!");
        }
        this.phoneNumber = phoneNumber;
        if(createdDate.isAfter(LocalDate.now())){
            throw new SubscriptionException("main.ServiceTypes.Data e krijimit per subscription eshte dhene gabimisht!");
        }
        this.createdDate = createdDate;
        this.state = state;

        this.services=new ArrayList<>();
        this.products=new ArrayList<>();

        services.add(new Service(new SMS(),LocalDate.now(),State.ACTIVE));
        services.add(new Service(new Voice(),LocalDate.now(),State.ACTIVE));
    }

    private Subscription(String idNumber, String phoneNumber, LocalDate createdDate, State state, List<Service> services, List<Product> products) {
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.state = state;
        this.services = services;
        this.products = products;
    }
    public static Subscription querySubscriptionFile(String idNumber, String phoneNumber, LocalDate createdDate, State state) throws ServiceException {
        List<Service> services=new ArrayList<>();
        services.add(new Service(new SMS(),LocalDate.now(),State.ACTIVE));
        services.add(new Service(new Voice(),LocalDate.now(),State.ACTIVE));
        return new Subscription(idNumber,phoneNumber,createdDate,state,services,new ArrayList<>());

    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public State getState() {
        return state;
    }


    public boolean addService(Service s){
        for (Service s1 : services) {
            if (s1.getServiceType().getClass().equals(s.getServiceType().getClass())) {
                return false;
            }
        }
        if(!services.contains(s)) {
            services.add(s);
            return true;
        }
        return false;
    }

    public String getProducts() {
        if (products.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for (Product p : products) {
            sb.append(p.getIdNumber()).append(";");
        }
        return  sb.substring(0,sb.length()-1)+"}";
    }

    public String getServices() {
        if (services.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for (Service s : services) {
            sb.append(s.getIdNumber()).append(";");
        }
        return  sb.substring(0,sb.length()-1)+"}";
    }

    public boolean purchaseProduct(Product p){
        if(!LocalDate.now().isAfter(p.getFromDateTime()))
            return false;
        if(!LocalDate.now().isBefore(p.getToDateTime()))
            return false;
        for(ServiceType s:p.getServiceTypes()){
            boolean hasServiceType=false;
            for(Service s1:services){
                if(s1.getServiceType().getClass().equals(s.getClass())) {
                    hasServiceType = true;
                    break;
                }
            }
            if(!hasServiceType){
                return false;
            }
        }
        products.add(p);
        return true;
    }

    public boolean equals(Object o){
        if(o instanceof Subscription s){
            return s.idNumber.equals(idNumber);
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder(idNumber+" with phone number '" + phoneNumber +
                "', created on '" + createdDate +
                "', with state: "+state+", has the following services: \n");
        for(Service s:services) {
            sb.append("\t").append(s);
            sb.append("\n");
        }
        sb.append("And the following products:\n");
        for(Product p:products) {
            sb.append("\t").append(p);
            sb.append("\n");
        }
        return sb.toString();
    }
}

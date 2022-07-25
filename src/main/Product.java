package main;

import exceptions.ProductException;
import main.ServiceTypes.ServiceType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {

    private String idNumber;
    private String name;
    private double price;
    private List<ServiceType> serviceTypes;
    private LocalDate fromDateTime;
    private LocalDate toDateTime;

    public Product(String name, double price, LocalDate fromDateTime, LocalDate toDateTime)throws ProductException {
        if (StringUtils.nullOrEmpty(name)) {
            throw new ProductException("Emri produktit eshte i zbrazet!");
        }
        if (price < 0) {
            throw new ProductException("Qmimi i produktit me i vogel se 0!");
        }

        if (fromDateTime.isAfter(toDateTime)) {
            throw new ProductException("Afati i dhene eshte i skaduar per produktin!");
        }
        this.idNumber=GenerateId.Product.getId();
        this.name = name;
        this.price = price;
        this.serviceTypes = new ArrayList<>();
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    private Product(String idNumber, String name, double price, LocalDate fromDateTime, LocalDate toDateTime,List<ServiceType> serviceTypes) {
        this.idNumber = idNumber;
        this.name = name;
        this.price = price;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.serviceTypes=serviceTypes;
    }
    public static Product queryFileProduct(String idNumber, String name, double price, LocalDate fromDateTime, LocalDate toDateTime, List<ServiceType> serviceTypes){
        return new Product(idNumber,name,price,fromDateTime,toDateTime,serviceTypes);
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public List<ServiceType> getServiceTypeList() {
        return serviceTypes;
    }

    public LocalDate getFromDateTime() {
        return fromDateTime;
    }

    public LocalDate getToDateTime() {
        return toDateTime;
    }
    public boolean addServiceType(ServiceType s){
        for (ServiceType s1 : serviceTypes) {
            if (s1.getClass().equals(s.getClass())) {
                return false;
            }
        }
        if(!serviceTypes.contains(s)) {
            serviceTypes.add(s);
            return true;
        }
        return false;
    }
    public String getServiceTypes(){
        if(serviceTypes.size()==0){
            return null;
        }
        StringBuilder sb=new StringBuilder("{");
        for(ServiceType s:serviceTypes){
            sb.append(s.getClass().getSimpleName()).append(";");
        }
        return  sb.substring(0,sb.length()-1)+"}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber);
    }

    public boolean equals(Object o){
        if(o instanceof Product p){
            return p.idNumber.equals(idNumber);
        }
        return false;
    }

    public String toString(){
        StringBuilder sb=new StringBuilder("Product "+name+", has price: "+price+
                ", is available from '"+fromDateTime+
                "' until '"+toDateTime +"', and has the following service types:\n\t\t");
        for(ServiceType s:serviceTypes) {
            sb.append(s.getClass().getSimpleName());
            sb.append(", ");
        }
        return sb.substring(0,sb.length()-2)+".";
    }
}

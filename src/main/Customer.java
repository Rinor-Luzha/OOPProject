package main;

import exceptions.CustomerException;
import main.ServiceTypes.ServiceType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String idNumber;
    public enum CustomerType{INDIVIDUAL,BUSINESS};
    private CustomerType customerType;
    private LocalDate createdDate;
    private State state;
    private List<Contract> contracts;
    private List<Product> products;
    public Customer(CustomerType customerType,LocalDate createdDate,State state) throws CustomerException {
        if(createdDate.isAfter(LocalDate.now())){
            throw new CustomerException("Data e krijimit per konsumatorin eshte dhene gabimisht!");
        }
        idNumber=GenerateId.Customer.getId();
        this.customerType=customerType;
        this.createdDate=createdDate;
        this.state=state;
        products=new ArrayList<>();
        contracts=new ArrayList<Contract>();//data structure can be changed with any other type of list
    }

    private Customer(String idNumber, CustomerType customerType, LocalDate createdDate, State state, List<Contract> contracts,List<Product> products) {
        this.idNumber = idNumber;
        this.customerType = customerType;
        this.createdDate = createdDate;
        this.state = state;
        this.contracts = contracts;
        this.products=products;
    }
    public static Customer queryCustomerFile(String idNumber, CustomerType customerType, LocalDate createdDate, State state, List<Contract> contracts,List<Product> products){
        return new Customer(idNumber, customerType, createdDate, state, contracts,products);
    }


    public String getIdNumber() {
        return idNumber;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public LocalDate getCreatedDate(){
        return createdDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Product> getProductList(){
        return products;
    }


    public boolean writeContract(Contract c){
        if(!contracts.contains(c)){
            contracts.add(c);
            return true;
        }
        return false;
    }

    public String getContracts() {
        if (contracts.size()==0){
            return null;
        }
        StringBuilder sb=new StringBuilder("{");
        for (Contract c:contracts) {
            sb.append(c.getIdNumber()).append(";");
        }


        return  sb.substring(0,sb.length()-1)+"}";
    }
    public boolean purchaseProduct(Product p){
        if(p.getServiceTypeList().size()==0){
            return false;
        }
        if(!LocalDate.now().isAfter(p.getFromDateTime()))
            return false;
        if(!LocalDate.now().isBefore(p.getToDateTime()))
            return false;
        ArrayList<String> customerServices=new ArrayList<>();
        for(Contract contract:contracts){
            for(Subscription subscription:contract.getSubscriptionList()){
                for(Service service:subscription.getServiceList()){
                    customerServices.add(service.getServiceType().getClass().getSimpleName());
                }
            }
        }
        for(ServiceType s: p.getServiceTypeList()){
            String serviceType=s.getClass().getSimpleName();
            if(!customerServices.contains(serviceType)){
                return false;
            }
        }
        products.add(p);
        return true;
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

    public boolean equals(Object o){
        if(o instanceof Customer c){
            return c.idNumber.equals(idNumber);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder(idNumber+" of type: " + customerType +
                ", with " + state +
                " state, has the following contracts:\n");
        for(Contract c:contracts){
            sb.append("\t").append(c);
            sb.append("\n");
        }
        sb.append("And the following products:\n");
        for(Product p:products) {
            sb.append("\t").append(p);
            sb.append("\n");
        }
        System.out.println("---------");
        return sb.toString();

    }
}

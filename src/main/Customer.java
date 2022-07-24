package main;

import exceptions.CustomerException;

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
    public Customer(CustomerType customerType,LocalDate createdDate,State state) throws CustomerException {
        if(createdDate.isAfter(LocalDate.now())){
            throw new CustomerException("main.ServiceTypes.Data e krijimit per konsumatorin eshte dhene gabimisht!");
        }
        idNumber=GenerateId.Customer.getId();
        this.customerType=customerType;
        this.createdDate=createdDate;
        this.state=state;
        contracts=new ArrayList<Contract>();//data structure can be changed with any other type of list
    }

    private Customer(String idNumber, CustomerType customerType, LocalDate createdDate, State state, List<Contract> contracts) {
        this.idNumber = idNumber;
        this.customerType = customerType;
        this.createdDate = createdDate;
        this.state = state;
        this.contracts = contracts;
    }
    public static Customer queryCustomerFile(String idNumber, CustomerType customerType, LocalDate createdDate, State state, List<Contract> contracts){
        return new Customer(idNumber, customerType, createdDate, state, contracts);
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
        return sb.toString();
    }
}

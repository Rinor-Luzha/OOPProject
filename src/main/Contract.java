package main;

import exceptions.ContractException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    public enum ContractType {PREPAID, POSTPAID};
    private String idNumber;
    private LocalDate createdDate;
    private State state;
    private ContractType contractType;
    private List<Subscription> subscriptions;

    public Contract(ContractType contractType, LocalDate createdDate, State state) throws ContractException {
        idNumber = GenerateId.Contract.getId();
        this.contractType = contractType;
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContractException("Data e krijimit per kontraten eshte dhene gabimisht!");
        }
        this.createdDate=createdDate;
        this.state = state;
        subscriptions = new ArrayList<>();
    }

    private Contract(String idNumber, LocalDate createdDate, State state, ContractType contractType, List<Subscription> subscriptions) {
        this.idNumber = idNumber;
        this.createdDate = createdDate;
        this.state = state;
        this.contractType = contractType;
        this.subscriptions = subscriptions;
    }

    public static Contract queryContractFile(String idNumber, LocalDate createdDate, State state, ContractType contractType, List<Subscription> subscriptions){
        return new Contract(idNumber, createdDate, state, contractType, subscriptions);
    }

    public String getIdNumber() {
        return idNumber;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public State getState() {
        return state;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean writeSubscription(Subscription s){
        if(!subscriptions.contains(s)){
            subscriptions.add(s);
            return true;
        }
        return false;
    }
    public List<Subscription> getSubscriptionList(){
        return subscriptions;
    }
    public String getSubscriptions() {

        StringBuilder sb=new StringBuilder("{");
        for (Subscription s:subscriptions) {
            sb.append(s.getIdNumber()).append(";");
        }
        return  sb.substring(0,sb.length()-1)+"}";
    }

    public boolean equals(Object o){
        if(o instanceof Contract c){
            return c.idNumber.equals(idNumber);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(idNumber + " of type: " + contractType +
                ", was created on " + createdDate + ", with " + state +
                " state, has the following subscriptions:\n");
        for(Subscription s:subscriptions) {
            sb.append("\t").append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}

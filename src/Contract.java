import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    enum ContractType {PREAPID, POSTPAID}

    ;
    private String idNumber;
    private LocalDate createdDate;
    private State state;
    private ContractType contractType;
    List<Subscription> subscriptions;


    public Contract(ContractType contractType, State state) {
        idNumber = GenerateId.Contract.getId();
        this.contractType = contractType;
        this.state = state;
        subscriptions = new ArrayList<Subscription>();
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

    public boolean writeSubscription(Subscription s){
        if(!subscriptions.contains(s)){
            subscriptions.add(s);
            return true;
        }
        return false;
    }

    public boolean equals(Object o){
        if(o instanceof Contract c){
            return c.idNumber==idNumber;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(idNumber + " of type: " + contractType +
                ", was created on " + createdDate + ", with " + state +
                " state, has the following subsciptions: ");
        for(Subscription s:subscriptions) {
            sb.append("\t").append(s);
        }
        return sb.toString();
    }


}

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    enum ContractType {PREPAID, POSTPAID};
    private String idNumber;
    private LocalDate createdDate;
    private State state;
    private ContractType contractType;
    private List<Subscription> subscriptions;

    public String getSubscriptions() {

            StringBuilder sb=new StringBuilder("{");
                for (Subscription s:subscriptions) {
                    sb.append(s.getIdNumber()).append(",");
                }


        return  sb.substring(0,sb.length()-1)+"}";
    }

    public Contract(ContractType contractType, LocalDate createdDate, State state) {
        idNumber = GenerateId.Contract.getId();
        this.contractType = contractType;
        this.createdDate=createdDate;
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

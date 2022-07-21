import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String idNumber;
    enum CustomerType{INDIVIDUAL,BUSINESS};
    private CustomerType customerType;
    private State state;
    private List<Contract> contracts;

    public Customer(CustomerType customerType,State state){
        idNumber=GenerateId.Customer.getId();
        this.customerType=customerType;
        this.state=state;
        contracts=new ArrayList<Contract>();          //data structure can be changed with any other type of list
    }

    public String getIdNumber() {
        return idNumber;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public State getState() {
        return state;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public boolean writeContract(Contract c){
        if(!contracts.contains(c)){
            contracts.add(c);
            return true;
        }
        return false;
    }

    public boolean equals(Object o){
        if(o instanceof Customer ){
            Customer c=(Customer) o;
            return c.idNumber.equals(idNumber);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder(idNumber+" of type: " + customerType +
                ", with " + state +
                " state, has the following contracts:");
        for(Contract c:contracts){
            sb.append("\t").append(c);
        }
        return sb.toString();
    }
}

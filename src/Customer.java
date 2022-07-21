import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static long counter=1; //unique for every instance
    private long idNumber;
    enum CustomerType{INDIVIDUAL,BUSINESS};
    private CustomerType customerType;
    private State state;
    private List<Contract> contracts;

    public Customer(CustomerType customerType,State state){
        idNumber=counter++;
        this.customerType=customerType;
        this.state=state;
        contracts=new ArrayList<Contract>();//data structure can be changed with any other type of list
    }

    public long getIdNumber() {
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

    public boolean equals(Object o){
        if(o instanceof Customer c){
            return c.idNumber==idNumber;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("Customer of ID: "+idNumber+" of type: " + customerType +
                ", with " + state +
                " state, has the following contracts:");
        for(Contract c:contracts){
            sb.append("\t").append(c);
        }
        return sb.toString();
    }
}

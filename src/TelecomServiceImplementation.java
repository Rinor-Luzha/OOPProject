import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TelecomServiceImplementation<E> implements TelecomService<E>{
    private String path;

    public TelecomServiceImplementation(String path) {
        this.path=path;
    }

    @Override
    public void create(ArrayList<E> list) throws IOException {
        if(list.size()<1){
            return;
        }
        FileWriter fw = new FileWriter(path + list.get(0).getClass().getSimpleName() + ".txt");
        if (list.get(0) instanceof Customer) {
            ArrayList<Customer> customers= (ArrayList<Customer>) list;
            for (Customer c1:customers){
                fw.write(String.format("%S,%S,%S,%S,%s\n", c1.getIdNumber(), c1.getCustomerType(), c1.getCreatedDate(), c1.getState(),c1.getContracts()));
                fw.flush();
            }
        }else if (list.get(0) instanceof Contract) {
            ArrayList<Contract> contracts= (ArrayList<Contract>) list;
            for (Contract c:contracts){
                fw.write(String.format("%S,%S,%s,%S,%s\n", c.getIdNumber(),c.getContractType(),c.getCreatedDate(),c.getState(),c.getSubscriptions()));
                fw.flush();
            }
        }else if(list.get(0) instanceof Subscription) {
            ArrayList<Subscription> subscriptions= (ArrayList<Subscription>) list;
            for (Subscription s:subscriptions){
                fw.write(String.format("%S,%S,%s,%S,%s,%s\n", s.getIdNumber(),s.getPhoneNumber(),s.getCreatedDate(),s.getState(),s.getServices(),s.getProducts()));
                fw.flush();
            }
        }
    }

    @Override
    public ArrayList<E> update(ArrayList<E> list,String idNumber) {
        return null;
    }

    @Override
    public ArrayList<E> delete(String idNumber) {
        return null;
    }

    @Override
    public E findByID(String idNumber) throws IOException {
        if(idNumber.matches("CUST_.")){
            return (E) Files.readAllLines(Path.of(path+"Customer.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==5)
                    .filter(array -> array[0].equals(idNumber))
                    .findFirst()
                    .map(array -> {
                        try {
                            return toCustomer(array);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .orElse(null);
        }else if(idNumber.matches("CONTRACT_.")){
            return (E) Files.readAllLines(Path.of(path+"Contract.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==5)
                    .filter(array -> array[0].equals(idNumber))
                    .findFirst()
                    .map(array -> {
                        try {
                            return toContract(array);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .orElse(null);

        }else if(idNumber.matches("SUBS_.")){
            return (E) Files.readAllLines(Path.of(path+"Subscription.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==6)
                    .filter(array -> array[0].equals(idNumber))
                    .findFirst()
                    .map(array -> toSubscription(array))
                    .orElse(null);
        }

        return null;
    }

    @Override
    public ArrayList<E> findAll(String type) throws IOException {
        if(type.equalsIgnoreCase("Customer")){
            return (ArrayList<E>) Files.readAllLines(Path.of(path+"Customer.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==5)
                    .map(array -> {
                        try {
                            return toCustomer(array);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        }else if(type.equalsIgnoreCase("Contract")){
            return (ArrayList<E>) Files.readAllLines(Path.of(path+"Contract.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==5)
                    .map(array -> {
                        try {
                            return toContract(array);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());

        }else if(type.equalsIgnoreCase("Subscription")){
            return (ArrayList<E>) Files.readAllLines(Path.of(path+"Subscription.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==6)
                    .map(array -> toSubscription(array))
                    .collect(Collectors.toList());
        }

        return null;
    }

    private Customer toCustomer(String[] tokens) throws IOException {
        String idNumber=tokens[0];
        Customer.CustomerType customerType= Customer.CustomerType.valueOf(tokens[1]);
        LocalDate createdDate=LocalDate.parse(tokens[2]);
        State state=State.valueOf(tokens[3]);
        String contracts=tokens[4].substring(1, tokens[4].length()-1);
        String[] contractsIds=contracts.split(";");
        List<Contract> list=new ArrayList<>();
        for(String contrId:contractsIds){
            Contract c= Files.readAllLines(Path.of(path+"Contract.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==5)
                    .filter(array -> array[0].equals(contrId))
                    .findFirst()
                    .map(array -> {
                        try {
                            return toContract(array);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .orElse(null);
            list.add(c);
        }
        return Customer.queryCustomerFile(idNumber,customerType,createdDate,state,list);

    }

    private Contract toContract(String[] tokens) throws IOException {
        String idNumber=tokens[0];
        Contract.ContractType contractType=Contract.ContractType.valueOf(tokens[1]);
        LocalDate createdDate=LocalDate.parse(tokens[2]);
        State state=State.valueOf(tokens[3]);
        String subscriptions=tokens[4].substring(1, tokens[4].length()-1);
        String[] subscriptionsIds=subscriptions.split(";");
        List<Subscription> list=new ArrayList<>();
        for(String subId:subscriptionsIds){
            Subscription s= Files.readAllLines(Path.of(path+"Subscription.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==6)
                    .filter(array -> array[0].equals(subId))
                    .findFirst()
                    .map(array -> toSubscription(array))
                    .orElse(null);
            list.add(s);
        }
        return Contract.queryContractFile(idNumber,createdDate,state,contractType,list);
    }

    private Subscription toSubscription(String[] tokens){
        String idNumber=tokens[0];
        String phoneNumber=tokens[1];
        LocalDate createdDate=LocalDate.parse(tokens[2]);
        State state=State.valueOf(tokens[3]);
        return Subscription.querySubscriptionFile(idNumber,phoneNumber,createdDate,state);
    }


}

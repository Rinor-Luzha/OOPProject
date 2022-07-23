package telecom;

import exceptions.ServiceException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    public ArrayList<E> update(ArrayList<E> list,String idNumber) throws IOException {
        if(list.size()<1){
            return list;
        }
        if (list.get(0) instanceof Customer) {
            ArrayList<Customer> customers= (ArrayList<Customer>) list;
            for(int i=0;i<customers.size();i++){
                Customer c=customers.get(i);
                if(c.getIdNumber().equals(idNumber)){
                    Customer update=(Customer)findByID(idNumber);
                    customers.set(i,update);
                    return list;
                }
            }
        }else if (list.get(0) instanceof Contract) {
            ArrayList<Contract> contracts= (ArrayList<Contract>) list;
            for(int i=0;i<contracts.size();i++){
                Contract c=contracts.get(i);
                if(c.getIdNumber().equals(idNumber)){
                    Contract update=(Contract)findByID(idNumber);
                    contracts.set(i,update);
                    return list;
                }
            }

        }else if(list.get(0) instanceof Subscription) {
            ArrayList<Subscription> subscriptions= (ArrayList<Subscription>) list;
            for(int i=0;i<subscriptions.size();i++){
                Subscription c=subscriptions.get(i);
                if(c.getIdNumber().equals(idNumber)){
                    Subscription update=(Subscription)findByID(idNumber);
                    subscriptions.set(i,update);
                    return list;
                }
            }
        }
        return list;
    }

    @Override
    public void delete(String idNumber) throws IOException{
        String sub=idNumber.substring(0,4);
        String filePath=null;
        switch (sub){
            case "CUST":
                filePath="Customer.txt";
                break;

            case "SUBS":
                filePath="Subscription.txt";
                break;
            case "CONT":
                filePath="Contract.txt";
                break;
            default:
                //throw exception id gabim anajsen
        }
        File file=new File(path+filePath);
        List<String> records= Files.readAllLines(file.toPath()).stream()
                .filter(row->!row.startsWith(idNumber))
                .collect(Collectors.toList());

        try (FileWriter fw=new FileWriter(file)){
            for (String record : records) {
                fw.write(record+"\n");
            }
        }
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
                    .map(array -> {
                        try {
                            return toSubscription(array);
                        } catch (ServiceException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
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
                    .map(array -> {
                        try {
                            return toSubscription(array);
                        } catch (ServiceException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        }

        return null;
    }

    private Customer toCustomer(String[] tokens) throws IOException {
        String idNumber=tokens[0];
        Customer.CustomerType customerType= Customer.CustomerType.valueOf(tokens[1]);
        LocalDate createdDate=LocalDate.parse(tokens[2]);
        State state= State.valueOf(tokens[3]);
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
        Contract.ContractType contractType= Contract.ContractType.valueOf(tokens[1]);
        LocalDate createdDate=LocalDate.parse(tokens[2]);
        State state= State.valueOf(tokens[3]);
        String subscriptions=tokens[4].substring(1, tokens[4].length()-1);
        String[] subscriptionsIds=subscriptions.split(";");
        List<Subscription> list=new ArrayList<>();
        for(String subId:subscriptionsIds){
            Subscription s= Files.readAllLines(Path.of(path+"Subscription.txt")).stream()
                    .map(row -> row.split(","))
                    .filter(array-> array.length==6)
                    .filter(array -> array[0].equals(subId))
                    .findFirst()
                    .map(array -> {
                        try {
                            return toSubscription(array);
                        } catch (ServiceException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .orElse(null);
            list.add(s);
        }
        return Contract.queryContractFile(idNumber,createdDate,state,contractType,list);
    }

    private Subscription toSubscription(String[] tokens) throws ServiceException {
        String idNumber=tokens[0];
        String phoneNumber=tokens[1];
        LocalDate createdDate=LocalDate.parse(tokens[2]);
        State state= State.valueOf(tokens[3]);
        return Subscription.querySubscriptionFile(idNumber,phoneNumber,createdDate,state);
    }


}
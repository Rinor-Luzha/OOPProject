import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TelecomServiceImplementation<E> implements TelecomService<E>{


    @Override
    public void create(ArrayList<E> list, String output) throws IOException {
        FileWriter fw;

        try {
            if (list.get(0) instanceof Customer) {
                ArrayList<Customer> customers= (ArrayList<Customer>) list;

                fw = new FileWriter(output + list.get(0).getClass().getSimpleName() + ".txt");
                for (Customer c1:customers){

                    fw.write(String.format("%S,%S,%S,%S,%s\n", c1.getIdNumber(), c1.getCustomerType(), c1.getCreatedDate(), c1.getState(),c1.getContracts()));
                    fw.flush();
                }
            }

            if (list.get(0) instanceof Contract) {
                ArrayList<Contract> contracts= (ArrayList<Contract>) list;

                fw = new FileWriter(output + list.get(0).getClass().getSimpleName() + ".txt");
                for (Contract c:contracts){
                    fw.write(String.format("%S,%S,%s,%S,%s\n", c.getIdNumber(),c.getContractType(),c.getCreatedDate(),c.getState(),c.getSubscriptions()));
                    fw.flush();
                }
            }

            if (list.get(0) instanceof Subscription) {
                ArrayList<Subscription> subscriptions= (ArrayList<Subscription>) list;

                fw = new FileWriter(output + list.get(0).getClass().getSimpleName() + ".txt");
                for (Subscription s:subscriptions){
                    fw.write(String.format("%S,%S,%s,%S,%s,%s\n", s.getIdNumber(),s.getPhoneNumber(),s.getCreatedDate(),s.getState(),s.getServices(),s.getProducts()));
                    fw.flush();
                }
            }


        }catch (IOException ioe){
            ioe.getMessage();
        }

    }
    /*private static void writeToFile(Map<Long, BankAccount> map, String fileName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            Collection<BankAccount> accounts = map.values();
            for (BankAccount acc : accounts) {
                if (acc instanceof CurrentAccount) {
                    pw.println(String.format("%C,%d,%s,%.2f,%d%n", 'C', acc.getAccountNr(),
                            acc.getName(), acc.getBalance(), ((CurrentAccount) acc).getOverdraft()));
                } else {
                    pw.println(String.format("%C,%d,%s,%.2f,%.2f%n", 'S', acc.getAccountNr(),
                            acc.getName(), acc.getBalance(), ((SavingsAccount) acc).getInterest()));
                }
            }
        }
    }
    * */

    @Override
    public void update(String input) {

    }

    @Override
    public void delete(String id) throws IOException{
        String sub=id.substring(0,4);
        String path=null;
        switch (sub){
            case "CUST":
                path="Customer";
                break;

            case "SUBS":
                path="Subscription";
                break;
            case "CONT":
                path="Contract";
                break;
            default:
                //throw exception id gabim anajsen
        }
        File file=new File("C:\\Users\\Lenovo\\Desktop\\OOPProject\\out\\production\\OOPProject\\"+path+".txt");
        List<String> records= Files.readAllLines(file.toPath()).stream()
                .filter(row->!row.startsWith(id))
                .collect(Collectors.toList());

        try (FileWriter fw=new FileWriter(file)){
            for (String record : records) {
                fw.write(record+"\n");
            }
        }
    }

    @Override
    public ArrayList<E> findByID(String input,String id) {
        return new ArrayList<E>();
    }

    @Override
    public void findAll() {

    }
}

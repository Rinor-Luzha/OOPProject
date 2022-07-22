import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
                    fw.write(String.format("%S,%S,%s,%S,%s\n", s.getIdNumber(),s.getPhoneNumber(),s.getCreatedDate(),s.getState(),s.getServices()));
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
    public void delete() {

    }

    @Override
    public ArrayList<E> findByID(String input,String id) {
        return new ArrayList<E>();
    }

    @Override
    public void findAll() {

    }
}

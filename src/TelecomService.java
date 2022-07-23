import java.io.IOException;
import java.util.ArrayList;

public  interface TelecomService<E> {
    void create(ArrayList<E> list) throws IOException;
    ArrayList<E> update(ArrayList<E> list,String idNumber);
    void delete(String idNumber)throws IOException;
    E findByID(String idNumber) throws IOException;
    ArrayList<E> findAll(String type) throws IOException;

}

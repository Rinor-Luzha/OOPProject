import java.io.IOException;
import java.util.ArrayList;

public  interface TelecomService<E> {
    void create(ArrayList<E> list, String output) throws IOException;
    void update(String input);
    void delete(String id)throws IOException;
    ArrayList<E> findByID(String input,String id);


    void findAll();

}

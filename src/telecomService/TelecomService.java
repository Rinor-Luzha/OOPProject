package telecomService;

import exceptions.TelecomServiceException;

import java.io.IOException;
import java.util.ArrayList;

public  interface TelecomService<E> {
    void create(ArrayList<E> list) throws IOException, TelecomServiceException;
    ArrayList<E> update(ArrayList<E> list,String idNumber) throws IOException;
    void delete(String idNumber)throws IOException, TelecomServiceException;
    E findByID(String idNumber) throws IOException;
    ArrayList<E> findAll(String type) throws IOException;

}

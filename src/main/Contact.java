package main;

import exceptions.ContactException;

import java.time.LocalDate;

public class Contact {
    private String idNumber;
    public enum IdType {
        CU("main.Customer"),
        CO("main.Contract"),
        SU("main.Subscription");
        private String prefix;
        IdType(String prefix){
            this.prefix=prefix;
        }
        public String toString(){
            return prefix;
        }
    };
    private IdType idType;
    private String name;
    private String lastName;
    private String customerName;
    private char gender;
    private LocalDate dateOfBirth;
    private LocalDate createdDate;
    private State state;

    private Contact(IdType idType,String name, String lastName, String customerName, char gender, LocalDate dateOfBirth, LocalDate createdDate, State state) {
        this.idNumber = GenerateId.Contact.getId();
        this.idType=idType;
        this.name = name;
        this.lastName = lastName;
        this.customerName = customerName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.createdDate = createdDate;
        this.state = state;
    }

    public Contact (Customer c, String name, String lastName, char gender, LocalDate dateOfBirth, LocalDate createdDate, State state) throws ContactException {
        this(IdType.CU,name,lastName,null,gender,dateOfBirth,createdDate,state);
        if(c==null){
            throw new ContactException("Custumer i derguar per contact eshte null!");
        }
        if(StringUtils.nullOrEmpty(name)){
            throw new ContactException("Emri per kontaktin eshte i zbrazte!");
        }
        if(StringUtils.nullOrEmpty(lastName)){
            throw new ContactException("Emri per kontaktin eshte i zbrazte!");
        }
        if(dateOfBirth.isAfter(LocalDate.now())){
            throw new ContactException("main.ServiceTypes.Data lindjes per kontaktin eshte dhene gabimisht!");
        }
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContactException("main.ServiceTypes.Data krijimit kontaktit eshte dhene gabimisht!");
        }
        if(c.getCustomerType()!= Customer.CustomerType.INDIVIDUAL){
            throw new ContactException("main.Customer nuk eshte i tipit individual!");
        }
        if(gender!='M' && gender!='F'){
            throw new ContactException("Gjinia eshte dhene gabim!");
        }
    }
    public Contact(Customer c,String customerName,LocalDate createdDate,State state) throws ContactException {
        this(IdType.CU,null,null,customerName,'\u0000',null,createdDate,state);
        if(c==null){
            throw new ContactException("Custumer i derguar per contact eshte null!");
        }
        if(StringUtils.nullOrEmpty(customerName)){
            throw new ContactException("Emri per kontaktin eshte i zbrazte!");
        }
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContactException("main.ServiceTypes.Data krijimit kontaktit eshte dhene gabimisht!");
        }
        if(c.getCustomerType()!= Customer.CustomerType.BUSINESS){
            throw new ContactException("main.Customer nuk eshte i tipit business!");
        }
    }
    public Contact(Subscription s,LocalDate createdDate,State state) throws ContactException {
        this(IdType.SU,null,null,null,'\u0000',null,createdDate,state);
        if(s==null){
            throw new ContactException("main.Subscription i derguar per contact eshte null!");
        }
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContactException("main.ServiceTypes.Data krijimit kontaktit eshte dhene gabimisht!");
        }
    }
    public Contact(Contract c,LocalDate createdDate,State state) throws ContactException {
        this(IdType.CO,null,null,null,'\u0000',null,createdDate,state);
        if(c==null){
            throw new ContactException("main.Contract e derguar per contact eshte null!");
        }
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContactException("main.ServiceTypes.Data krijimit kontaktit eshte dhene gabimisht!");
        }
    }

    public String getIdNumber() {
        return idNumber;
    }

    public IdType getIdType() {
        return idType;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public char getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public State getState() {
        return state;
    }
    public boolean equals(Object o){
        if(o instanceof Contact c){
            return c.idNumber.equals(idNumber);
        }
        return false;
    }

    public String toString(){
        if(idType==IdType.CU){
            if(getCustomerName()==null){
                return idNumber + " of type: " + idType + ", belongs to Individual: \n"+
                        name+" "+lastName+", Gender: "+(gender=='M'?"Male":"Female")+
                        ", date of birth: "+dateOfBirth+"\n"+
                        ", was created on " + createdDate + ", with " + state +
                        " state.";
            }else{
                return idNumber + " of type: " + idType + ", belongs to Business: \n"+
                        customerName+"\n"+
                        ", was created on " + createdDate + ", with " + state +
                        " state.";
            }
        }else{
            return idNumber + " of type: " + idType + ", was created on " + createdDate + ", with " + state +
                    " state.";
        }
    }
}

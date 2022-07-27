package main;

import exceptions.ContactException;

import java.time.LocalDate;

public class Contact {
    private String idNumberContact;
    public enum IdType {
        CU("Customer"),
        CO("Contract"),
        SU("Subscription");
        private String prefix;
        IdType(String prefix){
            this.prefix=prefix;
        }
        public String toString(){
            return prefix;
        }
    };
    private IdType idType;
    private String idNumber;
    private String name;
    private String lastName;
    private String customerName;
    private char gender;
    private LocalDate dateOfBirth;
    private LocalDate createdDate;
    private State state;

    private Contact(IdType idType,String idNumber,String name, String lastName, String customerName, char gender, LocalDate dateOfBirth, LocalDate createdDate, State state) {
        this.idNumberContact = GenerateId.Contact.getId();
        this.idType=idType;
        this.name = name;
        this.lastName = lastName;
        this.customerName = customerName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.createdDate = createdDate;
        this.state = state;
        this.idNumber=idNumber;
    }

    public Contact (Customer c, String name, String lastName, char gender, LocalDate dateOfBirth, LocalDate createdDate, State state) throws ContactException {
        this(IdType.CU,c.getIdNumber(),name,lastName,null,gender,dateOfBirth,createdDate,state);
        if(StringUtils.nullOrEmpty(name)){
            throw new ContactException("Emri per kontaktin eshte i zbrazte!");
        }
        if(StringUtils.nullOrEmpty(lastName)){
            throw new ContactException("Mbiemri per kontaktin eshte i zbrazte!");
        }
        if(dateOfBirth.isAfter(LocalDate.now())){
            throw new ContactException("Data lindjes per kontaktin eshte dhene gabimisht!");
        }
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContactException("Data krijimit kontaktit eshte dhene gabimisht!");
        }
        if(c.getCustomerType()!= Customer.CustomerType.INDIVIDUAL){
            throw new ContactException("Customer nuk eshte i tipit individual!");
        }
        if(gender!='M' && gender!='F'){
            throw new ContactException("Gjinia eshte dhene gabim!");
        }
    }
    public Contact(Customer c,String customerName,LocalDate createdDate,State state) throws ContactException {
        this(IdType.CU,c.getIdNumber(),null,null,customerName,'\u0000',null,createdDate,state);
        if(StringUtils.nullOrEmpty(customerName)){
            throw new ContactException("Emri per kontaktin eshte i zbrazte!");
        }
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContactException("Data krijimit kontaktit eshte dhene gabimisht!");
        }
        if(c.getCustomerType()!= Customer.CustomerType.BUSINESS){
            throw new ContactException("Customer nuk eshte i tipit business!");
        }
    }
    public Contact(Subscription s,LocalDate createdDate,State state) throws ContactException {
        this(IdType.SU,s.getIdNumber(),null,null,null,'\u0000',null,createdDate,state);
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContactException("Data krijimit kontaktit eshte dhene gabimisht!");
        }
    }
    public Contact(Contract c,LocalDate createdDate,State state) throws ContactException {
        this(IdType.CO,c.getIdNumber(),null,null,null,'\u0000',null,createdDate,state);
        if(createdDate.isAfter(LocalDate.now())){
            throw new ContactException("Data krijimit kontaktit eshte dhene gabimisht!");
        }
    }

    public String getIdNumberContact() {
        return idNumberContact;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean equals(Object o){
        if(o instanceof Contact c){
            return c.idNumberContact.equals(idNumberContact);
        }
        return false;
    }

    public String toString(){
        if(idType==IdType.CU){
            if(getCustomerName()==null){
                return idNumberContact + " of type: " + idType + ", belongs to Individual: \n"+
                        idNumber+" with name "+name+" "+lastName+", Gender: "+(gender=='M'?"Male":"Female")+
                        ", date of birth: "+dateOfBirth+"\n"+
                        ", was created on " + createdDate + ", with " + state +
                        " state.";
            }else{
                return idNumberContact + " of type: " + idType + ", belongs to Business: \n"+
                        idNumber+" with name "+customerName+"\n"+
                        ", was created on " + createdDate + ", with " + state +
                        " state.";
            }
        }else{
            return idNumberContact + " of type: " + idType + " with id "+idNumber+", was created on " + createdDate + ", with " + state +
                    " state.";
        }
    }
}

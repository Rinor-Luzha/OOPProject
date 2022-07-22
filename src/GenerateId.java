public enum GenerateId {
    Service("SERV"),
    Subscription("SUBS"),
    Contract("CONTRACT"),
    Contact("CONTACT"),
    Customer ("CUST"),
    Product ("PROD");


    private String prefix;
    private int counter;

    GenerateId(String prefix) {
        this(prefix,0);
    }

    GenerateId(String prefix, int counter) {
        this.prefix = prefix;
        this.counter = counter;
    }

    String getId(){
        return String.format("%S_%d",prefix,++counter);
    }
}

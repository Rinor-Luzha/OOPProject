public interface ServiceType {
    void SimCard();
    void Voice();
    default void SMS(){

    }
    default void Data(){

    }
}


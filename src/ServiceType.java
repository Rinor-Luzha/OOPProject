public interface ServiceType {
    boolean getSimCard();
    boolean getVoice();
    default boolean getSMS(){
        return false;
    }
    default boolean getData(){
        return false;
    }
}


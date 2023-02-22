package domain.values;
import domain.generic.Identity;

public class OperariosPinturaId extends Identity {
    public OperariosPinturaId(String uuid) {super(uuid);}

    public OperariosPinturaId(){

    }

    public static OperariosPinturaId of(String uuid) { return new OperariosPinturaId(uuid);
    }

}
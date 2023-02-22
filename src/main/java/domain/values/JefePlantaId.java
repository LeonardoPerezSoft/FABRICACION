package domain.values;

import domain.generic.Identity;

public class JefePlantaId extends Identity {

    public JefePlantaId(String uuid) {super(uuid);}
    public JefePlantaId(){

    }
    public static JefePlantaId of(String uuid){return new JefePlantaId(uuid);}

}

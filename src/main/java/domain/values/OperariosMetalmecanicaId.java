package domain.values;

import domain.generic.Identity;

public class OperariosMetalmecanicaId extends Identity {
     public OperariosMetalmecanicaId(String uuid) {super(uuid);}

    public OperariosMetalmecanicaId(){

    }

    public static OperariosMetalmecanicaId of(String uuid) { return new OperariosMetalmecanicaId(uuid);
    }

}

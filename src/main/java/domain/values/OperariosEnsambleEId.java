package domain.values;

import domain.generic.Identity;

public class OperariosEnsambleEId extends Identity{
    public OperariosEnsambleEId(String uuid) {super(uuid);}

    public OperariosEnsambleEId(){

    }

    public static OperariosEnsambleEId of(String uuid) { return new OperariosEnsambleEId(uuid);
    }

}


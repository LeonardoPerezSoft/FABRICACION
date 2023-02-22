package domain.values;

import domain.generic.Identity;

public class TareasId extends Identity {
    public TareasId(String value){ super(value);}

        public TareasId(){

        }


    public static TareasId of (String value){ return new TareasId(value);}
}

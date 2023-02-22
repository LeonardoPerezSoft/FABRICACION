package domain.values;

import domain.generic.Identity;

public class OdsId extends Identity {

    public OdsId(String uuid){super(uuid);}

    public OdsId(){

    }

    public static OdsId of (String uuid){return new OdsId(uuid);}

}

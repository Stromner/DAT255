package fg.hazmateasiermanagement.database;

import java.util.Arrays;

/**
 * Used to setup the database for the first time
 * Created by Henrik on 2014-10-14.
 */
public class Seed {
    private static Seed seed;

    private Seed(){

    }

    public static Seed getInstance(){
        if(seed == null){
            seed = new Seed();
        }
        return seed;
    }

// un_id name description label hazzmat list<String>
    public void seedElements(Database db){
        String list = "1;1.4;1.5;1.6;4.1;5.2"; // 2.1
        db.addElement(4, "AMMONIUM PICTRATE", "Dry or wetted with less than 10% water, by mass", "2.1", "default", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;4.1;5.2"; // 3
        db.addElement(1286, "ROSIN OIL", "(vapour pressure at 50 C more than 110 kPa, boiling point of more than 35 C)", "3", "default", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;2.1;2.2;2.3;4.1;5.2"; // 6.1
        db.addElement(1541, "ACETONE CYANOHYDRIN", "(STABILIZED)", "6.1", "default", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;4.1;5.2"; // 8
        db.addElement(1761, "CUPRIETHYLENEDIAMINE SOLUTION", "(STABILIZED)", "8", "default", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;2.1;2.2;2.3;3;4.1;4:2;4.3;5.2;6.1;6.2;7;8;9"; // 5.1
        db.addElement(1474, "MAGNESIUM NITRATE", "SALT", "5.1", "default", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;2.1;2.2;2.3;3;4.1;4:2;4.3;5.2;6.1;6.2;7;8;9"; // 5.2
        db.addElement(2909, "URANIUM", "RADIOACTIVE MATERIAL", "5.1", "radioactive", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;4.1;5.2"; // 3
        db.addElement(1203, "MOTOR SPIRIT", "GASOLINE or PETROL", "3", "flammable", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;4.1;5.2"; // 3
        db.addElement(1204, "NITROGLYCERIN", "SOLUTION IN ALCOHOL with not more than 1% nitroglycerin", "3", "explosive", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;4.1;5.2"; // 3
        db.addElement(1210, "PRINTING INK", "Flammable or PRINTING INK RELATED MATERIAL (including printing ink", "3", "flammable", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;4.1;5.2"; // 8
        db.addElement(2923, "CORROSIVE SOLID", "CORROSIVE SOLID, TOXIC N.O.S", "8", "corrosive", Arrays.asList(list.split(";")));

        list = "1;1.4;1.5;1.6;4.1;5.2"; // 8
        db.addElement(3077, "ENVIRONMENTALLY HAZARDOUS", "ENVIRONMENTALLY HAZARDOUS SUBSTANCE, SOLID, N.O.S.", "8", "environmentally", Arrays.asList(list.split(";")));

    }
}

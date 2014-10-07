package fg.hazmateasiermanagement;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;


/**
 * Created by Beni on 2014-10-06.
 */
public class ExampleInfoButton extends Activity {
    HashMap<String, String> UNitem;


    ExampleInfoButton(){
        UNitem = new HashMap<String, String>();
        UNitem.put("0001","Babyfood");
        UNitem.put("0002","Football");

    }

 private String getFakeUN(String UNnr){
       return UNitem.get(UNnr);
 }











}

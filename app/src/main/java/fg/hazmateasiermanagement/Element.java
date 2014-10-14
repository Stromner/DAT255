package fg.hazmateasiermanagement;

import android.media.Image;

import java.util.Arrays;
import java.util.List;

/**
 * Contains all necessary information about a single element in the UN list of hazardous materials.
 * It also contains extra functions and variables so it can be transported(I.e the UN element
 * doesn't contain any weight but if you were to transport it you need to provide a weight param)
 *
 * @author Johansson, Henrik
 * @author Stromner, David
 * @version 2014-10-13
 */

public class Element implements Cloneable{
    private int unNumber;       // UN number
    private String name;        // Name of the element
    private String description; // Describing the Element in detail
    // private int classNumber;    // Class number that covers dangerous substance or article
    // private String packingGroup;// Packing Group I II or III
    private String label;       // Labels used to show what material that can be shipped together.
    private float weight;       // The weight of the material
    private String hazmatImage; // String that contains the name of the image
    private List<String> notCompatible;  // String that shows which labels this element cannot be shipped with.

    public Element(int unNumber, String name, String description, String label, String hazmatImage, String notCompatible){
        this.unNumber = unNumber;
        this.name = name;
        this.description = description;
        this.label = label;
        this.hazmatImage = hazmatImage;
        this.notCompatible = Arrays.asList(notCompatible.split(";"));
    }

    /**
     * Minimal constructor with only the number of the element, used for testing.
     * @param unNumber
     */
    public Element(int unNumber){    //Minimum constructor
        this.unNumber = unNumber;
    }

    public int getUNNumber(){
        return unNumber;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){ return description;}

   /* public int getClassNumber(){
       return classNumber;
    }

    public String getPackingGroup(){
        return packingGroup;
    }*/

    public String getLabel(){
        return label;
    }

    public float getWeight(){
        return weight;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public String getHazmatImage(){
        return hazmatImage;
    }

    public void setHazmatImage(String hazmatImage){
        this.hazmatImage = hazmatImage;
    }

    public List<String> getCompatible() {
        return notCompatible;
    }

    public boolean isCompatible(Element other) {
        return !other.getCompatible().contains(label) && !notCompatible.contains(other.label);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        // Since strings are immutable and any other variable in this class is of simple format calling super is enough.
        Element clone = (Element)super.clone();
        for(String s:notCompatible){
            clone.notCompatible.add(s);
        }

        return clone;
    }
}

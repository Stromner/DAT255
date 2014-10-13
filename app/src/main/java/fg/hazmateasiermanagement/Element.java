package fg.hazmateasiermanagement;

import android.media.Image;

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
    private int classNumber;    // Class number that covers dangerous substance or article
    private String packingGroup;// Packing Group I II or III
    private String label;       // Labels used to show what material that can be shipped together.
    private float weight;       // The weight of the material
    private String hazmatImage; // String that contains the name of the image

    public Element(int unNumber, String name, String description, int classNumber, String packingGroup, String label, int weight, String hazmatImage){
        this.unNumber = unNumber;
        this.name = name;
        this.description = description;
        this.classNumber = classNumber;
        this.packingGroup = packingGroup;
        this.label = label;
        this.weight = weight;
        this.hazmatImage = hazmatImage;
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

    public int getClassNumber(){
       return classNumber;
    }

    public String getPackingGroup(){
        return packingGroup;
    }

    public String getLabel(){
        return label;
    }

    public float getWeight(){
        return weight;
    }

    public Boolean setWeight(float weight){
        this.weight = weight;

        return this.weight == weight ? true:false;
    }

    public String getHazmatImage(){
        return hazmatImage;
    }

    public boolean setHazmatImage(String hazmatImage){
        this.hazmatImage = hazmatImage;
        return this.hazmatImage.compareTo(hazmatImage) == 0 ? true:false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        // Since strings are immutable and any other variable in this class is of simple format calling super is enough.
        return super.clone();
    }
}

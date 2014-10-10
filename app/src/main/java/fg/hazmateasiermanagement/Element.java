package fg.hazmateasiermanagement;

import android.media.Image;

/**
 * Contains all necessary information about a single element in the UN list of hazardous materials.
 * It also contains extra functions and variables so it can be transported(I.e the UN element
 * doesn't contain any weight but if you were to transport it you need to provide a weight param)
 *
 * @author Johansson, Henrik
 * @author Stromner, David
 * @version 2014-10-09
 */

public class Element {
    private int unNumber;           //UN number
    private String name;            //Name of the element
    private String description;     //Describing the Element in detail
    private int classNumber;        //Class number that covers dangerous substance or article
    private String packingGroup;    //Packing Group I II or III
    private String label;           //labels used to show what material that can be shipped together.
    private int weight;             //The weight of the material
    private String hazmatImage;     //String that contains the name of the image

    /**
     * Constructor with all necessary fields included.
     * @param unNumber
     * @param name
     * @param description
     * @param classNumber
     * @param packingGroup
     * @param label
     * @param weight;
     * @param hazmatImage;
     */
    public Element(int unNumber, String name, String description, int classNumber, String packingGroup, String label, int weight, String hazmatImage){         //Full constructor
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
     * Constructor with only unNumber field, used for testing.
     * @param unNumber
     */
    public Element(int unNumber){    //Minimum constructor
        this.unNumber = unNumber;
    }

    /**
     *
     * @param unNumber
     * @return true if unNumber has been set to new value, null ow
     */
    public boolean setUNNumber(int unNumber){
        this.unNumber = unNumber;
        return this.unNumber == unNumber ? true:false;
    }

    /**
     *
     * @return unNumber if exist null ow.
     */
    public int getUNNumber(){
        return unNumber;
    }

    /**
     *
     * @param name
     * @return true if name has been set to new value, null ow
     */
    public boolean setName(String name){
        this.name = name;
        return this.name == name ? true:false;
    }
    /**
     *
     * @return name if exist, null ow.
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @param description
     * @return true if description has been set to new value, null ow
     */
    public boolean setDescription(String description){
        this.description = description;
        return this.description == description ? true:false;
    }

    /**
     *
     * @return description if exist, null ow
     */
    public String getDescription(){ return description;}

    /**
     *
     * @param classNumber
     * @return true if classNumber has been set to new value, false ow
     */
    public boolean setClassNumber(int classNumber){
        this.classNumber = classNumber;
        return this.classNumber == classNumber ? true:false;
    }

    /**
     *
     * @return classNumber if exist, null ow.
     */
    public int getClassNumber(){
       return classNumber;
    }

    /**
     *
     * @return true if packinggroup has been set to new value, flase ow
     */
    public boolean setPackingGroup(){
        this.packingGroup = packingGroup;
        return this.packingGroup == packingGroup ? true:false;
    }

    /**
     *
     * @return packingGroup if exist, null ow.
     */
    public String getPackingGroup(){
        return packingGroup;
    }

    /**
     *
     * @param label
     * @return true if label has been set to new, false ow
     */
    public boolean setLabel(String label){
        this.label = label;
        return this.label == label ? true:false;
    }

    /**
     *
     * @return label if exist, null ow
     */
    public String getLabel(){
        return label;
    }

    /**
     *
     * @return weight if exist, null ow
     */
    public int getWeight(){
        return weight;
    }
    public Boolean setWeight(int weight){
        this.weight = weight;

        return this.weight == weight ? true:false;
    }

    /**
     *
     * @return hazmatImage if exist, null ow
     */
    public String getHazmatImage(){
        return hazmatImage;
    }

    /**
     *
     * @param hazmatImage
     * @return true if created, false ow
     */
    public boolean setHazmatImage(String hazmatImage){
        this.hazmatImage = hazmatImage;
        return this.hazmatImage == hazmatImage ? true:false;
    }

    @Override
    public Object clone(){
        //Left it empty so you would notice when compiling.
        return null;
    }

    @Override
    public int hashCode(){
       //This one to!
        return 0;
    }
}

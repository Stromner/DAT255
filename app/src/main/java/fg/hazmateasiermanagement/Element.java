package fg.hazmateasiermanagement;

import android.media.Image;

/**
 * Contains all necessary information about a single element in the UN list of hazardous materials.
 * It also contains extra functions and variables so it can be transported(I.e the UN element
 * doesn't contain any weight but if you were to transport it you need to provide a weight param)
 *
 * @author Johansson, Henrik
 * @author Stromner, David
 * @version 2014-10-07
 */

public class Element {
    private int unNumber;           //UN number
    private String nameDesc;        //Name and description
    private int classNumber;        //Class number that covers dangerous substance or article
    private String packingGroup;    //Packing Group I II or III
    private String label;          //labels used to show what material that can be shipped together.
    private int weight;             //The weight of the material
    //private String hazmatImage;    //TODO fix the image

    /**
     * Constructor with all necessary fields included.
     * @param unNumber
     * @param nameDesc
     * @param classNumber
     * @param packingGroup
     * @param label
     */
    public Element(int unNumber, String nameDesc, int classNumber, String packingGroup, String label, int weight){         //Full constructor
        this.unNumber = unNumber;
        this.nameDesc = nameDesc;
        this.classNumber = classNumber;
        this.packingGroup = packingGroup;
        this.label = label;
        this.weight = weight;
    }

    /**
     * Constructor with only unNumber field, used for testing.
     * @param unNumber
     */
    public Element(int unNumber){    //Minimum constructor
        this.unNumber = unNumber;
    }

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

    public boolean setNameDesc(String getNameDesc){
        this.nameDesc = nameDesc;
        return this.nameDesc == nameDesc ? true:false;
    }

    /**
     *
     * @return nameDesc if exist, null ow.
     */
    public String getNameDesc(){
        return nameDesc;
    }

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
}

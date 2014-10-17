package fg.hazmateasiermanagement.database;

import java.util.Arrays;
import java.util.List;

/**
 * Contains all necessary information about a single element in the UN list of hazardous materials.
 * It also contains extra functions and variables so it can be transported(I.e the UN element
 * doesn't contain any weight but if you were to transport it you need to provide a weight param)
 *
 * @author Johansson, Henrik
 * @author Stromner, David
 * @version 2014-10-14
 */

public class Element implements Cloneable{
    private int unNumber;       // UN number
    private String name;        // Name of the element
    private String description; // Describing the Element in detail
    private float maxWeight;    // The maximum total weight allowed for the element.
    private String label;       // Labels used to show what material that can be shipped together.
    private float weight;       // The weight of the material
    private String hazmatImage; // String that contains the name of the image
    private List<String> notCompatible;  // String that shows which labels this element cannot be shipped with.

    public Element(int unNumber, String name, String description, float maxWeight, String label, String hazmatImage, String notCompatible){
        this.unNumber = unNumber;
        this.name = name;
        this.description = description;
        this.maxWeight = maxWeight;
        this.label = label;
        this.hazmatImage = hazmatImage;
        this.notCompatible = Arrays.asList(notCompatible.split(";"));
    }

    /**
     *
     * @return the UN number if exist, null ow
     */
    public int getUNNumber(){
        return unNumber;
    }

    /**
     *
     * @return the name if exist, null ow
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return the description if exist, null ow
     */
    public String getDescription(){ return description;}

    /**
     *
     * @return the total max weight allowed if exist, null ow
     */
    public float getMaxWeight() { return maxWeight; }

    /**
     *
     * @return the label if exist, null ow
     */
    public String getLabel(){
        return label;
    }

    /**
     *
     * @return the weight if exist, null ow
     */
    public float getWeight(){
        return weight;
    }

    /**
     *
     * @param weight value to set to new weight
     */
    public void setWeight(float weight){
        this.weight = weight;
    }

    /**
     * Returns the name of the image file that has the correct "dagner type"
     * So far: explosive, flammable, corrosive, radioactive, environmentally and default.
     * @return the name of the Image that is to be associated with the element.
     */
    public String getHazmatImage(){
        return hazmatImage;
    }

    /**
     *
     * @param hazmatImage the new name for the hazmat image.
     */
    public void setHazmatImage(String hazmatImage){
        this.hazmatImage = hazmatImage;
    }

    /**
     *
      * @return a list of the not compatible labels
     */
    public List<String> getNotCompatible() {
        return notCompatible;
    }

    /**
     * Set the new not compatible labels for this elemnt, notice that this is not a list but a string.
     * @param notCompatible value for the new not compatible labels
     */
    public void setNotCompatible(String notCompatible){
        this.notCompatible = Arrays.asList(notCompatible.split(";"));
    }

    /**
     * Checks if two elements are compatible with each other.
     * @param other is the element that want to be checked with this element
     * @return true if the elements can be transported together, false ow
     */
    public boolean isCompatible(Element other) {
        return !other.getNotCompatible().contains(label) && !notCompatible.contains(other.label);
    }

    /**
     * Compares a list of elements to see if they are compatible
     * @param list the list that should be checked for compabilty
     * @return a string that shows which elements who are not compatible.
     */
    public String isCompatible(List<Element> list){
        String s = unNumber+"("+name+") is incompatible with\n";
        for(Element e: list){
            if(isCompatible(e)){
                s += e.unNumber+"("+e.name+")\n";
            }
        }

        return s;
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

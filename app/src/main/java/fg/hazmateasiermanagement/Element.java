package fg.hazmateasiermanagement;

/**
 * Contains all necessary information about a single element in the UN list of hazardous materials.
 * It also contains extra functions and variables so it can be transported(I.e the UN element
 * doesn't contain any weight but if you were to transport it you need to provide a weight param)
 *
 * @author Johansson, Henrik
 * @author Strömner, David
 * @version 2014-09-24
 */

public class Element {
    private int weight;

    public int getWeight(){
        return weight;
    }
    public Boolean setWeight(int weight){
        this.weight = weight;

        return this.weight == weight ? true:false;
    }
}

// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 - 2020T2, Assignment 1
 * Name: Chloe Crowe
 * Username: Crowechlo
 * ID: 300539785
 */

//DO NOT CHANGE THESE IMPORTS OR ADD ANY FURTHER IMPORTS.
import ecs100.UI;   
import java.util.List;
import java.util.ArrayList;


/** Questions1   */

public class Questions1{


        
    /**
     * union is passed two lists, list1 and list2.
     * It can assume that there are no duplicates in list1 or list2.
     * It should return a new list which contains every Thing that is in
     * list1 or list2 or both, but should not contain any duplicates.
    */
    public List<Thing> union(List<Thing> list1, List<Thing> list2){
        //Creates new List
        List<Thing> everything = new ArrayList<>();
        for(int i=0; i<list1.size(); i++){
            //Get the first item in the first list
           Thing currentThing =  list1.get(i);
           //Adds it to the list
            everything.add(currentThing);
        }


        for(int i=0; i<list2.size(); i++){
            //Get the thing from the second list
            Thing currentThing =  list2.get(i);
            //If that thing isn't in the big list
            if(!everything.contains(currentThing)) {
                //Add it to the list
                everything.add(currentThing);
            }
        }

        return everything;
    }
    
    /**
     * everyNth is passed a list of Things, and two integers (start and nth)
     * It returns a new list consisting of every nth thing in the list, begining
     * at item at index start.
     * For example, if myList contains
     *     {a, b, c, d, e, f, g, h}
     *  everyNth(myList, 3, 2)
     *  should return the list
     *     {d, f, h},
     *  since d is the item at index 3, and f and h are every 2nd item after d.
     * If start is not the index of an item in the list, everyNth should return an empty list.
     * If nth is 0 or negative, then everyNth should return a list with only the item at start
    */
    public List<Thing> everyNth(List<Thing> list, int start, int nth){
        //Creates a new List
        List<Thing> newList = new ArrayList<>();

        //Add the thing at the start position
        newList.add(list.get(start));
        //For each of the things following places
        for (int i = start+nth; i < list.size(); i=(i+nth) ) {
            newList.add(list.get(i));
        }
        return newList;
    }


    /**
     * Rotates the values in the list n steps to the right
     * by moving each item n steps to the right, and bringing items that
     *  "fall off the end"  to the front of the list.
     * If n is negative, it should rotate the list -n steps to the left
     * For example, if myList contains
     *     {a, b, c, d, e, f, g, h}
     *  rotateRight(myList, 3)
     *  should result in myList being changed to contain
     *     {f, g, h, a, b, c, d, e}
     *
     *  rotateRight(myList, 9)
     *  should result in myList being changed to contain
     *     {h, a, b, c, d, e, f, g}
     *  (8 steps will return the list to its original order)
     *
     *  rotateRight(myList, -2)
     *  should result in myList being changed to contain
     *     {c, d, e, f, g, h, a, b}
     */
    public void rotateRight(List<Thing> list, int n) {

        //If the n is less than 0 create a new int and times by -1 (to get positive)
        int newN =0;
         if(n<0){
             newN = n * -1;
             for (int i = 0; i < newN; i++) {
                 //Add the first item to the end of the list
                 list.add(list.get(i));
                 //Remove the same item from the front
                 list.remove(list.get(i));
             }
        }
         else {
             newN = list.size()-n;
             for (int i = 0; i < newN; i++) {
                 //Add the first item to the end of the list
                 list.add(list.get(0));
                 //Remove the first item from the front
                 list.remove(list.get(0));
             }
         }


    }
    

        
                    
    /**
     * Checks whether list2 is a sublist of list1,
     * list2 is a sublist of list1, if you could remove items from the front and/or
     *   end of list1 to make it the same as list2
     * For example,
     *     
     *    hasSublist({a, b, c, d, e, f, g, h}, {c, d, e, f, g}) is true
     *    hasSublist({a, b, c, d, e, f, g, h}, {a, c, e, g}) is not true
     *      - the values in list2 are all in list1, but not in sequence.
     *    hasSublist({a, b, c}, {a, b, c}) is NOT true
     *      - list2 must be smaller than list1
     * An empty list is a sublist of any list (except an empty list).
     */
    public boolean hasSublist(List<Thing> list1, List<Thing> list2){

        boolean isSublist = false;
        //If list 1 contains all of list 2.
        if(list1.containsAll(list2)){
            //Returns the first thing of list 2 and the index of list1.
            int id = list1.indexOf(list2.get(0));
            for(int i=0; i<list2.size(); i++){
                //If the id of list one equals the item in list 2.
                isSublist = list1.get(i+id).equals(list2.get(i));
            }
            if(isSublist && list1.size() == list2.size()){
                //If the sublist size is the same as the list sizes then it is not a sublist.
                isSublist=false;
            }
        }
        else if(list2.size() ==0){
            //If the size of the list 2 is 0 then its technically a sublist
            isSublist = true;
        }


        return isSublist;
    }



    //===========================================================
    // For your testing.

    public static void main(String[] args){
        Questions1 q1 = new Questions1();
        q1.setupGUI();
    }

    public void setupGUI(){
        UI.addButton("union", this::testUnion);
        UI.addButton("everyNth", this::testEveryNth);
        UI.addButton("rotateRight", this::testRotateRight);
        UI.addButton("hasSublist", this::testHasSublist);
        UI.addButton("quit", UI::quit);
        UI.setDivider(1.0);
    }

    /** Test the union method */
    public void testUnion(){
        List<Thing> list1 = askThingList();
        UI.println("List1: "+list1);
        List<Thing> list2 = askThingList();
        UI.println("List2: "+list2);
        UI.println("union: " + union(list1, list2));
    }
    /** Test the everyNth method */
    public void testEveryNth(){
        List<Thing> list = askThingList();
        UI.println("List: "+list);
        int start = UI.askInt("Start at:");
        int nth = UI.askInt("nth:");
        UI.println("everyNth: " + everyNth(list, start, nth));
    }
    /** Test the rotateRight method */
    public void testRotateRight(){
        List<Thing> list = askThingList();
        UI.println("List: " + list);
        int steps = UI.askInt("Steps:");
        rotateRight(list, steps);
        UI.println("List: " + list );
    }
    /** Test the hasSublist method */
    public void testHasSublist(){
        List<Thing> list1 = askThingList();
        UI.println("List1: "+list1);
        List<Thing> list2 = askThingList();
        UI.println("List2: "+list2);
        UI.println("hasSublist: " + hasSublist(list1, list2));
    }



    /** Asks user for a string of letters, and constructs
     * a List of Things, with each letter in the string as the name of a Thing
     */
    public List<Thing> askThingList(){
        String str = UI.askString("Enter string of letters:");
        List<Thing> ans = new ArrayList<Thing>();
        for (int i=0; i<str.length(); i++){
            String nm = str.substring(i, i+1);
            if (!nm.equals(" ")) {ans.add(new Thing(nm));}
        }
        return ans;
    }
    

    //================================================
    // DO NOT CHANGE ANYTHING BELOW THIS LINE!
    // IF YOU CHANGE THIS TO MAKE YOUR CODE COMPILE,
    // THE AUTOMATED MARKING WILL FAIL AND GIVE YOU ZERO

    public void checkCompile(){
        List<Thing> list = new ArrayList<Thing>();
        list = union(list, list);
        list = everyNth(list, 1, 1);
        rotateRight(list, 1);
        if (hasSublist(list, list)){};
    }


}


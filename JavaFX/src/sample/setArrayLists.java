package sample;

import java.util.ArrayList;

public class setArrayLists {

    //------------------------METHODS FOR SETTING THE ARRAY LIST WITH VALID INFO FROM THE DATABASE, WHEN THE APPROPRIATE BUTTON IS PRESSED-----------

    public static void setArrayList(ArrayList<Integer> temporary, ArrayList<Integer> getInfo)
    {
        for (Integer i :getInfo)
        {
            temporary.add(getInfo.get(i));
        }
        //temporary.add(29); test to see if the function runs (it does)
    }


}

package ait.model.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Reverse implements Action {

    @Override
    public List<String> perform(List<String> lines) {
        List<String>copyList=new ArrayList<>(lines);
        Collections.reverse(copyList);
        return copyList;
    }
}

package ait.model.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort implements Action {


    @Override
    public List<String> perform(List<String> lines) {
        List<String>copyList= new ArrayList<>(lines);
        Collections.sort(copyList);
        return copyList;
    }
}

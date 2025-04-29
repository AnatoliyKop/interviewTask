package ait.model.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffle implements Action {
    @Override
    public List<String> perform(List<String> lines) {
        List<String> copyList = new ArrayList<>(lines);
        Collections.shuffle(copyList);
        return copyList;
    }
}

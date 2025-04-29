package ait.model.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Action {
    List<String> perform(List<String> lines);

}

package com.api.portal.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static List<String> splitHelper(String str){
        return Stream.of(str.split(","))
                .map (elem -> new String(elem))
                .collect(Collectors.toList());
    }
}

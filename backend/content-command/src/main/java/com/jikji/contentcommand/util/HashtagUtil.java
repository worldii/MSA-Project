package com.jikji.contentcommand.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class HashtagUtil {

    public static final String REGEX = "#[a-z0-9_가-힣]+";

    public static List<String> getHashtagInText(String text) {
        Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        List<String> result = new ArrayList<>();

        while (matcher.find()) {
            result.add(matcher.group(0).substring(1));
        }
        return result;
    }
}

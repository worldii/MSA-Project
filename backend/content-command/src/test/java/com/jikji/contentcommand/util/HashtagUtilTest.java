package com.jikji.contentcommand.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class HashtagUtilTest {

    @Test
    public void 본문에서_해시태그_가져오기() {
        // given
        String text = "하하하 ... ??? !! #아이유 #dlwlrma";

        // when
        final List<String> result = HashtagUtil.getHashtagInText(text);

        // then
        assertThat(result).contains("아이유", "dlwlrma");
    }
}
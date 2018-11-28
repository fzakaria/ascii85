package com.github.fzakaria.ascii85;

import org.junit.Ignore;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
/**
 * A simple test class for {@link Ascii85}
 */
public class Ascii85Test {


    @Test(expected=IllegalArgumentException.class)
    public void basicIllegalArgumentTest() {
        Ascii85.decode(null);
    }

    @Test
    public void basicWikiDecodeTest() {
        String encodedString = "9jqo^BlbD-BleB1DJ+*+F(f,q/0JhKF<GL>Cj@.4Gp$d7F!,L7@<6@)/0JDEF<G%<+EV:2F!," +
                "O<DJ+*.@<*K0@<6L(Df-\\0Ec5e;DffZ(EZee.Bl.9pF\"AGXBPCsi+DGm>@3BB/F*&OCAfu2/AKY" +
                "i(DIb:@FD,*)+C]U=@3BN#EcYf8ATD3s@q?d$AftVqCh[NqF<G:8+EV:.+Cf>-FD5W8ARlolDIa" +
                "l(DId<j@<?3r@:F%a+D58'ATD4$Bl@l3De:,-DJs`8ARoFb/0JMK@qB4^F!,R<AKZ&-DfTqBG%G" +
                ">uD.RTpAKYo'+CT/5+Cei#DII?(E,9)oF*2M7/c";

        String solution = "Man is distinguished, not only by his reason, but by this singular passion from other animals, "+
                "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation "+
                "of knowledge, exceeds the short vehemence of any carnal pleasure.";
        assertThat(solution, is(new String(Ascii85.decode(encodedString), StandardCharsets.US_ASCII)));
    }

    @Test
    public void basicWikiDecodeNewLinesTest() {
        String encodedString = "9jqo^BlbD-BleB1DJ+*+F(f,q/0JhKF<GL>Cj@.4Gp$d7F!,L7@<6@)/0JDEF<G%<+EV:2F!,\n" +
                "O<DJ+*.@<*K0@<6L(Df-\\0Ec5e;DffZ(EZee.Bl.9pF\"AGXBPCsi+DGm>@3BB/F*&OCAfu2/AKY\n" +
                "i(DIb:@FD,*)+C]U=@3BN#EcYf8ATD3s@q?d$AftVqCh[NqF<G:8+EV:.+Cf>-FD5W8ARlolDIa\n" +
                "l(DId<j@<?3r@:F%a+D58'ATD4$Bl@l3De:,-DJs`8ARoFb/0JMK@qB4^F!,R<AKZ&-DfTqBG%G\n" +
                ">uD.RTpAKYo'+CT/5+Cei#DII?(E,9)oF*2M7/c";

        String solution = "Man is distinguished, not only by his reason, but by this singular passion from other animals, "+
                "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation "+
                "of knowledge, exceeds the short vehemence of any carnal pleasure.";
        assertThat("we should ignore whitespace", solution, is(new String(Ascii85.decode(encodedString), StandardCharsets.US_ASCII)));
    }

    @Test
    public void basicWikiDecodeIgnoreSpacesTest() {
        String encodedString = "9jqo^BlbD-BleB1DJ+*+F    (f,q/0JhKF<GL>Cj@.4Gp$d7F!,L7@<6@)/0JDEF<G%<+EV:2F!,\n" +
                "O<DJ+*.@<*K0@<6L(Df-\\0Ec5e;DffZ(EZee.  Bl.9pF\"AGXBPCsi+DGm>@3BB/F*&OCAfu2/AKY\n" +
                "i(DIb:@FD,*)+C]U=@3BN#EcYf8ATD3s@q?d$AftVqCh[NqF<G:8+EV:.+Cf>-FD5W8ARlolDIa\n" +
                "l(DId<j@<?3r@:F%a+D58'ATD4$Bl@l3De:,-DJs`8ARoFb/0JMK@qB4^F!,R<AKZ&-DfTqBG%G\n" +
                ">uD.RTpAKYo'+CT/5+Cei#DII?(E,9)oF*2M7/c";

        String solution = "Man is distinguished, not only by his reason, but by this singular passion from other animals, "+
                "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation "+
                "of knowledge, exceeds the short vehemence of any carnal pleasure.";
        assertThat("we should ignore whitespace", solution, is(new String(Ascii85.decode(encodedString), StandardCharsets.US_ASCII)));
    }

    @Test
    public void basicWikiEncodeTest() {
        String solution = "9jqo^BlbD-BleB1DJ+*+F(f,q/0JhKF<GL>Cj@.4Gp$d7F!,L7@<6@)/0JDEF<G%<+EV:2F!," +
                "O<DJ+*.@<*K0@<6L(Df-\\0Ec5e;DffZ(EZee.Bl.9pF\"AGXBPCsi+DGm>@3BB/F*&OCAfu2/AKY" +
                "i(DIb:@FD,*)+C]U=@3BN#EcYf8ATD3s@q?d$AftVqCh[NqF<G:8+EV:.+Cf>-FD5W8ARlolDIa" +
                "l(DId<j@<?3r@:F%a+D58'ATD4$Bl@l3De:,-DJs`8ARoFb/0JMK@qB4^F!,R<AKZ&-DfTqBG%G" +
                ">uD.RTpAKYo'+CT/5+Cei#DII?(E,9)oF*2M7/c";

        String decodedString = "Man is distinguished, not only by his reason, but by this singular passion from other animals, "+
                "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation "+
                "of knowledge, exceeds the short vehemence of any carnal pleasure.";
        assertThat(solution, is(Ascii85.encode(decodedString.getBytes())));
    }

    //https://github.com/fzakaria/ascii85/issues/2
    @Test
    public void testNegativeBytes() {
        byte[] randoms = new byte[] {-127, -127, -127, -127};
        String encoded = Ascii85.encode(randoms);
        try {
            byte[] actual = Ascii85.decode(encoded);
            assertThat(actual, is(randoms));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This test is ignored at the moment, because it requires setting the heap space quite large.
     * Which is something I don't want to make default.
     * It is a good stress test nonetheless
     */
    @Ignore
    @Test
    public void overflowTest() {
        final int N = 0x23C34567;
        final StringBuilder sb = new StringBuilder(N);
        for (int i = 0; i < N; ++i) {
            sb.append("-");
        }
        final String encodedString = sb.toString();
        assertNotNull(Ascii85.decode(encodedString));
    }

    @Test
    public void testZeroStringDecodes() {
        testZeroStringDecode(530);
        testZeroStringDecode(531);
        testZeroStringDecode(532);
        testZeroStringDecode(533);
        testZeroStringDecode(534);
    }

    public void testZeroStringDecode(int length) {
        byte[] data = new byte[length];
        for (int c = 0; c < length; c++) data[c] = 0;
        try {
            String encoded = Ascii85.encode(data);
            byte[] decoded = Ascii85.decode(encoded);
            assertEquals(length, decoded.length);
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }
    }
}

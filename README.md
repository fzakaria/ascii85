#Ascii85

[![Maven Central](https://img.shields.io/maven-central/v/com.github.fzakaria/ascii85.svg)]()

```
<dependency>
  <groupId>com.github.fzakaria</groupId>
  <artifactId>ascii85</artifactId>
  <version>1.0</version>
</dependency>
```

This is a very simple project with a Ascii85/Base85 codec (decoder & encoder). The version most similar to what is
implemented is the **Adobe Version**. 

This codec supports:
1. using `u` to pad the last block
2. using `z` as a short form for all-zero group

In Ascii85-encoded blocks, whitespace and line-break characters may be present anywhere, including in the middle of a 5-character block, but they must be silently ignored.

> Ascii85, also called Base85, is a form of binary-to-text encoding developed by Paul E. Rutter for the btoa utility. 
> By using five ASCII characters to represent four bytes of binary data (making the encoded size ¹⁄₄ larger than the original,
>assuming eight bits per ASCII character), it is more efficient than uuencode or Base64, which use four characters
>to represent three bytes of data (¹⁄₃ increase, assuming eight bits per ASCII character).

[Ascii85](https://en.wikipedia.org/wiki/Ascii85)

There are some included test cases that use the example given in the wiki link above.

```
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
```

#License

Copyright © 2016 Farid Zakaria

Distributed under the Eclipse Public License
package com.project.base.datatypes;

/**
 * @author Reisen
 */
public class TypeString {

    public static void main(String[] args) {
        String str = "String";
        char[] character = new char[6];
        character[0] = 'S';
        character[1] = 't';
        character[2] = 'r';
        character[3] = 'i';
        character[4] = 'n';
        character[5] = 'g';
        String charArray = String.valueOf(character);
        System.out.println(charArray);
    }
}

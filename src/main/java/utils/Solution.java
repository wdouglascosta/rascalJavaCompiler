//package utils;
//
//class Solution {
//    static int decodes;
//    static int decodeVariations(String S) {
//        // your code goes here
//        decodes = 0;
//        countDecodes(S, 0);
//        return decodes;
//    }
//
//    static void countDecodes(String s, int idx){
//        if(idx == s.length()){
//            decodes++;
//            return;
//        }
//        countDecodes(s,idx+1);
//        if(idx+1<s.length() && s.charAt(idx)<='2' && s.charAt(idx+1) <= '6'){
//            countDecodes(s,idx+2);
//
//        }
//    }
//
//    public static void main(String[] args) {
//        String input = "1270";
//        int result = decodeVariations(input);
//        System.out.println(result);
//    }
//}
///*
//123445
//     ^
//
// */
package utils;
public class Solution2 {

    public static void main(String[] args) {
        int in = 100;
        printevenNum(in);
    }

    private static void printevenNum(int in) {

        if(isEven(in)){
            System.out.println(in);
        }
        if(in > 0){
            printevenNum(in - 1);
        }
    }

    private static boolean isEven(int in) {
        int aux = in / 2;
        aux = aux * 2;
        if(in == aux){
            return true;
        } else {
            return false;
        }
    }

}

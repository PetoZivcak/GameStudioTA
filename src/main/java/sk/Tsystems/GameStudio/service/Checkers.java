package sk.Tsystems.GameStudio.service;

public class Checkers {
    public static String[] checkersInitialInputString = {"open", "close", "exit"};
    public static String[] checkersInputs1String;
    public static String[] checkersInputs2String;
    public static String[] checkersInputs3String;
    public static String[] checkersInputs4String;
    public static String[] checkersInputs5String;

    public static int[] checkersInitialInputInt = {1, 2, 3};
    public static int[] checkersInputs1Int;
    public static int[] checkersInputs2Int;
    public static int[] checkersInputs3Int;
    public static int[] checkersInputs4Int;
    public static int[] checkersInputs5Int;

    public static String checkInputString(String[] string,String stringToCheck){
         int checker=0;
         String resultString="";
         int i=0;
       //String stringToCheck2= stringToCheck.toLowerCase();
        while (checker==0 && i<string.length ){
            for (i = 0; i < string.length; i++) {
                if(string[i].equals(stringToCheck.toLowerCase())){
                    return string[i];
                } else{
                    resultString="notmatch";
                }

            }
        }
      return resultString;
    }

    public static String checkInputIntFromString(int[] integer,String stringToCheck){
        int checker=0;
        String resultString="";
        int i=0;
        //String stringToCheck2= stringToCheck.toLowerCase();
        while (checker==0 && i<integer.length ){
            for (i = 0; i < integer.length; i++) {
                if(integer[i]==Integer.parseInt(stringToCheck)){
                    resultString=String.valueOf(integer[i]);
                    return resultString;
                } else{
                    resultString="notmatch";
                }

            }
        }
        return resultString;
    }

    public static void main(String[] args) {
        String myString="0";
        System.out.println(Checkers.checkInputIntFromString(Checkers.checkersInitialInputInt,myString));
    }


}

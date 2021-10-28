import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.regex.Pattern;

public class StringCalculator {

    public static void main(String[] args) {
        StringCalculator p = new StringCalculator();
       System.out.println(p.Add("//[&][%%][***]\n999&2%%3***7"));
        System.out.println(p.Add("//[&][%%]\n2&3%%7"));

    }

    public int Add(String numbers)  {
        if(numbers.equals("")){
            return 0;
        }
        else if(consist_negative_numbers(numbers)) {
            try{
                throw new IOException();
            }
            catch (IOException e){
                System.out.print("negatives not allowed: " );
                for(String str: print_negative_numbers(numbers)){
                    if(str!=null){
                        System.out.print(str+" ");
                    }
                }

                return -1;
            }
        }
        else if(numbers.length() == 1){
            return Integer.parseInt(numbers);
        }
        else{
            String delimiter = "";
            String delimiter2 = "";
            String delimiter3 = "";
            if(numbers.contains("//")){
                if(!multipleDelimiter(numbers)) {
                    char[] t = numbers.toCharArray();
                    for (int i = 2; i < t.length; i++) {
                        if (t[i] != '\n') {
                            if(t[i] == '[' || t[i] == ']')continue;
                            else delimiter += String.valueOf(t[i]);
                        }
                        else break;
                    }
                    numbers = numbers.substring(5 + delimiter.length());
                }
                else{
                    char[] t1 = numbers.toCharArray();
                    int sum1 = 0;
                    for (int i = 2; i < t1.length; i++){
                        if(t1[i]!='\n') {
                            if ( t1[i] == ']'){
                                sum1++;
                                continue;
                            }
                            if ( t1[i] =='['){
                                continue;
                            }
                            if ( t1[i]!='[' && t1[i]!=']' && sum1 == 0){
                                delimiter+=t1[i];
                            }
                            if ( t1[i]!='[' && t1[i]!=']' && sum1 == 1){
                                delimiter2+=t1[i];
                            }
                            if ( t1[i]!='[' && t1[i]!=']' && sum1 == 2){
                                delimiter3+=t1[i];
                            }

                        }
                        else break;
                    }
                    if(delimiter3.equals(""))
                        numbers = numbers.substring(7 + delimiter.length()+delimiter2.length());
                    else
                        numbers = numbers.substring(9 + delimiter.length()+delimiter3.length()+delimiter2.length());
                }
            }
            else  delimiter = ",";

            if(delimiter2.equals("")) {
                String[] str = numbers.replace("\n", delimiter).split(Pattern.quote(delimiter));
                int sum = 0;
                for (String number : str) {
                    if(isNumeric(number)) sum += ignore_number_bigger_than_1000(Integer.parseInt(number));
                    else continue;
                }
                return sum;
            }
            else if (delimiter3.equals("")){
                Formatter f = new Formatter();
                f.format("[%s%s] ?", delimiter, delimiter2);
                String[] str1 = numbers.split(String.valueOf(f));
                int sum = 0;
                for (String number : str1) {
                    if(isNumeric(number)) sum += ignore_number_bigger_than_1000(Integer.parseInt(number));
                    else continue;
                }
                return sum;
            }
            else{
                Formatter f1 = new Formatter();
                f1.format("[%s%s%s] ?", delimiter, delimiter2, delimiter3);
                String[] str1 = numbers.split(String.valueOf(f1));
                int sum = 0;
                for (String number : str1) {
                    if(isNumeric(number)) sum += ignore_number_bigger_than_1000(Integer.parseInt(number));
                    else continue;
                }
                return sum;
            }
        }
        /*else {
            int sum = 0;
            String numberOnly= numbers.replaceAll("[^0-9]", "");
            char[] res  = numberOnly.toCharArray();
            for(char number: res){
                sum+=  Integer.parseInt(String.valueOf(number));
            }
            return sum;
        }*/

    }

    public  static boolean consist_negative_numbers(String string){
        char[] ar = string.toCharArray();
        for(int i = 0; i<ar.length; i++){
            if(ar[i] == '-' && Character.isDigit(ar[i+1]))
                return true;
        }
        return false;
    }

    public  static String[] print_negative_numbers(String string){
       int j =0;
        char[] ar = string.toCharArray();
        String[] negatives = new String[10];
        for(int i = 0; i<ar.length; i++){
            if(ar[i] == '-' )
            {
                negatives[j] = "-";
               while (Character.isDigit(ar[i+1]) ){
                   negatives[j] = negatives[j]+ar[i + 1];
                   if( i+1== ar.length-1){
                       break;
                   }
                   else i++;
               }
               j++;
            }
        }
        return negatives;
    }

    public static int ignore_number_bigger_than_1000(int number){
        if(number>1000){
            return 0;
        }
        else return number;
    }

    public static boolean multipleDelimiter(String string){
        int sum = 0;
        char[] a = string.toCharArray();
        for(char ch: a){
            if (ch =='[')sum++;
        }
        if (sum == 1) return false;
        else return true;
    }
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}


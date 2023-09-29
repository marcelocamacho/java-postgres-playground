package com.hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
/*
4
99910001001
7891011
9899100
999100010001
 */
class SeparateNumbersResult {

    /*
     * Complete the 'separateNumbers' function below.
     *
     * The function accepts STRING s as parameter.
     */

    public static void separateNumbers(String s) {
        int count=0;
        int pivot=0;
        int len=1;
        List<String> beautifull = new ArrayList<>();
        int next = pivot+len;
        int end = next+len;
        String first;
        String second;
        //System.out.println(s);
        if(s.length()<2){
            System.out.println("NO");
        } else if (s.length()==2){
            if(Character.getNumericValue(s.charAt(1))==Character.getNumericValue(s.charAt(0))+1)
                System.out.println("YES "+s.charAt(0));
            else System.out.println("NO");
        } else{
           do {
                first = s.substring(pivot, next);
                second = s.substring(next, end);

               // System.out.printf("First: %s -> Second: %s%n", first, second);

                if(second.charAt(0) == '0' | second.length()>first.length()){
                    ++len;
                    //System.out.println("Aumenta o len -> "+len);
                } else if(first.charAt(first.length()-1) == '9' || first.length()>second.length()){
                    ++end;
                    //System.out.println("Aumenta o end -> "+end);
                }
                
                if(Integer.parseInt(second) == Integer.parseInt(first)+1){
                    beautifull.add(first);
                    pivot=next;
                    //System.out.println("Aumenta o pivot -> "+pivot);
                    end = end+len;  
                }
                
                next = pivot+len;
                count++;
                
            } while (end<s.length() || next<s.length()  || count>200);    
        
            if(Integer.parseInt(second) == Integer.parseInt(first)+1)
                    beautifull.add(second);
            
            String result = String.join("", beautifull);
            
            if(result.equalsIgnoreCase(s)){
                System.out.println("YES "+beautifull.get(0));
            } else System.out.println("NO");  
        }       
    }


}

public class SeparateNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                SeparateNumbersResult.separateNumbers(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}


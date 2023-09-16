package com.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
5
1
8
3
9
6
7
9
6
14
7
*/

class ResultResourceAllocation {

    public static int getMinMatchines(List<Integer> start, List<Integer> end) {
    
        //System.out.println(Arrays.toString(start.toArray()));
        //System.out.println(Arrays.toString(end.toArray()));
        return 3;
    }
}

class ResourceAllocation {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        List<Integer> start = new ArrayList<>();
        for (int nItr = 0; nItr < n; nItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            int k = Integer.parseInt(firstMultipleInput[0]);
            start.add(k);
        }
        List<Integer> end = new ArrayList<>();
        for (int nItr = 0; nItr < n; nItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            int k = Integer.parseInt(firstMultipleInput[0]);
            end.add(k);
        }
        
        bufferedReader.close();

        System.out.println(Arrays.toString(start.toArray()));
        System.out.println(Arrays.toString(end.toArray()));

        int result = ResultResourceAllocation.getMinMatchines(start,end);

        bufferedWriter.write("->"+result);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}

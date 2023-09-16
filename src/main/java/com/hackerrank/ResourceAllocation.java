package com.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* INPUT
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

class Task{
    private static int countTasks = 0;
    private final int id;
    private int start;
    private int end;
    private int totalTime;
    private boolean isAllocated;

    public Task(int startTime, int endTime){

        if(endTime > startTime){
            this.start = startTime;
            this.end = endTime;
            this.totalTime = this.end - this.start;
            this.id = countTasks++;
            this.isAllocated = false;
            
        } else {
            System.out.println("End time must be greather than start time;");
            this.id = 0;
        }    
    }

    public boolean isAllocated(){
        return this.isAllocated;
    }

    public void setAllocationStatus(boolean isAlloc){
        this.isAllocated = isAlloc; 
    }

    public int getId(){
        return this.id;
    }
    public int getTotalTime(){
        return this.totalTime;
    }
    public int getStart(){
        return this.start;
    }
    public int getEnd(){
        return this.end;
    }
    public String toString(){
        return "Task "+this.id+" : "+"("+this.getStart()+","+this.getEnd()+")";
    }

}

class Machine{
    private static int countMachines = 0;
    private final int id;
    private List<Task> tasks = new ArrayList<>();

    public Machine(Task task){
        this.id = countMachines++;
        this.tasks.add(task);
        setAllocationStatusOfTaskAdded();
    }

    public int getId(){
        return this.id;
    }
    public void setAllocationStatusOfTaskAdded(){
        this.tasks.get(this.tasks.size()-1).setAllocationStatus(true);
    }

    public void addTask(Task t){
        this.tasks.add(t);
        setAllocationStatusOfTaskAdded();
    }

    public int getTotalTimeAllocated(){
        int totalTime = 0;
        for(Task t: tasks){
            totalTime += t.getTotalTime();
        }
        return totalTime;
    }

    public String toString(){
        String machineIdentification = "Machine "+this.id+" ";
        String machinesTask = "";
        String timeAllocated = " -> "+getTotalTimeAllocated();
        for (Task t: this.tasks){
            machinesTask += "Task "+t.getId()+" ("+t.getStart()+","+t.getEnd()+") ";
        }
        return machineIdentification+machinesTask+timeAllocated;   
    }
}

class ResultResourceAllocation {

    public static int getMinMatchines(List<Integer> start, List<Integer> end) {
        
        List<Task> queue = new ArrayList<>();
        Machine[] cluster = new Machine[10];
        Arrays.fill(cluster,null);

        for (int i = 0; i < start.size(); i++) {
            queue.add(new Task(start.get(i), end.get(i)));
        }

        Machine server = new Machine(queue.get(0));
        cluster[0] = server;

        for (Task t : queue) {
            System.out.println(t.toString());
            for (int i=0;i < cluster.length; i++) {
                System.out.println("Machine "+cluster[i].getId()+" with "+ cluster[i].getTotalTimeAllocated()+" time allocated");
                if(t.isAllocated()){
                    System.out.println("Task has be allocated");
                    break;
                } else if(!t.isAllocated() && t.getStart() > cluster[i].getTotalTimeAllocated()){
                    cluster[i].addTask(t);
                    System.out.println("Add task to existent machine");
                    break;
                }else {
                    cluster[++i]=new Machine(t);
                    System.out.println("Add task to new machine");
                    break;
                }
            }
        }



        int result = 0;
        for (int i=0;i < cluster.length;i++) {
            if(cluster[i]!=null){
                System.out.println(cluster[i].toString());
                result++;
            }
        }
        return result;
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

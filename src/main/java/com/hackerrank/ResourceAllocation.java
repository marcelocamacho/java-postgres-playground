package com.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
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

    public Machine(){
        this.id = countMachines++;
    }

    public Machine(Task task){
        this.id = countMachines++;
        this.tasks.add(task);
        setAllocationStatusOfTaskAdded();
    }

    public List<Task> getTasks(){
        return this.tasks;
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
        for(Task t: this.tasks){
            totalTime += t.getTotalTime();
        }
        return totalTime;
    }

    public int getStartTimeOfFistTask(){
        int startTime=99;
        for(Task t: this.tasks){
            if(t.getStart()<startTime)
                startTime = t.getStart();
        }

        return startTime;
    }

    public int getEndTimeOfLastTask(){
        int endTime=0;
        for(Task t : this.tasks){
             if(t.getEnd()>endTime)
                endTime = t.getEnd();
        }
        return endTime;
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

    public static List<Machine> allocationStrategy(List<Machine> cluster, Task task){
            int machineID = 99;
            System.out.println(task);
        for (Machine machine : cluster) {
            int loadMachine = machine.getTotalTimeAllocated(); //0
            machineID = machine.getId();

            if(loadMachine==0){
                machine.addTask(task);
                machineID = machine.getId();
                task.setAllocationStatus(true);
            } else if(task.getEnd() < machine.getStartTimeOfFistTask()){
                machine.addTask(task);
                machineID = machine.getId();
                task.setAllocationStatus(true);
            } else if(task.getStart() > machine.getEndTimeOfLastTask()){
                machine.addTask(task);
                task.setAllocationStatus(true);
                machineID = machine.getId();
            }
        }

        if(!task.isAllocated()){
            cluster.add(new Machine(task));
            machineID = cluster.get(cluster.size()-1).getId();
        }

        System.out.println("Task "+task.getId()+" alocada na maquina "+machineID);
        return cluster;
    }

    public static int getMinMatchines(List<Integer> start, List<Integer> end) {
        
        List<Task> queue = new ArrayList<>();
        List<Machine> cluster = new ArrayList<>();

        for (int i = 0; i < start.size(); i++) {
            queue.add(new Task(start.get(i), end.get(i)));
        }
        
        for (Task t : queue) {

            if(cluster.isEmpty()){
                cluster.add(new Machine());
                System.out.println("Cluster is empity. Add new machine");
            } 
            
            allocationStrategy(cluster,t);
            
        }
        System.out.println("");
        System.out.println("Minimum number of machines in the cluster is: " + cluster.size());
        return cluster.size();
    }
}

class ResourceAllocation {
    public static void main(String[] args) throws IOException {
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedReader bufferedReader = new BufferedReader(new StringReader("5\n1\n8\n3\n9\n6\n7\n9\n6\n14\n7"));
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

        int result = ResultResourceAllocation.getMinMatchines(start,end);

        bufferedWriter.write(result);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}

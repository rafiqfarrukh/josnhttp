package com.company;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.*;
//class Forecast {
//    private String name;
//    private List<Double> forecast;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Double> getForecast() {
//        return new ArrayList<>(forecast);
//    }
//
//    public void setForecast(List<Double> forecast) {
//        this.forecast = forecast;
//    }
//
//    public String toString() {
//        List<String> forecastStrings = new ArrayList<>();
//        for (Double temp: forecast) {
//            forecastStrings.add(temp.toString());
//        }
//        String forecastString = String.join(", ", forecastStrings);
//        return String.format("The forecast for %s: is %s", name, forecastString);
//    }
//}
//
//class ForecastCollection extends ArrayList<Forecast> {}
//
//
//public class Main {
//    static final String FILENAME = "data.json";
//
//    public static ForecastCollection load(String filename) throws IOException {
//        Gson gson = new Gson();
//        FileReader reader = new FileReader(filename);
//        try {
//            return gson.fromJson(reader, ForecastCollection.class);
//        }
//        finally {
//            reader.close();
//        }
//    }
//
//
//    public static void save(String filename, ForecastCollection data) throws IOException {
//        Gson gson = new Gson();
//        FileWriter writer = new FileWriter(filename);
//        try {
//            gson.toJson(data, writer);
//        }
//        finally {
//            writer.close();
//        }
//    }
//
//
//
//
//
//
//
//    public static void main(String[] args) {
//        try {
//
//            // load forecasts from file
//            ForecastCollection forecasts = load(FILENAME);
//
//            // print each forecast from the file
//            for (Forecast f: forecasts) {
//                System.out.println(f);
//            }
//
//            // create new forecast
//            Forecast dayton = new Forecast();
//            dayton.setName("Dayton");
//
//            List<Double> daytonForecast = new ArrayList<>();
//            daytonForecast.add(70.0);
//            daytonForecast.add(75.0);
//            daytonForecast.add(80.0);
//            daytonForecast.add(72.5);
//            daytonForecast.add(70.0);
//
//            dayton.setForecast(daytonForecast);
//
//            forecasts.add(dayton);
//
//            // save the data to a file
//            save(FILENAME, forecasts);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
//class Task {
//    private String body;
//    private boolean done;
//    private int id;
//    private int priority;
//    private String title;
//
//    public String toString() {
//        return title + ": " + body;
//    }
//}
//
//class TaskCollection implements Iterable<Task>{
//    // should be private; public for simplicity
//    private List<Task> todos;
//
//    public Iterator<Task> iterator() {
//        return todos.iterator();
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        String jsonData = "{\n" +
//                "  \"todos\": [\n" +
//                "    {\n" +
//                "      \"body\": \"Walk the dog\",\n" +
//                "      \"done\": false,\n" +
//                "      \"id\": 0,\n" +
//                "      \"priority\": 3,\n" +
//                "      \"title\": \"dog\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"body\": \"Pay the bills\",\n" +
//                "      \"done\": false,\n" +
//                "      \"id\": 1,\n" +
//                "      \"priority\": 1,\n" +
//                "      \"title\": \"bills\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//
//        Gson gson = new Gson();
//        TaskCollection tasks = gson.fromJson(jsonData, TaskCollection.class);
//        for (Task t: tasks) {
//            System.out.println(t);
//        }
//
//        System.out.println(gson.toJson(tasks));
//
//    }
//}
import java.util.*;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.Iterator;

/************************************************************************************************************
 * Name : Frank Rafiq
 * Instructor : Aurther N.
 * Class: CISC 2994
 * Project Name: Project 4
 * Due date 5/11/17
 *
 * Description: This program uses Collection class to add elements to an array, remove elements from an list,
 *              update and print the element of the array. It also implements Comparable and iterable interface.
 *              It takes an input from a Json data file and writes to json data file.
 *
 */



class TaskDetail implements Comparable<TaskDetail>{
    private String name;
    private String desc;
    private int priority;



    public void setName(String sName) {
        this.name = sName;
    }

    public void setDesc(String sDesc) {
        this.desc = sDesc;
    }

    public void setPriority(int sPriority) {
        this.priority = sPriority;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }


    @Override
    public int compareTo(TaskDetail other) {

        if (getPriority() - other.getPriority()== 0) {
            return name.compareTo(other.name);
        }
        else{
            return getPriority() - other.getPriority();
        }
    }

}

class TaskCollection implements Iterable<TaskDetail> {
    private List<TaskDetail> myTasks = new ArrayList<>();

    public void add(TaskDetail newTask) {
        myTasks.add(newTask);
    }

    public void remove(int removeIndex) {
        myTasks.remove(removeIndex);
    }

    public void update(int updateIndex, TaskDetail updateTask) {
        myTasks.set(updateIndex, updateTask);
    }

    public int indexOf(TaskDetail newTask) {
        return myTasks.indexOf(newTask);
    }

    @Override
    public void forEach(Consumer<? super TaskDetail> action) {

    }

    @Override
    public Iterator<TaskDetail> iterator() {
        return myTasks.iterator();
    }

    public void sort() {
        Collections.sort(myTasks);
    }


    public void display() {
        myTasks.forEach(tasks -> System.out.println("Task index: " + myTasks.indexOf(tasks) + ", " + "Name: "
                + tasks.getName() + ", " + "Description: " + tasks.getDesc() + ", " + "Priority: "
                + tasks.getPriority()));
    }

    public void displayByPriority(int checkPriority) {
        for (TaskDetail tasks : myTasks) {
            if (tasks.getPriority() == checkPriority) {
                System.out.println("Task index: " + myTasks.indexOf(tasks) + ", " + "Name: "
                        + tasks.getName() + ", " + "Description: " + tasks.getDesc() + ", " + "Priority: "
                        + tasks.getPriority());
            }
        }
    }
}

public class Main {
    static final String FILENAME = "task.json";

    public static TaskCollection load(String filename) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        try {
            return gson.fromJson(reader, TaskCollection.class);
        }
        finally {
            reader.close();
        }
    }


    public static void save(String filename, TaskCollection data) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(filename);
        try {
            gson.toJson(data, writer);
        }
        finally {
            writer.close();
        }
    }


    static int inputMethod() {
        int inputChoice = -1;
        boolean isInt = false;
        String inputName, inputDescription;
        Scanner input = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1) Add a task. ");
        System.out.println("2) Remove  a task. ");
        System.out.println("3) Update a task. ");
        System.out.println("4) List all tasks. ");
        System.out.println("5) List all tasks of a certain priority.");
        System.out.println("(0) Exit.");
        System.out.println("Choose an option.");
        while (!isInt) {

            // while outside try
            try {
                inputChoice = Integer.parseInt(input.nextLine());
                isInt = true;

            } catch (Exception e) {
                // while is based on inputchoice instead
                System.out.println("Choose an option.");
            }
        }

        return inputChoice;
    }

    public static void main(String[] args) {
        int choose, inputPriority, indexLocation =0;
        String inputName, inputDescription;
        String inputTask;
        boolean isInt = false;
        // collection of task detail
        TaskCollection collectionOfTasks = new TaskCollection();
        Scanner input = new Scanner(System.in);
        try {
            collectionOfTasks  = load(FILENAME);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        choose = inputMethod();
        while(choose != 0){
            if(choose == 1){
                //add
                TaskDetail myTask = new TaskDetail();
                isInt = false;
                System.out.println("Enter the new task's name. " );
                inputName = input.nextLine();
                myTask.setName(inputName);
                System.out.println("Enter the new task's description. " );
                inputDescription = input.nextLine();
                myTask.setDesc(inputDescription);
                System.out.println("Enter the new task's priority. ");

                while(!isInt){
                    try {
                        inputPriority = Integer.parseInt(input.nextLine());
                        isInt = true;
                        myTask.setPriority(inputPriority);
                        collectionOfTasks.add(myTask);
                        // save the data to a file
                        save(FILENAME, collectionOfTasks);
                    }
                    catch (Exception e) {
                        // while not int
                        System.out.println("Enter the new task's priority. ");
                    }
                }

            }// add
            else if(choose == 2){
                //remove
                isInt = false;
                while(!isInt){
                    System.out.println("Enter the index of the task to remove: ");
                    try {
                        indexLocation = Integer.parseInt(input.nextLine());
                        isInt = true;
                        collectionOfTasks.remove(indexLocation);
                        // save the data to a file
                        save(FILENAME, collectionOfTasks);
                    }
                    catch (Exception e){
                        System.out.println("Enter the index of the task to remove: ");

                    }

                }

                //remove
            }
            else if (choose == 3){
                //update;
                TaskDetail myTask = new TaskDetail();
                isInt = false;
                while(!isInt){
                    System.out.println("Enter the index of the task to update. ");
                    try {
                        indexLocation = Integer.parseInt(input.nextLine());

                        isInt = true;
                    }
                    catch(Exception e){
                        System.out.println("Enter the index of the task to update. ");
                    }
                }

                System.out.println("Enter the new task's name. " );
                myTask.setName(input.nextLine());
                System.out.println("Enter the new task's description. " );
                myTask.setDesc(input.nextLine());;
                isInt = false;
                while (!isInt){
                    try {
                        System.out.println("Enter the new task's priority. ");
                        myTask.setPriority(Integer.parseInt(input.nextLine()));
                        isInt = true;
                        collectionOfTasks.update(indexLocation,myTask);
                        // save the data to a file
                        save(FILENAME, collectionOfTasks);
                    }
                    catch (Exception e) {
                        // while not int
                        System.out.println("Enter the new task's priority. ");
                    }
                }

            }// update
            else if (choose == 4){
                //print collections;
                collectionOfTasks.sort();
                collectionOfTasks.display();

            }// print collections
            else if (choose == 5){
                // print base on priority
                TaskDetail myTask = new TaskDetail();
                isInt = false;
                System.out.println("Enter a priority.");
                while (!isInt){
                    try {
                        inputPriority = Integer.parseInt(input.nextLine());
                        isInt = true;
                        collectionOfTasks.displayByPriority(inputPriority);

                    }
                    catch (Exception e){
                        System.out.println("Enter a priority.");
                    }

                }
            }// print base on priority
            else if (choose == 0){
                //default
                System.exit(0);
            }

            choose=  inputMethod();

        }
    }
}

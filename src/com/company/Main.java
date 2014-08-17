package com.company;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static Gson gson = new Gson();
    private static Type marathonRunnerArrayListType = new TypeToken<ArrayList<MarathonRunner>>() {}.getType();
    private static File jsonFile = new File("./data/results.json");
    private static File textFile = new File("./data/results.txt");

    public static void main(String[] args) {

        ArrayList<MarathonRunner> runnerList = GetMarathonRunners();

        Collections.sort(runnerList);

        System.out.println("Runner (time)");
        for (MarathonRunner marathonRunner : runnerList)
            System.out.println(marathonRunner);

        WriteMarathonRunnerData(runnerList);
    }

    private static ArrayList<MarathonRunner> GetMarathonRunners() {
        ArrayList<MarathonRunner> runnerList = new ArrayList<MarathonRunner>();

        try {
            if (jsonFile.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile)));
                runnerList = gson.fromJson(bufferedReader, marathonRunnerArrayListType);
            } else if (textFile.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(textFile)));
                String marathonner;
                while ((marathonner = bufferedReader.readLine()) != null) {
                    String[] nameAndTime = marathonner.split(" ");
                    runnerList.add(new MarathonRunner(Integer.valueOf(nameAndTime[1]), nameAndTime[0]));
                }
            } else {
                System.err.println("No Marathon Data Found");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return runnerList;
    }

    private static void WriteMarathonRunnerData(ArrayList<MarathonRunner> runners) {
        try {
            FileWriter fileWriter = new FileWriter("./data/results.json");
            fileWriter.write(gson.toJson(runners));
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.company;

/*

Elena 341
Thomas 273
Hamilton 278
Suzie 329
Phil 445
Matt 402
Alex 388
Emma 275
John 243
James 334
Jane 412
Emily 393
Daniel 299
Neda 343
Aaron 317
Kate 265

 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static Type marathonRunnerArrayListType = new TypeToken<ArrayList<MarathonRunner>>() {}.getType();

    public static void main(String[] args) {

        ArrayList<MarathonRunner> runnerList = GetMarathonRunners();

        Collections.sort(runnerList);

        for (MarathonRunner marathonRunner : runnerList)
            System.out.println(marathonRunner);
        WriteMarathonRunnerData(runnerList);
    }

    private static ArrayList<MarathonRunner> GetMarathonRunners() {
        ArrayList<MarathonRunner> runnerList = new ArrayList<MarathonRunner>();
        File jsonFile = new File("./data/results.json");

        if (jsonFile.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile)));
                runnerList = gson.fromJson(bufferedReader, marathonRunnerArrayListType);
            } catch (Exception e) {
            }
        }
        else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./data/results.txt"))));
                String marathonner;
                while ((marathonner = bufferedReader.readLine()) != null) {
                    String[] nameAndTime = marathonner.split(" ");
                    runnerList.add(new MarathonRunner(Integer.valueOf(nameAndTime[1]), nameAndTime[0]));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

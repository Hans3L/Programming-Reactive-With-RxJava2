package com.creativity.reactiveprogramming.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJSONExample {

    public FileWriter getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(FileWriter jsonFile) {
        this.jsonFile = jsonFile;
    }

    private FileWriter jsonFile;

    public static void main(String[] args) {
        //primera data
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Hansel");
        employeeDetails.put("lastName", "Ingaruca Rimac");
        employeeDetails.put("country", "Peru");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);

        //segunda data
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Jah");
        employeeDetails2.put("lastName", "Jerusalem");
        employeeDetails2.put("country", "Israel");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);

        //agregamos a una lista

        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);

        //Escribir en un Archivo Json

        try (FileWriter jsonFile = new FileWriter("employees.json")) {
            jsonFile.write(employeeList.toJSONString());
            jsonFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

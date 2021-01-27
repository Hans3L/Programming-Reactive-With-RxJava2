package com.creativity.reactiveprogramming.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadJSONExample {
    public static void main(String[] args) {
        //JSON parsea un objeto a un parseo de archvo lectura
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("employees.json")) {
            //Leer JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            employeeList.forEach(emp -> parseEmployeeObject((JSONObject) emp));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void parseEmployeeObject(JSONObject employee) {
        //obtencion del objecto empleado dentro de la lista
        JSONObject employeeObject = (JSONObject) employee.get("employee");
        //obtener firstName empleado
        String firstName = employeeObject.get("firstName").toString();
        System.out.println(firstName);
        //obtener el lastName del empleado
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);
        //obtener el age del empleado
        String age = (String) employeeObject.get("country");
        System.out.println(age);
    }
}

package system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Employee {
    private int Id;
    private String name;
    private int password;
    private boolean isManager;
    private static int NumOfEmployees;
    private static Employee[] employees = setEmployeesInFunc();

    // getters & setters

        public static Employee[] getEmployees () {
        return employees;
    }

        public boolean isManager () {
        return isManager;
    }

        public void setManager ( boolean manager){
        isManager = manager;
    }

        public int getId () {
        return Id;
    }

        public String getName () {
        return name;
    }

        public int getPassword () {
        return password;
    }

        public static int getNumOfEmployees () {
        return NumOfEmployees;
    }

        public void setId ( int id){
        Id = id;
    }

        public void setName (String name){
        this.name = name;
    }

        public void setPassword ( int password){
        this.password = password;
    }

    ///////////////////////////////////////////////

    public Employee(int id, String name, int password, boolean isManager) {
        Id = id;
        this.name = name;
        this.password = password;
        this.isManager = isManager;
    }

    private static Employee[] setEmployeesInFunc() { // return array of all the employees

        Scanner x;
        Employee[] employees = new Employee[100];
        try {
            x = new Scanner(new File("Employees_Data.txt"));
            while (x.hasNext()) {

                int tempId = Integer.parseInt(x.next());
                String tempName = x.next();
                int tempPassword = Integer.parseInt(x.next());
                boolean tempIsManager = Boolean.parseBoolean(x.next());


                Employee tempEmployee = new Employee(tempId, tempName, tempPassword, tempIsManager);
                employees[NumOfEmployees] = tempEmployee;
                NumOfEmployees++;
            }
            x.close();
        } catch (IOException e) {
            System.out.println("Error occurred in Employee/getEmployee");
        }

        return employees;
    } // collecting initial data from file

    public void printEmployee() {

        System.out.println(this.getId());
        System.out.println(this.getName());
        System.out.println(this.getPassword());
        System.out.println("Is Employee Manager: " + this.isManager);

    }

    public void addEmployeeToFile() {

      try{

          BufferedWriter writer = new BufferedWriter(new FileWriter("Employees_Data.txt",true));
          if(NumOfEmployees != 0)
              writer.newLine();
          writer.append(Integer.toString(this.getId()));
          writer.newLine();
          writer.append(this.getName());
          writer.newLine();
          writer.append(Integer.toString(this.getPassword()));
          writer.newLine();
          if(this.isManager())
              writer.append("true");
          else{writer.append("false");}
          writer.close();

      }catch (IOException e){
          System.out.println("error occurred in Employee/addEmployee");

      }
    }

    public static void addEmployee(Employee employee){
        employee.addEmployeeToFile();
        employees[NumOfEmployees] = employee;
        NumOfEmployees++;
    }

    public static void removeEmployee(Employee employee){

        for(int i = 0 ;i < NumOfEmployees ; i++){
            if (employees[i].getId() == employee.getId()) {
                for (int j = i; j < NumOfEmployees; j++)
                    employees[i] = employees[j];
                NumOfEmployees--;
            }
        }
        refreshData();
    }

    public static void refreshData(){

        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter("Employees_Data.txt",false));
            for (int i = 0 ; i < NumOfEmployees ; i++) {
                writer.append(Integer.toString(employees[i].getId()));
                writer.newLine();
                writer.append(employees[i].getName());
                writer.newLine();
                writer.append(Integer.toString(employees[i].getPassword()));
                writer.newLine();
                if (employees[i].isManager())
                    writer.append("true");
                else {
                    writer.append("false");
                }
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            System.out.println("error occurred in Employee/refreshData");

        }
    } // moving data from array to file

    public static Employee searchEmployee(int Id){

        for(int i = 0 ;i < NumOfEmployees ; i++){
            if (employees[i].getId() == Id)
                return employees[i];
        }
        return  new Employee(0,"no employee",0,false);

    }

    public static Employee searchEmployee(String name){

        for(int i = 0 ;i < NumOfEmployees ; i++){
            if (employees[i].getName().equalsIgnoreCase(name))
                return employees[i];
        }
        return  new Employee(0,"no employee",0,false);

    }




}
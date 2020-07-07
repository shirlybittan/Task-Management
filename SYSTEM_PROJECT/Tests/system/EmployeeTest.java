package system;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    protected static Employee employee = new Employee(123,"test",123,false);


    @Test
    public void isManager() {
        assertEquals(employee.isManager(),false);
    }

    @Test
    public void setManager() {
        employee.setManager(true);
        assertEquals(employee.isManager(),true);
        employee.setManager(false);


    }

    @Test
    public void getId() {
        assertEquals(employee.getId(),123);

    }

    @Test
    public void getName() {
        assertEquals(employee.getName(),"test");

    }

    @Test
    public void getPassword() {
        assertEquals(employee.getPassword(),123);

    }

    @Test
    public void setId() {
        employee.setId(555);
        assertEquals(employee.getId(),555);

    }

    @Test
    public void setName() {
        employee.setName("pirate");
        assertEquals(employee.getName(),"pirate");

    }

    @Test
    public void setPassword() {
        employee.setPassword(666);
        assertEquals(employee.getPassword(),666);
        employee.setPassword(123);

    }

    @Test
    public void Employee_Adding_Searching_Deleting() {
        Employee.addEmployee(employee);
        assertEquals(Employee.searchEmployee(employee.getId()),employee);// check search by ID
        assertEquals(Employee.searchEmployee(employee.getName()),employee);// check search by NAME
        Employee.removeEmployee(employee);
        Employee tempEmployee = Employee.searchEmployee(employee.getId());
        assertEquals(tempEmployee.getId(),0);// check if employee has been deleted

    }

}
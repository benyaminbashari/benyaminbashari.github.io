public class Employee {
    private int hours, vacationDays;
    private double salary;

    public Employee() {
        hours = 40;
        vacationDays = 20;
        salary = 100000.00;
    }

    public double getSalary() {
        return salary;
    }

    public int getHours() {
        return hours;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "hours=" + hours +
                ", vacationDays=" + vacationDays +
                ", salary=" + salary +
                '}';
    }
}

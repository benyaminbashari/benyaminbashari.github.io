public class Manager extends Employee {
    private int vacationDays;

    public Manager() {
        super();
        vacationDays = super.getVacationDays() + 10;
    }

    @Override
    public double getSalary() {
        return super.getSalary() * 3;
    }

    public void doManagerStuff() {
        System.out.println("Doing manager stuff");
    }

    @Override
    public String toString() {
        return "Employee{" +
                "hours=" + super.getHours() +
                ", vacationDays=" + vacationDays +
                ", salary=" + getSalary() +
                '}';
    }
}

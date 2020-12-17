public class Main {

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Sheldon Cooper",
                "Head of Research", "bazinga@cooper.com",
                "37876497826", 70000, 22);
        employees[1] = new Employee("Leonard Hoffsteader",
                "Professor", "leoNerd@cooper.com",
                "37983298479", 60000, 42);
        employees[2] = new Employee("Rajesh Koothrapalli",
                "Scientist", "mumbaibuns@cooper.com",
                "37564875634", 55000, 39);
        employees[3] = new Employee("Howard Wolowitz",
                "Engineer", "wolowizard@mit.com",
                "37487568347", 50000, 55);
        employees[4] = new Employee("Barry Kripke",
                "Scientist", "kripto@kripke.com",
                "37465873465", 70000, 61);

        selectEmployees(employees, 40);
    }

    static void selectEmployees(Employee[] employees, int age){
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > age){
                employees[i].printProfile();
            }
        }
    }
}

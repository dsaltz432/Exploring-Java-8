import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestingComparators {

    public static void main(String[] args){
        TestingComparators instance = new TestingComparators();
        instance.run();
    }

    private void run() {
        // new way of sorting objects
        Employee john = new Employee("John");
        Employee lucy = new Employee("Lucy");
        Employee suzy = new Employee("Suzy");
        Employee ernie = new Employee("Ernie");

        List<Employee> s1 = Arrays.asList(john, lucy, suzy, ernie);
        List<Employee> s2 = Arrays.asList(john, lucy, suzy, ernie);
        sortUsingJava7(s1);
        sortUsingJava8(s2);
        System.out.println("\n Testing new sorting technique:");
        System.out.println(s1);
        System.out.println(s2);
    }

    private void sortUsingJava7(List<Employee> list) {
        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getName().compareTo(e2.getName());
            }
        });
    }

    private void sortUsingJava8(List<Employee> list) {
        Collections.sort(list, (e1, e2) -> e1.getName().compareTo(e2.getName()));

        // could also do this:
//        Collections.sort(list, Comparator.comparing(Employee::getName));
    }


    class Employee {
        private String name;

        Employee(String name) {
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

}

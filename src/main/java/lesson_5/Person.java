package lesson_5;

public class Person {
    private String name;
    private String position;
    private String eMail;
    private String telephoneNumber;
    private int salary;
    private int age;

    public Person(String name, String position, String eMail, String telephoneNumber, int salary, int age) {
        this.name = name;
        this.position = position;
        this.eMail = eMail;
        this.telephoneNumber = telephoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo(){

        System.out.printf("%30s%15s%30s%15s%10d%10d",name,position,eMail,telephoneNumber,salary,age);
    }

    public void printTop(){
        System.out.printf("%30s%15s%30s%15s%10s%10s","ФИО", "Должность", "Электронная почта", "Телефон", "Зарплата", "Возраст");
        System.out.println();
        for (int i = 1; i <= 110; i++){
            System.out.print("=");
        }
        System.out.println();
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

package lesson_5;

public class Persons {

    public static void main(String[] args) {

        Person[] person = new Person[5];

        initPerson(person);

        allPersons(person);

        ageFilter(person, 40);

    }

    protected static void initPerson(Person[] person) {
        person[0] = new Person("Смолин Владимир Петрович", "Бухгалтер", "smolin_vp@mail.ru", "89004322143", 27000, 47);
        person[1] = new Person("Владимиров Петр Александрович", "Водитель", "vladimirom78_a@ya.ru", "89034561233", 30000, 42);
        person[2] = new Person("Петров Александр Васильевич", "Инженер", "petrov@mail.ru", "89091239078", 60000, 35);
        person[3] = new Person("Александров Василий Петрович", "Программист", "spiderman@mail.ru", "89010020304", 80000, 18);
        person[4] = new Person("Васильев Петр Сергеевич", "Директор", "vasilev_direct@mail.ru", "89001002030", 15000, 55);
    }

    protected static void allPersons(Person[] person) {
        person[0].printTop();

        for(int i = 0; i < 5; i++) {
                person[i].printInfo();
                System.out.println();
        }
    }

    protected static void ageFilter(Person[] person, int age) {
        System.out.println();
        System.out.println("Сотрудники старше 40 лет:");
        person[0].printTop();

        for(int i = 0; i < 5; i++) {
            if (person[i].getAge() > age) {
                 person[i].printInfo();
                 System.out.println();
             }
        }
    }
}

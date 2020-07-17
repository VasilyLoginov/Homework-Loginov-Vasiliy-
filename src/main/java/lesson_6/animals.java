package lesson_6;

public class animals {

    public static void main(String[] args) {

        Cat cat1 = new Cat();
        Dog dog1 = new Dog();
        Cat cat2 = new Cat();
        Dog dog2 = new Dog();

        print("cat1: ");
        cat1.runLimit = 200;
        cat1.run(150);
        cat1.swim();
        cat1.jumpLimit = 2;
        cat1.jump(1);

        print("dog1: ");
        dog1.runLimit = 400;
        dog1.run(450);
        dog1.swimLimit = 10;
        dog1.swim(12);
        dog1.jumpLimit = 0.5;
        dog1.jump(0.2);

        print("cat2");
        cat2.runLimit = 150;
        cat2.run(200);
        cat2.swim();
        cat2.jumpLimit = 3;
        cat2.jump(4);

        print("dog2: ");
        dog2.runLimit = 600;
        dog2.run(500);
        dog2.swimLimit = 20;
        dog2.swim(15);
        dog2.jumpLimit = 1;
        dog2.jump(0.7);
    }

    public static void print(String idAnimal) {
        System.out.println();
        System.out.println(idAnimal);
    }


}

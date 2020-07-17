package lesson_6;

//1. Создать классы Собака и Кот с наследованием от класса Животное.
//2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
//  В качестве параметра каждому методу передается величина, означающая или длину препятствия
//  (для бега и плавания), или высоту (для прыжков).
//3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.;
//  прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
//4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль.
//  (Например, dog1.run(150); -> результат: run: true)
//5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м.,
//  у другой 600 м.

public class Animal {

    protected int runLimit;
    protected int swimLimit;
    protected double jumpLimit;

    public void run(int distance){
        if(distance > 0 && distance <= runLimit) {
            System.out.println("Run: true");
            return;
        }
        System.out.println("Run: false");
    }

    public void swim(int distance){
        if(distance > 0 && distance <= swimLimit) {
            System.out.println("Swim: true");
            return;
        }
        System.out.println("Swim: false");
    }

    public void jump(double height){
        if(height > 0 && height <= jumpLimit) {
            System.out.println("Jump: true");
            return;
        }
        System.out.println("Jump: false");
    }

    public void setRunLimit(int runLimit) {
        this.runLimit = runLimit;
    }

    public void setSwimLimit(int swimLimit) {
        this.swimLimit = swimLimit;
    }

    public void setJumpLimit(double jumpLimit) {
        this.jumpLimit = jumpLimit;
    }
}

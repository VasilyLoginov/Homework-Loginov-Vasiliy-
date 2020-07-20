package lesson_7;

public class Feeding_Cats {

    public static final int numberOfCats = 5;
    public static final int numberOfPlates = 5;
    public static final int capacityOfPlate = 20;
    public static final int firstLevelOfPlate = 3;


    public static Cat[] cat = new Cat[numberOfCats];
    public static Plate[] plates = new Plate[numberOfPlates];

    public static Plate plate = new Plate(capacityOfPlate, firstLevelOfPlate);

    public static void main(String[] args) {

        initCats();
        initPlates();

        printInfo(cat);

        feedingCats(cat, plates);

        printInfo(cat);

    }

    public static void initCats() {
        cat[0] = new Cat("Барсик", 10, 5);
        cat[1] = new Cat("Мурзик", 9, 9);
        cat[2] = new Cat("Амур", 7, 4);
        cat[3] = new Cat("Васька", 8, 5);
        cat[4] = new Cat("Кузя", 10, 6);
    }

    public static void initPlates(){
        for(int i = 0; i < numberOfPlates; i++){
            plates[i] = new Plate(capacityOfPlate, firstLevelOfPlate);
        }
    }

    public static void printTop(){
        System.out.printf("%10s%15s%10s%11s%15s","Имя кота", "Прожорливость", "Аппетит", "Кот сытый", "Еды в мисках:");
        System.out.println();
        for (int i = 1; i <= 71; i++){
            System.out.print("=");
        }
        System.out.println();
    }

    public static void printAllCats(Cat[] cat){
        for(int i = 0; i < numberOfCats; i++){
            cat[i].info();
            printPlateInfo();
            System.out.println();
        }
    }

    public static void printPlateInfo(){
        for (int i = 0; i < numberOfPlates; i++) {
            plates[i].infoPlate();
        }
    }


    public static void printInfo(Cat[] cat){
        printTop();
        printAllCats(cat);
    }

    public static void feedingCats(Cat[] cat, Plate[] plates){
        int numberPlate = 0;
        int food;
        for(int i = 0; i < numberOfCats; i++) {
            while (cat[i].getAppetite() != 0 ) {
                food = plates[numberPlate].takeFood(cat[i].getAppetite());
                if (food == 0) {
                    numberPlate++;
                    if(numberPlate == numberOfPlates){
                        System.out.println("Еда законилась во всех тарелках!");
                        numberPlate = 0;
                        System.out.println("Добавим ещё еды.");
                        plates[numberPlate].addFood(firstLevelOfPlate);
                    }
                    food = plates[numberPlate].takeFood(cat[i].getAppetite());
                }
                if(numberPlate == numberOfPlates){
                    System.out.println("Еда законилась во всех тарелках!");
                    return;
                }
                cat[i].eat(food);
            }
        }
    }
}

package lesson_7;

public class Plate {

    protected int capacityPlate;
    protected int foodRate;

    public Plate(int capacityPlate, int foodRate ) {
        this.capacityPlate = capacityPlate;
        this.foodRate = foodRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void infoPlate(){
        System.out.printf("%5d", foodRate);
    }

    public void addFood(int food) {
        foodRate += food;
        if(foodRate >= capacityPlate){
            foodRate = capacityPlate;
            System.out.println("Миска уже полна еды! " + foodRate);
            return;
        }

    }

    public int takeFood(int food){
        if(food < foodRate){
            foodRate -= food;
            return food;
        }
        food = foodRate;
        foodRate = 0;
        return food;
    }
}

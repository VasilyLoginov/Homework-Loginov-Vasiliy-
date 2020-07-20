package lesson_7;

public class Cat {
    private String name;
    private int maxAppetite;
    private int appetite; //0 - сытый ...
    private boolean isFullCat;

    public Cat(String name, int maxAppetite,int appetite) {
        this.name = name;
        this.maxAppetite = maxAppetite;
        this.appetite = (appetite > maxAppetite)?maxAppetite:appetite;
        isFullCat = (appetite == 0)?true:false;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public int getMaxAppetite() {
        return maxAppetite;
    }

    public boolean isFullCat() {
        return isFullCat;
    }

    public void eat(int food) {
        appetite -= food;
        if(appetite == 0){
            isFullCat = true;
            return;
        }
        isFullCat = false;
        //System.out.println(name + "у не досталось еды! " + isFullCat);

    }

    public void info(){
        System.out.printf("%10s%15d%10d%11b", name, maxAppetite, appetite, isFullCat);
    }

}

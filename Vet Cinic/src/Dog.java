public class Dog extends Pet{
    private double droolRate;

    public Dog(String name, double health, int painLevel, double droolRate) {
        super(name, health, painLevel);
        if (droolRate <= 0)
            this.droolRate = 0.5;
        else
            this.droolRate = droolRate;
    }

    public Dog(String name, double health, int painLevel) {
        this(name, health, painLevel, 5);
    }

    public double getDroolRate() {
        return droolRate;
    }

    public int treat(){
        int minutes;
        if (this.droolRate < 3.5){
            minutes = (int)(Math.ceil(painLevel * 2)/health);
        }
        else if (this.droolRate > 7.5){
            minutes = (int)(Math.ceil(painLevel/(health*2)));
        }
        else {
            minutes = (int)(Math.ceil(painLevel/health));
        }
        super.heal();
        return minutes;
    }

    @Override
    public void speak(){
        super.speak();
        if (painLevel > 5)
            for (int i = 0; i < painLevel; i++){
                System.out.print("BARK ");
            }
        else{
            for (int i = 0; i < painLevel; i++){
                System.out.print("bark ");
            }
        }
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj) && this.droolRate == ((Dog)obj).getDroolRate();
    }
}

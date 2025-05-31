
public class Cat extends Pet{
    private int miceCaught;

    public Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);
        if (miceCaught < 0)
            miceCaught = 0;
        this.miceCaught = miceCaught;
    }

    public Cat(String name, double health, int painLevel) {
        this(name, health, painLevel, 0);
    }

    public int getMiceCaught() {
        return miceCaught;
    }

    public int treat(){
        int minutes;
        if (miceCaught > 7)
            minutes = (int)Math.ceil(painLevel/(health*2));
        else if (miceCaught < 4)
            minutes = (int)Math.ceil((painLevel * 2)/(health));
        else
            minutes = (int)Math.ceil(painLevel/(health));
        super.heal();
        return minutes;
    }

    public void speak(){
        super.speak();
        if (painLevel > 5) {
            for (int i = 0; i < miceCaught; i++)
                System.out.print("MEOW ");
        }
        else {
            for (int i = 0; i < miceCaught; i++)
                System.out.print("meow ");
        }
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj) && this.miceCaught == ((Cat)obj).getMiceCaught();
        }
}

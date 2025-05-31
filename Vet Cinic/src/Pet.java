public abstract class Pet {
    protected String name;
    protected double health;
    protected int painLevel;

    public Pet(String name, double health, int painLevel) {
        this.name = name;
        if (health < 0)
            this.health = 0;
        else if (health > 1)
            this.health = 1.0;
        else
            this.health = health;
        if (painLevel < 1)
            this.painLevel = 1;
        else if (painLevel > 10)
            this.painLevel = 10;
        else
            this.painLevel = painLevel;
    }

    public String getName(){
        return name;
    }

    public double getHealth(){
        return health;
    }

    public int getPainLevel(){
        return painLevel;
    }

    public abstract int treat();

    public void speak(){
        String stringSpeak = "Hello! My name is " + name;
        if (painLevel > 5)
            System.out.println(stringSpeak.toUpperCase());
        else
            System.out.println(stringSpeak);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (obj instanceof Pet) {
            Pet pet = (Pet) obj;
            return this.name.equals(pet.name);
            }
        return false;
    }

    protected void heal(){
        health = 1.0;
        painLevel = 1;
    }
}

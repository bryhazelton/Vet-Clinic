import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Clinic {

    private File patientFile;
    private int day;
    private String newpatientstr = "";



    public Clinic(File file){
        patientFile = file;
        day = 1;
        }

    public Clinic(String file){
        this(new File(file));
        }

    public String nextDay(File file) throws FileNotFoundException, InvalidPetException{
        day++;
        Scanner input = null;
        Scanner userinput = new Scanner(System.in);
        String name;
        String typeOfPet;
        double droolRate = 0.0;
        int miceCaught = 0;
        String timeIn;
        String timeOut;
        int timeTaken;
        int pain = 0;
        double health = 0;
        String currentLine = "";
        String [] splitLine = null;
        String patientstr = "";


        try {
            input = new Scanner(file);
            while (input.hasNextLine()) {
                currentLine = input.nextLine();
                splitLine = currentLine.split(",");
                name = splitLine[0];
                typeOfPet = splitLine[1];
                if (typeOfPet.equals("Dog")) {
                    droolRate = Float.parseFloat(splitLine[2]);
                } else {
                    miceCaught = Integer.parseInt(splitLine[2]);
                }
                timeIn = splitLine[3];
                //System.out.printf("%s %s %d %s", name, typeOfPet, specialty, timeIn);

                if (!typeOfPet.equals("Dog") && !typeOfPet.equals("Cat")) {
                    throw new InvalidPetException();
                }

                System.out.printf("Consultation for %s the %s at %s.%n", name, typeOfPet, timeIn);
                boolean valid = false;
                while (!valid) {
                    System.out.print("On a scale of 1 to 10 (10 best), what is the health of " + name + "? \n");
                    if (userinput.hasNextDouble()) {
                        health = userinput.nextDouble();
                        valid = true;
                    } else {
                        System.out.println("Please enter a valid number");
                        userinput.next();
                    }
                }
                valid = false;
                while (!valid) {
                    System.out.print("On a scale of 1 to 10, how much pain is " + name + " in right now?\n");
                    if (userinput.hasNextInt()) {
                        pain = userinput.nextInt();
                        valid = true;
                    } else {
                        System.out.println("Please enter a valid number");
                        userinput.next();
                    }
                }

                if (typeOfPet.equals("Dog")) {
                    Dog dog = new Dog(name, health, pain, droolRate);
                    health = dog.getHealth();
                    dog.speak();
                    System.out.println();
                    timeTaken = dog.treat();
                    timeOut = addTime(timeIn, timeTaken);
                    patientstr += String.format("%s,%s,%.1f,Day %d,%s,%s,%.1f,%d%n", name, typeOfPet, droolRate, day, timeIn, timeOut, health, pain);
                } else {
                    Cat cat = new Cat(name, health, pain, miceCaught);
                    health = cat.getHealth();
                    cat.speak();
                    System.out.println();
                    timeTaken = cat.treat();
                    timeOut = addTime(timeIn, timeTaken);
                    patientstr += String.format("%s,%s,%d,Day %d,%s,%s,%.1f,%d%n", name, typeOfPet, miceCaught, day, timeIn, timeOut, health, pain);
                }
            }
        } catch(FileNotFoundException e){
                System.out.println("File not found");
                System.out.println(e.getMessage());
            }
        if (input != null) {
            input.close();
            }
        if (userinput != null) {
            userinput.close();
            }
        return patientstr;
    }

    public String nextDay(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return nextDay(file);
        }

    public boolean addToFile(String patientInfo){
        boolean written = false;
        String [] splitPatient = patientInfo.split(",");
        String name = splitPatient[0];
        String addInfo = ",";
        for (int i = 3; i < splitPatient.length; i++){
            addInfo += splitPatient[i] + ",";
            }
        
        Scanner input = null;
        try {
            input = new Scanner(patientFile);
            while (input.hasNextLine()) {
                String patient = input.nextLine();
                if (patient.contains(name)) {
                    newpatientstr += patient + addInfo + "\n";
                    return written = true;
                } else {
                    if (!newpatientstr.contains(patientInfo)) {
                        newpatientstr += patientInfo + "\n";
                    }
                    if (!newpatientstr.contains(patient)) {
                        newpatientstr += patient + "\n";
                    }
                }
            }
            } catch(FileNotFoundException e){
                System.out.println(e.getMessage());
            } finally{
                if (input != null)
                    input.close();
            }

       try (PrintWriter print = new PrintWriter(patientFile)) {
            print.print(newpatientstr);
            written = true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return written;
    }

    private static String addTime(String timeIn, int treatmentTime){
        int hour = Integer.parseInt(timeIn.substring(0, 2));
        int minute = Integer.parseInt(timeIn.substring(2, 4));

        hour += treatmentTime/60;
        minute += treatmentTime%60;
        return String.format("%02d%02d", hour, minute);
    }
}

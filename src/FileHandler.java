import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final String filename = "data/animals.csv";

    public ArrayList<Animal> loadAnimalsFromFile() {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            // Åbn en Scanner der læser fra csv-filen
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                Animal animal = parseCSVLineToAnimal(line);
                animals.add(animal);
            }
        } catch (FileNotFoundException exception) {
            // Ignorér fejl - sørg bare for at det er en tom liste
            animals.clear();
        }
        return animals;
    }

    private Animal parseCSVLineToAnimal(String line) {
        // Opret et tomt Animal-objekt til at returnere
        Animal animal = null;

        // Split linjen op i strings adskilt af ;
        String[] parts = line.split(";");

        // Og træk variablerne ud, i den rækkefølge de var skrevet
        try {
            String name = parts[0];
            String desc = parts[1];
            String type = parts[2];
            int age = Integer.parseInt(parts[3]);
            double weight = Double.parseDouble(parts[4]);

            // Opret et rigtigt animal-objekt med de værdier
            animal = new Animal(name,desc,type,age,weight);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            System.err.println("File error in line: " + line);
            ex.printStackTrace();
        }

        // Og returnerer objektet (eller null hvis der ikke var lavet noget)
        return animal;
    }

    public boolean saveAnimalsToFile(ArrayList<Animal> animals) {
        boolean success = false;
        // Åbn en PrintStream der skriver til filen
        try {
            PrintStream outputfile = new PrintStream(new File(filename));

            // for hvert objekt i listen:
            for (Animal animal : animals) {
                // kald en metode der skriver objektet til filen
                writeAnimalToCSVLine(outputfile, animal);
            }
            // slut af med at lukke filen
            outputfile.flush();
            outputfile.close();
            success = true;
        } catch (FileNotFoundException exception) {
            success = false;
        }

        return success;
    }

    private void writeAnimalToCSVLine(PrintStream outputfile, Animal animal) {
        // Hver attribut skrives til filen som en almindelig String
        outputfile.print(animal.getName());
        // efterfulgt af et ;
        outputfile.print(";");
        outputfile.print(animal.getDesc());
        outputfile.print(";");
        outputfile.print(animal.getType());
        outputfile.print(";");
        outputfile.print(animal.getAge());
        outputfile.print(";");
        outputfile.print(animal.getWeight());
        // undtaget den sidste, der efterfølges af et linjeskift
        outputfile.print("\n");
    }
}

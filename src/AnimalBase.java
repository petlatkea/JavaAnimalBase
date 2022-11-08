import java.util.ArrayList;

public class AnimalBase {

    private ArrayList<Animal> animals;

    public AnimalBase() {
        animals = new ArrayList<>();
    }

    public void start() {
        UserInterface ui = new UserInterface(this);
        ui.start();
    }

    public static void main(String[] args) {
        AnimalBase app = new AnimalBase();
        app.start();
    }

    public Iterable<Animal> getAllAnimals() {
        return animals;
    }

    public int getAnimalCount() {
        return animals.size();
    }

    public void sortBy(String sortBy, SortDirection sortDirection) {
        // TODO: Implement sorting!
        System.out.println("TODO: Sort the list of animals by: " + sortBy);
    }

    public void createNewAnimal(String name, String description, String type, int age, double weight) {
        Animal animal = new Animal(name,description,type,age,weight);
        animals.add(animal);
    }

    public boolean deleteAnimal(String name) {
        // find animal with this name
        Animal animal = findAnimalByName(name);
        if(animal == null) {
            return false;
        } else {
            animals.remove(animal);
            return true;
        }
    }

    private Animal findAnimalByName(String name) {
        for(Animal animal : animals) {
            if(animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }


    public void loadDatabase() {
        FileHandler fileHandler = new FileHandler();
        animals = fileHandler.loadAnimalsFromFile();

    }

    public boolean saveDatabase() {
        FileHandler fileHandler = new FileHandler();
        return  fileHandler.saveAnimalsToFile(animals);
    }

}

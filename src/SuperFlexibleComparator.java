import java.util.Comparator;

public class SuperFlexibleComparator implements Comparator<Animal> {
    private String sortBy;

    public SuperFlexibleComparator(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }


    @Override
    public int compare(Animal o1, Animal o2) {
        return switch (sortBy) {
            case "name" -> o1.getName().compareTo(o2.getName());
            case "type" -> o1.getType().compareTo(o2.getType());
            case "age" -> Integer.compare(o1.getAge(), o2.getAge());
            case "weigth" -> Double.compare(o1.getWeight(), o2.getWeight());
        };
    }
}

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
        int resultat = 0;
        switch (sortBy) {
            case "name" -> resultat = o1.getName().compareTo(o2.getName());
            case "type" -> resultat = o1.getType().compareTo(o2.getType());
            case "age" -> resultat = Integer.compare(o1.getAge(), o2.getAge());
            case "weigth" -> resultat = Double.compare(o1.getWeight(), o2.getWeight());
        }
        return resultat;
    }
}

import java.util.ArrayList;
import java.util.Collections;

interface Joinable<T> {
    public T join (T other);
}

class Circle implements Joinable<Circle>, Comparable<Circle>{
    private int radius;
    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public Circle join(Circle other) {
        return new Circle(radius+other.getRadius());
    }

    @Override
    public int compareTo(Circle circle) {
        return radius - circle.radius;
    }
}

public class InterfaceExample {

    public static Object joinAll(ArrayList<Joinable> v) {
        if(v.size() == 0)
            return null;
        Object ret = v.get(0);
        for(int i = 1 ; i < v.size() ; i++) {
            Joinable tmp = (Joinable) ret;
            ret = tmp.join(v.get(i));
        }
        return ret;
    }

    public static void main(String[] args) {

        //Sort Example
        Circle a = new Circle(22);
        Circle b = new Circle (1);
        Circle c = new Circle (17);
        ArrayList<Circle> arr = new ArrayList<>();
        arr.add(a);
        arr.add(b);
        arr.add(c);
        Collections.sort(arr);
        for(int i = 0 ; i < arr.size() ; i++)
            System.out.println(arr.get(i).getRadius());

        //Comparable Example
        /*Comparable a = new Circle(7);
        Comparable b = new Circle(15);

        System.out.println(a.compareTo(b));*/

        //joinAll Example
        /*Joinable a = new Circle(10);
        Joinable b = new Circle (20);
        Joinable c = new Circle (55);
        ArrayList<Joinable> arr = new ArrayList<>();
        arr.add(a);
        arr.add(b);
        arr.add(c);
        Circle ret = (Circle) joinAll(arr);
        System.out.println(ret.getRadius());*/

        //Joinable Example
        /*Circle a = new Circle(10);
        Circle b = new Circle (20);
        Circle c = a.join(b);
        System.out.println(c.getRadius());*/
    }
}

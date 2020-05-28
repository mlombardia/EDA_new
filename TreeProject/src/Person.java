public class Person implements Comparable<Person> {
    private Integer legajo;
    private String nombre;

    public Person(Integer legajo, String nombre){
        this.legajo = legajo;
        this.nombre = nombre;
    }

    public Integer getLegajo(){
        return this.legajo;
    }


    public String toString(){
        return String.format("legajo=%d\nnombre=%s", this.legajo,this.nombre);
    }

    @Override
    public int compareTo(Person o) {
        return this.legajo.compareTo(o.getLegajo());
    }
}

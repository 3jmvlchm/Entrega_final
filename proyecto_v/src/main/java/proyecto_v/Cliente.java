package proyecto_v;

/* 
 * Bean Cliente para mapear registros de la base de datos
 *
 * @author samue
 */
public class Cliente {

    private int id;
    private String name;
    private String last_name;

    public Cliente() {
    }

    public Cliente(String name, String last_name) {
        this.name = name;
        this.last_name = last_name;
    }

    public Cliente(int id, String name, String last_name) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}

package Relaciones7;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "profesores")
public class ProfesorEntity4 implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dni")
    private String dni;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="id")
    @OrderColumn(name="Idx")
    private List<CorreoElectronicoEntity2> correosElectronicos;

    public ProfesorEntity4() {
    }

    public ProfesorEntity4(String apellido, String nombre, String dni) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<CorreoElectronicoEntity2> getCorreosElectronicos() {
        return correosElectronicos;
    }

    public void setCorreosElectronicos(List<CorreoElectronicoEntity2> correosElectronicos) {
        this.correosElectronicos = correosElectronicos;
    }

    @Override
    public String toString() {
        return "ProfesorEntity3{" + "id=" + id + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", correosElectronicos=" + correosElectronicos + '}';
    }

}

package Relaciones8;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2021 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
 * - con licencia GNU GPL3.
 * <p> Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos de la
 * Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
 * bien con la versión 3 de dicha Licencia o bien (según su elección) con cualquier versión posterior. 
 * Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
 * incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN PROPÓSITO
 * PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.
 * Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
 * Si no ha sido así ingrese a <a href = "http://www.gnu.org/licenses/"> GNU org </a>
 */
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "profesores")
public class ProfesorEntity5 implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dni")
    private String dni;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "ProfesorMateria", joinColumns = {
        @JoinColumn(name = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idMateria")})
    private Set<MateriaEntity> materias = new HashSet();

    public ProfesorEntity5() {
    }

    public ProfesorEntity5(String apellido, String nombre, String dni) {
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

    public Set<MateriaEntity> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<MateriaEntity> materias) {
        this.materias = materias;
    }

    @Override
    public String toString() {
        return "ProfesorEntity5{" + "id=" + id + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", materias=" + materias + '}';
    }

}

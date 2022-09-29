package Relaciones6;
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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mails")
public class CorreoElectronicoEntity implements Serializable {

    @Id
    @Column(name = "IdCorreo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCorreo;
    @Column(name = "DireccionCorreo")
    private String direccionCorreo;
    @ManyToOne
    @JoinColumn(name = "Id")
    private ProfesorEntity3 profesor;

    public CorreoElectronicoEntity() {

    }

    public CorreoElectronicoEntity(String direccionCorreo, ProfesorEntity3 profesor) {
        this.direccionCorreo = direccionCorreo;
        this.profesor = profesor;
    }

    public int getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(int idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getDireccionCorreo() {
        return direccionCorreo;
    }

    public void setDireccionCorreo(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
    }

    public ProfesorEntity3 getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorEntity3 profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "CorreoElectronico{" + "idCorreo=" + idCorreo + ", direccionCorreo=" + direccionCorreo + '}';
    }

}

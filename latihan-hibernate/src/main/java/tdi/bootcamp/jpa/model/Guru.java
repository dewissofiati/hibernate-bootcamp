package tdi.bootcamp.jpa.model;

import javax.persistence.*;
import java.sql.Timestamp;

//@MappedSuperclass
@Embeddable
//@Entity
@Table(name = "Guru", schema = "public")
public class Guru {

    @Column(name = "id_guru", updatable = false, nullable = false)
    private Integer idGuru;

    @Column(name = "nama_guru", length = 100)
    private String namaGuru;

    public Integer getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(Integer idGuru) {
        this.idGuru = idGuru;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

}

package tdi.bootcamp.jpa.model;

import javax.persistence.Column;
import java.sql.Timestamp;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass //menandakan bahwa yang di kelas ini akan di wariskan atau diturunkan
public class BaseClass {

    @Column(name = "id_entry", length = 30)
    private String idEntry;
    @Column(name = "d_entry")
    private Timestamp tglEntry;

    public String getIdEntry() {
        return idEntry;
    }

    public void setIdEntry(String idEntry) {
        this.idEntry = idEntry;
    }

    public Timestamp getTglEntry() {
        return tglEntry;
    }

    public void setTglEntry(Timestamp tglEntry) {
        this.tglEntry = tglEntry;
    }
}

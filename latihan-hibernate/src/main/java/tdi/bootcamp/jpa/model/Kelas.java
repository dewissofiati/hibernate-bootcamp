package tdi.bootcamp.jpa.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "kelas", schema = "public")
public class Kelas extends BaseClass {

    @Id //sebagai primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_kelas", updatable = false, nullable = false)
    private int idKelas;

    @Column(name = "nama_kelas", length = 50)
    private String namaKelas;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_waliKelas")
    private WaliKelas waliKelas;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "kelas")
    List<Murid> daftarMurid;

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public List<Murid> getDaftarMurid() {
        return daftarMurid;
    }

    public void setDaftarMurid(List<Murid> daftarMurid) {
        this.daftarMurid = daftarMurid;
    }

    public WaliKelas getWaliKelas() {
        return waliKelas;
    }

    public void setWaliKelas(WaliKelas waliKelas) {
        this.waliKelas = waliKelas;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

}

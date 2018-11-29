package tdi.bootcamp.jpa.model;


import javax.persistence.*;

@Entity
@Table(name = "murid", schema = "public")
public class Murid extends BaseClass{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //hanya di generate tp tidak diisi
    @Column(name = "id_murid", updatable = false, nullable = false)
    private int idMurid;

    @Column(name = "nama_murid",length = 150)
    private String namaMurid;

    @OneToOne
    @JoinColumn(name = "id_kelas")
    private Kelas kelas;


    public int getIdMurid() {
        return idMurid;
    }

    public void setIdMurid(int idMurid) {
        this.idMurid = idMurid;
    }

    public String getNamaMurid() {
        return namaMurid;
    }

    public void setNamaMurid(String namaMurid) {
        this.namaMurid = namaMurid;
    }

    public Kelas getKelas() {
        return kelas;
    }

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }
}

package tdi.bootcamp.jpa.model;

import javax.persistence.*;

@Entity
@Table(name = "walikelas", schema = "public")
public class WaliKelas extends BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_waliKelas", updatable = false, nullable = false)
    private int idWaliKelas;

    @Column(name = "nama_waliKelas", length = 50)
    private String namaWaliKelas;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kelas")
    private Kelas kelas;

    @Embedded
    private Guru guru;

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    public int getIdWaliKelas() {
        return idWaliKelas;
    }

    public void setIdWaliKelas(int idWaliKelas) {
        this.idWaliKelas = idWaliKelas;
    }

    public String getNamaWaliKelas() {
        return namaWaliKelas;
    }

    public void setNamaWaliKelas(String namaWaliKelas) {
        this.namaWaliKelas = namaWaliKelas;
    }

    public Kelas getKelas() {
        return kelas;
    }

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }
}

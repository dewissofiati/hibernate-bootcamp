package tdi.bootcamp.jpa;

import org.hibernate.Session;
import tdi.bootcamp.jpa.model.*;

import tdi.bootcamp.jpa.util.HibernateUtil;

import java.sql.Timestamp;
import java.util.*;

public class MainApp {

	private static String getNativeQuery(Session session, String sql){
		return (String) session.createNativeQuery(sql).getSingleResult();
	}

//	========== SIMPAN WALIKELAS, KELAS, MURID =========
	public static Integer simpanSemua(Session session){
		Guru guru = new Guru();
		guru.setIdGuru(1);
		guru.setNamaGuru("Aziza");

		WaliKelas waliKelas = new WaliKelas();
		waliKelas.setGuru(guru);
//		waliKelas.setIdWaliKelas(5);
		waliKelas.setNamaWaliKelas("Aziza");
		waliKelas.setIdEntry("Senior");
		waliKelas.setTglEntry(new Timestamp(System.currentTimeMillis()));

		Kelas kelas = new Kelas();
		kelas.setNamaKelas("tiga");
		kelas.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		kelas.setIdEntry("lama");
		kelas.setTglEntry(new Timestamp(System.currentTimeMillis()));
		kelas.setWaliKelas(waliKelas);
		waliKelas.setKelas(kelas);

		List<Murid> daftarMurid = new ArrayList<>();
		for(int i=0; i<3; i++){
			Murid murid = new Murid();
			murid.setNamaMurid("Sofiati " + i);
			murid.setTglEntry(new Timestamp(System.currentTimeMillis()));
			murid.setKelas(kelas);
			daftarMurid.add(murid);
		}
		kelas.setDaftarMurid(daftarMurid);


		return (Integer) session.save(kelas);
	}

//	========== TAMBAH MURID ==========
	private static Integer simpanMurid(Session session){
		Kelas kelas = new Kelas();
		kelas.setNamaKelas("lima");
		kelas.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		kelas.setIdEntry("testing");
		kelas.setTglEntry(new Timestamp(System.currentTimeMillis()));

		Murid murid = new Murid();
		murid.setKelas(kelas);
		murid.setNamaMurid("Dewi");
		murid.setIdEntry("testing");
		murid.setTglEntry(new Timestamp(System.currentTimeMillis()));

		return (Integer) session.save(murid);
	}

//	========== UPDATE KELAS ==========
	private static void updateKelas(Session session) {
		Kelas kelas = session.find(Kelas.class, 92);
		kelas.setNamaKelas("empat");
		kelas.setCreatedDate(new Timestamp(System.currentTimeMillis()));

		session.update(kelas);
	}

//	========== UPDATE KELAS DGN HQL ==========
	private static int updateKelasLagi(Session session){
		return session.createQuery("update Kelas set nama_kelas = :nama where id_kelas = :id")
				.setParameter("nama", "enam")
				.setParameter("id", 97).executeUpdate();
	}

//	========== SELECT WALIKELAS DGN HQL ========
	private static Set<WaliKelas> getListWaliKelas(Session session){
		List<WaliKelas> ListData =
				session.createQuery(" select w from WaliKelas w where w.idWaliKelas = :namaWaliKelas")
						.setParameter("namaWaliKelas", 93)
						.getResultList();
		return new HashSet<>(ListData);
	}

//	========== DELETE MURID ==========
	private static void deleteMurid(Session session) {
		Murid murid= session.find(Murid.class, 100);
		session.delete(murid);
	}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.getTransaction().begin();
//		String result = getNativeQuery(session, "select version()");

//		MainApp.simpanMurid(session);
//		MainApp.simpanSemua(session);
		MainApp.deleteMurid(session);
//		MainApp.updateKelas(session);
//		MainApp.updateKelasLagi(session);

		session.flush();
		//===== mengampilkan HQL =====
		Set<WaliKelas> waliKelasSet = MainApp.getListWaliKelas(session);
		for(Iterator iterator = waliKelasSet.iterator(); iterator.hasNext();){
			WaliKelas waliKelas = (WaliKelas) iterator.next();
			System.out.println("nama == " + waliKelas.getIdWaliKelas() + " : " + waliKelas.getNamaWaliKelas());
		}

		session.close();

		HibernateUtil.shutdown();
	}
}
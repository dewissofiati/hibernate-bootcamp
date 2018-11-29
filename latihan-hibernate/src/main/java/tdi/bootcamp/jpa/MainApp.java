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

		List<Murid> daftarMurid = new ArrayList<>();
		for(int i=1; i<3; i++){
			Murid murid = new Murid();
			murid.setNamaMurid("RoseLy " + i);
			murid.setTglEntry(new Timestamp(System.currentTimeMillis()));
			murid.setKelas(kelas);
			daftarMurid.add(murid);
		}
		kelas.setDaftarMurid(daftarMurid);

		return (Integer) session.save(kelas);
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
	
//	========== SELECT KELAS DGN HQL ========
	private static Set<Kelas> getListKelas(Session session){
		List<Kelas> ListData =
				session.createQuery(" select k from Kelas k where k.idKelas = :namaKelas")
						.setParameter("namaKelas", 104)
						.getResultList();
		return new HashSet<>(ListData);
	}
//	========== SELECT MURID DGN HQL ========
	private static Set<Murid> getListMurid(Session session){
		List<Murid> ListData =
				session.createQuery(" select m from Murid m where m.idMurid = :namaMurid")
						.setParameter("namaMurid", 105)
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
//===== SELECT WALI KELAS DGN HQL =====
		Set<WaliKelas> waliKelasSet = MainApp.getListWaliKelas(session);
		for(Iterator iterator = waliKelasSet.iterator(); iterator.hasNext();){
			WaliKelas waliKelas = (WaliKelas) iterator.next();
			System.out.println("id waliKelas: " + waliKelas.getIdWaliKelas() + " , nama waliKelas: " + waliKelas.getNamaWaliKelas());
		}
//===== SELECT KELAS DGN HQL =====
		Set<Kelas> kelasSet = MainApp.getListKelas(session);
		for(Iterator iterator = kelasSet.iterator(); iterator.hasNext();){
			Kelas kelas = (Kelas) iterator.next();
			System.out.println("id kelas: " + kelas.getIdKelas() + " , nama kelas: " + kelas.getNamaKelas());
		}
//===== SELECT MURID DGN HQL =====
		Set<Murid> muridSet = MainApp.getListMurid(session);
		for(Iterator iterator = muridSet.iterator(); iterator.hasNext();){
			Murid murid = (Murid) iterator.next();
			System.out.println("id murid: " + murid.getIdMurid() + " , nama murid: " + murid.getNamaMurid());
		}

		session.close();

		HibernateUtil.shutdown();
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Mahasiswa.DAOMahasiswa;
import Model.Mahasiswa.InterfaceDAOMahasiswa;
import Model.Mahasiswa.ModelMahasiswa;
import Model.Mahasiswa.ModelTable;
import View.Mahasiswa.EditData;
import View.Mahasiswa.InputData;
import View.Mahasiswa.ViewMahasiswa;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class ControllerMahasiswa {
    ViewMahasiswa halamanTable;
    InputData halamanInput;
    EditData halamanEdit;
    
    InterfaceDAOMahasiswa daoMahasiswa;
    List<ModelMahasiswa> daftarMahasiswa;
    
    public ControllerMahasiswa(ViewMahasiswa halamanTable){
        this.halamanTable = halamanTable;
        this.daoMahasiswa = new DAOMahasiswa();
        
    }
        public ControllerMahasiswa(InputData halamanTable){
        this.halamanInput = halamanTable;
        this.daoMahasiswa = new DAOMahasiswa();
        
    }
        public ControllerMahasiswa(EditData halamanTable){
        this.halamanEdit = halamanTable;
        this.daoMahasiswa = new DAOMahasiswa();
        
    }
        
        public void showAllMahasiswa(){
            daftarMahasiswa = daoMahasiswa.getAll();
            
            ModelTable table = new ModelTable(daftarMahasiswa);
            
            halamanTable.getTableMahasiswa().setModel(table);
        }
        public void insertMahasiswa() {
        try {
            // Membuat "mahasiswa baru" yang isinya masih kosong
            ModelMahasiswa mahasiswaBaru = new ModelMahasiswa();
            
            /*
              Mengambil input nama dan nim menggunakan getter yang telah dibuat di view
              Nilai dari input kemudian disimpan ke dalam variabel "nama" dan "nim".
            */
            String nama = halamanInput.getInputNama();
            String nim = halamanInput.getInputNIM();

            /*
              Mengecek apakah input dari nama atau nim kosong/tidak.
              Jika kosong, maka buatlah sebuah exception.
             */
            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }
            
            // Mengisi nama dan nim dari "mahasiswa baru" yang dibuat tadi.
            mahasiswaBaru.setNama(nama);
            mahasiswaBaru.setNim(nim);
            
            // Memasukkan "mahasiswa baru" ke dalam database.
            daoMahasiswa.insert(mahasiswaBaru);
            
            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");
            
            // Terakhir, program akan pindah ke halaman Table Mahasiswa()
            halamanInput.dispose();
            new ViewMahasiswa();
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editMahasiswa(int id) {
        try {
            /*
              Membuat instance "mahasiswa yang mau diedit" buat 
              menyimpan informasi mahasiswa yang mau diedit.
            */
            ModelMahasiswa mahasiswaYangMauDiedit = new ModelMahasiswa();
                        
            /*
              Mengambil input nama dan nim menggunakan getter yang telah dibuat di view
              Nilai dari input kemudian disimpan ke dalam variabel "nama" dan "nim".
            */
            String nama = halamanEdit.getInputNama();
            String nim = halamanEdit.getInputNIM();

            /*
              Mengecek apakah input dari nama atau nim kosong/tidak.
              Jika kosong, maka buatlah sebuah exception.
             */
            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM tidak boleh kosong!");
            }
            
            // Mengisi id, nama dan nim dari "mahasiswa baru" yang dibuat tadi.
            mahasiswaYangMauDiedit.setId(id);
            mahasiswaYangMauDiedit.setNama(nama);
            mahasiswaYangMauDiedit.setNim(nim);
            
            // Memasukkan "mahasiswa baru" ke dalam database.
            daoMahasiswa.update(mahasiswaYangMauDiedit);

            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil diubah.");

            // Terakhir, program akan pindah ke halaman Table Mahasiswa()
            halamanEdit.dispose();
            new ViewMahasiswa();
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) {
        // Mengambil id dan nama berdasarkan baris yang dipilih
        Integer id = (int) halamanTable.getTableMahasiswa().getValueAt(baris, 0);
        String nama = halamanTable.getTableMahasiswa().getValueAt(baris, 1).toString();

        // Membuat Pop-Up untuk mengonfirmasi apakah ingin menghapus data
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        // Jika user memilih opsi "yes", maka hapus data.
        if (input == 0) {
            /* 
              Memanggil method delete() untuk menghaous data dari DB
              berdasarkan id yang dipilih.
            */
            daoMahasiswa.delete(id);
            
            // Menampilkan pop-up jika berhasil menghapus.
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            // Memanggil method "showAllMahasiswa()" untuk merefresh table.
            showAllMahasiswa();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Mahasiswa;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hp
 */
public class ModelTable extends AbstractTableModel{
    List<ModelMahasiswa> daftarMahasiswa;
    
    private String kolom[] = {"ID","Nama","NIM"};
    
    public ModelTable(List<ModelMahasiswa> daftarMahasiswa){
        this.daftarMahasiswa = daftarMahasiswa;
    }

    @Override
    public int getRowCount() {
        return daftarMahasiswa.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       switch(columnIndex){
           case 0:
               return daftarMahasiswa.get(rowIndex).getId();
           case 1:
               return daftarMahasiswa.get(rowIndex).getNama();
           case 2:
               return daftarMahasiswa.get(rowIndex).getNim();
           default:
               return null;
       }
    }
    @Override
    public String getColumnName(int columnIndex){
        return kolom[columnIndex];
    }
}

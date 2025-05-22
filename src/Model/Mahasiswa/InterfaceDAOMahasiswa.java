/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Mahasiswa;

import java.util.List;

/**
 *
 * @author hp
 */
public interface InterfaceDAOMahasiswa {
    public void insert(ModelMahasiswa mahasiswa);
    
    public void update(ModelMahasiswa mahasiswa);
    
    public void delete(int id);
    
    public List<ModelMahasiswa> getAll();
    
    
}

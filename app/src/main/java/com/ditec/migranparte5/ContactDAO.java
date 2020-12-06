package com.ditec.migranparte5;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    public void insert(Contacto... contacts);


    @Update
    public void update(Contacto... contacts);
    @Delete
    public void delete(Contacto contact);
    @Query("SELECT * FROM contact")
    public List<Contacto> getContacts();
    @Query("SELECT * FROM contact WHERE phoneNumber = :number")
    public Contacto getContactWithId(String number);
}

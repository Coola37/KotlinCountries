package com.example.kotlincountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlincountries.model.Country

@Dao
interface CountryDao {
    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>

    //Insert -> insert into
    // suspend -> coroutine, pause & resume
    //vararg -> multiple country objects
    //List<Long> -> For List Primary Keys

    @Query("SELECT * FROM country")
    suspend fun gelAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()


}
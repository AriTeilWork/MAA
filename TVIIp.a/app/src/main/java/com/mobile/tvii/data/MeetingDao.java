package com.mobile.tvii.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mobile.tvii.model.Meeting;

import java.util.List;

@Dao
public interface MeetingDao {

    @Insert
    long insert(Meeting meeting);

    @Update
    void update(Meeting meeting);

    @Delete
    void delete(Meeting meeting);

    @Query("SELECT * FROM meetings ORDER BY date ASC, time ASC")
    List<Meeting> getAll();

    @Query("SELECT * FROM meetings WHERE id = :id LIMIT 1")
    Meeting getById(int id);

    @Query("SELECT * FROM meetings WHERE title LIKE :titlePattern ESCAPE '\\'")
    List<Meeting> findByTitlePattern(String titlePattern);

    @Query("SELECT * FROM meetings WHERE date = :date")
    List<Meeting> findByDate(String date);

    @Query("SELECT * FROM meetings WHERE place LIKE :placePattern ESCAPE '\\'")
    List<Meeting> findByPlacePattern(String placePattern);

    @Query("SELECT * FROM meetings WHERE participants LIKE :participantPattern ESCAPE '\\'")
    List<Meeting> findByParticipantPattern(String participantPattern);

    @Query("SELECT * FROM meetings WHERE time = :time")
    List<Meeting> findByTime(String time);

    /**
     * Комбинированный поиск: пустые критерии соответствуют шаблону % (любое значение).
     */
    @Query("SELECT * FROM meetings WHERE "
            + "title LIKE :titlePat ESCAPE '\\' AND "
            + "place LIKE :placePat ESCAPE '\\' AND "
            + "participants LIKE :participantPat ESCAPE '\\' AND "
            + "date LIKE :datePat ESCAPE '\\' AND "
            + "time LIKE :timePat ESCAPE '\\' "
            + "ORDER BY date ASC, time ASC")
    List<Meeting> searchCombined(
            String titlePat,
            String placePat,
            String participantPat,
            String datePat,
            String timePat
    );
}

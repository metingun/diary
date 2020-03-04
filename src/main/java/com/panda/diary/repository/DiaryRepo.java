package com.panda.diary.repository;

import com.panda.diary.model.DiaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepo extends JpaRepository<DiaryModel, Long> {
    List<DiaryModel> findAllByUserId(int userId);
}

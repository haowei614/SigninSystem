package com.chhnet.dao;

import com.chhnet.vo.SigninRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SigninRecordDao extends JpaRepository<SigninRecord,Integer> {
    List<SigninRecord> findSigninRecordsByGroupId(Integer id);
}

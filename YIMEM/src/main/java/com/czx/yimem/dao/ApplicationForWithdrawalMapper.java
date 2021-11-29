package com.czx.yimem.dao;

import com.czx.yimem.dto.ApplicationVO;
import com.czx.yimem.entity.ApplicationForWithdrawal;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface ApplicationForWithdrawalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplicationForWithdrawal record);

    int insertSelective(ApplicationForWithdrawal record);

    ApplicationForWithdrawal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplicationForWithdrawal record);

    int updateByPrimaryKey(ApplicationForWithdrawal record);

    List<ApplicationForWithdrawal> findByapplication(ApplicationForWithdrawal record);

    List<ApplicationVO> findByTime(ApplicationVO applicationVO);

    List<ApplicationForWithdrawal> selectapplicationForWithdrawalsBytime(ApplicationVO applicationVO);

    BigDecimal sumEnMoney(Integer userId);

    Integer enMoneyCount(Integer userId);

    String getstate(Integer id);
}

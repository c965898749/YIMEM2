package com.czx.yimem.dao;

import com.czx.yimem.dto.ManshVO;
import com.czx.yimem.entity.DeliverDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeliverDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliverDetail record);

    int insertSelective(DeliverDetail record);

    int updateisRuIncome(Integer id);

    int updateisRuIncome2(Integer id);

    int updatestate3for6(Integer devId);

    ManshVO findmanshVO(Integer id);

    String  findstate(Integer devId);

    DeliverDetail selectByPrimaryKey(Integer id);

    DeliverDetail selectByPrimaryKey2(Integer id);

    DeliverDetail selectByPrimaryKey11(Integer id);

    DeliverDetail selectBydevId(Integer dev_id);

    int updateByPrimaryKeySelective(DeliverDetail record);

    int updateByPrimaryKey(DeliverDetail record);

    Integer findcompanyuserid(@Param("devId")Integer devId);

    Integer findUserid(@Param("devId")Integer devId);

    List<DeliverDetail> findBydevId(@Param("devId")Integer devId);

    List<DeliverDetail> findBydeliverDetail(DeliverDetail record);

    Integer deleteBystate3(Integer devId);
}

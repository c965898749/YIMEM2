package com.czx.yimem.dao;

import com.czx.yimem.dto.DeliverVO;
import com.czx.yimem.dto.UserParam;
import com.czx.yimem.entity.Deliver;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliverMapper {
    int deleteByPrimaryKey(Integer devId);

    UserParam vpnrecommendUserDetailGetsvc(Integer userId, Integer id);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Integer devId);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);

    int selectcountByjobId(Integer jobId);

    int selectcountByjobIdAndState(Integer jobId);

    String selectState(Integer devId);

    List<Deliver> findBydeliver(Deliver record);

    List<Deliver> findBydeliver6(Deliver record);

    Integer finduseidandjobid(Deliver record);

    List<Deliver> findBydeliver4(Deliver record);

    List<Deliver> findBydeliver5(Deliver record);

    List<DeliverVO> findBydeliver2(Deliver record);

    List<DeliverVO> findBydeliver3(Deliver record);

    String getjobName(Integer devId);
}

package com.czx.yimem.dao;

import com.czx.yimem.entity.Wallet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalletMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Integer id);

    Wallet findByWallet(Wallet record);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKeySelective2(Wallet record);

    int updateByPrimaryKey(Wallet record);
}

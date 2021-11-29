package com.czx.yimem.service.financial;

import com.czx.yimem.dto.OssVO;
import com.czx.yimem.entity.Wallet;
import com.czx.yimem.entity.responsebean.BaseResp;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/21 0021 16:27
 */
public interface WalletService {
    int deleteByPrimaryKey(Integer id);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Integer id);

    Wallet findByWallet(Wallet record);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKeySelective2(Wallet record);

    int updateByPrimaryKey(Wallet record);

    BaseResp vpnWalletGetsvc(OssVO ossVO);
}

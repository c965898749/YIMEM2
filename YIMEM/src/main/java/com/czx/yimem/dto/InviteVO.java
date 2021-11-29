package com.czx.yimem.dto;

import com.czx.yimem.entity.DeliverDetail;
import com.czx.yimem.entity.Job;
import lombok.Data;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/24 0024 15:07
 */
@Data
public class InviteVO {
    private Job job;
    private DeliverDetail detail;
    private Integer isagress;
}

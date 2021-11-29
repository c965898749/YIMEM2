package com.czx.yimem.service.admin;

import com.czx.yimem.dto.DepartmentVO;
import com.czx.yimem.entity.responsebean.BaseResp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface DepartmentService {
    BaseResp getAll();

    BaseResp getByid(int id);

    BaseResp create(DepartmentVO depart);

    BaseResp update(int id, DepartmentVO depart);

    BaseResp delete(int id);

    String getAllBYid(Integer id);
}

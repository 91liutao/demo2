package com.itliutao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itliutao.mapper.ClazzMapper;
import com.itliutao.pojo.Clazz;
import com.itliutao.pojo.PageResult;
import com.itliutao.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.chrono.ChronoLocalDate;
import java.util.List;
@Service
public class ClazzServiceimpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
@Override
public PageResult<Clazz> Page(Integer page, Integer pageSize) {
    PageHelper.startPage(page, pageSize);
    List<Clazz> list = clazzMapper.list();
    list.forEach(
            clazz -> {
                if (clazz != null && clazz.getUpdateTime() != null) {
                    ChronoLocalDate updateDate = ChronoLocalDate.from(clazz.getUpdateTime());
                    if (clazz.getBeginDate() != null && clazz.getBeginDate().isAfter(updateDate)) {
                        clazz.setStatus("未开班");
                    } else if (clazz.getEndDate() != null && clazz.getEndDate().isAfter(updateDate)) {
                        clazz.setStatus("已开班");
                    } else if (clazz.getEndDate() != null) {
                        clazz.setStatus("已结束");
                    }
                }
            }
    );
    Page<Clazz> clazzPage = (Page<Clazz>) list;
    return new PageResult<Clazz>(clazzPage.getTotal(),clazzPage.getResult());
}

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void insert(Clazz clazz) {
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }
}

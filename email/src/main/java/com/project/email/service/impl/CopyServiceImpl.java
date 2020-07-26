package com.project.email.service.impl;

import com.project.email.dao.BaseMapper;
import com.project.email.dao.CopyMapper;
import com.project.email.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Reisen
 */
@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    private BaseMapper baseMapper;

    @Autowired
    private CopyMapper copyMapper;

    @Override
    public List CopyAToB() {
        ArrayList list = new ArrayList();
        list.add("数据库一");
        list.add(baseMapper.queryAll());
        list.add("数据库二");
        list.add(copyMapper.queryAll());
        return list;
    }
}

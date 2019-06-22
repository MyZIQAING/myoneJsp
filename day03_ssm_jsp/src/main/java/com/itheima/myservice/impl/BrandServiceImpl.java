package com.itheima.myservice.impl;

import com.github.pagehelper.Page;
import com.itheima.dao.TbBrandMapper;
import com.itheima.domain.PageBean;
import com.itheima.domain.TbBrand;
import com.itheima.domain.TbBrandExample;
import com.itheima.myservice.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper bm;
    @Override
    public Object listOne(PageBean pb) {
        List<TbBrand> tbBrands = bm.selectByExample(null);

        return tbBrands;
    }

    @Override
    public void deleteByone(TbBrand tbb) {
        bm.deleteByPrimaryKey(tbb.getId());
    }

    @Override
    public Object listBytow(PageBean pb, TbBrand tb) {
        TbBrandExample example=new TbBrandExample ();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if(tb!=null){
            if(tb.getName()!=null||"".equals(tb.getName())){
                criteria.andNameLike("%"+tb.getName()+"%");
            }
            if(tb.getFirstChar()!=null||"".equals(tb.getFirstChar())){
                criteria.andFirstCharLike("%"+tb.getFirstChar()+"%");
            }
        }
        List<TbBrand> tbBrands = bm.selectByExample(example);
        return tbBrands;
    }

    @Override
    public void daleteAll(Integer[] ids) {
        List list=new ArrayList();
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        TbBrandExample example=new TbBrandExample ();
        TbBrandExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(list);
        bm.deleteByExample(example);
    }

    @Override
    public void saveOne(TbBrand tbb) {
        bm.insertSelective(tbb);
    }

    @Override
    public void updataById(TbBrand tbb) {
        bm.updateByPrimaryKey(tbb);
    }
}

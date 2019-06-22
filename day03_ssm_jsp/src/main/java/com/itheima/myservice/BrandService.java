package com.itheima.myservice;

import com.github.pagehelper.Page;
import com.itheima.domain.PageBean;
import com.itheima.domain.TbBrand;

import java.util.List;

public interface BrandService {
    Object listOne(PageBean pb);

    void deleteByone(TbBrand tbb);

    Object listBytow(PageBean pb, TbBrand tb);

    void daleteAll(Integer[] ids);

    void saveOne(TbBrand tbb);

    void updataById(TbBrand tbb);
}

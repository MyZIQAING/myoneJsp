package com.itheima.mymanage;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.PageBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component("qiemian")
public class mynage_1 {
    @Pointcut("execution(* com.itheima.myservice.impl.*.list*(..))")
    public void p1(){}
    @Around("p1()")
    public Object fenye(ProceedingJoinPoint pjp)  {
        try {

            Object[] args = pjp.getArgs();
            PageBean arg = (PageBean)args[0];
            if( arg.getCurrentPage()<=0){
                arg.setCurrentPage(1);
            }
            PageHelper.startPage(arg.getCurrentPage(),arg.getPageSize());
            List list =(List) pjp.proceed(args);
            PageInfo info=new PageInfo(list);
            arg.setList(list);
            arg.setTotalCount(info.getTotal());
            arg.setTotalPage(info.getPages());
            arg.setCurrentPage(info.getPageNum());
            //开始
            Integer kaishi;
            //结束
            Integer jieshu;
            //当前页数
            Integer currentPage = info.getPageNum();
            //总页数
            Integer totalPage =info.getPages();
            if(totalPage<=10){
                kaishi=1;
                jieshu=totalPage;
            }else {
                if(currentPage>=7){
                    if(currentPage+4<=totalPage){
                        kaishi=currentPage-5;
                        jieshu=currentPage+4;
                    }else {
                        kaishi=totalPage-9;
                        jieshu=totalPage;
                    }
                }else {
                    kaishi=1;
                    jieshu=10;
                }
            }
            //上一页
            Integer number = currentPage-1;
            if(number<=1){
                number=1;
            }
            //下一页
            Integer newVar = currentPage+1;
            if(newVar>=totalPage){
                newVar=totalPage;
            }
            Integer[] I={kaishi,jieshu};
            Map map=new HashMap();
            map.put("bg",arg);
            map.put("xianDing",I);
            return map;
        }catch (Throwable t){
            throw new RuntimeException("分页异常");
        }
    }
}

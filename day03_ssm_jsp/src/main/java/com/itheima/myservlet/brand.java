package com.itheima.myservlet;

import com.itheima.domain.PageBean;
import com.itheima.domain.TbBrand;
import com.itheima.myservice.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/brand")
public class brand {
    @Autowired
    BrandService service;


    @RequestMapping("/listOne")
    public ModelAndView listOne(PageBean pb){
        Map page =(Map) service.listOne(pb);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("brand");
        mv.addObject("list",page.get("bg"));
        mv.addObject("XD",page.get("xianDing"));
        return mv;
    }
    @RequestMapping("/deleteByone")
    public void deleteByone(PageBean pb,TbBrand tbb,HttpServletResponse response,HttpServletRequest request)  {
        try {
            service.deleteByone(tbb);
            //实现
            int currentPage = pb.getCurrentPage();//d当前页码
            int pageSize = pb.getPageSize();//每页数
            int totalCount =(int) pb.getTotalCount()-1;//总计如数
            pb.setTotalCount(totalCount);
            int totalPage = pb.getTotalPage();//总页数
            if(currentPage==totalPage) {
                if(totalCount % pageSize == 0){
                    currentPage-=1;
                }
            }
            String current=currentPage+"";
            request.setAttribute("current",current);
            request.getRequestDispatcher (request.getContextPath()+"/brand/listBytow").forward(request, response);
            }catch (Exception e){
                System.out.println("WEB：删除失败");
            }
    }
    @RequestMapping("/listBytow")
    public ModelAndView listBytow(PageBean pb,TbBrand tb,HttpServletRequest request ){
        String current =(String) request.getAttribute("current");
        if(current!=null){
            pb.setCurrentPage(Integer.parseInt(current));
        }
        Map page = (Map)service.listBytow(pb,tb);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("brand");
        mv.addObject("list",page.get("bg"));
        mv.addObject("XD",page.get("xianDing"));
        mv.addObject("tb",tb);
        return mv;
    }
    @RequestMapping("/daleteAll")
    public void daleteAll(Integer[] ids,HttpServletResponse response,HttpServletRequest request){
        if(ids==null||ids.length==0){

        }else {
            service.daleteAll(ids);
        }
        try {
            response.sendRedirect(request.getContextPath()+"/brand/listOne");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/saveORupdata")
    public void saveORupdata(PageBean pb,TbBrand tbb,HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
        Long id =tbb.getId();
        if(id==null){//保存
            service.saveOne(tbb);
            response.sendRedirect(request.getContextPath()+"/brand/listOne");
            return;
        }else {//修改
            service.updataById(tbb);
            request.getRequestDispatcher (request.getContextPath()+"/brand/listOne").forward(request, response);
        }
    }
    public void a(Model model){}

}

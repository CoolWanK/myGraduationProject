package com.wjk.demo.handler;
import java.util.ArrayList;
import java.util.List;

public class ListHandle {
    public static boolean isFirstPage=false;
    public static boolean isLastPage=false;
    public static Integer page=0;
    public static List startPage(Integer pageNum,Integer size,List list){
        page=pageNum;
        if (list!=null&&list.size()>0){
            if (pageNum==1){
                isFirstPage=true;
            }
            if (pageNum>1){
                isFirstPage=false;
            }
            Integer temp=(pageNum-1)*size;

            List l=new ArrayList<>();
            if ((temp+size)<list.size()){
                l=list.subList(temp,temp+size);
                isLastPage=false;
            }
            if ((temp+size)>=list.size()){
                l=list.subList(temp,list.size());
                isLastPage=true;
            }else {
                isLastPage=false;
            }
            return l;
        }else {
            return null;
        }


    }
    public static void main(String[] args) {
        List<Integer>list=new ArrayList<>();
        for (int i=0;i<30;i++){//给list放入0-29共30个数
            list.add(i);
        }
        Integer pageNum;//定义页数
        Integer pageSize=10;//设置每页大小为10
        for (pageNum=1;pageNum<=3;pageNum++){
            List<Integer>l= startPage(pageNum,pageSize,list);
            System.out.println(l.toString());
            System.out.println("第"+ListHandle.page+"页");
            System.out.println("是第一页？："+ListHandle.isFirstPage);
            System.out.println("是最后一页？："+ListHandle.isLastPage);
            System.out.println("------------------------------------");
        }
    }
}

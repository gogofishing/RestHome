package com.resthome.dao;

import java.util.List;

import com.resthome.entity.SystemMeta;
import com.resthome.vo.PageVo;


public interface SystemMetaDaoInter {

	String addSystemMeta(SystemMeta systemMeta);

    String updateSystemMeta(String hql);
 
    List<SystemMeta> findSystemMetaByPage(PageVo pageVo,String hql);
  
    SystemMeta findSystemMeta(String hql);
   
    List<SystemMeta> findSystemMetas(String hql,List<Object> params);

    List<SystemMeta> findStstemMetasByCN(String hql);

    Integer getTotalNum(String hql);

}

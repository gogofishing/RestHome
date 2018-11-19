package com.resthome.service;

import java.util.List;
import java.util.Map;

import com.resthome.entity.SystemMeta;
import com.resthome.vo.PageVo;
import com.resthome.vo.SystemMetaVo;

public interface SystemMetaServiceInter {

	String addSystemMeta(SystemMetaVo systemMetaVo);

	String delSystemMeta(SystemMetaVo systemMetaVo);

	String updateSystemMeta(SystemMetaVo systemMetaVo);

	SystemMeta findSystemMeta(SystemMetaVo systemMetaVo);

	List<Object> findSystemMetaByPage(PageVo pageVo,SystemMetaVo systemMetaVo);

	List<SystemMeta> findSystemMetaByParent(SystemMeta systemMeta);

	Integer queryMaxOrder(SystemMeta systemMeta);

	Integer queryMaxPn(SystemMeta systemMeta);

	Integer getTotalNum(PageVo pageVo,SystemMetaVo systemMetaVo);

}

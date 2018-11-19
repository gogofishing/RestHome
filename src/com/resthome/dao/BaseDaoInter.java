package com.resthome.dao;

import java.util.List;


public interface BaseDaoInter {

	
	/**
	 * @return 总页数
	 * 查询总页数的方法
	 * @param rowsPerPage 每一页的记录行数
	 * @param hql 查询语句
	 */
	public int findTotlePage(int rowsPerPage,String hql);
	/**
	 * @param page 当前页数
	 * @param rowsPerPage 每页记录数
	 * @param hql 查询语句
	 * @return 结果的list集合
	 */
	public List<?> findObjectsByPage(int page,int rowsPerPage,String hql);
	/**
	 * @param hql 查询语句
	 * @return 数据库中总的记录数
	 */
	public int findTotleNum(String hql);
	/**
	 * @param hql 查询语句
	 * @return 结果的list集合
	 */
	public List<?> findObjectsByHql(String hql);
	/**
	 * @param sql 查询语句
	 * @return 结果list
	 */
	public List<?> findObjectsBySql(String sql);
	/**
	 * @param hql 查询语句
	 * @return 单个对象
	 */
	public Object findObjectByHql(String hql);
	/**
	 * @param hql 查询语句
	 * @return 单个对象 
	 */
	public Object findObjectBySql(String sql);
	/**
	 * @param object 需要更新的对象
	 * @return 返回 “success”成功 “error”失败
	 */
	public String updateObject(Object object );
	/**
	 * @param object 需要保存的对象
	 * @return 返回 “success”成功 “error”失败
	 */
	public String addObject(Object object );
	/**
	 * @param object 需要删除的对象
	 * @return 返回 “success”成功 “error”失败
	 */
	public String deleteObject(Object object );
	/**
	 * @param hql 需要执行的hql语句
	 * @return 返回 “success”成功 “error”失败
	 */
	public String updateByHql(String hql);
	/**
	 * @param sql 需要执行的sql语句
	 * @return 返回 “success”成功 “error”失败
	 */
	public String updateBySql(String sql);
	
	/**
	 * @param hql
	 * @param params 查询参数
	 * @return
	 */
	public List<?> findObjectsByHqlByPage(Integer page, Integer rowsPerPage,String hql,List<Object> params);
	
	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	public Object findObjectByHql(String hql,List<Object> params);
	
	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	public String updateByHql(String hql,List<Object> params);
	
	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	public int findTotleNum(String hql,List<Object> params);
	/**
	 * @param rowsPerPage
	 * @param hql
	 * @param params
	 * @return
	 */
	public int findTotlePage(int rowsPerPage,String hql,List<Object> params);
	
}

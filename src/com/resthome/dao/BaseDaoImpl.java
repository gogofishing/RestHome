package com.resthome.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository(value="baseDao")
public class BaseDaoImpl  implements BaseDaoInter {

	@Resource
	private SessionFactory sessionFactory;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
   

	/**
	 * @return 当前线程中的session
	 * 说明信息：获取当前线程中的session
	 */
	public Session getSession(){
		if(null==sessionFactory.getCurrentSession()){
			return sessionFactory.openSession();
		}else{
			return sessionFactory.getCurrentSession();
		}
    
    }
	/**
	 * @return 全新的session
	 * 说明信息：获取一个全新session
	 */
	public Session openSession(){
		return sessionFactory.openSession();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public int findTotlePage(int rowsPerPage, String hql) {
		Query query=getSession().createQuery(hql);
		List list=query.list();
		int num=(list.size())%rowsPerPage;
		if(num==0){
			return (list.size())/rowsPerPage;
		}else{
			return (list.size())/rowsPerPage+1;
		}
	}

	@Override
	public List<?> findObjectsByPage(int page, int rowsPerPage, String hql) {
		Query query=getSession().createQuery(hql);
		//每页做多显示记录数
		query.setMaxResults(rowsPerPage);
		//每页从第几条记录开始
		query.setFirstResult((page-1)*rowsPerPage);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findTotleNum(String hql) {
		Query query=getSession().createQuery(hql);
		List<Object> list=query.list();
		return list.size();
	}

	@Override
	public List<?> findObjectsByHql(String hql) {
		Query query=getSession().createQuery(hql);
		if(!query.list().isEmpty()){
			return query.list();
		}else{
			return null;
		}
	}

	@Override
	public List<?> findObjectsBySql(String sql) {
		Query query=getSession().createSQLQuery(sql);
		if(!query.list().isEmpty()){
			return query.list();
		}else{
			return null;
		}
	}

	@Override
	public Object findObjectByHql(String hql) {
		Query query=getSession().createQuery(hql);
		query.setCacheable(true);
		if(!query.list().isEmpty()){	
			return query.list().get(0);
		}else{	
			return null;
		}
	}

	@Override
	public Object findObjectBySql(String sql) {
		Query query=getSession().createSQLQuery(sql);
		if(!query.list().isEmpty()){
			return query.list().get(0);
		}else{
			return null;
		}
	}

	@Override
	public String updateObject(Object object) {
		try {
			getSession().update(object);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String addObject(Object object) {
		try {
			getSession().save(object);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String deleteObject(Object object) {
		try {
			getSession().delete(object);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public String updateByHql(String hql) {
		Query query = getSession().createQuery(hql);
		//executeUpdate()  返回数据库中受到影响的记录数
		int flag=query.executeUpdate();
		if(flag>=1){
			return "success";
		}else{
			return "error";
		}
	}

	@Override
	public String updateBySql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		//executeUpdate()  返回数据库中受到影响的记录数
		int flag=query.executeUpdate();
		if(flag>=1){
			return "success";
		}else{
			return "error";
		}
	}
	@Override
	public List<?> findObjectsByHqlByPage(Integer page, Integer rowsPerPage,String hql,List<Object> params) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql);
		fillQueryParams(params, query);
		
		if(page!=null&&rowsPerPage!=null){
		//每页做多显示记录数
	    query.setMaxResults(rowsPerPage);
		//每页从第几条记录开始
	    query.setFirstResult((page-1)*rowsPerPage);
		}
		if(query.list().isEmpty()){
			return null;
		}else{
			return query.list();
		}
		
	}
	@Override
	public Object findObjectByHql(String hql, List<Object> params) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql);
		fillQueryParams(params, query);
		if(query.list()!=null&&!query.list().isEmpty()){
			return query.list().get(0);
					
		}
		return null;
	}
	@Override
	public String updateByHql(String hql, List<Object> params) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql);
		fillQueryParams(params, query);
		int flag = query.executeUpdate();
		//System.out.println(flag);
		if(flag>0){
			return SUCCESS;
		}
		return ERROR;
	}
	@Override
	public int findTotleNum(String hql, List<Object> params) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		fillQueryParams(params, query);
		List<Object> list=query.list();
		if(list==null){
			return 0;
		}
		return list.size();
	}
	@Override
	public int findTotlePage(int rowsPerPage, String hql, List<Object> params) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		fillQueryParams(params, query);
		List list=query.list();
		if(list==null){
			return 0;
		}
		int num=(list.size())%rowsPerPage;
		if(num==0){
			return (list.size())/rowsPerPage;
		}else{
			return (list.size())/rowsPerPage+1;
		}
	}
	
	private void fillQueryParams(List<Object> params, Query query) {
		if(params!=null){
			for(int i=0;i<params.size();i++){
				query.setParameter(i, params.get(i));
			}
		}
	}

}

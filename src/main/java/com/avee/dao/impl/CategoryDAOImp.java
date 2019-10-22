package com.avee.dao.impl;

import java.util.ArrayList;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Order;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.CategoryDAO;
import com.avee.form.Category;

import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class CategoryDAOImp implements CategoryDAO {
	static final Logger logger = LogManager.getLogger(CategoryDAOImp.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Category> searchCategory(String CategoryGroup) {
		List<Category> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.Category.class);
			 if (strUtility.checkNullOrEmptyString(CategoryGroup) && CategoryGroup.equalsIgnoreCase("PaidOuts")) {
				criteria.add(Restrictions.eq("categoryGroup", CategoryGroup)).addOrder(Order.asc("categoryName"));
			}else if (strUtility.checkNullOrEmptyString(CategoryGroup) && CategoryGroup.equalsIgnoreCase("Coupons")) {
				criteria.add(Restrictions.eq("categoryGroup", CategoryGroup)).addOrder(Order.asc("categoryName"));
			}else
			{
				criteria.add(Restrictions.eq("categoryGroup", CategoryGroup)).addOrder(Order.asc("displayOrder"));
			}
			List<com.avee.domain.Category> categoryList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			for (com.avee.domain.Category categoryDomain : categoryList) {
				Category categoryForm = new Category();
				bub.copy(categoryForm, categoryDomain, exclusionsList);
				list.add(categoryForm);

			}

		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCategoryGroup() {
		List<Object[]> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.getNamedQuery("getCategoryGroup");
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return list;
	}

}

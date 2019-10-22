package com.avee.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.CashingDAO;
import com.avee.form.AmendmentTill;
import com.avee.form.AmendmentTillHO;
import com.avee.form.Banking;
import com.avee.form.Cashing;
import com.avee.form.PaidOuts;
import com.avee.form.TakingCoupons;
import com.avee.form.TakingsCards;
import com.avee.form.TakingsCash;
import com.avee.form.TakingsCheques;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;
import com.fasterxml.jackson.annotation.JsonFormat.Value;

@Transactional
@Component
public class CashingDAOImpl implements CashingDAO {
	static final Logger logger = LogManager.getLogger(CashingDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertCashing(Cashing cashing) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.Cashing domain = new com.avee.domain.Cashing();
			bub.copy(domain, cashing, exclusionsList);
			
			session.save(domain);

			/// add In tacking cash
			if (cashing.getTakingscash() != null && cashing.getTakingscash().size() > 0
					&& !(cashing.getTakingscash().isEmpty())) {
				for (TakingsCash takingscash : cashing.getTakingscash()) {
					if (takingscash.getQuantity() != 0) {
						takingscash.setCashId(domain.getId());
						takingscash.setBranchId(cashing.getBranchId());
						takingscash.setCreatedBy(cashing.getCreatedBy());
						takingscash.setCreatedDt(cashing.getCreatedDt());
						takingscash.setUpdatedDt(cashing.getUpdatedDt());
						com.avee.domain.TakingsCash takingsCashDomain = new com.avee.domain.TakingsCash();
						bub.copy(takingsCashDomain, takingscash, exclusionsList);
						session.save(takingsCashDomain);

					}
				}
			}

			/// add In takings Cheques
			if (cashing.getTakingscheques() != null && cashing.getTakingscheques().size() > 0
					&& !(cashing.getTakingscheques().isEmpty())) {
				for (TakingsCheques takingsCheques : cashing.getTakingscheques()) {
					if (strUtility.checkNullOrEmptyString(takingsCheques.getCname())) {
						takingsCheques.setCashId(domain.getId());
						takingsCheques.setBranchId(cashing.getBranchId());
						takingsCheques.setCreatedBy(cashing.getCreatedBy());
						takingsCheques.setCreatedDt(cashing.getCreatedDt());
						takingsCheques.setUpdatedDt(cashing.getUpdatedDt());

						com.avee.domain.TakingsCheques takingsChequesDomain = new com.avee.domain.TakingsCheques();
						bub.copy(takingsChequesDomain, takingsCheques, exclusionsList);
						session.save(takingsChequesDomain);
					}

				}
			}

			/// add In takings Cards
			if (cashing.getTakingscards() != null && cashing.getTakingscards().size() > 0
					&& !(cashing.getTakingscards().isEmpty())) {
				for (TakingsCards takingsCards : cashing.getTakingscards()) {
					if (takingsCards.getCtype() != 0) {
						takingsCards.setCashId(domain.getId());
						takingsCards.setBranchId(cashing.getBranchId());
						takingsCards.setCreatedBy(cashing.getCreatedBy());
						takingsCards.setCreatedDt(cashing.getCreatedDt());
						takingsCards.setUpdatedDt(cashing.getUpdatedDt());

						com.avee.domain.TakingsCards takingsCardsDomain = new com.avee.domain.TakingsCards();
						bub.copy(takingsCardsDomain, takingsCards, exclusionsList);
						session.save(takingsCardsDomain);
					}

				}
			}
			/// add In Taking coupons
			if (cashing.getTakingcoupons() != null && cashing.getTakingcoupons().size() > 0
					&& !(cashing.getTakingcoupons().isEmpty())) {
				for (TakingCoupons takingCoupons : cashing.getTakingcoupons()) {
					if (takingCoupons.getCtype() != 0) {
						takingCoupons.setCashId(domain.getId());
						takingCoupons.setBranchId(cashing.getBranchId());
						takingCoupons.setCreatedBy(cashing.getCreatedBy());
						takingCoupons.setCreatedDt(cashing.getCreatedDt());
						takingCoupons.setUpdatedDt(cashing.getUpdatedDt());

						com.avee.domain.TakingCoupons takingCouponsDomain = new com.avee.domain.TakingCoupons();
						bub.copy(takingCouponsDomain, takingCoupons, exclusionsList);
						session.save(takingCouponsDomain);
					}

				}
			}
			if (cashing.getPaidouts() != null && cashing.getPaidouts().size() > 0
					&& !(cashing.getPaidouts().isEmpty())) {
				for (PaidOuts paidouts : cashing.getPaidouts()) {
					if (paidouts.getCtype() != 0) {
						paidouts.setCashId(domain.getId());
						paidouts.setBranchId(cashing.getBranchId());
						paidouts.setCreatedBy(cashing.getCreatedBy());
						paidouts.setCreatedDt(cashing.getCreatedDt());
						paidouts.setUpdatedDt(cashing.getUpdatedDt());

						com.avee.domain.PaidOuts paidoutsDomain = new com.avee.domain.PaidOuts();
						bub.copy(paidoutsDomain, paidouts, exclusionsList);
						session.save(paidoutsDomain);
					}
				}
			}
			tx.commit();
			message = "Cashing inserted successfully.";

		} catch (Exception e) {
			message = "Cashing can not be inserted.";
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return message;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Cashing> searchCashing(Map<String, String> map) {
		List<Cashing> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String fdate = map.get("fDate");
			String tdate = map.get("tDate");
			String branchId = map.get("branchId");
			Criteria criteria = session.createCriteria(com.avee.domain.Cashing.class);

			if (strUtility.checkNullOrEmptyString(fdate) && strUtility.checkNullOrEmptyString(tdate)
					&& strUtility.checkNullOrEmptyString(branchId)) {
				Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdate);
				Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdate);

				criteria.add(Restrictions.between("date", fDate, tDate))
						.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
				criteria.addOrder(Order.asc("date"));
			} else {
				criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
			}
			criteria.add(Restrictions.eq("tillStatus", 1));
			List<com.avee.domain.Cashing> cashingList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.Cashing cashingDomain : cashingList) {
				Cashing cashingForm = new Cashing();
				bub.copy(cashingForm, cashingDomain, exclusionsList);
				double actual = 0.0;
				double diff = 0.0;
				actual = cashingForm.getCash() + cashingForm.getCheques() + cashingForm.getCards()
						+ cashingForm.getCoupuns() + cashingForm.getPaidOut1();
				diff = actual - cashingForm.getzReading();
				cashingForm.setWeekNo(strUtility.getWeekNumber(cashingForm.getDate()));
				cashingForm.setActualTill(actual);
				cashingForm.setDiff(diff);
				list.add(cashingForm);

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Cashing getCashing(int id) {
		Session session = null;
		Transaction tx = null;
		Cashing cashing = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.Cashing domain = (com.avee.domain.Cashing) session.get(com.avee.domain.Cashing.class, id);
			if (domain != null) {
				cashing = new Cashing();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(cashing, domain, exclusionsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return cashing;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String updateCashing(Cashing cashing) {
		Session session = null;
		Transaction tx = null;
		String strObj = "ERROR";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.Cashing domain = (com.avee.domain.Cashing) session.get(com.avee.domain.Cashing.class,
					cashing.getId());
			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, cashing, exclusionsList);
				session.saveOrUpdate(domain);

				// update Takings Cash
				if (cashing.getTakingscash() != null && cashing.getTakingscash().size() > 0
						&& !(cashing.getTakingscash().isEmpty())) {
					for (TakingsCash takingCash : cashing.getTakingscash()) {
						if (takingCash.getQuantity() != 0) {
							if (takingCash.getId() != 0) {
								takingCash.setCashId(cashing.getId());
								takingCash.setBranchId(cashing.getBranchId());
								takingCash.setUpdatedBy(cashing.getUpdatedBy());
								takingCash.setUpdatedDt(cashing.getUpdatedDt());
								com.avee.domain.TakingsCash takingsCashdomain = (com.avee.domain.TakingsCash) session
										.get(com.avee.domain.TakingsCash.class, takingCash.getId());

								if (takingsCashdomain != null) {
									bub.copy(takingsCashdomain, takingCash, exclusionsList);
									session.update(takingsCashdomain);
								}

							} else {
								takingCash.setCashId(cashing.getId());
								cashing.setCreatedBy(cashing.getUpdatedBy());
								cashing.setCreatedDt(cashing.getUpdatedDt());
								takingCash.setBranchId(cashing.getBranchId());
								takingCash.setCreatedBy(cashing.getCreatedBy());
								takingCash.setCreatedDt(cashing.getCreatedDt());
								takingCash.setUpdatedDt(cashing.getUpdatedDt());
								com.avee.domain.TakingsCash takingsCashDomainInsert = new com.avee.domain.TakingsCash();
								bub.copy(takingsCashDomainInsert, takingCash, exclusionsList);
								session.save(takingsCashDomainInsert);
							}

						} else {
							if (takingCash.getId() != 0) {

								com.avee.domain.TakingsCash takingscashDomaindelete = (com.avee.domain.TakingsCash) session
										.get(com.avee.domain.TakingsCash.class, takingCash.getId());

								if (takingscashDomaindelete != null) {
									session.delete(takingscashDomaindelete);
								}
							}
						}
					}
				}

				// update Takings Cheques
				if (cashing.getTakingscheques() != null && cashing.getTakingscheques().size() > 0
						&& !(cashing.getTakingscheques().isEmpty())) {
					for (TakingsCheques takingsCheques : cashing.getTakingscheques()) {
						if (strUtility.checkNullOrEmptyString(takingsCheques.getCname())) {
							if (takingsCheques.getId() != 0) {
								takingsCheques.setCashId(cashing.getId());
								takingsCheques.setBranchId(cashing.getBranchId());
								takingsCheques.setUpdatedBy(cashing.getUpdatedBy());
								takingsCheques.setUpdatedDt(cashing.getUpdatedDt());
								com.avee.domain.TakingsCheques takingsChequesdomain = (com.avee.domain.TakingsCheques) session
										.get(com.avee.domain.TakingsCheques.class, takingsCheques.getId());
								if (takingsChequesdomain != null) {
									bub.copy(takingsChequesdomain, takingsCheques, exclusionsList);
									session.update(takingsChequesdomain);
								}
							} else {
								takingsCheques.setCashId(cashing.getId());
								takingsCheques.setBranchId(cashing.getBranchId());
								takingsCheques.setCreatedBy(cashing.getUpdatedBy());
								takingsCheques.setCreatedDt(cashing.getUpdatedDt());
								takingsCheques.setUpdatedDt(cashing.getUpdatedDt());
								com.avee.domain.TakingsCheques takingsChequesDomainInsert = new com.avee.domain.TakingsCheques();
								bub.copy(takingsChequesDomainInsert, takingsCheques, exclusionsList);
								session.save(takingsChequesDomainInsert);
							}
						} else {
							if (takingsCheques.getId() != 0) {
								com.avee.domain.TakingsCheques takingsChequesDomainDelete = (com.avee.domain.TakingsCheques) session
										.get(com.avee.domain.TakingsCheques.class, takingsCheques.getId());

								if (takingsChequesDomainDelete != null) {
									session.delete(takingsChequesDomainDelete);
								}
							}
						}

					}
				}

				// update Takings Cards
				if (cashing.getTakingscards() != null && cashing.getTakingscards().size() > 0
						&& !(cashing.getTakingscards().isEmpty())) {
					for (TakingsCards takingscards : cashing.getTakingscards()) {
						if (takingscards.getCtype() != 0) {
							if (takingscards.getId() != 0) {
								takingscards.setCashId(cashing.getId());
								takingscards.setBranchId(cashing.getBranchId());
								takingscards.setUpdatedBy(cashing.getUpdatedBy());
								takingscards.setUpdatedDt(cashing.getUpdatedDt());
								com.avee.domain.TakingsCards takingsCardsDomain = (com.avee.domain.TakingsCards) session
										.get(com.avee.domain.TakingsCards.class, takingscards.getId());

								if (domain != null) {
									bub.copy(takingsCardsDomain, takingscards, exclusionsList);
									session.update(takingsCardsDomain);
								}

							} else {
								takingscards.setCashId(cashing.getId());
								takingscards.setBranchId(cashing.getBranchId());
								takingscards.setCreatedBy(cashing.getUpdatedBy());
								takingscards.setCreatedDt(cashing.getUpdatedDt());
								takingscards.setUpdatedDt(cashing.getUpdatedDt());

								com.avee.domain.TakingsCards takingsCardsDomainInsert = new com.avee.domain.TakingsCards();
								bub.copy(takingsCardsDomainInsert, takingscards, exclusionsList);
								session.save(takingsCardsDomainInsert);
							}

						} else {
							if (takingscards.getId() != 0) {
								com.avee.domain.TakingsCards takingsCardsDomainDelete = (com.avee.domain.TakingsCards) session
										.get(com.avee.domain.TakingsCards.class, takingscards.getId());

								if (takingsCardsDomainDelete != null) {
									session.delete(takingsCardsDomainDelete);
								}
							}
						}
					}
				}

				// update Takings Coupons
				if (cashing.getTakingcoupons() != null && cashing.getTakingcoupons().size() > 0
						&& !(cashing.getTakingcoupons().isEmpty())) {
					for (TakingCoupons takingCoupons : cashing.getTakingcoupons()) {
						if (takingCoupons.getCtype() != 0) {
							if (takingCoupons.getId() != 0) {

								takingCoupons.setCashId(cashing.getId());
								takingCoupons.setBranchId(cashing.getBranchId());
								takingCoupons.setUpdatedBy(cashing.getUpdatedBy());
								takingCoupons.setUpdatedDt(cashing.getUpdatedDt());
								com.avee.domain.TakingCoupons takingCouponsdomain = (com.avee.domain.TakingCoupons) session
										.get(com.avee.domain.TakingCoupons.class, takingCoupons.getId());

								if (takingCouponsdomain != null) {
									bub.copy(takingCouponsdomain, takingCoupons, exclusionsList);
									session.update(takingCouponsdomain);
								}
							} else {
								takingCoupons.setCashId(cashing.getId());
								takingCoupons.setBranchId(cashing.getBranchId());
								takingCoupons.setCreatedBy(cashing.getUpdatedBy());
								takingCoupons.setCreatedDt(cashing.getUpdatedDt());
								takingCoupons.setUpdatedDt(cashing.getUpdatedDt());
								com.avee.domain.TakingCoupons takingCouponsDomainInsert = new com.avee.domain.TakingCoupons();
								bub.copy(takingCouponsDomainInsert, takingCoupons, exclusionsList);
								session.save(takingCouponsDomainInsert);

							}

						} else {
							if (takingCoupons.getId() != 0) {
								com.avee.domain.TakingCoupons takingCouponsDomainDelete = (com.avee.domain.TakingCoupons) session
										.get(com.avee.domain.TakingCoupons.class, takingCoupons.getId());

								if (takingCouponsDomainDelete != null) {
									session.delete(takingCouponsDomainDelete);
								}
							}
						}

					}
				}

				// update Takings Paidouts
				if (cashing.getPaidouts() != null && cashing.getPaidouts().size() > 0
						&& !(cashing.getPaidouts().isEmpty())) {
					for (PaidOuts paidouts : cashing.getPaidouts()) {
						if (paidouts.getCtype() != 0) {
							if (paidouts.getId() != 0) {
								paidouts.setCashId(cashing.getId());
								paidouts.setBranchId(cashing.getBranchId());
								paidouts.setUpdatedBy(cashing.getUpdatedBy());
								paidouts.setUpdatedDt(cashing.getUpdatedDt());
								com.avee.domain.PaidOuts paidOutDomain = (com.avee.domain.PaidOuts) session
										.get(com.avee.domain.PaidOuts.class, paidouts.getId());

								if (domain != null) {
									bub.copy(paidOutDomain, paidouts, exclusionsList);
									session.update(paidOutDomain);
								}
							} else {
								paidouts.setCashId(cashing.getId());

								paidouts.setBranchId(cashing.getBranchId());
								paidouts.setCreatedBy(cashing.getUpdatedBy());
								paidouts.setCreatedDt(cashing.getUpdatedDt());
								paidouts.setUpdatedDt(cashing.getUpdatedDt());

								com.avee.domain.PaidOuts paidoutsDomainInsert = new com.avee.domain.PaidOuts();
								bub.copy(paidoutsDomainInsert, paidouts, exclusionsList);
								session.save(paidoutsDomainInsert);

							}
						} else {
							if (paidouts.getId() != 0) {
								com.avee.domain.PaidOuts paidoutsDomainDelete = (com.avee.domain.PaidOuts) session
										.get(com.avee.domain.PaidOuts.class, paidouts.getId());

								if (paidoutsDomainDelete != null) {
									session.delete(paidoutsDomainDelete);
								}
							}
						}
					}
				}

				tx.commit();
				strObj = "SUCCESS";
			} else {
				strObj = "ERROR";
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			strObj = "ERROR";
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return strObj;
	}

	@Override
	public void deleteCashing(int id) {
		logger.info("in CashingDAO deleteCashing. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.Cashing domain = (com.avee.domain.Cashing) session.get(com.avee.domain.Cashing.class, id);
			if (domain != null) {
				session.delete(domain);
				tx.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in CashingDAO deleteCashing.. end");

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Object[]> searchRemainingCashing(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		logger.info("in CashingDAO searchRemainingCashing.. strat");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String branchId = map.get("branchId");
			String tillNo = map.get("tillno");

			ProcedureCall call = session.createStoredProcedureCall("pharma.getremainingcashing");
			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(branchId));
			call.registerParameter(2, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(4, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(tillNo));

			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();

			results = output.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in CashingDAO searchRemainingCashing.. End");
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean cashingExistOrNot(Map<String, String> map) {
		logger.info("in CashingDAO cashingExistOrNot.. start");
		Session session = null;
		Transaction tx = null;
		boolean check = false;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			String cashingdate = map.get("cashingdate");
			String tillno = map.get("tillno");
			String branchid = map.get("branchid");
			// System.out.println(cashingdate + "--" + tillno + "--" +
			// branchid);
			Criteria criteria = session.createCriteria(com.avee.domain.Cashing.class);
			if (strUtility.checkNullOrEmptyString(cashingdate) && strUtility.checkNullOrEmptyString(tillno)
					&& strUtility.checkNullOrEmptyString(branchid)) {

				Date cdate = new SimpleDateFormat("yyyy-MM-dd").parse(cashingdate);

				Criterion date = Restrictions.eq("date", cdate);
				Criterion tillNo = Restrictions.eq("tillNo", Integer.parseInt(tillno));
				Criterion branchId = Restrictions.eq("branchId", Integer.parseInt(branchid));
				Conjunction andexp = Restrictions.and(date, tillNo, branchId);
				criteria.add(andexp);
			}
			List<com.avee.domain.Cashing> cashingList = criteria.list();

			if (cashingList != null && !(cashingList.isEmpty()) && cashingList.size() > 0) {
				check = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in CashingDAO cashingExistOrNot.. end");
		return check;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getUnBankingCashAndCheques(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			int branchId = Integer.parseInt(map.get("branchId"));
			Query qry;
			qry = session.getNamedQuery("getUnBankingCashAndCheques");
			qry.setInteger("branchId", branchId);
			qry.setDate("fdate", fdate);
			qry.setDate("tdate", tdate);
			results = qry.list();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return results;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Cashing> unBankingCashingAsList(Map<String, String> map) {
		List<Cashing> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(com.avee.domain.Cashing.class);
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));

			String branchId = map.get("branchId");
			if (strUtility.checkNullOrEmptyString(branchId)) {
				criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId))).add(Restrictions.ne("id", 0))
						.add(Restrictions.eq("tillStatus", 1)).add(Restrictions.eq("bankingId", 0))
						.add(Restrictions.between("date", fdate, tdate)).addOrder(Order.asc("date"))
						.addOrder(Order.asc("tillNo"));

			}

			List<com.avee.domain.Cashing> cashingList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			if (cashingList != null) {
				for (com.avee.domain.Cashing cashingDomain : cashingList) {
					Cashing cashingForm = new Cashing();
					bub.copy(cashingForm, cashingDomain, exclusionsList);
					list.add(cashingForm);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Cashing> bankingCashingAsList(int branchid, int bankid) {
		List<Cashing> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(com.avee.domain.Cashing.class);

			if (strUtility.isEmptyInteger(branchid)) {
				criteria.add(Restrictions.eq("branchId", branchid)).add(Restrictions.eq("bankingId", bankid))
						.addOrder(Order.asc("date")).addOrder(Order.asc("tillNo"));
				;
			}

			List<com.avee.domain.Cashing> cashingList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.Cashing cashingDomain : cashingList) {
				Cashing cashingForm = new Cashing();
				bub.copy(cashingForm, cashingDomain, exclusionsList);
				list.add(cashingForm);

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return list;
	}

	@Override
	public void updateBankingIdInCashing(int cashid, Banking banking) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Query query = session.getNamedQuery("updateBankingIdInCashing");
			query.setString("updatedBy1", banking.getCreatedBy());
			query.setTimestamp("updatedDt1", banking.getCreatedDt());

			query.setInteger("cashid", cashid);
			query.setInteger("bankid", banking.getId());
			query.executeUpdate();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getNotification(Map<String, String> map) {

		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			int branchId = Integer.parseInt(map.get("branchIdObj"));
			int mode = Integer.parseInt(map.get("modeObj"));

			ProcedureCall call = session.createStoredProcedureCall("pharma.show_notification");
			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(4, Integer.class, ParameterMode.IN).bindValue(mode);

			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();

			results = output.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return results;

	}

	@Override
	public String editCashing(Cashing cashing) {
		String msg = "";
		Session session = null;
		Transaction tx = null;

		logger.info("in editCashing . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Query qry = session.createSQLQuery(" { CALL  pharma.updateCashSummary( ?,? ) } ");
			qry.setInteger(0, cashing.getId());
			qry.setString(1, cashing.getDeleteComment());
			qry.executeUpdate();

			tx.commit();
			msg = "SUCCESS";

		} catch (Exception e) {
			msg = "ERROR";
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return msg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int checkRemainingCashingUp(Map<String, String> map) {
		int msg = 0;
		Session session = null;
		Transaction tx = null;
		List<Object> obj = null;
		logger.info("in editCashing . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String branchId = map.get("branchId");
			String tillNo = map.get("tillno");

			Query qry = session.getNamedQuery("checkRemainingCashingUp");

			qry.setInteger("branchID", Integer.parseInt(branchId));
			qry.setDate("fdate", fdate);
			qry.setDate("tdate", tdate);
			qry.setInteger("tillno", Integer.parseInt(tillNo));
			obj = qry.list();

			if (obj != null && obj.size() > 0) {
				msg = Integer.parseInt(obj.get(0).toString());
			}
			tx.commit();
			// msg = "SUCCESS";

		} catch (Exception e) {
			// msg = "ERROR";
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return msg;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(readOnly=true)
	public Cashing getCashSummaryById(int id) {
		Session session = null;
		Transaction tx = null;
		Cashing cashing = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query qry = null;
			qry = session.getNamedQuery("getcashSummaryById");
			qry.setInteger("id", id);
			com.avee.domain.Cashing domain = (com.avee.domain.Cashing) qry.uniqueResult();
			if (domain != null) {
				cashing = new Cashing();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(cashing, domain, exclusionsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return cashing;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String[] insertAmendmentTilRequest(AmendmentTill amendmentTill) {
		String message = "";
		String[] messageArry = new String[2];
		String messageObj = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.AmendmentTill domain = new com.avee.domain.AmendmentTill();
			bub.copy(domain, amendmentTill, exclusionsList);
			session.save(domain);
			// update cashingup
			if (domain.getId() != 0) {
				com.avee.domain.Cashing cashSummarydomain = (com.avee.domain.Cashing) session
						.get(com.avee.domain.Cashing.class, domain.getCashId());

				if (cashSummarydomain != null) {
					cashSummarydomain.setTillStatus(-1);
					session.update(cashSummarydomain);
					messageObj="<table><tr><td><strong>Date:</strong>"+strUtility.chageDateFormatPart(strUtility.covertDateToString(cashSummarydomain.getDate(), "yyyy-MM-dd"),"yyyy-MM-dd","dd/MM/yyyy")+""
							+ "</td></tr><tr><td><strong>Till No:</strong>"
							+ ""+cashSummarydomain.getTillNo()+"</td></tr><tr><td><strong>Reference No:</strong>"
							+cashSummarydomain.getRefNo()+"</td></tr></table>";
				}
				
			}

			tx.commit();
			
			messageArry[0]="SUCCESS";
			messageArry[1]=messageObj;
		} catch (Exception e) {
			messageArry[0]="ERROR";
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return messageArry;
	}

	@Override
	public String updateAmendmentTilRequest(AmendmentTill amendmentTill) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.AmendmentTill domain = (com.avee.domain.AmendmentTill) session
					.get(com.avee.domain.AmendmentTill.class, amendmentTill.getId());
			session.buildLockRequest(new LockOptions(LockMode.PESSIMISTIC_WRITE)).lock(domain);
			domain.setUpdatedBy(amendmentTill.getUpdatedBy());
			domain.setUpdatedDt(amendmentTill.getUpdatedDt());
			domain.setWorkingBy(amendmentTill.getWorkingBy());
			domain.setStatus(amendmentTill.getStatus());
			session.update(domain);
			
			tx.commit();
			message = "SUCCESS";

		} catch (Exception e) {
			message = "ERROR";
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return message;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String updateCashingByRequset(Cashing cashing, String note, String amdId) {
		Session session = null;
		Transaction tx = null;
		String strObj = "ERROR";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.Cashing domain = (com.avee.domain.Cashing) session.get(com.avee.domain.Cashing.class,
					cashing.getId());
			session.buildLockRequest(new LockOptions(LockMode.PESSIMISTIC_WRITE)).lock(domain);
			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, cashing, exclusionsList);
				session.saveOrUpdate(domain);

				com.avee.domain.AmendmentTill amdDomain = (com.avee.domain.AmendmentTill) session
						.get(com.avee.domain.AmendmentTill.class, Integer.parseInt(amdId));
				amdDomain.setUpdatedBy(domain.getUpdatedBy());
				amdDomain.setUpdatedDt(domain.getUpdatedDt());
				amdDomain.setWorkingBy(domain.getUpdatedBy());
				amdDomain.setCommentsByHO(note);
				amdDomain.setStatus(2);
				session.update(amdDomain);

				tx.commit();
				strObj = "SUCCESS";
			} else {
				strObj = "ERROR";
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			strObj = "ERROR";
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return strObj;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String saveOrUpdateAmendmentTilByHO(AmendmentTillHO amendmentTillHO, Cashing cashing,String mode) {
		Session session = null;
		Transaction tx = null;
		String strObj = "ERROR";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			if(mode.equalsIgnoreCase("Insert")){
			com.avee.domain.AmendmentTillHO amendHODomain = new com.avee.domain.AmendmentTillHO();
			bub.copy(amendHODomain, amendmentTillHO, exclusionsList);
			session.save(amendHODomain);
			}
			if(mode.equalsIgnoreCase("Update")){
				com.avee.domain.AmendmentTillHO amendUpdateDomain = (com.avee.domain.AmendmentTillHO) session.get(com.avee.domain.AmendmentTillHO.class,
						amendmentTillHO.getId());
				if (amendUpdateDomain != null) {

					bub.copy(amendUpdateDomain, amendmentTillHO, exclusionsList);
					session.saveOrUpdate(amendUpdateDomain);
					amendmentTillHO.setCreatedBy(amendmentTillHO.getUpdatedBy());
					amendmentTillHO.setCreatedDt(amendmentTillHO.getUpdatedDt());
				}
			}

				com.avee.domain.Cashing domain = (com.avee.domain.Cashing) session.get(com.avee.domain.Cashing.class,
						cashing.getId());

			if (domain != null) {

				bub.copy(domain, cashing, exclusionsList);
				session.saveOrUpdate(domain);

				// update Takings Cash
				if (cashing.getTakingscash() != null && cashing.getTakingscash().size() > 0
						&& !(cashing.getTakingscash().isEmpty())) {
					for (TakingsCash takingCash : cashing.getTakingscash()) {
						if (takingCash.getQuantity() != 0) {
							if (takingCash.getId() != 0) {
								takingCash.setCashId(cashing.getId());
								takingCash.setBranchId(cashing.getBranchId());
								takingCash.setUpdatedBy(amendmentTillHO.getCreatedBy());
								takingCash.setUpdatedDt(amendmentTillHO.getUpdatedDt());
								com.avee.domain.TakingsCash takingsCashdomain = (com.avee.domain.TakingsCash) session
										.get(com.avee.domain.TakingsCash.class, takingCash.getId());

								if (takingsCashdomain != null) {
									bub.copy(takingsCashdomain, takingCash, exclusionsList);
									session.update(takingsCashdomain);
								}

							} else {
								takingCash.setCashId(cashing.getId());
								cashing.setCreatedBy(amendmentTillHO.getCreatedBy());
								cashing.setCreatedDt(amendmentTillHO.getUpdatedDt());
								takingCash.setBranchId(cashing.getBranchId());
								takingCash.setCreatedBy(amendmentTillHO.getCreatedBy());
								takingCash.setCreatedDt(amendmentTillHO.getCreatedDt());
								takingCash.setUpdatedDt(amendmentTillHO.getUpdatedDt());
								com.avee.domain.TakingsCash takingsCashDomainInsert = new com.avee.domain.TakingsCash();
								bub.copy(takingsCashDomainInsert, takingCash, exclusionsList);
								session.save(takingsCashDomainInsert);
							}

						} else {
							if (takingCash.getId() != 0) {

								com.avee.domain.TakingsCash takingscashDomaindelete = (com.avee.domain.TakingsCash) session
										.get(com.avee.domain.TakingsCash.class, takingCash.getId());

								if (takingscashDomaindelete != null) {
									session.delete(takingscashDomaindelete);
								}
							}
						}
					}
				}

				// update Takings Cheques
				if (cashing.getTakingscheques() != null && cashing.getTakingscheques().size() > 0
						&& !(cashing.getTakingscheques().isEmpty())) {
					for (TakingsCheques takingsCheques : cashing.getTakingscheques()) {
						if (strUtility.checkNullOrEmptyString(takingsCheques.getCname())) {
							if (takingsCheques.getId() != 0) {
								takingsCheques.setCashId(cashing.getId());
								takingsCheques.setBranchId(cashing.getBranchId());
								takingsCheques.setUpdatedBy(amendmentTillHO.getCreatedBy());
								takingsCheques.setUpdatedDt(amendmentTillHO.getUpdatedDt());
								com.avee.domain.TakingsCheques takingsChequesdomain = (com.avee.domain.TakingsCheques) session
										.get(com.avee.domain.TakingsCheques.class, takingsCheques.getId());
								if (takingsChequesdomain != null) {
									bub.copy(takingsChequesdomain, takingsCheques, exclusionsList);
									session.update(takingsChequesdomain);
								}
							} else {
								takingsCheques.setCashId(cashing.getId());
								takingsCheques.setBranchId(cashing.getBranchId());
								takingsCheques.setCreatedBy(amendmentTillHO.getCreatedBy());
								takingsCheques.setCreatedDt(amendmentTillHO.getUpdatedDt());
								takingsCheques.setUpdatedDt(amendmentTillHO.getUpdatedDt());
								com.avee.domain.TakingsCheques takingsChequesDomainInsert = new com.avee.domain.TakingsCheques();
								bub.copy(takingsChequesDomainInsert, takingsCheques, exclusionsList);
								session.save(takingsChequesDomainInsert);
							}
						} else {
							if (takingsCheques.getId() != 0) {
								com.avee.domain.TakingsCheques takingsChequesDomainDelete = (com.avee.domain.TakingsCheques) session
										.get(com.avee.domain.TakingsCheques.class, takingsCheques.getId());

								if (takingsChequesDomainDelete != null) {
									session.delete(takingsChequesDomainDelete);
								}
							}
						}

					}
				}

				// update Takings Cards
				if (cashing.getTakingscards() != null && cashing.getTakingscards().size() > 0
						&& !(cashing.getTakingscards().isEmpty())) {
					for (TakingsCards takingscards : cashing.getTakingscards()) {
						if (takingscards.getCtype() != 0) {
							if (takingscards.getId() != 0) {
								takingscards.setCashId(cashing.getId());
								takingscards.setBranchId(cashing.getBranchId());
								takingscards.setUpdatedBy(amendmentTillHO.getCreatedBy());
								takingscards.setUpdatedDt(amendmentTillHO.getUpdatedDt());
								com.avee.domain.TakingsCards takingsCardsDomain = (com.avee.domain.TakingsCards) session
										.get(com.avee.domain.TakingsCards.class, takingscards.getId());

								if (domain != null) {
									bub.copy(takingsCardsDomain, takingscards, exclusionsList);
									session.update(takingsCardsDomain);
								}

							} else {
								takingscards.setCashId(cashing.getId());
								takingscards.setBranchId(cashing.getBranchId());
								takingscards.setCreatedBy(amendmentTillHO.getCreatedBy());
								takingscards.setCreatedDt(amendmentTillHO.getUpdatedDt());
								takingscards.setUpdatedDt(amendmentTillHO.getUpdatedDt());

								com.avee.domain.TakingsCards takingsCardsDomainInsert = new com.avee.domain.TakingsCards();
								bub.copy(takingsCardsDomainInsert, takingscards, exclusionsList);
								session.save(takingsCardsDomainInsert);
							}

						} else {
							if (takingscards.getId() != 0) {
								com.avee.domain.TakingsCards takingsCardsDomainDelete = (com.avee.domain.TakingsCards) session
										.get(com.avee.domain.TakingsCards.class, takingscards.getId());

								if (takingsCardsDomainDelete != null) {
									session.delete(takingsCardsDomainDelete);
								}
							}
						}
					}
				}

				// update Takings Coupons
				if (cashing.getTakingcoupons() != null && cashing.getTakingcoupons().size() > 0
						&& !(cashing.getTakingcoupons().isEmpty())) {
					for (TakingCoupons takingCoupons : cashing.getTakingcoupons()) {
						if (takingCoupons.getCtype() != 0) {
							if (takingCoupons.getId() != 0) {

								takingCoupons.setCashId(cashing.getId());
								takingCoupons.setBranchId(cashing.getBranchId());
								takingCoupons.setUpdatedBy(amendmentTillHO.getCreatedBy());
								takingCoupons.setUpdatedDt(amendmentTillHO.getUpdatedDt());
								com.avee.domain.TakingCoupons takingCouponsdomain = (com.avee.domain.TakingCoupons) session
										.get(com.avee.domain.TakingCoupons.class, takingCoupons.getId());

								if (takingCouponsdomain != null) {
									bub.copy(takingCouponsdomain, takingCoupons, exclusionsList);
									session.update(takingCouponsdomain);
								}
							} else {
								takingCoupons.setCashId(cashing.getId());
								takingCoupons.setBranchId(cashing.getBranchId());
								takingCoupons.setCreatedBy(amendmentTillHO.getCreatedBy());
								takingCoupons.setCreatedDt(amendmentTillHO.getUpdatedDt());
								takingCoupons.setUpdatedDt(amendmentTillHO.getUpdatedDt());
								com.avee.domain.TakingCoupons takingCouponsDomainInsert = new com.avee.domain.TakingCoupons();
								bub.copy(takingCouponsDomainInsert, takingCoupons, exclusionsList);
								session.save(takingCouponsDomainInsert);

							}

						} else {
							if (takingCoupons.getId() != 0) {
								com.avee.domain.TakingCoupons takingCouponsDomainDelete = (com.avee.domain.TakingCoupons) session
										.get(com.avee.domain.TakingCoupons.class, takingCoupons.getId());

								if (takingCouponsDomainDelete != null) {
									session.delete(takingCouponsDomainDelete);
								}
							}
						}

					}
				}

				// update Takings Paidouts
				if (cashing.getPaidouts() != null && cashing.getPaidouts().size() > 0
						&& !(cashing.getPaidouts().isEmpty())) {
					for (PaidOuts paidouts : cashing.getPaidouts()) {
						if (paidouts.getCtype() != 0) {
							if (paidouts.getId() != 0) {
								paidouts.setCashId(cashing.getId());
								paidouts.setBranchId(cashing.getBranchId());
								paidouts.setUpdatedBy(amendmentTillHO.getCreatedBy());
								paidouts.setUpdatedDt(amendmentTillHO.getUpdatedDt());
								com.avee.domain.PaidOuts paidOutDomain = (com.avee.domain.PaidOuts) session
										.get(com.avee.domain.PaidOuts.class, paidouts.getId());

								if (domain != null) {
									bub.copy(paidOutDomain, paidouts, exclusionsList);
									session.update(paidOutDomain);
								}
							} else {
								paidouts.setCashId(cashing.getId());

								paidouts.setBranchId(cashing.getBranchId());
								paidouts.setCreatedBy(amendmentTillHO.getCreatedBy());
								paidouts.setCreatedDt(amendmentTillHO.getUpdatedDt());
								paidouts.setUpdatedDt(amendmentTillHO.getUpdatedDt());

								com.avee.domain.PaidOuts paidoutsDomainInsert = new com.avee.domain.PaidOuts();
								bub.copy(paidoutsDomainInsert, paidouts, exclusionsList);
								session.save(paidoutsDomainInsert);

							}
						} else {
							if (paidouts.getId() != 0) {
								com.avee.domain.PaidOuts paidoutsDomainDelete = (com.avee.domain.PaidOuts) session
										.get(com.avee.domain.PaidOuts.class, paidouts.getId());

								if (paidoutsDomainDelete != null) {
									session.delete(paidoutsDomainDelete);
								}
							}
						}
					}
				}

				tx.commit();
				strObj = "SUCCESS";

			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			strObj = "ERROR";
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return strObj;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly=true)
	public AmendmentTillHO getAmendmentTillByHO(int cashId, int branchId) {
		logger.info("in CashingDAO getAmendmentTillByHO.. start");
		Session session = null;
		Transaction tx = null;
		AmendmentTillHO amendmentTillHO = new AmendmentTillHO();
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			Criteria criteria = session.createCriteria(com.avee.domain.AmendmentTillHO.class);
				Criterion cashIdCrt = Restrictions.eq("cashId", cashId);
				Criterion branchIdCrt = Restrictions.eq("branchId", branchId);
				LogicalExpression andexp = Restrictions.and(cashIdCrt, branchIdCrt);
				criteria.add(andexp);
	
				List<com.avee.domain.AmendmentTillHO> amendmentTillHOList = criteria.list();

			if (amendmentTillHOList != null && !(amendmentTillHOList.isEmpty()) && amendmentTillHOList.size() > 0) {
				bub.copy(amendmentTillHO, amendmentTillHOList.get(0), exclusionsList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in CashingDAO getAmendmentTillByHO.. end");
		return amendmentTillHO;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(readOnly=true)
	public AmendmentTill getAmendmentTilRequest(int id) {
		Session session = null;
		Transaction tx = null;
		AmendmentTill amendmentTill = new AmendmentTill();;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.AmendmentTill domain = (com.avee.domain.AmendmentTill) session.get(com.avee.domain.AmendmentTill.class, id);
			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(amendmentTill, domain, exclusionsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return amendmentTill;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public boolean checkAmendmentTilRequestExistOrNot(String cashId, int branchid) {
		logger.info("in CashingDAO checkAmendmentTilRequestExistOrNot.. start");
		Session session = null;
		Transaction tx = null;
		boolean check = true;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(com.avee.domain.AmendmentTill.class);

			if (strUtility.checkNullOrEmptyString(cashId)					) {
				Criterion cashid = Restrictions.eq("cashId", Integer.parseInt(cashId));
				Criterion branchId = Restrictions.eq("branchId", branchid);
				LogicalExpression andexp = Restrictions.and(cashid, branchId);
				criteria.add(andexp);
			}
			List<com.avee.domain.AmendmentTill> cashingList = criteria.list();

			if (cashingList != null && !(cashingList.isEmpty()) && cashingList.size() > 0) {
				check = false;
			}

		} catch (Exception e) {
			check = true;
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in CashingDAO checkAmendmentTilRequestExistOrNot.. end");
		return check;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkChashingCompleteOrNot(int cashId, int branchid) {
		logger.info("in CashingDAO checkChashingCompleteOrNot.. start");
		Session session = null;
		Transaction tx = null;
		boolean check = true;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(com.avee.domain.Cashing.class);

			
				Criterion cashid = Restrictions.eq("id", cashId);
				Criterion branchId = Restrictions.eq("branchId", branchid);
				Criterion till_status = Restrictions.eq("tillStatus", 1);
				Conjunction andexp = Restrictions.and(cashid, branchId,till_status);
				criteria.add(andexp);
			
			List<com.avee.domain.Cashing> cashingList = criteria.list();

			if (cashingList != null && !(cashingList.isEmpty()) && cashingList.size() > 0) {
				check = false;
			}

		} catch (Exception e) {
			check = true;
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in CashingDAO checkChashingCompleteOrNot.. end");
		return check;
	}

}

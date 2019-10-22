package com.avee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.CashingDAO;
import com.avee.form.AmendmentTill;
import com.avee.form.AmendmentTillHO;
import com.avee.form.Banking;
import com.avee.form.Cashing;
import com.avee.service.CashingService;
import com.avee.service.PaidOutsService;
import com.avee.service.TakingCashService;
import com.avee.service.TakingsCardsService;
import com.avee.service.TakingsChequesService;
import com.avee.service.TakingsCouponsService;
import com.avee.utility.StringUtility;

@Service
public class CashingServiceImpl implements CashingService {

	@Autowired
	CashingDAO cashingDAO;
	@Autowired
	TakingCashService takingCashService;
	@Autowired
	private StringUtility strUtility;
	@Autowired
	TakingsChequesService takingsChequesService;
	@Autowired
	TakingsCardsService takingsCardsService;

	@Autowired
	TakingsCouponsService takingsCouponsService;
	@Autowired
	PaidOutsService paidoutsService;

	@Override
	public String insertCashing(Cashing cashing) {
		String message = "";
		try {
			message = cashingDAO.insertCashing(cashing);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<Cashing> searchCashing(Map<String, String> map) {
		List<Cashing> list = null;
		try {
			list = cashingDAO.searchCashing(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Cashing getCashing(int id) {
		Cashing cashing = null;
		try {
			cashing = cashingDAO.getCashing(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cashing;
	}

	@Override
	public String updateCashing(Cashing cashing) {
		String strObj = "";
		try {
			strObj = cashingDAO.updateCashing(cashing);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strObj;
	}

	@Override
	public void deleteCashing(int id) {
		try {
			cashingDAO.deleteCashing(id);
		} catch (Exception e) {

		}
	}

	@Override
	public List<Object[]> searchRemainingCashing(Map<String, String> map) {
		List<Object[]> list = null;
		try {
			list = cashingDAO.searchRemainingCashing(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean cashingExistOrNot(Map<String, String> map) {
		boolean check = false;
		try {
			check = cashingDAO.cashingExistOrNot(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public List<Object[]> getUnBankingCashAndCheques(Map<String, String> map) {
		List<Object[]> list = null;
		try {
			list = cashingDAO.getUnBankingCashAndCheques(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Cashing> unBankingCashingAsList(String cashid, Map<String, String> map) {
		List<Cashing> list = null;
		// Map<String, String> unBank = new HashMap<>();

		try {

			list = cashingDAO.unBankingCashingAsList(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Cashing> bankingCashingAsList(int branchid, int bankId) {
		List<Cashing> list = null;
		Map<String, String> unBank = new HashMap<>();

		try {
			list = cashingDAO.bankingCashingAsList(branchid, bankId);
			if (list != null) {
				for (Cashing cashing : list) {
					if (cashing.getBranchId() != 0 && cashing.getId() != 0) {
						unBank.put("cashid", String.valueOf(cashing.getId()));
						unBank.put("branchid", String.valueOf(cashing.getBranchId()));
						if (cashing.getCash() != 0) {
							cashing.setTakingscash(strUtility.initialize(takingCashService.bankingCashAsList(unBank)));

						}
						if (cashing.getCheques() != 0) {
							unBank.put("bankid", String.valueOf(cashing.getBankingId()));
							cashing.setTakingscheques(takingsChequesService.bankingChequesAsList(unBank));
						}
						if (cashing.getPaidOut1() != 0) {
							unBank.put("bankid", String.valueOf(cashing.getBankingId()));
							cashing.setPaidouts(paidoutsService.searchPaidOuts(cashing.getId()));
						}

						list.set(list.indexOf(cashing), cashing);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateBankingIdInCashing(int cashid, Banking banking) {
		try {
			cashingDAO.updateBankingIdInCashing(cashid, banking);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Object[]> getNotification(Map<String, String> map) {
		return cashingDAO.getNotification(map);
	}

	@Override
	public String editCashing(Cashing cashing) {
		return cashingDAO.editCashing(cashing);
	}

	@Override
	public int checkRemainingCashingUp(Map<String, String> map) {
		return cashingDAO.checkRemainingCashingUp(map);
	}

	@Override
	public Cashing getCashSummaryById(int id) {
		return cashingDAO.getCashSummaryById(id);
	}

	@Override
	public String[] insertAmendmentTilRequest(AmendmentTill amendmentTill) {
		return cashingDAO.insertAmendmentTilRequest(amendmentTill);
	}

	@Override
	public String updateAmendmentTilRequest(AmendmentTill amendmentTill) {
		return cashingDAO.updateAmendmentTilRequest(amendmentTill);
	}

	@Override
	public String updateCashingByRequset(Cashing cashing,String note,String amdId) {
		return cashingDAO.updateCashingByRequset(cashing,note,amdId);
	}

	@Override
	public String saveOrUpdateAmendmentTilByHO(AmendmentTillHO amendmentTillHO, Cashing cashing,String mode) {
		return cashingDAO.saveOrUpdateAmendmentTilByHO(amendmentTillHO, cashing,mode);
	}

	
	@Override
	public AmendmentTillHO getAmendmentTillByHO(int cashId, int branchId) {
		return cashingDAO.getAmendmentTillByHO(cashId, branchId);
	}

	@Override
	public AmendmentTill getAmendmentTilRequest(int id) {
		return cashingDAO.getAmendmentTilRequest(id);
	}

	@Override
	public boolean checkAmendmentTilRequestExistOrNot(String cashId,int branchid) {
		// TODO Auto-generated method stub
		return cashingDAO.checkAmendmentTilRequestExistOrNot(cashId,branchid);
	}

	@Override
	public boolean checkChashingCompleteOrNot(int cashId, int branchid) {
		// TODO Auto-generated method stub
		return cashingDAO.checkChashingCompleteOrNot(cashId, branchid);
	}

}

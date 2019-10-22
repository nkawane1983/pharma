package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.BranchDAO;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.Branch;
import com.avee.form.BranchDetails;

import com.avee.service.BranchService;
@Service
public class BranchServiceImpl implements BranchService{

	@Autowired
	BranchDAO branchDAO;
	@Override
	public String insertBranchDetails(BranchDetails branchDetails) {
		String id = "";
		try{
			id = branchDAO.insertBranchDetails(branchDetails);
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public List<BranchDetails> searchBranchDetails(Map<String, String> map) {
		List<BranchDetails> list = null;
		try{
			list = branchDAO.searchBranchDetails(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public BranchDetails getBranchDetails(int id) {
		BranchDetails phyBranch =null;
		try{
			phyBranch = branchDAO.getBranchDetails(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return phyBranch;
	}
	@Override
	public void updateBranch(Branch branch) {
		try{
			branchDAO.updateBranch(branch);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void deleteBranch(int id) {
		try{
			branchDAO.deleteBranch(id);
		}catch(Exception e){
		
		}
	}
	@Override
	public String updateBranchDetails(BranchDetails branchDetails,String operation) {
		String msg = "";
		try{
			msg=branchDAO.updateBranchDetails(branchDetails,operation);
		}catch(Exception e){
			e.printStackTrace();
		}
		return msg;
		
	}
	@Override
	public List<BranchDetails> getBranchDetailsList(int id) {
		List<BranchDetails> list = null;
		try{
			list = branchDAO.getBranchDetailsList(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Object[]> getBranchDetailsByDefaultId(String id) {
		// TODO Auto-generated method stub
		return branchDAO.getBranchDetailsByDefaultId(id);
	}
	@Override
	public List<Object[]> branchDetailslist(Map<String, String> map) {
		// TODO Auto-generated method stub
		return branchDAO.branchDetailslist(map);
	}
	@Override
	public List<Object[]> branchDetailsAssolist(Map<String, String> map) {
		// TODO Auto-generated method stub
		return branchDAO.branchDetailsAssolist(map);
	}
	@Override
	public List<AppUserGroupBranchMapping> getBranchDetailsaAsList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return branchDAO.getBranchDetailsaAsList(map);
	}
	@Override
	public boolean checkBranchWorkingDayOrNo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return branchDAO.checkBranchWorkingDayOrNo(map);
	}
	@Override
	public List<BranchDetails> getBranchDetailsASList(String id,String mode,String userId,String type) {
		// TODO Auto-generated method stub
		return branchDAO.getBranchDetailsASList(id,mode,userId,type);
	}
	
}

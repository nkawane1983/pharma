package com.avee.utility;

import java.util.List;

import com.avee.form.SystemParameter;

public class SystemUtility {
	static private double VATRate0;
	static private double vatRate1;
	static private double vatRate2;
	static private double currentPrescriptionCharge;
	static private double discrepancyLimit;
	static private double reminderPeriod;
	static private double pewPrescriptionCharge;
	static private double previousPrescriptionCharge;
	static private double NewChargeDate;
	static private double BankingReminderPeriod;
	static private double CashReminderPeriod;
	public void setyStemUtility(List<SystemParameter> list)
	{
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getParameterName().equalsIgnoreCase("VATRate0"))
			{
				VATRate0=Double.parseDouble(list.get(i).getParameterValue());
			}
		}
	}
}

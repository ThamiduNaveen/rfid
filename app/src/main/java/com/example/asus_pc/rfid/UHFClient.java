package com.example.asus_pc.rfid;


import jni.Linuxc;
import uhf.api.UHF;

public class UHFClient
{
	
	public static UHF mUHF;
	private static UHFClient instance = null;
	//private String cmd_open1="echo on > /proc/uart3_3v3_en";
	
	/**
	 * ����ģʽ����ֹ�ظ�ʹ�ô���
	 * @return
	 */
	public static UHFClient getInstance() 
	{
		if (instance == null) 
		{
			
			//mUHF=new UHF("/dev/ttyMT0",Linuxc.BAUD_RATE_115200,1,0);
			//mUHF=new UHF("/dev/ttySAC3",Linuxc.BAUD_RATE_115200,1,0);
			//mUHF=new UHF("/dev/ttyMSM2",Linuxc.BAUD_RATE_115200,1,0);//0 2
			mUHF=new UHF("/dev/ttysWK2", Linuxc.BAUD_RATE_115200,1,0);   //2016.4.1�� ����P6300U��
			
			
			
			mUHF.com_fd=mUHF.transfer_open(mUHF);
			if(mUHF.com_fd>0)
			{
				instance=new UHFClient();
			}
		}
		return instance;
	}
	
	
	public static void Disconnect()
	{
		if(instance!=null)
		{
			if(mUHF!=null)
			{
				mUHF.transfer_close(mUHF);
				mUHF=null;
			}
			instance=null;
		}
	}
	
	
	

}

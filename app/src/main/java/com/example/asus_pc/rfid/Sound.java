package com.example.asus_pc.rfid;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;


public class Sound 
{

	public int max;
	public int current;
	MediaPlayer player_fail;
	MediaPlayer player_success;
	Vibrator vibrator;
	
	public Sound(Context context)
	{
		super();
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); // ������ʾ��
		max = audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
		current = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
		player_fail = MediaPlayer.create(context, R.raw.beep);
		player_fail.setVolume((float) current / (float) max, (float) current / (float) max); // ������ʾ����
		player_success = MediaPlayer.create(context, R.raw.duka3);
		player_success.setVolume((float) current / (float) max, (float) current / (float) max); // ������ʾ����
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE); // ��100����

	}
	
	/**
	 * 
	 * @param isok �ɹ�����ʧ�ܱ���
	 * @param ms ��ʱ���������λms
	 */

	public  void callAlarm(Boolean isok, int ms)
	{
		vibrator.vibrate(ms); // ��100����
		if(isok)
		{
			if(!player_success.isPlaying())
			{
				player_success.start();
			}
		}
		else
		{
			if(!player_fail.isPlaying())
			{
				player_fail.start();
			}
		}
		
	}
}

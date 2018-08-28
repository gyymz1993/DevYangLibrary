package vaxsoft.com.vaxphone.PhoneSIP.DigitTone;

import vaxsoft.com.vaxphone.PhoneSIP.AudioPlayer.AudioPlayer;
import vaxsoft.com.vaxphone.R;

public class PlayDTMF
{
	private AudioPlayer mPlayerWave = null;

	public PlayDTMF()
	{
		mPlayerWave = new AudioPlayer();
	}

	private void PlayStop()
	{
	    mPlayerWave.PlayStop();
	}

	private void PlayStart(int nToneId)
	{
    	PlayStop();

		mPlayerWave.PlayLoad(nToneId);
    	mPlayerWave.PlayStart(false);
	}

	public void PlayTone(String sDigit)
	{
		switch (sDigit)
		{
			case "0":
				this.PlayStart(R.raw.zero);
				break;

			case "1":
				this.PlayStart(R.raw.one);
				break;

			case "2":
				this.PlayStart(R.raw.two);
				break;

			case "3":
				this.PlayStart(R.raw.three);
				break;

			case "4":
				this.PlayStart(R.raw.four);
				break;

			case "5":
				this.PlayStart(R.raw.five);
				break;

			case "6":
				this.PlayStart(R.raw.six);
				break;

			case "7":
				this.PlayStart(R.raw.seven);
				break;

			case "8":
				this.PlayStart(R.raw.eight);
				break;

			case "9":
				this.PlayStart(R.raw.nine);
				break;

			case "*":
				this.PlayStart(R.raw.star);
				break;

			case "#":
				this.PlayStart(R.raw.number);
				break;
		}
	}
}
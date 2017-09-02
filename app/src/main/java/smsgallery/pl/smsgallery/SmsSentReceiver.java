package smsgallery.pl.smsgallery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsManager;
import android.util.Log;

public class SmsSentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent arg1) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                Log.d("httpmon", "SMS sent");
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                Log.d("httpmon", "SMS generic failure");
                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                Log.d("httpmon", "SMS no service");
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                Log.d("httpmon", "SMS null PDU");
                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                Log.d("httpmon", "SMS radio off");
                break;
        }
    }
}
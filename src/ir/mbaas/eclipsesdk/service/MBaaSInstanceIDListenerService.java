package ir.mbaas.eclipsesdk.service;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

import java.io.IOException;

import ir.mbaas.eclipsesdk.MBaaS;
import ir.mbaas.eclipsesdk.helper.PrefUtil;
import ir.mbaas.eclipsesdk.helper.StaticMethods;
import ir.mbaas.eclipsesdk.mbaas.Registration;

/**
 * Created by Mahdi on 4/7/2016.
 */
public class MBaaSInstanceIDListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        try {
            String senderId = StaticMethods.getSenderId(MBaaS.context);
            InstanceID instanceID = InstanceID.getInstance(MBaaS.context);
            String token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            String localToken =
                    PrefUtil.getString(MBaaS.context, PrefUtil.REGISTRATION_ID);

            if(localToken.equals(token))
                return;

            PrefUtil.putString(MBaaS.context, PrefUtil.REGISTRATION_ID, token);
            Registration regApi = new Registration(MBaaS.context, token, MBaaS.device,
                    MBaaS.hasGooglePlayService);
            regApi.execute();
            //send token to app server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ir.mbaas.eclipsesdk.logic;

import android.content.Intent;
import ir.mbaas.eclipsesdk.MBaaS;
import ir.mbaas.eclipsesdk.helper.AppConstants;
import ir.mbaas.eclipsesdk.models.MBaaSAppVersion;
import ir.mbaas.eclipsesdk.receivers.NotificationButtonReceiver;
import ir.mbaas.eclipsesdk.receivers.UpdateButtonReceiver;

/**
 * Created by Mehdi on 6/21/2016.
 */
public class UpdateActions {
    public enum UpdateActionType {
        Now(0), Later(1), Never(2);

        private final int value;

        private UpdateActionType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    static public Intent createUpdateButtonAction(UpdateActionType updateActionType,
                                                  MBaaSAppVersion appVersion,
                                                  int notificationId) {
        Intent intent = new Intent(MBaaS.context, UpdateButtonReceiver.class);
        intent.putExtra(AppConstants.UD_ACTION_TYPE, updateActionType.getValue());
        intent.putExtra(AppConstants.UD_DOWNLOAD_URL, appVersion.downloadUrl);
        intent.putExtra(AppConstants.APP_NOTIFICATION_ID, notificationId);

        return intent;
    }
}

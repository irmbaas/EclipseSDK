package ir.mbaas.eclipsesdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import ir.mbaas.eclipsesdk.dfapi.ApiException;
import ir.mbaas.eclipsesdk.dfapi.ApiInvoker;

import java.util.ArrayList;
import java.util.List;

public class NotificationButtons {
    @JsonProperty("Buttons")
    public List<NotificationButton> records = new ArrayList<>();

    public static NotificationButtons fromJson(String buttonsStr) {
        try {
            buttonsStr = "{\"Buttons\":" + buttonsStr + "}";
            return (NotificationButtons) ApiInvoker.deserialize(buttonsStr, "", NotificationButtons.class);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;
    }
}

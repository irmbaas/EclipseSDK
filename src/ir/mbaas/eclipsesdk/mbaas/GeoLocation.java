package ir.mbaas.eclipsesdk.mbaas;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import ir.mbaas.eclipsesdk.dfapi.ApiException;
import ir.mbaas.eclipsesdk.dfapi.BaseAsyncRequest;
import ir.mbaas.eclipsesdk.helper.AppConstants;
import ir.mbaas.eclipsesdk.helper.PrefUtil;

/**
 * Created by Mahdi on 4/25/2016.
 */
public class GeoLocation extends BaseAsyncRequest {

    private String TAG = "GeoLocation";
    private Context ctx;
    private String geoLocations;

    public GeoLocation(Context ctx, String geoLocations) {
        this.ctx = ctx;
        this.geoLocations = geoLocations;
    }

    @Override
    protected void doSetup() throws ApiException, JSONException {
        callerName = "sendLocations";

        serviceName = AppConstants.GCM_SERVICE;
        endPoint = AppConstants.GCM_LOCATIONS_API;
        contentType = "";
        verb = "POST";

        requestString = "GeoLocations={ \"locations\": [" + geoLocations + "]}";

        // need to include the API key and session token
        applicationApiKey = AppConstants.API_KEY;
        //sessionToken = PrefUtil.getString(ctx, PrefUtil.SESSION_TOKEN);
    }

    @Override
    protected void processResponse(String response) throws ApiException, JSONException {
        Log.d(TAG, "Response Received.");
    }

    @Override
    protected void onCompletion(boolean success) {
        if(success){
            PrefUtil.putString(ctx, PrefUtil.GEO_LOCATIONS, "");
        }
    }
}

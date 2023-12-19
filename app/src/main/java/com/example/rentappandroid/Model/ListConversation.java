package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.FireBase.DataCallback;
import com.example.rentappandroid.api.ApiLandrod;
import com.example.rentappandroid.api.ApiTenant;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListConversation {
    private String landrodId;
    private String tenantId;
    private Owner landrod;
    private Owner tenant;

    public ListConversation(String landrodId, String tenantId) {
        this.landrodId = landrodId;
        this.tenantId = tenantId;
    }

    // Method to initialize the data
    public void initializeData(String token, DataCallback dataCallback) {
        ApiLandrod.apiLandrod.getOne(landrodId, token).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                landrod = response.body();
                // Notify when the data is updated
                onDataInitialized(dataCallback);
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                // Handle failure
            }
        });

        ApiTenant.apiTenant.getOne(tenantId, token).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                tenant = response.body();
                // Notify when the data is updated
                onDataInitialized(dataCallback);
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                // Handle failure
            }
        });
    }

    // Callback to notify when data is initialized
    private void onDataInitialized(DataCallback dataCallback) {
        if (dataCallback != null) {
            dataCallback.onDataInitialized();
        }
    }

    public String getLandrodId() {
        return landrodId;
    }

    public void setLandrodId(String landrodId) {
        this.landrodId = landrodId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Owner getLandrod() {
        return landrod;
    }

    public void setLandrod(Owner landrod) {
        this.landrod = landrod;
    }

    public Owner getTenant() {
        return tenant;
    }

    public void setTenant(Owner tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ListConversation that = (ListConversation) obj;
        return landrodId.equals(that.landrodId) && tenantId.equals(that.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(landrodId, tenantId);
    }
}

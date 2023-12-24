package com.example.rentappandroid.FireBase;

import androidx.annotation.NonNull;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Model.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseHelper {
    private DatabaseReference databaseReference;

    public FirebaseHelper() {
        // Initialize the Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void addNotification(Notification notification) {
        // Generate a unique key for the new notification
        String notificationId = databaseReference.child("notifications").push().getKey();

        // Set the notification with the generated key
        notification.set_id(notificationId);

        // Save the notification to the "notifications" node in the database
        saveNotification(notification);
    }

    public void getNotificationsByTenant(String tenantId, final OnNotificationListReadListener listener) {
        // Read the notifications from the database based on tenantId
        databaseReference.child("notifications")
                .orderByChild("tenant/_id")
                .equalTo(tenantId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Notification> notificationList = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Notification notification = snapshot.getValue(Notification.class);
                            if (notification != null) {
                                notificationList.add(notification);
                            }
                        }
                        Collections.reverse(notificationList);
                        listener.onNotificationListRead(notificationList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        listener.onFirebaseError(databaseError.getMessage());
                    }
                });
    }

    public void getNotificationsByLandlord(String landlordId, final OnNotificationListReadListener listener) {
        // Read the notifications from the database based on landlordId
        databaseReference.child("notifications")
                .orderByChild("landlord/_id")
                .equalTo(landlordId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Notification> notificationList = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Notification notification = snapshot.getValue(Notification.class);
                            if (notification != null) {
                                notificationList.add(notification);
                            }
                        }
                        Collections.reverse(notificationList);
                        listener.onNotificationListRead(notificationList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        listener.onFirebaseError(databaseError.getMessage());
                    }
                });
    }

    private void saveNotification(Notification notification) {
        // Convert the Notification object to a Map
        Map<String, Object> notificationMap = new HashMap<>();
        notificationMap.put("_id", notification.get_id());
        notificationMap.put("description", notification.getDescription());
        notificationMap.put("tenant", convertOwnerToMap(notification.getTenant()));
        notificationMap.put("landlord", convertOwnerToMap(notification.getLandlord()));
        notificationMap.put("date", notification.getDate());
        notificationMap.put("type", notification.getType());
        notificationMap.put("id_type", notification.getId_type());

        // Save to the "notifications" node in the database
        databaseReference.child("notifications").child(notification.get_id()).setValue(notificationMap);
    }

    private Map<String, Object> convertOwnerToMap(Owner owner) {
        Map<String, Object> ownerMap = new HashMap<>();
        ownerMap.put("_id", owner.get_id());
        ownerMap.put("name", owner.getName());
        ownerMap.put("email", owner.getEmail());
        ownerMap.put("password", owner.getPassword());
        ownerMap.put("phoneNumber", owner.getPhoneNumber());
        ownerMap.put("address", owner.getAddress());
        ownerMap.put("role", owner.getRole());
        ownerMap.put("__v", owner.get__v());
        ownerMap.put("token", owner.getToken());
        return ownerMap;
    }
    public interface OnNotificationListReadListener {
        void onNotificationListRead(List<Notification> notificationList);
        void onFirebaseError(String errorMessage);
    }
}

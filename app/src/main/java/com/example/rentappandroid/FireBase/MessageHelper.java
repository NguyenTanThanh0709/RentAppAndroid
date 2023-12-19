package com.example.rentappandroid.FireBase;

import androidx.annotation.NonNull;

import com.example.rentappandroid.Model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageHelper {

    private DatabaseReference databaseReference;

    public MessageHelper() {
        // Initialize the Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void addMessage(Message message) {
        String conversationId = message.getLandrodId() + "_" + message.getTenantId();
        String messageId = databaseReference.child("messages").child(conversationId).push().getKey();
        databaseReference.child("messages").child(conversationId).child(messageId).setValue(message);
    }

    public void getMessages(String landrodId, String tenantId, ValueEventListener valueEventListener) {
        String conversationId = landrodId + "_" + tenantId;
        DatabaseReference conversationRef = databaseReference.child("messages").child(conversationId);
        conversationRef.addValueEventListener(valueEventListener);
    }


    public void getConversationIds(ConversationIdsCallback callback) {
        DatabaseReference messagesRef = databaseReference.child("messages");
        messagesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> conversationIds = new ArrayList<>();
                for (DataSnapshot conversationSnapshot : dataSnapshot.getChildren()) {
                    // Get each conversation ID and add it to the list
                    String conversationId = conversationSnapshot.getKey();
                    conversationIds.add(conversationId);
                }
                // Pass the list of conversation IDs to the callback
                callback.onConversationIdsReceived(conversationIds);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled
            }
        });
    }

}



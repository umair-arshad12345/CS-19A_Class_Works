package com.example.test.myclasses;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Firestore {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context ctx;
    public Firestore(Context ctx){
        this.ctx = ctx;
    }
    public void Add(String collection,Object data){
        db.collection(collection).add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(ctx, "Record Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public Task<QuerySnapshot> Read(String collection){
        return db.collection(collection).get();
    }
    public Task<DocumentSnapshot> Read(String collection, String documentid){
        return db.collection(collection).document(documentid).get();
    }
    public Task<QuerySnapshot> filter(String collection, String field, String query){
        return db.collection(collection).whereEqualTo(field,query).get();
    }
    public void Update(String collection,String Documentid,Object data){
        db.collection(collection).document(Documentid).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ctx, "Record Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void Delete(String collection,String Documentid) {
        db.collection(collection).document(Documentid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ctx, "Record Deleted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    }


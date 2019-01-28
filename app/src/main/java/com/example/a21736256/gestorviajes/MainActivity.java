package com.example.a21736256.gestorviajes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.a21736256.gestorviajes.javabean.Viaje;
import com.example.a21736256.gestorviajes.javabean.ViajeAdaptador;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference dbr;
    private ChildEventListener cel;

    ArrayList<Viaje> datos;
    ViajeAdaptador adapter;

    RecyclerView rvViajes;
    LinearLayoutManager llLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = new ArrayList<Viaje>();
        adapter= new ViajeAdaptador(datos);
        rvViajes=findViewById(R.id.rvViajes);

        llLayout= new LinearLayoutManager(this);

        rvViajes.setLayoutManager(llLayout);
        rvViajes.setAdapter(adapter);
        rvViajes.setItemAnimator(new DefaultItemAnimator());
        dbr=FirebaseDatabase.getInstance().getReference().child("viaje");


        addChildEvenListener();
    }

    private void addChildEvenListener() {
        if(cel==null){
            cel = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Viaje v= dataSnapshot.getValue(Viaje.class);
                    datos.add(v);
                    adapter.notifyItemInserted(datos.size()-1);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    System.out.println("Mensaje Modificado");
                    Viaje v = dataSnapshot.getValue(Viaje.class);

                    int pos=0;

                    for (int i=0; i<datos.size();i++){
                        if(datos.get(i).getNombre().equals(v.getNombre())){
                            datos.set(i,v);
                            pos=i;
                        }
                    }
                    adapter.notifyItemChanged(pos);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    //System.out.println("Mensaje Borrado: " + dataSnapshot.getValue(Mensaje.class).getTexto());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            dbr.addChildEventListener(cel);
        }
    }
    public void añadir(View view){
        Intent i = new Intent(this, Anadir.class);
        startActivity(i);
    }


}


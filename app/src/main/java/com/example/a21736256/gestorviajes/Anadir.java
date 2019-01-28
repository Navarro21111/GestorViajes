package com.example.a21736256.gestorviajes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.a21736256.gestorviajes.javabean.Viaje;
import com.example.a21736256.gestorviajes.javabean.ViajeAdaptador;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Anadir extends AppCompatActivity {

    EditText etNombre;
    EditText etCiudad;
    EditText etPais;
    EditText etNumDias;
    EditText etPrecio;

    RelativeLayout rlAñadir;

    private DatabaseReference dbr;
    private ChildEventListener cel;

    ArrayList<Viaje> datos;
    ViajeAdaptador adapter;

    String nombre;
    String ciudad;
    String pais;
    int numdias;
    int precio;

    Viaje viaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        etNombre=findViewById(R.id.etNombre);
        etCiudad=findViewById(R.id.etCiudad);
        etPais=findViewById(R.id.etPais);
        etNumDias=findViewById(R.id.etNumDias);
        etPrecio=findViewById(R.id.etPrecio);



        dbr=FirebaseDatabase.getInstance().getReference().child("viaje");
    }
    private void addChildEvenListener() {
        if(cel==null){
            cel = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    System.out.println("Nuevo Mensaje");
                    Viaje m= (Viaje) dataSnapshot.getValue(Viaje.class);
                    datos.add(m);
                    adapter.notifyItemInserted(datos.size()-1);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    System.out.println("Mensaje Modificado");
                    Viaje m = dataSnapshot.getValue(Viaje.class);

                    int pos=0;

                    for (int i=0; i<datos.size();i++){
                        /*if(datos.get(i).getTexto().equals(m.getTexto())){
                            datos.set(i,m);
                            pos=i;
                        }*/
                    }
                    adapter.notifyItemChanged(pos);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    System.out.println("Mensaje Borrado: " + dataSnapshot.getValue(Viaje.class).getNombre());
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

    public void Enviar(View view){
        nombre=etNombre.getText().toString();
        ciudad=etCiudad.getText().toString();
        pais=etPais.getText().toString();
        numdias=Integer.parseInt(etNumDias.getText().toString());
        precio=Integer.parseInt(etPrecio.getText().toString());
        viaje= new Viaje(nombre,ciudad,pais,numdias,precio);

        String clave=dbr.push().getKey();
        dbr.child(clave).setValue(viaje);


    }

}

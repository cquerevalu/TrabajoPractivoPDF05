package com.ditec.migranparte5;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterContacto  extends RecyclerView.Adapter<AdapterContacto.ViewHolder> {
    private LayoutInflater inflador; List<Contacto> contactList; Context micontext; private int[] colors;
    public AdapterContacto(Context context, ArrayList<Contacto> contactList, int[] colors) {
        this.contactList= contactList; this.micontext=context; this.colors = colors;
        inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.item_contacto, parent, false);return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        Contacto contact = contactList.get(i);
        String fullName = contact.getFirstName() + " " + contact.getLastName();
        holder.tvcodigo.setText(contact.getIdcontacto()+"");
        holder.tvdireccion.setText(contact.getDireccion());
        holder.mNameTextView.setText(fullName);
        String initial = contact.getFirstName().toUpperCase().substring(0, 1);
        holder.mInitialsTextView.setText(initial);
        holder.mInitialsBackground.setColor(colors[i % colors.length]);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(micontext, UpdateContact.class);
                intent.putExtra(UpdateContact.EXTRA_CONTACT_ID, contact.getPhoneNumber());
                ((MainActivity)micontext).startActivityForResult(intent, 2);
            }
        });
    }
    @Override
    public int getItemCount() { return contactList.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mInitialsTextView, tvdireccion, tvcodigo;
        private GradientDrawable mInitialsBackground;

        ViewHolder(View itemView) {
            super(itemView);
            mInitialsTextView = itemView.findViewById(R.id.initialsTextView);
            mNameTextView = itemView.findViewById(R.id.nameTextView);
            tvdireccion= itemView.findViewById(R.id.tvdireccion);
            tvcodigo=itemView.findViewById(R.id.tvcodigo);
            mInitialsBackground = (GradientDrawable) mInitialsTextView.getBackground();
        }
    }
    void updateData(List<Contacto> contacts) {
        this.contactList = contacts; notifyDataSetChanged();
    }
}

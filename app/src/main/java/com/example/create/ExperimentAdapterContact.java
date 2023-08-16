package com.example.create;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExperimentAdapterContact extends RecyclerView.Adapter<ExperimentAdapterContact.ContactViewHolder> {

    private Context context;
    private ArrayList<Experiment> contactList;
    private DbHelper dbHelper;

    public ExperimentAdapterContact(Context context, ArrayList<Experiment> contactList) {
        this.context = context;
        this.contactList = contactList;
        dbHelper = new DbHelper(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_contact_item_extantion, parent, false);
        ContactViewHolder vh = new ContactViewHolder(view);
        return vh;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Experiment modelContact = contactList.get(position);
        String name = modelContact.getName();
        holder.contactName.setText(name);
        Log.d("AdapterContact", "Binding data for position: " + position + " Name: " + name);
    }




    class ContactViewHolder extends RecyclerView.ViewHolder {

        //view for row_contact_item
        ImageView  contactDial;
        TextView contactName;
        RelativeLayout relativeLayout;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            //init view
            contactDial = itemView.findViewById(R.id.contactRv);
            contactName = itemView.findViewById(R.id.contact_name);

        }
    }
}

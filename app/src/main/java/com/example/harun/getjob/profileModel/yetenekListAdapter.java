package com.example.harun.getjob.profileModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.harun.getjob.Profile.ProfileInterfaces;
import com.example.harun.getjob.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mayne on 24.02.2018.
 */

public class yetenekListAdapter extends RecyclerView.Adapter<yetenekListAdapter.MyViewHolder> {
    private static final String TAG = "yetenekListAdapter";

    private LayoutInflater layoutInflater;
    private ArrayList<yetenekModel> yetenekListe;
    // yetenekModel yetenekModel=new yetenekModel();
    private HashMap<String, ArrayList<yetenekModel>> yetenekHash = new HashMap<>();
    private ArrayList<yetenekModel> hashyetenek = new ArrayList<>();
    private boolean visibilityCheck;
    Context mContext;
    ProfileInterfaces mcontentFragment;


    public yetenekListAdapter(Context context, ArrayList<yetenekModel> yetenekListe, boolean visibility) {
        Log.d(TAG, "yetenekListAdapter: ");
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.yetenekListe = yetenekListe;
        this.visibilityCheck = visibility;//yetenek ekleme butonu profil pagede gösterilmeyecek editprofile pagede gösterilecek
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View v = layoutInflater.inflate(R.layout.yeteneklist_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: ÇALIŞIYOPRRR\t" + position);
        final yetenekModel myetenekmodel = yetenekListe.get(position);
        setYetenekHash(String.valueOf(position), myetenekmodel);
        holder.tvYetenek.setText(myetenekmodel.getYetenekName().toUpperCase());
        holder.rateStar.setRating(((float) myetenekmodel.getRate()));

        if (visibilityCheck) {

            holder.editYetenekrow.setVisibility(View.GONE);
        } else {
            holder.editYetenekrow.setVisibility(View.VISIBLE);
        }

        holder.editYetenekrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListRow(myetenekmodel, position);
            }
        });


    }

    private void updateListRow(yetenekModel myetenekmodel, int position) {
        Log.d(TAG, "updateListRow: ");
        this.mcontentFragment = (ProfileInterfaces) mContext;
        mcontentFragment.updateYetenekListItem(myetenekmodel, position);

    }

    private void setYetenekHash(String position, yetenekModel model) {

        Log.d(TAG, "setYetenekHash: ");
        hashyetenek.add(model);
        yetenekHash.put(position, hashyetenek);
        Log.d("HASHMAPPİNGG ", "hashmapping: " + yetenekHash.entrySet());

        for (yetenekModel mode : hashyetenek) {

            Log.d("FOR ", "hashmapping: " + mode.getYetenekName()
                    + "\t" + mode.getRate() + "\t" + model.getYetenekName());

        }

    }

    public HashMap<String, ArrayList<yetenekModel>> getYetenekHash() {
        Log.d(TAG, "getYetenekHash: +" + yetenekHash.entrySet());
        return this.yetenekHash;

    }

    @Override
    public int getItemCount() {
        return yetenekListe.size();
    }

    public void removeItem(int yetenekListPosition, ArrayList<yetenekModel> recyclerYetenekList) {

        Log.d(TAG, "restoreItem: " +"\t"+ yetenekListPosition);

        yetenekHash.clear();
        yetenekHash.put(String.valueOf(yetenekListPosition),recyclerYetenekList);
        notifyItemRemoved(yetenekListPosition);


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvYetenek;
        RatingBar rateStar;
        ImageView editYetenekrow;
        public LinearLayout yetenekForeground;

        public MyViewHolder(View itemView) {
            super(itemView);
            //satır elemanları tanımlanacak

            tvYetenek = itemView.findViewById(R.id.yetenek);
            rateStar = itemView.findViewById(R.id.yetenekRating);
            editYetenekrow = itemView.findViewById(R.id.editYetenekrow);
            yetenekForeground = itemView.findViewById(R.id.yetenekForeground);
        }
    }
}

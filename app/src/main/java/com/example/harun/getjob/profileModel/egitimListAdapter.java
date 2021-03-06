package com.example.harun.getjob.profileModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.harun.getjob.Profile.ProfileInterfaces;
import com.example.harun.getjob.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mayne on 17.02.2018.
 */

public class egitimListAdapter extends RecyclerView.Adapter<egitimListAdapter.ViewHolder> {

    private ArrayList<egitimListModel> egitimList;
    private final static String TAG = "egitimListAdapter";
    private LayoutInflater inflater;
    private egitimListModel egitimListModel;
    private Context mcontext;
    private boolean visibilityCheck;
    ProfileInterfaces mcontentFragment;

    public HashMap<String, ArrayList<egitimListModel>> egitimHash = new HashMap<>();
    public ArrayList<egitimListModel> egitimHashList = new ArrayList<>();


    public egitimListAdapter(Context context, ArrayList<egitimListModel> egitimListe, boolean visibility) {
        Log.d(TAG, "egitimListAdapter: " + egitimListe);
        this.egitimList = egitimListe;
        this.inflater = LayoutInflater.from(context);
        this.mcontext = context;
        this.visibilityCheck = visibility;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.egitimlist_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.d(TAG, "EgitimListOnCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        egitimListModel = egitimList.get(position);
        Log.d(TAG, "onBindViewHolder: ");

        setEgitimHashMap(String.valueOf(position), egitimListModel);

        holder.bsYil.setText(egitimListModel.getBsYılı());
        holder.btsYil.setText(egitimListModel.getBtsYılı());
        holder.nameOkul.setText(egitimListModel.getOkul());
        holder.tvBolum.setText(egitimListModel.getBolum());
        holder.tvTur.setText(egitimListModel.getOgrenimTuru());

        if (visibilityCheck) {

            holder.editListRow.setVisibility(View.GONE);
        } else {
            holder.editListRow.setVisibility(View.VISIBLE);
        }

        holder.editListRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateListItem(egitimListModel, position);
            }
        });

    }

    private void updateListItem(egitimListModel egitimListModel, int position) {
        Log.d(TAG, "updateListItem: ");
        mcontentFragment = (ProfileInterfaces) mcontext;
        mcontentFragment.updateEgitimListItem(egitimListModel, position);

    }


    private void setEgitimHashMap(String s, egitimListModel egitimListModel) {
        Log.d(TAG, "setEgitimHashMap: " + "s" + "\t" + egitimListModel.getBolum());
        egitimHashList.add(egitimListModel);
        egitimHash.put(s, egitimHashList);

    }


    public HashMap<String, ArrayList<egitimListModel>> getEgitimHashMap() {
        Log.d(TAG, "getEgitimHashMap: ");

//        if (this.egitimHash.isEmpty()) {
//
//            egitimHashList.add(egitimListModel);
//            Log.d(TAG, "getEgitimHashMap: EGitimHASH EMPTY eN AZINDAN NULL DONMEZ HATA VERMEZ ZATEN KAYIT EDİLMEYCEK ");
//            egitimHash.put("0", egitimHashList);
//            return this.egitimHash;
//
//        } else {
//
//        }

        return this.egitimHash;
    }

    @Override
    public int getItemCount() {
        return egitimList.size();
    }

    public void removeItem(int egitimListposition, ArrayList<egitimListModel> egitimListe) {

        egitimHash.clear();

        egitimHash.put(String.valueOf(egitimListposition), egitimListe);

        notifyItemRemoved(egitimListposition);


    }


    // Bir satırda bulunan elemanları tanımlayacagımız sınıf
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bsYil, btsYil, nameOkul, tvBolum, tvTur;
        ImageView editListRow;
        public RelativeLayout egitim_foreground;

        public ViewHolder(View itemView) {
            super(itemView);
            bsYil = itemView.findViewById(R.id.bsYil);
            btsYil = itemView.findViewById(R.id.btsYil);
            nameOkul = itemView.findViewById(R.id.nameOkul);
            tvBolum = itemView.findViewById(R.id.tvBolum);
            tvTur = itemView.findViewById(R.id.tvTur);
            editListRow = itemView.findViewById(R.id.editListRow);
            egitim_foreground = itemView.findViewById(R.id.egitim_foreground);
        }
    }
}

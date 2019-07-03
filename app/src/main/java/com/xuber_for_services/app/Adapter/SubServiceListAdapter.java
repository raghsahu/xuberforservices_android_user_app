package com.xuber_for_services.app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.xuber_for_services.app.Fragments.HomeCategoryFragment;
import com.xuber_for_services.app.Fragments.SubServiceFragment;
import com.xuber_for_services.app.Helper.SharedHelper;
import com.xuber_for_services.app.Models.ServiceListModel;
import com.xuber_for_services.app.Models.SubServiceListModel;
import com.xuber_for_services.app.R;
import com.xuber_for_services.app.Utils.Utilities;

import org.json.JSONArray;

import java.util.ArrayList;

public class SubServiceListAdapter extends RecyclerView.Adapter<SubServiceListAdapter.ViewHolder> implements View.OnClickListener {
    ArrayList<SubServiceListModel> listModels = new ArrayList<SubServiceListModel>();
    Context context;
    JSONArray jsonArraylist;
    private RadioButton lastChecked = null;
    BottomSheetBehavior behavior;
    String TAG = "ServiceListAdapter";
    private int lastCheckedPos = 0;
    JSONArray jsonArray;
    Utilities utils = new Utilities();
    SubServiceFragment.SubServiceFgmtListener mListener;
    ArrayList<SubServiceListModel> subServiceModel;

    public SubServiceListAdapter(SubServiceFragment.SubServiceFgmtListener mListener, JSONArray jsonArray, ArrayList<SubServiceListModel> subServiceModel) {
        this.jsonArray = jsonArray;
        this.mListener = mListener;
        this.subServiceModel = subServiceModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_list_item, null);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.service_list_name.setText(jsonArray.optJSONObject(position).optString("name"));
        System.out.println("=====>"+Utilities.getImageURL(jsonArray.optJSONObject(position).optString("image")));
        Picasso.with(context).load(Utilities.getImageURL(jsonArray.optJSONObject(position).optString("image")))
                .centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE)
                .fit()
                .into(holder.service_image_icon);

        holder.service_list_layout.setTag(position);


        holder.service_list_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedHelper.putKey(context, "sub_service_type", "" + jsonArray.optJSONObject(Integer.parseInt(view.getTag().toString())).optString("id"));
                SubServiceListModel subServiceListModel = subServiceModel.get(Integer.parseInt(view.getTag().toString()));
                utils.print("sub_service_type",""+SharedHelper.getKey(context, "sub_service_type"));
//                SharedHelper.putKey(context, "hourly_fare", subServiceListModel.getHourlyFare());
//                SharedHelper.putKey(context, "service_name", subServiceListModel.getServiceType());
                utils.print(TAG, "onItemClicked: " + subServiceListModel);
                mListener.moveToServiceFlowFragment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView service_list_name;
        ImageView service_image_icon, imageDark;
        CheckBox service_checkbox;
        CardView service_list_layout;


        public ViewHolder(View itemView) {
            super(itemView);
            service_image_icon = (ImageView) itemView.findViewById(R.id.service_image_icon);
            service_list_name = (TextView) itemView.findViewById(R.id.service_list_name);
            service_list_layout = (CardView) itemView.findViewById(R.id.service_list_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Log.v(TAG, "Response 1 " + getAdapterPosition());
//            Fragment fragment = new MapFragment();
//            FragmentManager manager = ((Home) context).getSupportFragmentManager();
//            @SuppressLint("CommitTransaction") FragmentTransaction transaction = manager.beginTransaction();
//            transaction.replace(R.id.container, fragment);
//            transaction.addToBackStack("MapFragment");
//            transaction.commit();
        }
    }
}
package com.example.horizontalnumberpicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomPickerAdapter extends RecyclerView.Adapter<CustomPickerAdapter.CustomViewHolder> {

    private Context  mContext;
    private int      mCurrentSelectedPos;

    public CustomPickerAdapter() {
        mCurrentSelectedPos = 0;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View holder = LayoutInflater.from(mContext).inflate(R.layout.item_number_picker, parent, false);
        return new CustomViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int iViewIdx = getCalcValueIdx(position);
        holder.tvNumber.setText(String.valueOf(iViewIdx));

        if ( position == mCurrentSelectedPos ) {
            // set ui current center text
            holder.tvNumber.setAlpha(1.0f);
        } else {
            holder.tvNumber.setAlpha(0.3f);
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public int getCurrentPosition() {
        return mCurrentSelectedPos;
    }

    public void setCurrentPosition(int _iPosition) {
        mCurrentSelectedPos = _iPosition;
        notifyDataSetChanged();
    }

    public int getCalcValueIdx(int _iPosition) {
        if ( 0 == _iPosition )
            return 0;
        else
            return _iPosition % 11;
    }

    protected class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }
    }
}

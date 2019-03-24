package h.alexeypipchuk.worklist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private String[] mCaptions;
    private String[] mStatuses;
    private String[] mDescriptions;
    private String[] mDates;
    private String[] mImportances;
    private Listener mListener;

    public MyAdapter(String[] mCaptions, String[] mStatuses, String[] mDescriptions, String[] mDates, String[] mImportances) {
        this.mCaptions = mCaptions;
        this.mStatuses = mStatuses;
        this.mDescriptions = mDescriptions;
        this.mDates = mDates;
        this.mImportances = mImportances;
    }

    public static interface Listener {
        public void onClick(int position);
    }

    public void setListener(Listener listener){
        this.mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        CardView cardView = holder.cardView;
        TextView caption = (TextView)cardView.findViewById(R.id.caption);
        caption.setText(mCaptions[position]);
        TextView importance = (TextView)cardView.findViewById(R.id.importance);
        importance.setText(mImportances[position]);
        TextView status = (TextView)cardView.findViewById(R.id.status);
        status.setText(mStatuses[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(position);
                }
            }
        });
    }
    @Override
    public int getItemCount(){
        return mCaptions.length;
    }
}

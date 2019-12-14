package com.example.quotescollector.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quotescollector.Model.tools.SourceFullWithNumbOfQ;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

import java.util.ArrayList;
import java.util.List;

public class SourceListAdapter extends RecyclerView.Adapter<SourceListAdapter.SourceViewHolder> {

    private List<SourceFullWithNumbOfQ> mSources;
    private List<SourceFullWithNumbOfQ> mSourcesCopy = new ArrayList<>();
    private LayoutInflater mInflater;


    public SourceListAdapter(Context context, List<SourceFullWithNumbOfQ> mSources) {
        this.mSources = mSources;
        mInflater = LayoutInflater.from(context);
        mSourcesCopy.addAll(mSources);
    }


    @Override
    public SourceListAdapter.SourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.source_row, parent, false);
        SourceListAdapter.SourceViewHolder vh = new SourceListAdapter.SourceViewHolder(v, mSources);
        return vh;
    }


    @Override
    public void onBindViewHolder(SourceListAdapter.SourceViewHolder holder, int position) {
        holder.sourceNameTextView.setText(mSources.get(position).sourceTitle);
        holder.sourceTypeTextView.setText(mSources.get(position).sourceTypeName);
        holder.numberOfQuotesTextView.setText(Integer.toString(mSources.get(position).numberOfQuotes));
    }

    @Override
    public int getItemCount() {
        return mSources.size();
    }




    public void filter(String text) {
        mSources.clear();
        if(text.isEmpty()){
            mSources.addAll(mSourcesCopy);
        } else{
            text = text.toLowerCase();
            for(SourceFullWithNumbOfQ source: mSourcesCopy){
                if(source.sourceTitle.toLowerCase().contains(text)) {
                    mSources.add(source);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class SourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView sourceNameTextView, sourceTypeTextView, numberOfQuotesTextView;
        private List<SourceFullWithNumbOfQ> sourceWNList;
        QuotesDatabase database;

        public SourceViewHolder(View view, List<SourceFullWithNumbOfQ> sourceWNList) {
            super(view);

            this.sourceWNList = sourceWNList;

            this.sourceNameTextView = view.findViewById(R.id.sourceNameInSourceRowTV);
            this.sourceTypeTextView = view.findViewById(R.id.sourceTypeInSourceRow);
//            authorNameTextView.setWidth(Resources.getSystem().getDisplayMetrics().widthPixels * 2 / 3);

            view.setOnClickListener(this);

            this.numberOfQuotesTextView = view.findViewById(R.id.numbOfQuoInSourceRowTV);

            database = QuotesDatabase.getInstance(view.getContext());
        }

        @Override
        public void onClick(View view) {
            System.out.println("Działa");
            Toast.makeText(view.getContext(), "Usuwanko", Toast.LENGTH_SHORT).show();
            //show author quotes
        }
    }

}

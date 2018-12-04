package com.example.greendao.allsignin.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greendao.allsignin.R;
import com.example.greendao.allsignin.model.ResponseDatum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<ResponseDatum> mContact;

    private ListItemClickListner mOnClickListener;

    public interface ListItemClickListner
    {
         void onButtonClick(ResponseDatum response);
    }

    public void setOnItemClickListner(ListItemClickListner onClickListener)
    {
        this.mOnClickListener=onClickListener;
    }


    public RecyclerAdapter(List<ResponseDatum> mContact) {

        this.mContact = mContact;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ResponseDatum contact = mContact.get(i);
        myViewHolder.txt.setText(contact.getPassword());
        myViewHolder.txt2.setText(contact.getUsername());
        myViewHolder.itemView.setTag(contact);

        Picasso.get()
                .load("http://portfolio.barodaweb.com/dynamic/imageapi/api/Image/Compress?sp=a1.jpg&s=1000&e=jpg")
                .into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {

        return mContact.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt, txt2;
        ImageView imageView;
        Button btn_add;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            txt = itemView.findViewById(R.id.textView);
            txt2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.image_poster);
            btn_add = itemView.findViewById(R.id.add);
            btn_add.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view == btn_add) {

                ResponseDatum response = mContact.get(getAdapterPosition());
                mOnClickListener.onButtonClick(response);


            }

        }
    }
}

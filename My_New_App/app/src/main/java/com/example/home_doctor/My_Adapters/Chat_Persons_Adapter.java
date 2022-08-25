package com.example.home_doctor.My_Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.home_doctor.R;
import com.example.home_doctor.Storages.Chat_Person_Details;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat_Persons_Adapter extends ArrayAdapter {
    List<Chat_Person_Details> list;
    Context context;

    public Chat_Persons_Adapter(Context context, List<Chat_Person_Details> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        if (newView == null) {
            newView = LayoutInflater.from(getContext()).inflate(R.layout.person_chat, parent, false);
        }

        Chat_Person_Details chatPersonDetails = (Chat_Person_Details) getItem(position);
        newView.setTag(chatPersonDetails);
        TextView doc_name = newView.findViewById(R.id.person_name);
        TextView msg = newView.findViewById(R.id.person_msg);
        TextView chat_Badge = newView.findViewById(R.id.chat_badge);
        CircleImageView circleImageView = newView.findViewById(R.id.person_img);

        doc_name.setText(chatPersonDetails.getName());

        Glide.with(context).load(chatPersonDetails.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(circleImageView);


     /*   newView.findViewById(R.id.person_img_layout).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ViewHolder")
            @Override
            public void onClick(View v) {
                AlertDialog builderShowProfile = new AlertDialog.Builder(getContext()).create();
                builderShowProfile.setCanceledOnTouchOutside(true);
                LayoutInflater layoutInflaterShowProfile = LayoutInflater.from(getContext());
                View myImgView = layoutInflaterShowProfile.inflate(R.layout.my_image, null, false);
                ImageView imageViewShowProfile = myImgView.findViewById(R.id.profile);
                LinearLayout root = (LinearLayout) myImgView.getRootView();
                root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(), Profile_Viewer.class);
                        intent.putExtra("image", chatPersonDetails.getImg());
                        intent.putExtra("name", doc_name.getText().toString());
                        getContext().startActivity(intent);
                        builderShowProfile.cancel();
                    }
                });
                TextView user = myImgView.findViewById(R.id.user);
                user.setText(doc_name.getText().toString());
                Glide.with(getContext()).load(chatPersonDetails.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageViewShowProfile);
                builderShowProfile.setTitle(doc_name.getText().toString());
                builderShowProfile.setView(myImgView);
                builderShowProfile.show();

            }
        });*/


        chat_Badge.setText(String.valueOf(chatPersonDetails.getBadgeNum()));

        return newView;
    }
}

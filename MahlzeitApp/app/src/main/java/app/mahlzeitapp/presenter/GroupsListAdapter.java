package app.mahlzeitapp.presenter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;

import app.mahlzeitapp.model.Group;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Bianca on 20.04.2017.
 */

public class GroupsListAdapter extends ArrayAdapter<Group>
{
    private Context context;
    private int layoutResourceId;
    private ArrayList<Group> data = null;
    private final Person user;
    private final  MahlzeitServiceAPI service;

    public GroupsListAdapter(Context context, int layoutResourceId, ArrayList<Group> data, Person user, MahlzeitServiceAPI service) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.user = user;
        this.service = service;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Group group = getItem(position);

        View row = convertView;

        final GroupHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new GroupHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIconGroup);
            holder.imgIconDetail = (ImageView)row.findViewById(R.id.imgIconGroupDetail);
            holder.txtTitle = (TextView)row.findViewById(R.id.list_item_groups);
            row.setTag(holder);
        }
        else
        {
            holder = (GroupHolder) row.getTag();
        }

        holder.txtTitle.setText(group.getRestaurant().getName() + "(" + group.getRestaurant().getPlace() + ")");
        holder.imgIconDetail.setImageResource(R.mipmap.ic_detail);
        ArrayList<Person> members = group.getMembers();
        boolean user_in_group = false;
        for(int i = 0; i < members.size(); i++) {
            if (members.get(i).getPersonenkennziffer().equals(user.getPersonenkennziffer()))
                user_in_group = true;
        }
        final boolean user_is_group_member = user_in_group;
        if(user_is_group_member)
            holder.imgIcon.setImageResource(R.mipmap.ic_remove);
        else
            holder.imgIcon.setImageResource(R.mipmap.ic_add);
        holder.imgIcon.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View view)
            {

                try {
                    if(user_is_group_member) {
                        service.joinGroup(user, group, context,user_is_group_member);
                        holder.imgIcon.setImageResource(R.mipmap.ic_add);
                    }
                    else {
                        service.joinGroup(user, group, context,user_is_group_member);
                        holder.imgIcon.setImageResource(R.mipmap.ic_remove);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.imgIconDetail.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View view)
            {
                //go to detail view
            }
        });
        return row;
    }

    public Group getItem(int position){
        return data.get(position);
    }

    static class GroupHolder
    {
        ImageView imgIcon;
        ImageView imgIconDetail;
        TextView txtTitle;
    }
}
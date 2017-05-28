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
            holder.txtTitle = (TextView)row.findViewById(R.id.list_item_groups);
            row.setTag(holder);
        }
        else
        {
            holder = (GroupHolder) row.getTag();
        }

        holder.txtTitle.setText(group.getRestaurant().getName() + "(" + group.getRestaurant().getPlace() + ")");
        return row;
    }

    public Group getItem(int position){
        return data.get(position);
    }

    static class GroupHolder
    {
        TextView txtTitle;
    }
}

package com.example.mpsan.laboratorio2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mpsan.laboratorio2.models.Forms;

import java.util.List;

/**
 * Created by mpsan on 10-04-2018.
 */

public class FormAdapter extends ArrayAdapter<Forms>{
    public FormAdapter(Context context, List<Forms> forms){
        super(context, 0, forms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Forms form = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.form_data, parent, false);
        }
        // Lookup view for data population
        TextView formName = (TextView) convertView.findViewById(R.id.name_form_adapter);
        TextView formDate = (TextView) convertView.findViewById(R.id.date_form_adapter);
        TextView formCategory = (TextView) convertView.findViewById(R.id.category_form_adapter);
        TextView formComment = (TextView) convertView.findViewById(R.id.comments_form_adapter);
        // Populate the data into the template view using the data object
        formName.setText(form.getFormName());
        formDate.setText(form.getDateTime());
        formCategory.setText(form.getFormCategory());
        formComment.setText(form.getFormSomethingElse());
        // Return the completed view to render on screen
        return convertView;
    }


}

package uk.ac.stir.cs.unit_converter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnitConverterFragment extends Fragment {

    private TextView convert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the layout for the fragment
        View view = inflater.inflate(R.layout.unit_conversion_fragment, container, false);

        convert = view.findViewById(R.id.textView2);

        Bundle bundle = getArguments();

        if(bundle == null){
            convert.setText(R.string.default_selection);
        }else {
            convert.setText(String.valueOf(bundle.getString("selection")));
        }

        return view;
    }
}

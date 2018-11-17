package uk.ac.stir.cs.unit_converter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnitConverterFragment extends Fragment {

    private TextView startUnits, endUnits;
    private View unitConversionView;

    private String[] unit_conversions = {"Meters to Yards", "Miles to Yards", "Grams to Ounces", "Kilograms to Pounds"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the layout for the fragment
        unitConversionView = inflater.inflate(R.layout.unit_conversion_fragment, container, false);

        startUnits = unitConversionView.findViewById(R.id.tv_startUnits);
        endUnits = unitConversionView.findViewById(R.id.tv_endUnits);

        //retrieve selection from bundle

        Bundle bundle = getArguments();
        try{

            String selected = bundle.getString("selection");

            if(selected.equals(unit_conversions[0])){

                startUnits.setText(R.string.meter);
                endUnits.setText(R.string.yard);

            }else if(selected.equals(unit_conversions[1])){

                startUnits.setText(R.string.mile);
                endUnits.setText(R.string.yard);

            }else if(selected.equals(unit_conversions[2])){

                startUnits.setText(R.string.gram);
                endUnits.setText(R.string.ounce);

            }else{

                startUnits.setText(R.string.kilogram);
                endUnits.setText(R.string.pound);

            }

        }catch (NullPointerException e){

            startUnits.setText(R.string.default_selection);
            endUnits.setText(R.string.default_selection);

        }


        return unitConversionView;
    }

}

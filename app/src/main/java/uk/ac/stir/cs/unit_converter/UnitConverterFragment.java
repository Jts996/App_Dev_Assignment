package uk.ac.stir.cs.unit_converter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UnitConverterFragment extends Fragment {

    private TextView convert;
    private View unitConversionView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate the layout for the fragment
        unitConversionView = inflater.inflate(R.layout.unit_conversion_fragment, container, false);

;

        return unitConversionView;
    }

    @Override
    public void onResume(){

        convert = unitConversionView.findViewById(R.id.textView2);
        MainActivity mainActivity = new MainActivity();
        String conversion = mainActivity.receive();

        convert.setText(conversion);

        super.onResume();
    }
}

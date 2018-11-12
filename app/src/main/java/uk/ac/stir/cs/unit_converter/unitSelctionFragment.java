package uk.ac.stir.cs.unit_converter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class unitSelctionFragment extends Fragment implements View.OnClickListener{

    private Spinner spinner;
    private Button selectionButton;

    private String selected;

    /**
     * Creates the view when the tab is selected
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.unit_selection_fragment, container, false);
        selectionButton = view.findViewById(R.id.button);
        selectionButton.setOnClickListener(this);

        //Find the spinner element by its id
        spinner = view.findViewById(R.id.spinner);
        // Create and ArrayAdapter using the string array and the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.units_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply this adapter to the spinner
        spinner.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v){
        //Getting the string of the selected conversion
        selected = spinner.getSelectedItem().toString();

        //Beginning a fragment transaction to send the data to the next fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        UnitConverterFragment unitConverterFragment = new UnitConverterFragment();

        //Using Bundle to send the data
        Bundle bundle = new Bundle();
        bundle.putString("Selection", selected);
        unitConverterFragment.setArguments(bundle);
        transaction.replace(android.R.id.content, unitConverterFragment);
        transaction.commit();

    }
}

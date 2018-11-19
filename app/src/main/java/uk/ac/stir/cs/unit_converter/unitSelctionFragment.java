package uk.ac.stir.cs.unit_converter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class unitSelctionFragment extends Fragment implements View.OnClickListener{

    private View unitSelectionView;

    private Spinner spinner;
    private Button selectionButton;

    // Toast confirmations
    private Context context;
    private Toast toastConfirmation;

    private String selected;

    /**
     * Called to have the fragment instantiate its User view
     *
     * @param inflater              Object used to inflate any any views in the fragment
     * @param container             This is the parent view that the fragments UI is attached to too
     * @param savedInstanceState    The fragment is being reconstructed from a previous saved state
     *
     * @return      Returns the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        unitSelectionView  = inflater.inflate(R.layout.unit_selection_fragment, container, false);
        selectionButton = unitSelectionView.findViewById(R.id.select);
        selectionButton.setOnClickListener(this);

        context = unitSelectionView.getContext();
        toastConfirmation = Toast.makeText(context, getString(R.string.confirm_selection), Toast.LENGTH_LONG);

        spinner(unitSelectionView);

        return unitSelectionView;
    }


    /**
     * This method is used to fill the spinner object with the desired strings.
     *
     * @param view  This is the view object of the fragment
     */
    public void spinner(View view){

        //Find the spinner element by its id
        spinner = view.findViewById(R.id.spinner);
        // Create and ArrayAdapter using the string array and the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.units_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply this adapter to the spinner
        spinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View v){
        //Getting the string of the selected conversion
        String selection = spinner.getSelectedItem().toString();

        setSelected(selection);
    }

    /**
     * Get the conversion selected by the user
     *
     * @return      Return this selection
     */
    public String getSelected(){
        return selected;
    }

    /**
     * Set the conversion selected by the user
     *
     * @param selection     The selection from the spinner
     */
    public void setSelected(String selection){
        selected = selection;
        toastConfirmation.show();
    }

}

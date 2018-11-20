package uk.ac.stir.cs.unit_converter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class UnitSelectionFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    // User interface elements
    private Spinner spinnerCatergories;
    private Spinner spinnerUnits;
    private Button selectionButton;

    // Position of selection in categories
    private int catPos;

    // Conversion selected by user
    private String selectedUnits;
    private String selectedCategory;

    /**
     * Called to have the fragment instantiate its User view
     *
     * @param inflater           Object used to inflate any any views in the fragment
     * @param container          This is the parent view that the fragments UI is attached to too
     * @param savedInstanceState The fragment is being reconstructed from a previous saved state
     * @return Returns the inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View unitSelectionView;

        unitSelectionView = inflater.inflate(R.layout.unit_selection_fragment, container, false);
        selectionButton = unitSelectionView.findViewById(R.id.select);
        selectionButton.setOnClickListener(this);
        spinnerUnits = unitSelectionView.findViewById(R.id.unit_spinner);
        spinnerCatergories = unitSelectionView.findViewById(R.id.category_spinner);
        spinnerCatergories.setOnItemSelectedListener(this);

        spinner(unitSelectionView);

        return unitSelectionView;
    }


    /**
     * This method is used to fill the spinner object with the desired strings.
     *
     * @param view This is the view object of the fragment
     */
    public void spinner(View view) {

        // Find the categories spinner element by its id
        spinnerCatergories = view.findViewById(R.id.category_spinner);
        // Create an ArrayAdapter using the string array and the spinner
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.unit_categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply this adapter to the spinner
        spinnerCatergories.setAdapter(adapterCategory);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {

        // Find the Units spinner element by its id
        spinnerCatergories = v.findViewById(R.id.category_spinner);
        String cat;
        cat = (String) parent.getItemAtPosition(position);
        if(cat.equals("Select")){
            catPos = 0;
            populateUnits();
        }else if (cat.equals("Weight")) {
            catPos = 1;
            populateUnits();
        } else if (cat.equals("Length")) {

            catPos = 2;
            populateUnits();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast toast = Toast.makeText(getContext(), "Category not selected Yet", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        //Getting the string of the selected conversion details
        try{
            String selectedUnits = spinnerUnits.getSelectedItem().toString();

            if(selectedUnits.equals("Select")){

                Toast noUnitSelection = Toast.makeText(getContext(), "You have not made a Unit selection", Toast.LENGTH_SHORT);
                noUnitSelection.show();

            }else{

                Toast confirmSelection = Toast.makeText(getContext(), "Selection has been confirmed, moved to unit converter to convert", Toast.LENGTH_SHORT);
                confirmSelection.show();

                setSelected(selectedUnits);

            }
        }catch (NullPointerException e){

            Toast noCategorySelection = Toast.makeText(getContext(), "You have not made a Category selection", Toast.LENGTH_SHORT);
            noCategorySelection.show();

        }

    }

    /**
     * Get the conversion units selected by the user
     *
     * @return Return this selection
     */
    public String getSelectedUnits() {

        return selectedUnits;
    }

    /**
     * Set the conversion selected by the user
     *
     * @param selectedUnits    The Units selected by user
     */
    public void setSelected(String selectedUnits) {

        this.selectedUnits = selectedUnits;
    }


    public void populateUnits() {

        if(catPos == 0){
            spinnerUnits.setAdapter(null);

        }else if (catPos == 1) {
            // Create an ArrayAdapter using the string array and the spinner
            ArrayAdapter<CharSequence> adapterUnit = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                    R.array.weight_units_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Apply this adapter to the spinner
            spinnerUnits.setAdapter(adapterUnit);

        } else if (catPos == 2) {
            // Create an ArrayAdapter using the string array and the spinner
            ArrayAdapter<CharSequence> adapterUnit = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
            R.array.length_units_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Apply this adapter to the spinner
            spinnerUnits.setAdapter(adapterUnit);
        }

    }
}

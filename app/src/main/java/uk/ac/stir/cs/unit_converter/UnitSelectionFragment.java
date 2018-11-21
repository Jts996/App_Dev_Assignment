package uk.ac.stir.cs.unit_converter;

import android.content.res.Configuration;
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
    private Spinner spinnerCategories;
    private Spinner spinnerFirstUnits;
    private Spinner spinnerSecondUnits;
    private Button selectionButton;

    // Position of selection in categories
    private int catPos;

    // Layout inflaters and container
    private LayoutInflater inflater;
    private ViewGroup container;


    // Conversion selected by user
    private String category;
    private String firstUnits;
    private String secondUnits;


    /**
     * Method to initialise the user view
     *
     * @return      The view
     */
    public View initialiseUserInterface(){

        View unitSelectionView;

        int orientation = getActivity().getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            //inflate the layout for the fragment
            unitSelectionView = inflater.inflate(R.layout.unit_selection_fragment, container, false);

        }else{
            unitSelectionView = inflater.inflate(R.layout.unit_selection_fragment_horizontal, container, false);
        }


        selectionButton = unitSelectionView.findViewById(R.id.select);
        selectionButton.setOnClickListener(this);
        spinnerFirstUnits = unitSelectionView.findViewById(R.id.first_unit_spinner);
        spinnerSecondUnits = unitSelectionView.findViewById(R.id.second_unit_spinner);
        spinnerCategories = unitSelectionView.findViewById(R.id.category_spinner);
        spinnerCategories.setOnItemSelectedListener(this);

        spinnerCategory();

        return unitSelectionView;

    }


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

        this.inflater = inflater;
        this.container = container;

        return initialiseUserInterface();
    }

    /**
     * Overridden to handle the switching between orientations
     *
     * @param newConfig     This is the new configuration for the layout
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(null);



        // Make the new view
        View view = initialiseUserInterface();


        // Remove the old view from the container
        container.postInvalidate();

        // Add the new view to the container
        container.addView(view);


    }

    /**
     * Method which saves the variables when the orientation of the device is changed
     *
     * @param outState  This is the bundle to add the data too
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        System.out.println("Storing data");

        // Storing the contents before the new view is made
        outState.putInt("category", spinnerCategories.getSelectedItemPosition());
        outState.putInt("units", spinnerFirstUnits.getSelectedItemPosition());

    }


    /**
     * Method to retrieve the saved data from a change in state
     * This data is then displayed back to the View in the required fields
     *
     * @param savedInstanceState    This is the bundle where the data was saved
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){
            System.out.println("Filling text");

            spinnerCategories.setSelection(savedInstanceState.getInt("category"));
            spinnerFirstUnits.setSelection(savedInstanceState.getInt("units"));
        }

    }

    /**
     * This method is used to fill the category spinner object with the desired strings.
     *
     */
    public void spinnerCategory() {

        // Create an ArrayAdapter using the string array and the spinner
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.unit_categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply this adapter to the spinner
        spinnerCategories.setAdapter(adapterCategory);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {

        String cat;
        cat = (String) parent.getItemAtPosition(position);
        if(cat.equals("Select")){
            catPos = 0;
            populateUnits();
        }else if (cat.equals("Weight")) {
            catPos = 1;
            populateUnits();
        } else if (cat.equals("Distance")) {

            catPos = 2;
            populateUnits();
        }else if(cat.equals("Speed")){
            catPos = 3;
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
        try {
            String selectedCategory = spinnerCategories.getSelectedItem().toString();
            String selectedFirstUnits = spinnerFirstUnits.getSelectedItem().toString();
            String selectedSecondUnits = spinnerSecondUnits.getSelectedItem().toString();

            if (selectedFirstUnits.equals("Select") || selectedSecondUnits.equals("Select")) {

                Toast noUnitSelection = Toast.makeText(getContext(), "You have not made a Unit selection", Toast.LENGTH_SHORT);
                noUnitSelection.show();

            }else if(selectedFirstUnits.equals(selectedSecondUnits)){
                Toast sameUnits = Toast.makeText(getContext(), "Cannot convert between the same units", Toast.LENGTH_SHORT);
                sameUnits.show();
            }else{

                Toast confirmSelection = Toast.makeText(getContext(), "Selection has been confirmed, moved to unit converter to convert", Toast.LENGTH_SHORT);
                confirmSelection.show();

                setSelected(selectedCategory, selectedFirstUnits, selectedSecondUnits);

            }
        }catch (NullPointerException e){

            Toast noCategorySelection = Toast.makeText(getContext(), "You have not made a Category selection", Toast.LENGTH_SHORT);
            noCategorySelection.show();

        }

    }

    /**
     * Get the unit selected by the user to convert from
     *
     * @return Return this selection
     */
    public String getCategory() {

        return category;
    }


    /**
     * Get the unit selected by the user to convert from
     *
     * @return Return this selection
     */
    public String getFirstUnits() {

        return firstUnits;
    }

    /**
     * Get the unit selected by the user to convert too
     *
     * @return Return this selection
     */
    public String getSecondUnit(){

        return secondUnits;
    }


    /**
     * Set the conversion selected by the user
     *
     * @param category      The category of the units
     * @param firstUnits    The converting from Units selected by user
     * @param secondUnits   The converting too Units selected by the user
     */
    public void setSelected(String category, String firstUnits, String secondUnits) {

        this.category = category;
        this.firstUnits = firstUnits;
        this.secondUnits = secondUnits;
    }


    /**
     * method to populate the units spinner based on the selection in the categories spinner
     *
     */
    public void populateUnits() {

        if(catPos == 0){
            spinnerFirstUnits.setEnabled(false);
            spinnerFirstUnits.setAdapter(null);
            spinnerSecondUnits.setEnabled(false);
            spinnerSecondUnits.setAdapter(null);

        }else if (catPos == 1) {
            // Create an ArrayAdapter using the string array and the spinner
            ArrayAdapter<CharSequence> adapterUnit = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                    R.array.weight_units_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Apply this adapter to the spinner
            spinnerFirstUnits.setAdapter(adapterUnit);
            spinnerFirstUnits.setEnabled(true);
            spinnerSecondUnits.setAdapter(adapterUnit);
            spinnerSecondUnits.setEnabled(true);

        } else if (catPos == 2) {
            // Create an ArrayAdapter using the string array and the spinner
            ArrayAdapter<CharSequence> adapterUnit = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
            R.array.distance_units_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Apply this adapter to the spinner
            spinnerFirstUnits.setAdapter(adapterUnit);
            spinnerFirstUnits.setEnabled(true);
            spinnerSecondUnits.setAdapter(adapterUnit);
            spinnerSecondUnits.setEnabled(true);

        }else if (catPos == 3){
            // Create an ArrayAdapter using the string array and the spinner
            ArrayAdapter<CharSequence> adapterUnit = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                    R.array.speed_units_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Apply this adapter to the spinner
            spinnerFirstUnits.setAdapter(adapterUnit);
            spinnerFirstUnits.setEnabled(true);
            spinnerSecondUnits.setAdapter(adapterUnit);
            spinnerSecondUnits.setEnabled(true);

        }

    }
}

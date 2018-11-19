package uk.ac.stir.cs.unit_converter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UnitConverterFragment extends Fragment implements View.OnClickListener {


    // Conversion values
    private final double METERS_TO_YARDS = 1.09361;
    private final double MILES_TO_YARDS = 1760;
    private final double GRAMS_TO_OUNCES = 28.35;
    private final double KILOGRAMS_TO_POUNDS = 2.205;

    // User interface elements
    private TextView startUnits, endUnits, converted_value;
    private EditText userInput;


    // Variable to keep track of what conversion was selected
    private int conversion_selected = 0;

    // flag to detect whether the default text needs to be removed before appending numbers
    private boolean notClicked = true;

    // Conversions list
    private String[] unit_conversions = {"Select", "Meters to Yards", "Miles to Yards", "Grams to Ounces", "Kilograms to Pounds"};


    // Toast warnings
    private Context context;
    private Toast toastZero;
    private Toast toastNoConversionSelected;
    private Toast toastNoValueEntered;


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

        View unitConversionView;
        //inflate the layout for the fragment
        unitConversionView = inflater.inflate(R.layout.unit_conversion_fragment, container, false);

        // Toast warnings
        context = unitConversionView.getContext();
        toastZero = Toast.makeText(context, getString(R.string.zero_warning), Toast.LENGTH_SHORT);
        toastNoConversionSelected = Toast.makeText(context, getString(R.string.converion_not_selected_warning), Toast.LENGTH_SHORT);
        toastNoValueEntered = Toast.makeText(context, getString(R.string.no_value_entered_warning), Toast.LENGTH_SHORT);


        // TextViews fields
        startUnits = unitConversionView.findViewById(R.id.tv_startUnits);
        endUnits = unitConversionView.findViewById(R.id.tv_endUnits);
        converted_value = unitConversionView.findViewById(R.id.tv_convertedValue);

        // EditText fields
        userInput = unitConversionView.findViewById(R.id.entryValue);

        // Buttons
        Button oneButt;
        Button twobutt;
        Button threeButt;
        Button fourButt;
        Button fiveButt;
        Button sixButt;
        Button sevenButton;
        Button eightButt;
        Button nineButt;
        Button zeroButt;
        Button doublezeroButt;
        Button dotButt;
        Button clearButt;
        Button convertButton;

        // Buttons ID fields
        oneButt = unitConversionView.findViewById(R.id.one);
        twobutt = unitConversionView.findViewById(R.id.two);
        threeButt = unitConversionView.findViewById(R.id.three);
        fourButt = unitConversionView.findViewById(R.id.four);
        fiveButt = unitConversionView.findViewById(R.id.five);
        sixButt = unitConversionView.findViewById(R.id.six);
        sevenButton= unitConversionView.findViewById(R.id.seven);
        eightButt = unitConversionView.findViewById(R.id.eight);
        nineButt = unitConversionView.findViewById(R.id.nine);
        zeroButt = unitConversionView.findViewById(R.id.zero);
        doublezeroButt = unitConversionView.findViewById(R.id.doubleZero);
        dotButt = unitConversionView.findViewById(R.id.dot);
        clearButt = unitConversionView.findViewById(R.id.clear);
        convertButton = unitConversionView.findViewById(R.id.convert);

        // Setting up Button on click listeners
        oneButt.setOnClickListener(this);
        twobutt.setOnClickListener(this);
        threeButt.setOnClickListener(this);
        fourButt.setOnClickListener(this);
        fiveButt.setOnClickListener(this);
        sixButt.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButt.setOnClickListener(this);
        nineButt.setOnClickListener(this);
        zeroButt.setOnClickListener(this);
        doublezeroButt.setOnClickListener(this);
        dotButt.setOnClickListener(this);
        clearButt.setOnClickListener(this);
        convertButton.setOnClickListener(this);


        return unitConversionView;
    }


    /**
     * Method to update the TextViews with the units selection for conversion and to
     * set the "conversion_selected" to the corresponding calculation
     *
     * @param selection     This is the conversion that was selected by the user in the unit Selection fragment
     */
    public void update(String selection) {
        if(selection.equals(unit_conversions[0])){

            startUnits.setText(R.string.default_selection);
            endUnits.setText(R.string.default_selection);

            userInput.setText(getText(R.string.enter_value));
            converted_value.setText(R.string.waiting);

            conversion_selected= 0;

        }else if(selection.equals(unit_conversions[1])){

            startUnits.setText(R.string.meter);
            endUnits.setText(R.string.yard);

            userInput.setText(getText(R.string.enter_value));
            converted_value.setText(R.string.waiting);

            conversion_selected = 1;

        }else if(selection.equals(unit_conversions[2])){

            startUnits.setText(R.string.mile);
            endUnits.setText(R.string.yard);

            userInput.setText(getText(R.string.enter_value));
            converted_value.setText(R.string.waiting);

            conversion_selected = 2;

        }else if(selection.equals(unit_conversions[3])){

            startUnits.setText(R.string.gram);
            endUnits.setText(R.string.ounce);

            userInput.setText(getText(R.string.enter_value));
            converted_value.setText(R.string.waiting);

            conversion_selected = 3;

        }else{

            startUnits.setText(R.string.kilogram);
            endUnits.setText(R.string.pound);
            conversion_selected = 4;

        }
    }


    /**
     * To handle what happens when a button is clicked
     *
     * The if statement checks if this is the first button button click. This is done so that the program can decide
     * whether or not the default text value needs to be set to the number or just appended onto the end.
     *
     * @param v  This is the view object of the fragment
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.one:
                if(notClicked){
                    userInput.setText("1");
                    notClicked = false;
                }else {
                    userInput.append("1");
                }

                break;

            case R.id.two:
                if(notClicked){
                    userInput.setText("2");
                    notClicked = false;
                }else {
                    userInput.append("2");
                }

                break;

            case R.id.three:
                if(notClicked){
                    userInput.setText("3");
                    notClicked = false;
                }else{
                    userInput.append("3");
                }

                break;

            case R.id.four:
                if(notClicked){
                    userInput.setText("4");
                    notClicked = false;
                }else{
                    userInput.append("4");
                }

                break;

            case R.id.five:
                if(notClicked){
                    userInput.setText("5");
                    notClicked = false;
                }else {
                    userInput.append("5");
                }

                break;

            case R.id.six:
                if(notClicked){
                    userInput.setText("6");
                    notClicked = false;
                }
                else {
                    userInput.append("6");
                }

                break;

            case R.id.seven:
                if(notClicked){
                    userInput.setText("7");
                    notClicked = false;
                }else {
                    userInput.append("7");
                }

                break;

            case R.id.eight:
                if(notClicked){
                    userInput.setText("8");
                    notClicked = false;
                }else {
                    userInput.append("8");
                }

                break;

            case R.id.nine:
                if(notClicked){
                    userInput.setText("9");
                    notClicked = false;
                }else {
                    userInput.append("9");
                }

                break;

            case R.id.zero:
                if(notClicked){
                    toastZero.show(); // Informing user to not start a number with zero
                }else{
                    userInput.append("0");
                }

                break;

            case R.id.dot:
                if(notClicked){
                    userInput.setText(".");
                }else{
                    userInput.append(".");
                }

                break;

            case R.id.doubleZero:
                if(notClicked){
                    toastZero.show(); // Informing user to not start a number with zero
                }else{
                    userInput.append("00");
                }

                break;

            case R.id.clear: // Reset all input area and the conversion area back to default
                userInput.setText(getText(R.string.enter_value));
                converted_value.setText(R.string.waiting);

                notClicked = true;

                break;

            case R.id.convert: // Call the conversion method to carry out the required calculation

                conversion(conversion_selected);

                notClicked = true;

                break;

            default:
                break;
        }
    }


    /**
     * Method to handle the conversion calculations.
     *
     * @param conversion_num    This number relates to the set conversion type the user wants to conduct
     */
    public void conversion(int conversion_num){
        String input = userInput.getText().toString();

        if(conversion_num == 0) {
            toastNoConversionSelected.show(); // If 0 user has not selected a conversion type, output a toast warning
        }
        else if (input.equals(context.getString(R.string.enter_value)) || input.equals(context.getString(R.string.empty_string))) {

            toastNoValueEntered.show(); // If entry field empty or default text, user has not input any value, output toast warning
        }
        else{
            int enteredValue = Integer.parseInt(input);
            double converted;

            if(conversion_num == 1){
                converted = (enteredValue * METERS_TO_YARDS); // Converting from Meters to Yards

                converted_value.setText(String.valueOf(converted));

            }else if(conversion_num == 2){
                converted = (enteredValue * MILES_TO_YARDS); // Converting from Miles to Yards

                converted_value.setText(String.valueOf(converted));

            }else if(conversion_num == 3){
                converted = (enteredValue / GRAMS_TO_OUNCES); // Converting from Grams to Ounces

                converted_value.setText(String.valueOf(converted));

            }else if(conversion_num == 4){
                converted = (enteredValue * KILOGRAMS_TO_POUNDS); // Converting from Kilograms to Pounds

                converted_value.setText(String.valueOf(converted));

            }
        }

    }
}

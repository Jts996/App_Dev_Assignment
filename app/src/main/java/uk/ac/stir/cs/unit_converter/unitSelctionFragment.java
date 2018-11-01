package uk.ac.stir.cs.unit_converter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import uk.ac.stir.cs.unit_converter.R;

public class unitSelctionFragment extends Fragment {

    /**
     * Creates teh view when the tab is selected
     *
     * @param inflater
     * @param container
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.unit_selection_fragment, container, false);
    }
}

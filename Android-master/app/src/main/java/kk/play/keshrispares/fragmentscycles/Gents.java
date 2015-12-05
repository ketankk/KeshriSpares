package kk.play.keshrispares.fragmentscycles;

import java.util.List;

import kk.play.keshrispares.R;
import kk.play.keshrispares.controller.CompanyListController;
import kk.play.keshrispares.database.CyclesItemDBHandler;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Gents extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	private static final String fragment_tag = "gents";
	private static final String type = "Gents";

	public static Gents newInstance(int sectionNumber) {
		Gents fragment = new Gents();

		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Gents() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cycles, container, false);


		// Populate View

		// Get list of all cycles of type=Gents;
		CyclesItemDBHandler gentsHandler = new CyclesItemDBHandler(getActivity());
List<String> companies =gentsHandler.getListOfCompaniesBytype(type);
		
		CompanyListController controller=new CompanyListController();
		controller.createView(companies, rootView,getActivity(),type);

		//controller.createView(cycleList, rootView,getActivity());
		
		
		return rootView;
	}

}

package kk.play.keshrispares.controller;

import kk.play.keshrispares.fragmentscycles.Gents;
import kk.play.keshrispares.fragmentscycles.Kids;
import kk.play.keshrispares.fragmentscycles.Ladies;
import kk.play.keshrispares.fragmentscycles.Others;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentsController extends FragmentPagerAdapter {
	private final static int noTabs = 4;

	public 	FragmentsController(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
switch (position) {
		case 0:
			return Gents.newInstance(position);
			
		case 1:
			return Ladies.newInstance(position);
		case 2:
			return Kids.newInstance(position );
		case 3:
				return Others.newInstance(position );
				default :return null;

		}
	}

	@Override
	public int getCount() {
		return noTabs;
	}

	@Override
	public CharSequence getPageTitle(int position) {

		switch (position) {
		case 0:
			return "GENTS";
		case 1:
			return "LADIES";
		case 2:
			return "KIDS";
		case 3:			return "OTHERS";
		default:return null;

		}

	}
}

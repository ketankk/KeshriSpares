package kk.play.stockmanagement.controller;

import kk.play.stockmanagement.fragmentscycles.Gents;
import kk.play.stockmanagement.fragmentscycles.Kids;
import kk.play.stockmanagement.fragmentscycles.Ladies;
import kk.play.stockmanagement.fragmentscycles.Others;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class FragmentsController extends FragmentPagerAdapter {
	private final static int noTabs = 4;

	public FragmentsController(FragmentManager fm) {
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

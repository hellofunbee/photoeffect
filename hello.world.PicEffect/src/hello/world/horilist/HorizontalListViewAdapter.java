package hello.world.horilist;

import hello.world.builder.piceffect.R;
import hello.world.domain.Mapp;
import hello.world.paints.Pens;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class HorizontalListViewAdapter extends BaseAdapter {

	public int [] filter= Pens.getPen_filter();
	private Mapp app;

	public HorizontalListViewAdapter(Mapp app) {
		this.app = app;
	}

	public int getCount() {
		return filter.length;
	}

	public Object getItem(int position) {
		return null;
		
	}

	public long getItemId(int position) {
		return filter[position];
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			view = View.inflate(app, R.layout.fiter_demo_item, null);
			viewHolder.mImg = (ImageView) view
					.findViewById(R.id.item_demopic);

			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.mImg.setImageResource(filter[position]);
		// viewHolder.mText.setText("some info ");

		return view;
	}

	private class ViewHolder {
		ImageView mImg;
		// TextView mText;
	}
}
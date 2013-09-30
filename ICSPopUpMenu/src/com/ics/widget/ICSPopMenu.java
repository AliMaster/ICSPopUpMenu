package com.ics.widget;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ics.R;

public class ICSPopMenu implements OnItemClickListener{
	private OnItemClickListener onItemClickListener;
	private ArrayList<String> itemList = new ArrayList<String>();
	private Context mContext;
	private PopupWindow popupWindow ;
	private ListView listView;
	
	public ICSPopMenu(Context context) {
		this.mContext = context;
		View view = LayoutInflater.from(context).inflate(R.layout.icspopmenu, null);
        //设置 listview
        listView = (ListView)view.findViewById(R.id.ics_listView);
        listView.setAdapter(new PopAdapter());
        listView.setFocusable(true);
        listView.setOnItemClickListener(this);
        //宽度可以在添加完listview item后用listview的measure()方法测出宽度，然后设置。这里直接写死，适配屏幕时候修改不同xml变量值就好了
        popupWindow = new PopupWindow(view,context.getResources().getDimensionPixelSize(R.dimen.ics_popwindow_width), LayoutParams.WRAP_CONTENT, true);
		//设置Background后 按系统返回键，popupWindow会自动dismiss
		popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.icspopmenu_bg));
		popupWindow.setFocusable(true);
		popupWindow.setTouchable(true);
		//点击popupwindow 外部 自动dismiss
		popupWindow.setOutsideTouchable(true);
	}

	//设置菜单项点击监听器
	public void setOnItemClickListener(OnItemClickListener listener) {
		onItemClickListener = listener;
	}

	//批量添加菜单项
	public void addItems(String[] items) {
		for (String s : items)
			itemList.add(s);
	}

	//单个添加菜单项
	public void addItem(String item) {
		itemList.add(item);
	}

	//下拉式 弹出 pop菜单 parent 右下角，另外两个参数为偏移量
	public void showAsDropDown(View parent) {
		popupWindow.showAsDropDown(parent,0, -15);
		popupWindow.update();
	}
	//设置点击listview item后直接dismiss
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		this.dismiss();
		if (null!=onItemClickListener) {
			onItemClickListener.onItemClick(arg0, view, position, id);
		}
	}
	
	//隐藏菜单
	public void dismiss() {
		popupWindow.dismiss();
	}
	//自定义 listview adapter，简单数据可以用simpleadapter
	private final class PopAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return itemList.size();
		}

		@Override
		public Object getItem(int position) {
			return itemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.icspopmenu_item, null);
				holder = new ViewHolder();
				convertView.setTag(holder);
				holder.textView = (TextView) convertView.findViewById(R.id.ics_item_textview);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.textView.setText(itemList.get(position));
			return convertView;
		}

		private final class ViewHolder {
			TextView textView;
		}
	}

}

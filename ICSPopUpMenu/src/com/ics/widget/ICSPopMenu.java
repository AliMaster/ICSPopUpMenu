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
        //���� listview
        listView = (ListView)view.findViewById(R.id.ics_listView);
        listView.setAdapter(new PopAdapter());
        listView.setFocusable(true);
        listView.setOnItemClickListener(this);
        //��ȿ����������listview item����listview��measure()���������ȣ�Ȼ�����á�����ֱ��д����������Ļʱ���޸Ĳ�ͬxml����ֵ�ͺ���
        popupWindow = new PopupWindow(view,context.getResources().getDimensionPixelSize(R.dimen.ics_popwindow_width), LayoutParams.WRAP_CONTENT, true);
		//����Background�� ��ϵͳ���ؼ���popupWindow���Զ�dismiss
		popupWindow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.icspopmenu_bg));
		popupWindow.setFocusable(true);
		popupWindow.setTouchable(true);
		//���popupwindow �ⲿ �Զ�dismiss
		popupWindow.setOutsideTouchable(true);
	}

	//���ò˵�����������
	public void setOnItemClickListener(OnItemClickListener listener) {
		onItemClickListener = listener;
	}

	//������Ӳ˵���
	public void addItems(String[] items) {
		for (String s : items)
			itemList.add(s);
	}

	//������Ӳ˵���
	public void addItem(String item) {
		itemList.add(item);
	}

	//����ʽ ���� pop�˵� parent ���½ǣ�������������Ϊƫ����
	public void showAsDropDown(View parent) {
		popupWindow.showAsDropDown(parent,0, -15);
		popupWindow.update();
	}
	//���õ��listview item��ֱ��dismiss
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		this.dismiss();
		if (null!=onItemClickListener) {
			onItemClickListener.onItemClick(arg0, view, position, id);
		}
	}
	
	//���ز˵�
	public void dismiss() {
		popupWindow.dismiss();
	}
	//�Զ��� listview adapter�������ݿ�����simpleadapter
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

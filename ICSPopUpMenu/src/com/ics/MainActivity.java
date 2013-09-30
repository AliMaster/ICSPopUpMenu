package com.ics;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.ics.widget.ICSPopMenu;

public class MainActivity extends Activity implements OnClickListener , OnItemClickListener{

	ICSPopMenu icsPopMenu ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button).setOnClickListener(this);
		icsPopMenu = new ICSPopMenu(this);
		icsPopMenu.setOnItemClickListener(this);
		String [] items = {"�ͷ�Ԥ��","����Ԥ��","����Ԥ��","KTVԤ��"};
		//�������ͼƬʲô�ģ�item ʹ���Զ���model���ɡ�
		icsPopMenu.addItems(items);
	}
	@Override
	public void onClick(View v) {
		icsPopMenu.showAsDropDown(v);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		Toast.makeText(this, "you clicked "+position, Toast.LENGTH_SHORT).show();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(null!=icsPopMenu){
			icsPopMenu.dismiss();
			icsPopMenu = null;
		}
	}
	
}

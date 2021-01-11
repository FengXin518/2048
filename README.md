#2048
<br>笔记：1、使用layout_weight时，layout_weight或layout_height值置位0dp。<br>
      2、每个组件都有自己的LayoutParams,通过它来设置控件的height与width。<br>
      example:<br>
        >>secondaryLayout = findViewById(R.id.secondaryLayout);//通过id获取控件<br>
        >>LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);//声明一个LinearLayout的对象<br>
        >>secondaryLayout.setLayoutParams(params);//设置给控件<br>
       3、获取手机分辨率
       	>>dm = new DisplayMetrics();//获取手机分辨率<br>
        >>dm = new DisplayMetrics();<br>
        >>getWindowManager().getDefaultDisplay().getMetrics(dm);//获得手机的宽度和高度像素单位为px<br>
        >>String strPM = "手机屏幕分辨率为:" + dm.widthPixels+"* "+dm.heightPixels;<br>
	4、Toast.makeText(MyActivity.this, "向下滑", Toast.LENGTH_SHORT).show();<br>
	5、SharePreferences必须在onCreate函数中声明；<br>
	6、一像素=0.04cm<br>
	7、在自己创建的类中使用MainActivity.this<br>
	

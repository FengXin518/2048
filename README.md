#2048
笔记：1、使用layout_weight时，layout_weight或layout_height值置位0dp。
      2、每个组件都有自己的LayoutParams,通过它来设置控件的height与width。
      example:
        secondaryLayout = findViewById(R.id.secondaryLayout);//通过id获取控件
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);//声明一个LinearLayout的对象
        secondaryLayout.setLayoutParams(params);//设置给控件

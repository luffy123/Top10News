# Top10News
项目框架：
1.底部导航栏、各导航item对应页面、首页顶部滑动导航、其他页面待定
具体实现：
1.底部导航栏： tablayout中CommonTabLayout实现，并与各导航item对应页面联动
2.各导航item对应页面：fragment，fragment中首页为顶部滑动导航
3.首页顶部滑动导航：tablayout中SlidingTabLayout实现，并与viewpager各子item对应页面联动,子item对应页面也是fragment
4.viewpager各子item对应页面:GetMoreListViewFragment
其他要点：
1.listview加载用到imageloader
2.tablayout模块封装其他属性，入消息提醒角标

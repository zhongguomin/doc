Android-Ethernet
	Gene At 2014-05-12


##	参考资料网址
	android_system/network

##	涉及文件
	device/softwinner/common/packages/TvdSettings/src
		EthernetDialog.java
		EthernetSettings.java

	frameworks/base
		EthernetService.java
		EthernetDataTracker.java
		EthernetDevInfo.java
		EthernetManager.java
		
		ConnectivityService.java
		NativeDaemonConnector.java 
		NetworkManagementService.java
		android_net_NetUtils.cpp
		NetworkUtils.java

	system/netd
		CommandListener.cpp
		main.cpp
		NetlinkHandler.cpp
		NetlinkManager.cpp
		NetdCommand.cpp


##	基本流程
	EthernetSettings.java
		public void onClick(DialogInterface dialog, int whichbutton) { 
			if (whichbutton == DialogInterface.BUTTON_POSITIVE) {
				mEthManager.updateDevInfo(devIfo); 

	EthernetManager.java
		public void updateDevInfo(EthernetDevInfo info) {  
			mService.updateDevInfo(info);

	EthernetService.java
		public synchronized void updateDevInfo(EthernetDevInfo info) {
			mTracker.teardown(); 
			mTracker.reconnect();

	EthernetDataTracker.java
		public void ConnectNetwork(boolean up){  
			DHCP	runDhcp();
			MANU	mNMService.setInterfaceConfig(mIface, ifcg);

		这里会根据不同的处理情况，发送对应的消息和广播
			msg.sendToTarget();
			sendStateBroadcast(

		消息接收处理
			谁发的消息 EthernetDataTracker.java
				public void startMonitoring(Context context, Handler target) {
					mCsHandler = target;
			谁开启监听的 ConnectivityService.java
				public ConnectivityService(Context context, I
					case ConnectivityManager.TYPE_ETHERNET: 
						mNetTrackers[netType] = EthernetDataTracker.getInstance();
						mNetTrackers[netType].startMonitoring(context, mHandler);

					mHandler = new MyHandler(handlerThread.getLooper());
			
				private class MyHandler extends Handler {
					public void handleMessage(Message msg) { 

- 待续


	
		广播接收处理
- 待续


	DHCP 的处理流程
		EthernetDataTracker.java
			private void runDhcp() {
				if (!NetworkUtils.runDhcp("eth_" + mIface, dhcpInfoInternal)) {

		NetworkUtils.java
			public native static boolean runDhcp(String interfaceName, DhcpInfoInternal ipInfo); 

		android_net_NetUtils.cpp
			static jboolean android_net_utils_runDhcp(JNIEnv* env, jobject clazz, jstring ifname, jobject info)  
			static jboolean android_net_utils_runDhcpCommon(JNIEnv* env, jobject clazz, jstring ifname,
				jobject info, bool renew)
- 待续


	MANU 的处理流程
		EthernetDataTracker.java
			mNMService.setInterfaceConfig(mIface, ifcg);
		
		NetworkManagementService.java
			public void setInterfaceConfig( 
				String iface, InterfaceConfiguration cfg) throws IllegalStateException {
				mConnector.doCommand(cmd); 

		NativeDaemonConnector.java
			public ArrayList<String> doCommand(String cmd) 
			private ArrayList<String> doCommandLocked(String cmd) 
				sendCommandLocked(cmd); 
				String line = mResponseQueue.take(); 

			private void sendCommandLocked(String command, String argument)  
				mOutputStream.write(builder.toString().getBytes());
			

		mOutputStream 从哪里来的
			NetworkManagementService.java
				mConnector = new NativeDaemonConnector( 
					new NetdCallbackReceiver(), "netd", 10, NETD_TAG); 
				mThread = new Thread(mConnector, NETD_TAG); 

				service.mThread.start(); 

			NativeDaemonConnector.java
				public void run() { 
					listenToSocket()
				private void listenToSocket() throws IOException {
					socket = new LocalSocket(); 
					socket.connect(address);
					mOutputStream = socket.getOutputStream(); 


		谁接受，处理 cmd （netd）
			main.cpp
				if (!(nm = NetlinkManager::Instance())) { 
				cl = new CommandListener(); 
				nm->start()
				cl->startListener()

			NetlinkManager.cpp
				NetlinkHandler *handler = new NetlinkHandler(this, *sock, format); 
				if (handler->start()) {

			CommandListener.cpp
- 待续

			NetlinkHandler.cpp
			NetlinkListener.cpp
- 待续
			
				



















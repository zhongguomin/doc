/**
 *
 *
 */

		WindwoManagerService

InputManager				Activity

=====================================================

WindwoManagerService
	InputManager  mInputManager = new InputManager(context, this);
	mInputManager.start();

InputManager
	mWindowManagerService
	mCallback
	
	init();
		nativeInit(mCallback);

com_android_server_InputManager
	gNativeInputManager = new NativeInputManager(callback);

	eventHup = new EvetnHub();
	mInputManager = new InputManager(evetnHub, this, this);


InputManager (cpp)
	mDispather = new InputDisppatcher(...);
	mReader = new InputReader(eventHub, ..., mDispather);

	initialize()
		new InputReaderThread();
		new InputDispaterAThread()

-----------------------------------------------------------
WindowManagerService
	mInputManager.start()


InputManager
	nativeStart()


com_android_serv er_InputManager
	inputManager.start()


InputManager (cpp)
	mDispatcherThread->run....
	mReaderTAhread->run ...


InputDispatcher
	pollOnce


InputReader
	loopOnce
		mEvetnHub->getEvetn
		process


EventHub

. .  .

-----------------------------------------------------------

VIewRoot
	setView
		requestLayout()
			scheduleTraversals
			...
			WindowManagerSerivce.Session.relayout
			WindowManagerService.relayoutWindow
			InputMonitor.updateInputWindowLw
				InputManager.setInputWindows
				nativeSetInputWindows
				nativeInputManager.setInputWindow


		mWindwoSession.add ...
			mInputChannel = new InputChannel();
			sWindowSession.add(s...., mInputChannel)
			WindowManagerService add(..., InputChannel outInputChannel)
			WindowManagerService.addWindow(..., outInputChannel)
				WindowState win = new WindwoState(...)
				InputChannel[] inputChannels = InputChannel.openInputChannelPair
					nativeOpenInputChannelPair
						new ServerChannelObj
						new ClientChannelObj
				win.mInputChannel = inputChannels[0]
				inputChannels[1].transferToBinderOutParameter(outInputChannel);
					--> Client set to Application
				mInputManager.registerInputChannel(win.minputChannel)
					--> Server set to InputManager

					nativeRegisterInputChannel(inputChannel ,this)
					gNativeInputManager->registerInputChannel
					mInputManager->getDispapatcher()->registerInputChannel()
						Dispatcher.regsiterInputCHannel(inputChannel, monitor)
							sp<Conection> connection = new Connection(inputChannel)
							connection->initialize()
							inputChannel=>getReceivePipeFd()
							

		InputQueue.registerInputChannel()
			nativeRegisterInputChannel(inputChannel, inputHandler, messageQueue)
			gNativeInputManager.registerInputChannel(..., 
					inputChannelObj, inputHandlerObj, messageQueueObj)
				inputChannel = android_view_InputChannel.getInputChannel(..., 
						inputChannelObj)
				looper = android_os_MessageQueue_getLooper(..., 
						messageQueueObj)
				connection = new Connection(connectionId, inputChannel, looper)
				
			
-----------------------------------------------------------
inputReader.pollOnce
	EventHub.getEvent(&rawEvent)
	process(&rawEvent)
		InputReader.consumeEvent
		InputDevice.process
		InputWapper->process
		KeyboardInputWapper:::process
		KeyboardInputWapper::processKey
		getDispatcher()->notifyKey()
		InputDispatcher.notifyKey
		InputDispatcher::enqueueInboundEventLocked

		InputDispatcherThread
			if needWake		mLooper->wake()

		InputDispatcher.dispatcherOnce
		dispatchOnceInnertLocked
		mInboundQueue.dequeue()
		InputDispatcher.dispatchKeyLocked
		findFocusedWindowTargetsLocked

		InputDispatcher.addWindowTargetLocked
		InputDispatcher.dispatchEventToCurrentInputTargetLocked

-----------------------------------------------------------
	
InputReaderThread read at /dev/input/event0
	keyboard event
InputReader	 --> InputDispater
	
InputDispatcher  --> Applaction Main Thread

Applacton Main Thread  --> InputDispatcher



-----------------------------------------------------------

iActivityRecord WindowVisible()
	


































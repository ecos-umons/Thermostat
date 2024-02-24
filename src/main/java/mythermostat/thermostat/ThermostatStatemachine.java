/** Generated by YAKINDU Statechart Tools code generator. */
package mythermostat.thermostat;

import mythermostat.ITimer;

public class ThermostatStatemachine implements IThermostatStatemachine {
	protected class SCInterfaceImpl implements SCInterface {
	
		private SCInterfaceOperationCallback operationCallback;
		
		public void setSCInterfaceOperationCallback(
				SCInterfaceOperationCallback operationCallback) {
			this.operationCallback = operationCallback;
		}
		private boolean mode;
		
		
		public void raiseMode() {
			mode = true;
		}
		
		private boolean set;
		
		
		public void raiseSet() {
			set = true;
		}
		
		private boolean openWindow;
		
		
		public void raiseOpenWindow() {
			openWindow = true;
		}
		
		private boolean closeWindow;
		
		
		public void raiseCloseWindow() {
			closeWindow = true;
		}
		
		private long time;
		
		public long getTime() {
			return time;
		}
		
		public void setTime(long value) {
			this.time = value;
		}
		
		private long hour;
		
		public long getHour() {
			return hour;
		}
		
		public void setHour(long value) {
			this.hour = value;
		}
		
		private long day;
		
		public long getDay() {
			return day;
		}
		
		public void setDay(long value) {
			this.day = value;
		}
		
		private long night;
		
		public long getNight() {
			return night;
		}
		
		public void setNight(long value) {
			this.night = value;
		}
		
		private long minT;
		
		public long getMinT() {
			return minT;
		}
		
		public void setMinT(long value) {
			this.minT = value;
		}
		
		private long maxT;
		
		public long getMaxT() {
			return maxT;
		}
		
		public void setMaxT(long value) {
			this.maxT = value;
		}
		
		private long dayT;
		
		public long getDayT() {
			return dayT;
		}
		
		public void setDayT(long value) {
			this.dayT = value;
		}
		
		private long nightT;
		
		public long getNightT() {
			return nightT;
		}
		
		public void setNightT(long value) {
			this.nightT = value;
		}
		
		private long inTVariable;
		
		public long getInT() {
			return inTVariable;
		}
		
		public void setInT(long value) {
			this.inTVariable = value;
		}
		
		private boolean isOpen;
		
		public boolean getIsOpen() {
			return isOpen;
		}
		
		public void setIsOpen(boolean value) {
			this.isOpen = value;
		}
		
		protected void clearEvents() {
			mode = false;
			set = false;
			openWindow = false;
			closeWindow = false;
		}
	}
	
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		controller_Settings,
		controller_Settings_r1_SetDayTemperature,
		controller_Settings_r1_SetNightTemperature,
		controller_Status,
		controller_Status_r1_Off,
		controller_Status_r1_On,
		controller_Status_r1_On_r1_Day,
		controller_Status_r1_On_r1_Night,
		controller_Status_r1_Pause,
		window_Closed,
		window_Open,
		timer_IncrementSeconds,
		$NullState$
	}
	
	private State[] historyVector = new State[1];
	private final State[] stateVector = new State[3];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[9];
	
	public ThermostatStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public void init() {
		this.initialized = true;
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		if (this.sCInterface.operationCallback == null) {
			throw new IllegalStateException("Operation callback for interface sCInterface must be set.");
		}
		
		for (int i = 0; i < 3; i++) {
			stateVector[i] = State.$NullState$;
		}
		for (int i = 0; i < 1; i++) {
			historyVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setTime(0);
		
		sCInterface.setHour(0);
		
		sCInterface.setDay(8);
		
		sCInterface.setNight(22);
		
		sCInterface.setMinT(10);
		
		sCInterface.setMaxT(27);
		
		sCInterface.setDayT(21);
		
		sCInterface.setNightT(16);
		
		sCInterface.setInT(15);
		
		sCInterface.setIsOpen(false);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
				"The state machine needs to be initialized first by calling the init() function."
			);
		}
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		enterSequence_controller_default();
		enterSequence_window_default();
		enterSequence_timer_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case controller_Settings_r1_SetDayTemperature:
				controller_Settings_r1_SetDayTemperature_react(true);
				break;
			case controller_Settings_r1_SetNightTemperature:
				controller_Settings_r1_SetNightTemperature_react(true);
				break;
			case controller_Status_r1_Off:
				controller_Status_r1_Off_react(true);
				break;
			case controller_Status_r1_On_r1_Day:
				controller_Status_r1_On_r1_Day_react(true);
				break;
			case controller_Status_r1_On_r1_Night:
				controller_Status_r1_On_r1_Night_react(true);
				break;
			case controller_Status_r1_Pause:
				controller_Status_r1_Pause_react(true);
				break;
			case window_Closed:
				window_Closed_react(true);
				break;
			case window_Open:
				window_Open_react(true);
				break;
			case timer_IncrementSeconds:
				timer_IncrementSeconds_react(true);
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
	public void exit() {
		exitSequence_controller();
		exitSequence_window();
		exitSequence_timer();
	}
	
	/**
	 * @see mythermostat.IStatemachine#isActive()
	 */
	public boolean isActive() {
		return stateVector[0] != State.$NullState$||stateVector[1] != State.$NullState$||stateVector[2] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see mythermostat.IStatemachine#isFinal()
	*/
	public boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCInterface.clearEvents();
		for (int i=0; i<timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case controller_Settings:
			return stateVector[0].ordinal() >= State.
					controller_Settings.ordinal()&& stateVector[0].ordinal() <= State.controller_Settings_r1_SetNightTemperature.ordinal();
		case controller_Settings_r1_SetDayTemperature:
			return stateVector[0] == State.controller_Settings_r1_SetDayTemperature;
		case controller_Settings_r1_SetNightTemperature:
			return stateVector[0] == State.controller_Settings_r1_SetNightTemperature;
		case controller_Status:
			return stateVector[0].ordinal() >= State.
					controller_Status.ordinal()&& stateVector[0].ordinal() <= State.controller_Status_r1_Pause.ordinal();
		case controller_Status_r1_Off:
			return stateVector[0] == State.controller_Status_r1_Off;
		case controller_Status_r1_On:
			return stateVector[0].ordinal() >= State.
					controller_Status_r1_On.ordinal()&& stateVector[0].ordinal() <= State.controller_Status_r1_On_r1_Night.ordinal();
		case controller_Status_r1_On_r1_Day:
			return stateVector[0] == State.controller_Status_r1_On_r1_Day;
		case controller_Status_r1_On_r1_Night:
			return stateVector[0] == State.controller_Status_r1_On_r1_Night;
		case controller_Status_r1_Pause:
			return stateVector[0] == State.controller_Status_r1_Pause;
		case window_Closed:
			return stateVector[1] == State.window_Closed;
		case window_Open:
			return stateVector[1] == State.window_Open;
		case timer_IncrementSeconds:
			return stateVector[2] == State.timer_IncrementSeconds;
		default:
			return false;
		}
	}
	
	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correctly
	* executed.
	* 
	* @param timer
	*/
	public void setTimer(ITimer timer) {
		this.timer = timer;
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}
	
	public void timeElapsed(int eventID) {
		timeEvents[eventID] = true;
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public void raiseMode() {
		sCInterface.raiseMode();
	}
	
	public void raiseSet() {
		sCInterface.raiseSet();
	}
	
	public void raiseOpenWindow() {
		sCInterface.raiseOpenWindow();
	}
	
	public void raiseCloseWindow() {
		sCInterface.raiseCloseWindow();
	}
	
	public long getTime() {
		return sCInterface.getTime();
	}
	
	public void setTime(long value) {
		sCInterface.setTime(value);
	}
	
	public long getHour() {
		return sCInterface.getHour();
	}
	
	public void setHour(long value) {
		sCInterface.setHour(value);
	}
	
	public long getDay() {
		return sCInterface.getDay();
	}
	
	public void setDay(long value) {
		sCInterface.setDay(value);
	}
	
	public long getNight() {
		return sCInterface.getNight();
	}
	
	public void setNight(long value) {
		sCInterface.setNight(value);
	}
	
	public long getMinT() {
		return sCInterface.getMinT();
	}
	
	public void setMinT(long value) {
		sCInterface.setMinT(value);
	}
	
	public long getMaxT() {
		return sCInterface.getMaxT();
	}
	
	public void setMaxT(long value) {
		sCInterface.setMaxT(value);
	}
	
	public long getDayT() {
		return sCInterface.getDayT();
	}
	
	public void setDayT(long value) {
		sCInterface.setDayT(value);
	}
	
	public long getNightT() {
		return sCInterface.getNightT();
	}
	
	public void setNightT(long value) {
		sCInterface.setNightT(value);
	}
	
	public long getInT() {
		return sCInterface.getInT();
	}
	
	public void setInT(long value) {
		sCInterface.setInT(value);
	}
	
	public boolean getIsOpen() {
		return sCInterface.getIsOpen();
	}
	
	public void setIsOpen(boolean value) {
		sCInterface.setIsOpen(value);
	}
	
	/* Entry action for state 'SetDayTemperature'. */
	private void entryAction_controller_Settings_r1_SetDayTemperature() {
		timer.setTimer(this, 0, 500, false);
	}
	
	/* Entry action for state 'SetNightTemperature'. */
	private void entryAction_controller_Settings_r1_SetNightTemperature() {
		timer.setTimer(this, 1, 500, false);
	}
	
	/* Entry action for state 'Day'. */
	private void entryAction_controller_Status_r1_On_r1_Day() {
		timer.setTimer(this, 2, (1 * 1000), false);
		
		timer.setTimer(this, 3, (1 * 1000), false);
	}
	
	/* Entry action for state 'Night'. */
	private void entryAction_controller_Status_r1_On_r1_Night() {
		timer.setTimer(this, 4, (1 * 1000), false);
		
		timer.setTimer(this, 5, (1 * 1000), false);
	}
	
	/* Entry action for state 'Closed'. */
	private void entryAction_window_Closed() {
		sCInterface.setIsOpen(false);
	}
	
	/* Entry action for state 'Open'. */
	private void entryAction_window_Open() {
		timer.setTimer(this, 6, (1 * 1000), false);
		
		timer.setTimer(this, 7, (1 * 1000), false);
		
		sCInterface.setIsOpen(true);
	}
	
	/* Entry action for state 'IncrementSeconds'. */
	private void entryAction_timer_IncrementSeconds() {
		timer.setTimer(this, 8, (1 * 1000), false);
		
		sCInterface.setHour((((sCInterface.time / 3600)) % 24));
	}
	
	/* Exit action for state 'SetDayTemperature'. */
	private void exitAction_controller_Settings_r1_SetDayTemperature() {
		timer.unsetTimer(this, 0);
	}
	
	/* Exit action for state 'SetNightTemperature'. */
	private void exitAction_controller_Settings_r1_SetNightTemperature() {
		timer.unsetTimer(this, 1);
	}
	
	/* Exit action for state 'Day'. */
	private void exitAction_controller_Status_r1_On_r1_Day() {
		timer.unsetTimer(this, 2);
		
		timer.unsetTimer(this, 3);
	}
	
	/* Exit action for state 'Night'. */
	private void exitAction_controller_Status_r1_On_r1_Night() {
		timer.unsetTimer(this, 4);
		
		timer.unsetTimer(this, 5);
	}
	
	/* Exit action for state 'Open'. */
	private void exitAction_window_Open() {
		timer.unsetTimer(this, 6);
		
		timer.unsetTimer(this, 7);
	}
	
	/* Exit action for state 'IncrementSeconds'. */
	private void exitAction_timer_IncrementSeconds() {
		timer.unsetTimer(this, 8);
	}
	
	/* 'default' enter sequence for state Settings */
	private void enterSequence_controller_Settings_default() {
		enterSequence_controller_Settings_r1_default();
	}
	
	/* 'default' enter sequence for state SetDayTemperature */
	private void enterSequence_controller_Settings_r1_SetDayTemperature_default() {
		entryAction_controller_Settings_r1_SetDayTemperature();
		nextStateIndex = 0;
		stateVector[0] = State.controller_Settings_r1_SetDayTemperature;
	}
	
	/* 'default' enter sequence for state SetNightTemperature */
	private void enterSequence_controller_Settings_r1_SetNightTemperature_default() {
		entryAction_controller_Settings_r1_SetNightTemperature();
		nextStateIndex = 0;
		stateVector[0] = State.controller_Settings_r1_SetNightTemperature;
	}
	
	/* 'default' enter sequence for state Status */
	private void enterSequence_controller_Status_default() {
		enterSequence_controller_Status_r1_default();
	}
	
	/* 'default' enter sequence for state Off */
	private void enterSequence_controller_Status_r1_Off_default() {
		nextStateIndex = 0;
		stateVector[0] = State.controller_Status_r1_Off;
		
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state On */
	private void enterSequence_controller_Status_r1_On_default() {
		enterSequence_controller_Status_r1_On_r1_default();
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state Day */
	private void enterSequence_controller_Status_r1_On_r1_Day_default() {
		entryAction_controller_Status_r1_On_r1_Day();
		nextStateIndex = 0;
		stateVector[0] = State.controller_Status_r1_On_r1_Day;
	}
	
	/* 'default' enter sequence for state Night */
	private void enterSequence_controller_Status_r1_On_r1_Night_default() {
		entryAction_controller_Status_r1_On_r1_Night();
		nextStateIndex = 0;
		stateVector[0] = State.controller_Status_r1_On_r1_Night;
	}
	
	/* 'default' enter sequence for state Pause */
	private void enterSequence_controller_Status_r1_Pause_default() {
		nextStateIndex = 0;
		stateVector[0] = State.controller_Status_r1_Pause;
		
		historyVector[0] = stateVector[0];
	}
	
	/* 'default' enter sequence for state Closed */
	private void enterSequence_window_Closed_default() {
		entryAction_window_Closed();
		nextStateIndex = 1;
		stateVector[1] = State.window_Closed;
	}
	
	/* 'default' enter sequence for state Open */
	private void enterSequence_window_Open_default() {
		entryAction_window_Open();
		nextStateIndex = 1;
		stateVector[1] = State.window_Open;
	}
	
	/* 'default' enter sequence for state IncrementSeconds */
	private void enterSequence_timer_IncrementSeconds_default() {
		entryAction_timer_IncrementSeconds();
		nextStateIndex = 2;
		stateVector[2] = State.timer_IncrementSeconds;
	}
	
	/* 'default' enter sequence for region controller */
	private void enterSequence_controller_default() {
		react_controller__entry_Default();
	}
	
	/* 'default' enter sequence for region r1 */
	private void enterSequence_controller_Settings_r1_default() {
		react_controller_Settings_r1__entry_Default();
	}
	
	/* 'default' enter sequence for region r1 */
	private void enterSequence_controller_Status_r1_default() {
		react_controller_Status_r1__entry_Default();
	}
	
	/* shallow enterSequence with history in child r1 */
	private void shallowEnterSequence_controller_Status_r1() {
		switch (historyVector[0]) {
		case controller_Status_r1_Off:
			enterSequence_controller_Status_r1_Off_default();
			break;
		case controller_Status_r1_On_r1_Day:
			enterSequence_controller_Status_r1_On_default();
			break;
		case controller_Status_r1_On_r1_Night:
			enterSequence_controller_Status_r1_On_default();
			break;
		case controller_Status_r1_Pause:
			enterSequence_controller_Status_r1_Pause_default();
			break;
		default:
			break;
		}
	}
	
	/* 'default' enter sequence for region r1 */
	private void enterSequence_controller_Status_r1_On_r1_default() {
		react_controller_Status_r1_On_r1__entry_Default();
	}
	
	/* 'default' enter sequence for region window */
	private void enterSequence_window_default() {
		react_window__entry_Default();
	}
	
	/* 'default' enter sequence for region timer */
	private void enterSequence_timer_default() {
		react_timer__entry_Default();
	}
	
	/* Default exit sequence for state Settings */
	private void exitSequence_controller_Settings() {
		exitSequence_controller_Settings_r1();
	}
	
	/* Default exit sequence for state SetDayTemperature */
	private void exitSequence_controller_Settings_r1_SetDayTemperature() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_controller_Settings_r1_SetDayTemperature();
	}
	
	/* Default exit sequence for state SetNightTemperature */
	private void exitSequence_controller_Settings_r1_SetNightTemperature() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_controller_Settings_r1_SetNightTemperature();
	}
	
	/* Default exit sequence for state Status */
	private void exitSequence_controller_Status() {
		exitSequence_controller_Status_r1();
	}
	
	/* Default exit sequence for state Off */
	private void exitSequence_controller_Status_r1_Off() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state On */
	private void exitSequence_controller_Status_r1_On() {
		exitSequence_controller_Status_r1_On_r1();
	}
	
	/* Default exit sequence for state Day */
	private void exitSequence_controller_Status_r1_On_r1_Day() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_controller_Status_r1_On_r1_Day();
	}
	
	/* Default exit sequence for state Night */
	private void exitSequence_controller_Status_r1_On_r1_Night() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_controller_Status_r1_On_r1_Night();
	}
	
	/* Default exit sequence for state Pause */
	private void exitSequence_controller_Status_r1_Pause() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Closed */
	private void exitSequence_window_Closed() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state Open */
	private void exitSequence_window_Open() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
		
		exitAction_window_Open();
	}
	
	/* Default exit sequence for state IncrementSeconds */
	private void exitSequence_timer_IncrementSeconds() {
		nextStateIndex = 2;
		stateVector[2] = State.$NullState$;
		
		exitAction_timer_IncrementSeconds();
	}
	
	/* Default exit sequence for region controller */
	private void exitSequence_controller() {
		switch (stateVector[0]) {
		case controller_Settings_r1_SetDayTemperature:
			exitSequence_controller_Settings_r1_SetDayTemperature();
			break;
		case controller_Settings_r1_SetNightTemperature:
			exitSequence_controller_Settings_r1_SetNightTemperature();
			break;
		case controller_Status_r1_Off:
			exitSequence_controller_Status_r1_Off();
			break;
		case controller_Status_r1_On_r1_Day:
			exitSequence_controller_Status_r1_On_r1_Day();
			break;
		case controller_Status_r1_On_r1_Night:
			exitSequence_controller_Status_r1_On_r1_Night();
			break;
		case controller_Status_r1_Pause:
			exitSequence_controller_Status_r1_Pause();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r1 */
	private void exitSequence_controller_Settings_r1() {
		switch (stateVector[0]) {
		case controller_Settings_r1_SetDayTemperature:
			exitSequence_controller_Settings_r1_SetDayTemperature();
			break;
		case controller_Settings_r1_SetNightTemperature:
			exitSequence_controller_Settings_r1_SetNightTemperature();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r1 */
	private void exitSequence_controller_Status_r1() {
		switch (stateVector[0]) {
		case controller_Status_r1_Off:
			exitSequence_controller_Status_r1_Off();
			break;
		case controller_Status_r1_On_r1_Day:
			exitSequence_controller_Status_r1_On_r1_Day();
			break;
		case controller_Status_r1_On_r1_Night:
			exitSequence_controller_Status_r1_On_r1_Night();
			break;
		case controller_Status_r1_Pause:
			exitSequence_controller_Status_r1_Pause();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r1 */
	private void exitSequence_controller_Status_r1_On_r1() {
		switch (stateVector[0]) {
		case controller_Status_r1_On_r1_Day:
			exitSequence_controller_Status_r1_On_r1_Day();
			break;
		case controller_Status_r1_On_r1_Night:
			exitSequence_controller_Status_r1_On_r1_Night();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region window */
	private void exitSequence_window() {
		switch (stateVector[1]) {
		case window_Closed:
			exitSequence_window_Closed();
			break;
		case window_Open:
			exitSequence_window_Open();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region timer */
	private void exitSequence_timer() {
		switch (stateVector[2]) {
		case timer_IncrementSeconds:
			exitSequence_timer_IncrementSeconds();
			break;
		default:
			break;
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_controller__entry_Default() {
		enterSequence_controller_Status_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_controller_Settings_r1__entry_Default() {
		enterSequence_controller_Settings_r1_SetDayTemperature_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_controller_Status_r1_On_r1__entry_Default() {
		enterSequence_controller_Status_r1_On_r1_Day_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_controller_Status_r1__entry_Default() {
		enterSequence_controller_Status_r1_Off_default();
	}
	
	/* Default react sequence for shallow history entry history */
	private void react_controller_Status_r1_history() {
		/* Enter the region with shallow history */
		if (historyVector[0] != State.$NullState$) {
			shallowEnterSequence_controller_Status_r1();
		} else {
			enterSequence_controller_Status_r1_Off_default();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_window__entry_Default() {
		enterSequence_window_Closed_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_timer__entry_Default() {
		enterSequence_timer_IncrementSeconds_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean controller_Settings_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			did_transition = false;
		}
		return did_transition;
	}
	
	private boolean controller_Settings_r1_SetDayTemperature_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.set) {
				exitSequence_controller_Settings_r1_SetDayTemperature();
				enterSequence_controller_Settings_r1_SetNightTemperature_default();
				controller_Settings_react(false);
			} else {
				if (sCInterface.mode) {
					exitSequence_controller_Settings_r1_SetDayTemperature();
					sCInterface.setDayT(sCInterface.minT);
					
					enterSequence_controller_Settings_r1_SetDayTemperature_default();
					controller_Settings_react(false);
				} else {
					if (((timeEvents[0]) && (sCInterface.getDayT()<sCInterface.getMaxT()))) {
						exitSequence_controller_Settings_r1_SetDayTemperature();
						sCInterface.setDayT(sCInterface.getDayT() + 1);
						
						enterSequence_controller_Settings_r1_SetDayTemperature_default();
						controller_Settings_react(false);
					} else {
						did_transition = false;
					}
				}
			}
		}
		if (did_transition==false) {
			did_transition = controller_Settings_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean controller_Settings_r1_SetNightTemperature_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.set) {
				exitSequence_controller_Settings();
				react_controller_Status_r1_history();
			} else {
				if (sCInterface.mode) {
					exitSequence_controller_Settings_r1_SetNightTemperature();
					sCInterface.setNightT(sCInterface.minT);
					
					enterSequence_controller_Settings_r1_SetNightTemperature_default();
					controller_Settings_react(false);
				} else {
					if (((timeEvents[1]) && (sCInterface.getNightT()<sCInterface.getDayT()))) {
						exitSequence_controller_Settings_r1_SetNightTemperature();
						sCInterface.setNightT(sCInterface.getNightT() + 1);
						
						enterSequence_controller_Settings_r1_SetNightTemperature_default();
						controller_Settings_react(false);
					} else {
						did_transition = false;
					}
				}
			}
		}
		if (did_transition==false) {
			did_transition = controller_Settings_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean controller_Status_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.set) {
				exitSequence_controller_Status();
				enterSequence_controller_Settings_default();
			} else {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean controller_Status_r1_Off_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (((sCInterface.mode) && (isStateActive(State.window_Closed)))) {
				exitSequence_controller_Status_r1_Off();
				enterSequence_controller_Status_r1_On_default();
				controller_Status_react(false);
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = controller_Status_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean controller_Status_r1_On_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.mode) {
				exitSequence_controller_Status_r1_On();
				enterSequence_controller_Status_r1_Off_default();
				controller_Status_react(false);
			} else {
				if (sCInterface.openWindow) {
					exitSequence_controller_Status_r1_On();
					enterSequence_controller_Status_r1_Pause_default();
					controller_Status_react(false);
				} else {
					did_transition = false;
				}
			}
		}
		if (did_transition==false) {
			did_transition = controller_Status_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean controller_Status_r1_On_r1_Day_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if ((sCInterface.getHour()>=sCInterface.getNight() || sCInterface.getHour()<sCInterface.getDay())) {
				exitSequence_controller_Status_r1_On_r1_Day();
				enterSequence_controller_Status_r1_On_r1_Night_default();
				controller_Status_r1_On_react(false);
			} else {
				if (((timeEvents[2]) && (sCInterface.getInT()<sCInterface.getDayT()))) {
					exitSequence_controller_Status_r1_On_r1_Day();
					sCInterface.setInT(sCInterface.getInT() + 1);
					
					enterSequence_controller_Status_r1_On_r1_Day_default();
					controller_Status_r1_On_react(false);
				} else {
					if (((timeEvents[3]) && (sCInterface.getInT()>sCInterface.getDayT()))) {
						exitSequence_controller_Status_r1_On_r1_Day();
						sCInterface.setInT(sCInterface.getInT() - 1);
						
						enterSequence_controller_Status_r1_On_r1_Day_default();
						controller_Status_r1_On_react(false);
					} else {
						did_transition = false;
					}
				}
			}
		}
		if (did_transition==false) {
			did_transition = controller_Status_r1_On_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean controller_Status_r1_On_r1_Night_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if ((sCInterface.getHour()>=sCInterface.getDay() && sCInterface.getHour()<sCInterface.getNight())) {
				exitSequence_controller_Status_r1_On_r1_Night();
				enterSequence_controller_Status_r1_On_r1_Day_default();
				controller_Status_r1_On_react(false);
			} else {
				if (((timeEvents[4]) && (sCInterface.getInT()<sCInterface.getNightT()))) {
					exitSequence_controller_Status_r1_On_r1_Night();
					sCInterface.setInT(sCInterface.getInT() + 1);
					
					enterSequence_controller_Status_r1_On_r1_Night_default();
					controller_Status_r1_On_react(false);
				} else {
					if (((timeEvents[5]) && (sCInterface.getInT()>sCInterface.getNightT()))) {
						exitSequence_controller_Status_r1_On_r1_Night();
						sCInterface.setInT(sCInterface.getInT() - 1);
						
						enterSequence_controller_Status_r1_On_r1_Night_default();
						controller_Status_r1_On_react(false);
					} else {
						did_transition = false;
					}
				}
			}
		}
		if (did_transition==false) {
			did_transition = controller_Status_r1_On_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean controller_Status_r1_Pause_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.closeWindow) {
				exitSequence_controller_Status_r1_Pause();
				enterSequence_controller_Status_r1_On_default();
				controller_Status_react(false);
			} else {
				if (sCInterface.mode) {
					exitSequence_controller_Status_r1_Pause();
					enterSequence_controller_Status_r1_Off_default();
					controller_Status_react(false);
				} else {
					did_transition = false;
				}
			}
		}
		if (did_transition==false) {
			did_transition = controller_Status_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean window_Closed_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.openWindow) {
				exitSequence_window_Closed();
				enterSequence_window_Open_default();
			} else {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean window_Open_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.closeWindow) {
				exitSequence_window_Open();
				enterSequence_window_Closed_default();
			} else {
				if (((timeEvents[6]) && (sCInterface.getInT()<sCInterface.operationCallback.outTemp()))) {
					exitSequence_window_Open();
					sCInterface.setInT(sCInterface.getInT() + 1);
					
					enterSequence_window_Open_default();
				} else {
					if (((timeEvents[7]) && (sCInterface.getInT()>sCInterface.operationCallback.outTemp()))) {
						exitSequence_window_Open();
						sCInterface.setInT(sCInterface.getInT() - 1);
						
						enterSequence_window_Open_default();
					} else {
						did_transition = false;
					}
				}
			}
		}
		return did_transition;
	}
	
	private boolean timer_IncrementSeconds_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[8]) {
				exitSequence_timer_IncrementSeconds();
				sCInterface.setTime(sCInterface.getTime() + 1);
				
				enterSequence_timer_IncrementSeconds_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
}
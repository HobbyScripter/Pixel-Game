package de.game.state;


public class State {
	
	public static enum StateList {
		INTRO, GAME, MAIN_MENU;
	}
	
	public static void State_init(StateList state){
		switch(state){
			case INTRO:
				break;
			case GAME:
				break;
			case MAIN_MENU:
				break;
			default:
				break;
		}	
	}
}

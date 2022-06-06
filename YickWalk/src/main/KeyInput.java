package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{

	//This is unused
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public boolean pressUp;
	public boolean pressDown;
	public boolean pressLeft;
	public boolean pressRight;
	
	//On key press
	@Override
	public void keyPressed(KeyEvent e) {

		int keyBind = e.getKeyCode();
		if(keyBind == KeyEvent.VK_W) {
			pressUp = true;
		}
		if(keyBind == KeyEvent.VK_S) {
			pressDown = true;
		}
		if(keyBind == KeyEvent.VK_A) {
			pressLeft = true;
		}
		if(keyBind == KeyEvent.VK_D) {
			pressRight = true;
		}
	}
	
	//On key release
	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyBind = e.getKeyCode();
		if(keyBind == KeyEvent.VK_W) {
			pressUp = false;
		}
		if(keyBind == KeyEvent.VK_S) {
			pressDown = false;
		}
		if(keyBind == KeyEvent.VK_A) {
			pressLeft = false;
		}
		if(keyBind == KeyEvent.VK_D) {
			pressRight = false;
		}
	}

}


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class BTLlantas {
	static int xPosition;
	static int yPosition;
	static int gRotacion = 0;
	
	public static void main(String [] args)  throws IOException, InterruptedException
	{
		String connected = "Conexion\n exitosa!!!";
        String waiting = "Esperando\n conexion...";
        String closing = "Cerrando\n conexion...";
        
        Motor.A.resetTachoCount();
        Motor.B.resetTachoCount();
        Motor.C.resetTachoCount();
		while (true)
		{
			LCD.drawString(waiting,1,0);
			LCD.refresh();

	        BTConnection btc = Bluetooth.waitForConnection();
	        
			LCD.clear();
			LCD.drawString(connected,1,0);
			LCD.refresh();	

			DataInputStream dataIn = btc.openDataInputStream();
			//DataOutputStream dataOut = btc.openDataOutputStream();
			int option = -1;
			while(option != 0){
				option = dataIn.readInt();
				if(option == 1){
					trotar();
				}
				else if(option == 2){
					correr();
				}
				else if(option == 3){
					retroceder();
				}
				else if(option == 4){
					girarDerecha();
				}
				else if(option == 5){
					girarIzquierda();
				}
				else if(option == 6){
					chutar();
				}
				else if(option == 7){
					patear();
				}
				else{
					break;
				}
				Motor.A.stop();
				Motor.B.stop();
				Motor.C.stop();
				//dataOut.write(xPosition);
				//dataOut.write(yPosition);
				//dataOut.flush();
				}
			
			LCD.drawString(closing, 1, 0);
			dataIn.close();
			//dataOut.close();
			Thread.sleep(1000);//damos tiempo para que evacue los datos
			}

		   
		}
		
	public static void trotar() throws InterruptedException{
		Motor.A.setSpeed(360);
		Motor.B.setSpeed(360);
		Motor.A.forward();
		Motor.B.forward();
		Thread.sleep(2000);
		if(gRotacion == 0 || gRotacion == 360 || gRotacion == -360) actualizarPosicion(2,0);
		else if(gRotacion == 45 || gRotacion == -315 ) actualizarPosicion(1,1);
		else if(gRotacion == 90 || gRotacion == -270) actualizarPosicion(0,2);
		else if(gRotacion == 135 || gRotacion == -225) actualizarPosicion(-1,1);
		else if(gRotacion == 180 || gRotacion == -180) actualizarPosicion(-2,0);
		else if(gRotacion == 225 || gRotacion == -135) actualizarPosicion(-1,-1);
		else if(gRotacion == 270 || gRotacion == -90) actualizarPosicion(0,-2);
		else if(gRotacion == 315 || gRotacion == -45) actualizarPosicion(1,-1);
	}
	public static void correr() throws InterruptedException{
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.forward();
		Motor.B.forward();
		Thread.sleep(2000);
		if(gRotacion == 0 || gRotacion == 360 || gRotacion == -360) actualizarPosicion(4,0);
		else if(gRotacion == 45 || gRotacion == -315 ) actualizarPosicion(2,2);
		else if(gRotacion == 90 || gRotacion == -270) actualizarPosicion(0,4);
		else if(gRotacion == 135 || gRotacion == -225) actualizarPosicion(-2,2);
		else if(gRotacion == 180 || gRotacion == -180) actualizarPosicion(-4,0);
		else if(gRotacion == 225 || gRotacion == -135) actualizarPosicion(-2,-2);
		else if(gRotacion == 270 || gRotacion == -90) actualizarPosicion(0,-4);
		else if(gRotacion == 315 || gRotacion == -45) actualizarPosicion(2,-2);
	}
	public static void retroceder() throws InterruptedException{
		Motor.A.setSpeed(360);
		Motor.B.setSpeed(360);
		Motor.A.backward();
		Motor.B.backward();
		Thread.sleep(2000);
		if(gRotacion == 0 || gRotacion == 360 || gRotacion == -360) actualizarPosicion(-2,0);
		if(gRotacion == 45 || gRotacion == -315 ) actualizarPosicion(-1,-1);
		if(gRotacion == 90 || gRotacion == -270) actualizarPosicion(0,-2);
		if(gRotacion == 135 || gRotacion == -225) actualizarPosicion(1,-1);
		if(gRotacion == 180 || gRotacion == -180) actualizarPosicion(2,0);
		if(gRotacion == 225 || gRotacion == -135) actualizarPosicion(1,1);
		if(gRotacion == 270 || gRotacion == -90) actualizarPosicion(0,2);
		if(gRotacion == 315 || gRotacion == -45) actualizarPosicion(-1,1);
	}
	public static void girarDerecha() throws InterruptedException{
		Motor.B.rotate(720);
	}
	public static void girarIzquierda() throws InterruptedException{
		//Motor.B.setSpeed(0.5F);
		//Motor.A.setSpeed(1);
		//Motor.B.rotate(10);
		Motor.A.rotate(720);
		actualizarGRotacion(-45);
	}
	public static void chutar() throws InterruptedException{
		Motor.C.setSpeed(200);
		Motor.C.rotateTo(-45); //para prueba
		Motor.C.rotateTo(-8);
	}
	public static void patear() throws InterruptedException{
		Motor.C.setSpeed(400);
		Motor.C.rotateTo(-100);//para prueba
		Motor.C.rotateTo(-8);
	}
	
	public static void actualizarPosicion(int x, int y){
		xPosition += x;
		yPosition += y;
	}
	
	public static void actualizarGRotacion(int g){
		gRotacion += g;
		if(gRotacion > 360){
			gRotacion = gRotacion -360;
		}
		else if (gRotacion < -360){
			gRotacion = gRotacion + 360;
		}
	}
}

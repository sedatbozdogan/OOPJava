/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinemanager;

import java.text.MessageFormat;

/**
 *
 * @author sedat
 */
public class MachineManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Computer asus=new Computer("Siyah","Asus",2020);
        asus.Start();
        asus.MachineInfo();
        
        Keyboard klavye=new Keyboard();
        klavye.Plugin();
        klavye.MachineInfo();
         
        asus.ShutDown();
        asus.MachineInfo();
        
    }
    
    
    
    public abstract class Machine //temel olarak abstract makine sınıfımız
    {
            public String Color;  //renk alanı
            public String ModelName;  //model alanı
            public int CreatedYear;  //oluşturulma tarihi
            public boolean MachineState; //makine durumu 
 
            public abstract void MachineInfo();
    }
    
    public static class Computer extends Machine implements IComputer,IWriteConsole{
 
        private String messageText;
        public Computer(String color,String modelName,int createdYear){
            Color=color;
            ModelName=modelName;
            CreatedYear=createdYear;
        }
        
        @Override
        public void MachineInfo() {  
           messageText =Color+" "+ModelName+" "+CreatedYear;
           WriteToScreen(messageText); 
           if(MachineState==true){
                messageText ="Bilgisayar çalışıyor"; 
           }
           else{
               messageText ="Bilgisayar kapatıldı"; 
           }
            WriteToScreen(messageText);  
        }

        @Override
        public void Start() {  
           MachineState=true;
        }

        @Override
        public void ShutDown() { 
           MachineState=false;
        }

         public void WriteToScreen(String message){ 
                String messageFormatted =  MessageFormat.format("\n \"{0}\"",message);
                System.out.println(messageFormatted);
        }
    }
    
    public interface IComputer
    {
       public void Start();
       public void ShutDown();
    }
    
    public interface IWriteConsole{
        public void WriteToScreen(String message);
    }
    
    public static class Keyboard extends Machine implements IKeyboard,IWriteConsole{
        private String messageText;
        @Override
        public void MachineInfo() {
            if(MachineState==true){
                messageText="Klavye takıldı";
            }else{
                 messageText="Klavye çıkarıldı";
            }
             WriteToScreen(messageText); 
        }

        @Override
        public void Plugin() {
            MachineState=true;
        }

        @Override
        public void Eject() {
            MachineState=false;
        }
        
         public void WriteToScreen(String message){ 
                String messageFormatted =  MessageFormat.format("\n \"{0}\"",message);
                System.out.println(messageFormatted);
        }
         
    }
    
    public interface IKeyboard{
       void Plugin();
       void Eject();
    }
            
            
}



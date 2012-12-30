
 

import java.net.DatagramPacket;

import java.net.InetAddress;

import java.net.MulticastSocket;

 

public class MulticastReciever {

         final static int RECEIVE_LENGTH = 1024;

         static String multicastHost="224.0.0.1";

         static int localPort = 9998;

         public static void main(String[] args) throws Exception {

                   

                   InetAddress receiveAddress =InetAddress.getByName(multicastHost);

                   if(!receiveAddress.isMulticastAddress()){//测试是否为多播地址

                            throw new Exception("请使用多播地址");

                   }
                   int port = localPort;

                   MulticastSocket receiveMulticast = new MulticastSocket(port);

                   receiveMulticast.joinGroup(receiveAddress);

                   DatagramPacket dp = new DatagramPacket(new byte[RECEIVE_LENGTH], RECEIVE_LENGTH);

                   receiveMulticast.receive(dp);
                   String packetIpAddress=dp.getAddress().toString(); 
                   int sendport=dp.getPort();
                   
                   
                   String destAddressStr = "224.0.0.1";

       	        int destPortInt = 9998;

       	          int TTLTime = 1;

       	         InetAddress destAddress;
                   int destPort = destPortInt;

      	         int TTL = TTLTime;
      	         
      	       System.out.println(new String(dp.getData()).trim()+packetIpAddress);
   				destAddress = InetAddress.getByName(destAddressStr);


   				receiveMulticast.setTimeToLive(TTL);

      	         byte[] sendMSG = "tomessage".getBytes();

      	         DatagramPacket dp2 = new DatagramPacket(sendMSG, sendMSG.length, destAddress  , destPort);

      	       receiveMulticast.send(dp2);
                   


                   
                  // System.out.println(new String(dp.getData()).trim()+packetIpAddress);

                   receiveMulticast.close();

         }

}


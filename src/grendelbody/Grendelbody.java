/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grendelbody;

import basicstuff.basicObject;
import basicstuff.message;
import grendelbody.internetinout.internetInterface;
import grendelbody.output.output;
import grendelbody.soundin.soundin;
import grendelbody.visionin.visionin;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import static sun.misc.ThreadGroupUtils.getRootThreadGroup;

/*
 *
 * @author christopherrehm
 */
public class Grendelbody extends basicObject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //
        try {
            internetInterface myInternetInterface = new internetInterface();
            output myOutput = new output();
            soundin mySoundIn = new soundin();
            visionin myVisionIn= new visionin();
            
            myInternetInterface.start();
            myOutput.start();
            mySoundIn.start();
            myVisionIn.start();
            
            Thread.getAllStackTraces().keySet().forEach((t) -> System.out.println(t.getName() + " Is Daemon " + t.isDaemon() + " Is Alive " + t.isAlive()));
            
//            Thread[]threads = Thread.getAllThreads( ) {
//                final ThreadGroup root = getRootThreadGroup( );
//                final ThreadMXBean thbean = ManagementFactory.getThreadMXBean( );
//                int nAlloc = thbean.getThreadCount( );
//                int n = 0;
//                Thread[] threads;
//                do {
//                    nAlloc *= 2;
//                    threads = new Thread[ nAlloc ];
//                    n = root.enumerate( threads, true );
//                } while ( n == nAllThread[]threads = Thread.getAllThreads( ) {
//                final ThreadGroup root = getRootThreadGroup( );
//                final ThreadMXBean thbean = ManagementFactory.getThreadMXBean( );
//                int nAlloc = thbean.getThreadCount( );
//                int n = 0;
//                Thread[] threads;
//                do {
//                    nAlloc *= 2;
//                    threads = new Thread[ nAlloc ];
//                    n = root.enumerate( threads, true );
//                } while ( n == nAlloc );
//                return java.util.Arrays.copyOf( threads, n );
//            }oc );
//                return java.util.Arrays.copyOf( threads, n );
//            }
//            Thread T1 = new Thread ((Runnable) myInternetInterface);
//            Thread T2 = new Thread ((Runnable) myOutput);
//            Thread T3 = new Thread ((Runnable) mySoundIn);
//            Thread T4 = new Thread ((Runnable) myVisionIn);
//            
////            try (AffinityLock newthread = AffinityLock.acquireLock()) {
////    // do some work while locked to a CPU.
////            }
//
//            System.out.println("--> THREAD 0 starting threads 1 to 4 ");
//            T1.start();
//            T2.start();
//            T3.start();
//            T4.start();
        }
        catch (Exception e){
            System.out.println("something isnt working in grendelbody");    
        }   
    }   
}

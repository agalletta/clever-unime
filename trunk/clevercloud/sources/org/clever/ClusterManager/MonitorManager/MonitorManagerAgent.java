/*
 *  Copyright (c) 2011 Marco Sturiale
 *  Copyright (c) 2011 Alessio Di Pietro
 *  Copyright (c) 2012 Marco Carbone
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */
package org.clever.ClusterManager.MonitorManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.clever.Common.Communicator.Agent;
import static org.clever.Common.Communicator.Agent.logger;
import org.clever.Common.Communicator.CmAgent;
import org.clever.Common.Communicator.MethodInvoker;
import org.clever.Common.Communicator.ModuleCommunicator;
import org.clever.Common.Communicator.Notification;
import org.clever.Common.Exceptions.CleverException;
import org.clever.Common.Exceptions.LogicalCatalogException;
import org.clever.Common.Initiator.ModuleFactory.ModuleFactory;
import org.clever.Common.Shared.HostEntityInfo;
import org.clever.Common.XMLTools.MessageFormatter;
import org.clever.Common.XMPPCommunicator.ConnectionXMPP;
import org.jivesoftware.smackx.muc.Occupant;


import org.clever.ClusterManager.MonitorManager.SendMeasureRequest;
import org.clever.Common.Measure.*;


public class MonitorManagerAgent extends CmAgent
{
    private String version = "1.0";
    private String description = "Monitoring of the Clever resources about Cluster Manager";


    
    //private ConnectionXMPP connectionXMPP = null;
    //private  ArrayList <CmAgent> Agents = new ArrayList(3);
    //private ModuleFactory mf;
    
    
    
    public MonitorManagerAgent( ) throws CleverException {
        super();
        logger = Logger.getLogger( "MonitorManagerAgent" );
        
    }
    

    
    @Override
    public void initialization() throws CleverException {       
        
        
        System.out.println( "initialization mma" ); 
        
        if(super.getAgentName().equals("NoName"))
            super.setAgentName("MonitorManagerAgent");
        
        
        
        super.start();
        
        
        /*
        System.out.println( "richiamo get misura" ); 
        
        try{

            String aaa=this.getMeasure();
            
            logger.debug("OMG OMGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG: "+aaa);

          
        } catch (Exception ex) {
            logger.error("Errore: " + ex);
        }
        * */
        
        
    }
  
    @Override
    public Class getPluginClass() {
        return MonitorManagerAgent.class;
    }

    @Override
    public Object getPlugin() {

        return this;
    }


    @Override
    public void handleNotification(Notification notification) throws CleverException {
        logger.debug("Received notification type: "+notification.getId());
        
        
        //COSA IMPLEMENTARE????
    }

    
    @Override
    public void shutDown()
    {
        //AGGIUNGERE ???? -> throw new UnsupportedOperationException("Not supported yet.");
    }
    
        
    //-----------------------------------------------------
    //NECESSARIE????????????????
    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }
    //-----------------------------------------------------
    
    
    /*
    public getCpuMeasures() throws CleverException{
        
        try{
            
            
            
            
        }catch (Exception ex) {
            logger.error("Errore: " + ex);
        }
        
    }
    */
    
    
    public String getCpuIdle() throws CleverException{ 
        
        List params = new ArrayList();
        
        String result = null;
        result = getMeasure("webuntu", "getCpuIdle", params);
        
        //DESERIALIZZARE IL RISULTATO
        
        //SCRIVERE IL RISULTATO SU SEDNA (HUMAN READABLE)
        
        
        return result;
    }
    
    
    
    
    public String getMeasure(String target, String method, List params) throws CleverException{                        

        String measure = null;
       
        try{

            
            //Thread t = new Thread(new SendMeasureRequest(this, "webuntu", "CloudMonitorAgent", "getTotalUsedMemory", true, params));
            //t.start();
            
            measure = "NoThread --> " + this.remoteInvocation(target,"CloudMonitorAgent",method, true, params).toString(); 

            //measure = (String) this.invoke("CloudMonitorAgent","getTotalUsedMemory", true, params);
        
            /*
            while(t.isAlive()){
                logger.debug("Thread is working...");
            }
            t.interrupt();
            logger.debug("Thread successfully stopped.");
            */
            

        
        } catch (Exception ex) {
            logger.error("Errore: " + ex);
        }
        
        return measure;
        
        
     }

    /*
     public String getMeasure() throws CleverException{                        

        List params = new ArrayList();
        String measure = null;
        boolean threadDone = false;
        
        
        try{
                
            //INVOCARE IL THREAD
            
            Thread t = new Thread(new SendMeasureRequest(this, "webuntu", "CloudMonitorAgent", "getTotalUsedMemory", true, params));
            //"webuntu","CloudMonitorAgent","getTotalUsedMemory", true, params
            
            t.start();
            
            measure = "NoThread --> " + (String) this.remoteInvocation("webuntu","CloudMonitorAgent","getTotalUsedMemory", true, params); 

            //measure = (String) this.invoke("CloudMonitorAgent","getTotalUsedMemory", true, params);
        
            while(t.isAlive()){
                logger.debug("Thread is working...");
            }
            t.interrupt();
            logger.debug("Thread successfully stopped.");

            

        
        } catch (Exception ex) {
            logger.error("Errore: " + ex);
        }
        
        return measure;
        
        
     }
     */
    
    
    
}
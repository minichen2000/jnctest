
package com.nokia.eml;

import java.io.*;

import com.nokia.eml.yang.openconfigPlatform.Components;
import com.nokia.eml.yang.openconfigPlatform.OcPlatform;
import com.nokia.eml.yang.openconfigPlatform.components.Component;
import com.tailf.jnc.*;



public class Main {

    private static class Test {

        public Test() {
            init();
        }

        public Test(Subscriber s) {
            init(s);
        }
        
        
        private Device dev;
        private DeviceUser duser;

        private void init() {
            String emsUserName = "admin";
            duser = new DeviceUser(emsUserName, "admin", "admin");
            dev = new Device("mydev", duser, "135.251.96.208", 4830);

            try {
                dev.connect(emsUserName);
                dev.newSession("cfg");
            } catch (IOException e0) {
                System.err.println("Can't connect");
                System.exit(1);
            } catch (JNCException e1) {
                System.err.println("Can't authenticate" + e1);
                System.exit(1);
            }
        }



        private void init(Subscriber sub) {
            String emsUserName = "admin";
            duser = new DeviceUser(emsUserName, "admin", "admin");
            dev = new Device("mydev", duser, "135.251.96.208", 4830);
             try {
                 dev.connect(emsUserName);
                 dev.newSession(sub, "cfg");
             } catch (IOException e0) {
                 System.err.println("Can't connect");
                 System.exit(1);
             } catch (JNCException e1) {
                 System.err.println("Can't authenticate" + e1);
                 System.exit(1);
             }
        }

        /**
         * Gets the first configuration element in configs with specified name.
         * 
         * @param configs Set of device configuration data.
         * @param name The identifier of the configuration to select
         * @return First configuration with matching name, or null if none present.
         */
        Element getConfig(NodeSet configs, String name) {
            Element config = configs.first();
            if (!config.name.equals(name)) {
                config = null;
                for (Element elem : configs) {
                    if (elem.name.equals(name)) {
                        config = elem;
                    }
                }
            }
            return config;
        }

        NodeSet getConfig(Device d) throws IOException, JNCException{
            NodeSet reply = d.getSession("cfg").getConfig(
NetconfSession.RUNNING);
            return reply;
        }

        void getConfig() throws IOException,JNCException{
            System.out.println(getConfig(dev).toXMLString());
        }






        void listComponents()  throws IOException,JNCException{
            listComponents(dev);
        }

        void listComponents(Device d)  throws IOException,JNCException{
            NodeSet configs = getConfig(d);
            Element e=getConfig(configs, "components");
            System.out.println("Element name:"+e.name);
            System.out.println("Element class:"+e.getClass().toString());
            System.out.println("Element:"+e.toString());

            Components c = (Components) e;

            ElementChildrenIterator it = c.componentIterator();
            while (it.hasNext()) {
                Component co = (Component)it.next();
                System.out.println(co.getNameValue());
            }
        }



        /*void updateConfig() throws IOException,JNCException {
            updateConfig(dev);
        }



        void updateConfig(Device d) throws IOException,JNCException {
        	NodeSet configs = getConfig(d);
            Hosts h = (Hosts) getConfig(configs, "hosts");
            ElementChildrenIterator it = h.hostIterator();
            while (it.hasNext()) {
                Host hst = (Host)it.next();
                if (hst.getNameValue().equals("joe"))
                    hst.markDelete();
            }
            d.getSession("cfg").editConfig(h);
            // Inspect the updated RUNNING configuration
            Hosts h2 = (Hosts) getConfig(configs, "hosts");
            System.out.println("Resulting config:\n" + h2.toXMLString());
        }



        void revertConfig() throws IOException,JNCException {
            revertConfig(dev);
        }

        void revertConfig(Device d) throws IOException,JNCException {
            Hosts h = new Hosts();
            Host joe = new Host("joe");
            joe.setEnabledValue(true);
            joe.setNumberOfServersValue(5);
            joe.markReplace();
            h.addHost(joe);
            d.getSession("cfg").editConfig(h);
            // Inspect the updated RUNNING configuration
            NodeSet configs = getConfig(d);
            Hosts h2 = (Hosts) getConfig(configs, "hosts");
            System.out.println("Resulting config:\n" + h2.toXMLString());
        }

        // print small tree
        void h1() throws JNCException {
            Simple.enable();
            Hosts h = new Hosts();
            h.addHost("Jupiter").markDelete();
            h.addHost("Saturn").markDelete();
            System.out.println(h.toXMLString());
        }

        void print_cfg(String s, Device d) throws IOException, JNCException {
        	NodeSet configs = getConfig(d);
            Hosts h = (Hosts) getConfig(configs, "hosts");
             System.out.println(s + " \n" + h.toXMLString());
        }

        // Example on how to delete by explicitly constructing
        // the delete path
        void delete_vera() throws JNCException, IOException {
            print_cfg("Config With vera ", dev);
            Hosts h = new Hosts();
            Host vera = new Host("vera");
            vera.markDelete();
            h.addHost(vera);
            dev.getSession("cfg").editConfig(h);
            print_cfg("Config Without vera ", dev);
        }


        // Example on how to handle errors from the agent
        void delete_no_vera() throws JNCException, IOException, Exception {
            print_cfg("Config With vera ", dev);
            Hosts h = new Hosts();
            Host vera = new Host("vera_noExists");
            vera.markDelete();
            h.addHost(vera);
            try {
                dev.getSession("cfg").editConfig(h);
                throw new Exception("Expected0 rpc error");
            } catch (JNCException ex) {
            	String errorStr = ex.toString();
            	if (errorStr.startsWith("rpc-reply error")) {
                    System.out.println("errorCode and opaqueData = " + errorStr + "\n");
                }
                else {
                    throw new Exception("Expected1 rpc error");
                }
            }
        }

        // Example on how to create a Host entry - this also makes
        // it possible to run this code several times in a row
        // if it wasn't for this code - the second time we run this
        // the delete_vera() would fail - because there was no vera Host

        void create_vera() throws JNCException, IOException, Exception {
            print_cfg("Create vera ", dev);
            Hosts h = new Hosts();
            Host vera = new Host("vera");
            vera.setNumberOfServersValue(0);
            h.addHost(vera);
            dev.getSession("cfg").editConfig(h);
        }

        // Create an additional host
        void create_vera_space() throws JNCException, IOException, Exception {
            print_cfg("Create vera space", dev);
            Hosts h = new Hosts();
            Host vera = new Host("vera space");
            vera.setNumberOfServersValue(0);
            vera.setDomainAValue("1000");
            vera.setDomainAValue("1001");
            h.addHost(vera);
            dev.getSession("cfg").editConfig(h);
        }




        void writeReadFile() throws Exception {
            Simple.enable();
            Hosts h = new Hosts();
            h.addHost("Jupiter");
            h.addHost("Saturn");
            h.writeFile("Hosts.xml");
            Hosts h2 = (Hosts)YangElement.readFile("Hosts.xml");
            System.out.println(h2.toXMLString());
        }


        // actions cannot be constructed - nor can the reply be
        // deconstructed using generated JNC classes - rather
        // the more primitive Element class must be used

        void invokeAction() throws Exception {

          Element e = Element.create(
              Simple.NAMESPACE, "/hosts/sys/restart");
          Element restart = e.getChildren().first().getChildren().first();
          restart.createChild("mode", "mymode");
          restart.createChild("debug");
          System.out.println("Data = " +  e.toXMLString());
          Element reply = dev.getSession("cfg").action(e);
          System.out.println("Reply = " +  reply.toXMLString());

        }
        */
    }


    static public int NUMTESTS = 11;

    static void runTest(Test t, int n) throws Exception {
        System.out.println("TEST " + n);
        switch (n) {
        case 1:
            t.getConfig();
            break;
        case 2:
            //t.updateConfig();
            break;
        case 3:
            //t.revertConfig();
            break;
        case 4:
            //t.listHosts();
            t.listComponents();
            break;
        case 5:
            //t.h1();
            break;
        case 6:
            //t.writeReadFile();
            break;
        case 7:
            //t.delete_vera();
            break;
        case 8:
            //t.delete_no_vera();
            break;
        case 9:
            //t.create_vera();
            break;
        case 10:
            //t.invokeAction();
            break;
        case 11:
            //t.create_vera_space();
            break;
        default:
            System.out.println("bad testno");
            System.exit(1);
        }
    }


    static public void main(String args[]) {
        System.out.println("In MAIN");
        try {
            OcPlatform.enable();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
        int n = -1;
        boolean trace = false;
        try {
            for (int i=0; i<args.length; i++) {
                if (args[i].equals("-n")) {
                     try {
                         n = Integer.parseInt(args[++i]);
                     } catch (Exception e) {

                         System.err.println("Usage  : Main -n <int> [-t]");
                         System.exit(1);
                     }
                }
                else if (args[i].equals("-t")) {
                    trace = true;
                }
                else {
                    System.err.println("Usage  : Main -n <int> [-t]");
                    System.exit(1);
                }
            }

            Test t = new Test();
            if (trace)
                t = new  Test(new Subscriber("mydev"));
            if (n == -1) {
                for (int i=1; i< (NUMTESTS+1); i++) {
                    runTest(t, i);
                }
                System.out.println("OK");
            }
            else {
                runTest(t, n);
            }
        }
        catch (Exception e) {
            System.out.println("ERROR " + e);
            e.printStackTrace();
        }
    }
}




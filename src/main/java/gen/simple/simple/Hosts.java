/* 
 * @(#)Hosts.java        1.0 13/06/18
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "simple", revision: "unknown".
 */

package gen.simple.simple;

import com.tailf.jnc.Element;
import com.tailf.jnc.ElementChildrenIterator;
import com.tailf.jnc.JNCException;
import com.tailf.jnc.YangElement;
import com.tailf.jnc.YangString;

import gen.simple.simple.Simple;
import gen.simple.simple.hosts.Host;

/**
 * This class represents an element from 
 * the namespace http://acme.com/ns/simple/1.0
 * generated to "src/gen/simple/simple/hosts"
 * <p>
 * See line 4 in
 * yang/simple.yang
 *
 * @version 1.0 2018-06-13
 * @author Auto Generated
 */
public class Hosts extends YangElement {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for an empty Hosts object.
     */
    public Hosts() {
        super(Simple.NAMESPACE, "hosts");
        setDefaultPrefix();
        setPrefix(Simple.PREFIX);
    }

    /**
     * Clones this object, returning an exact copy.
     * @return A clone of the object.
     */
    public Hosts clone() {
        return (Hosts)cloneContent(new Hosts());
    }

    /**
     * Clones this object, returning a shallow copy.
     * @return A clone of the object. Children are not included.
     */
    public Hosts cloneShallow() {
        return (Hosts)cloneShallowContent(new Hosts());
    }

    /**
     * @return An array with the identifiers of any key children
     */
    public String[] keyNames() {
        return null;
    }

    /**
     * @return An array with the identifiers of any children, in order.
     */
    public String[] childrenNames() {
        return new String[] {
            "host",
        };
    }

    /* Access methods for list child: "host". */

    /**
     * Gets list entry "host", with specified keys.
     * @param nameValue Key argument of child.
     */
    public Host getHost(YangString nameValue) throws JNCException {
        String path = "host[name='" + nameValue + "']";
        return (Host)searchOne(path);
    }

    /**
     * Gets list entry "host", with specified keys.
     * The keys are specified as strings.
     * @param nameValue Key argument of child.
     */
    public Host getHost(String nameValue) throws JNCException {
        String path = "host[name='" + nameValue + "']";
        return (Host)searchOne(path);
    }

    /**
     * Iterator method for the list "host".
     * @return An iterator for the list.
     */
    public ElementChildrenIterator hostIterator() {
        return new ElementChildrenIterator(children, "host");
    }

    /**
     * Adds list entry "host", using an existing object.
     * @param host The object to add.
     * @return The added child.
     */
    public Host addHost(Host host) throws JNCException {
        insertChild(host, childrenNames());
        return host;
    }

    /**
     * Adds list entry "host", with specified keys.
     * @param nameValue Key argument of child.
     * @return The added child.
     */
    public Host addHost(YangString nameValue) throws JNCException {
        Host host = new Host(nameValue);
        return addHost(host);
    }

    /**
     * Adds list entry "host", with specified keys.
     * The keys are specified as strings.
     * @param nameValue Key argument of child.
     * @return The added child.
     */
    public Host addHost(String nameValue) throws JNCException {
        Host host = new Host(nameValue);
        return addHost(host);
    }

    /**
     * Adds list entry "host".
     * This method is used for creating subtree filters.
     * @return The added child.
     */
    public Host addHost() throws JNCException {
        Host host = new Host();
        insertChild(host, childrenNames());
        return host;
    }

    /**
     * Deletes list entry "host", with specified keys.
     * @param nameValue Key argument of child.
     */
    public void deleteHost(YangString nameValue) throws JNCException {
        String path = "host[name='" + nameValue + "']";
        delete(path);
    }

    /**
     * Deletes list entry "host", with specified keys.
     * The keys are specified as strings.
     * @param nameValue Key argument of child.
     */
    public void deleteHost(String nameValue) throws JNCException {
        String path = "host[name='" + nameValue + "']";
        delete(path);
    }

    /**
     * Support method for addChild.
     * Adds a child to this object.
     * 
     * @param child The child to add
     */
    public void addChild(Element child) {
        super.addChild(child);
    }

}

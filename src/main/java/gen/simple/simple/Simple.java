/* 
 * @(#)Simple.java        1.0 13/06/18
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "simple", revision: "unknown".
 */

package gen.simple.simple;

import com.tailf.jnc.JNCException;
import com.tailf.jnc.SchemaNode;
import com.tailf.jnc.SchemaParser;
import com.tailf.jnc.SchemaTree;
import com.tailf.jnc.Tagpath;
import com.tailf.jnc.YangElement;

import java.util.HashMap;

/**
 * The root class for namespace http://acme.com/ns/simple/1.0 (accessible from 
 * Simple.NAMESPACE) with prefix "simple" (Simple.PREFIX).
 *
 * @version 1.0 2018-06-13
 * @author Auto Generated
 */
public class Simple {

    public static final String NAMESPACE = "http://acme.com/ns/simple/1.0";

    public static final String PREFIX = "simple";

    /**
     * Enable the elements in this namespace to be aware
     * of the data model and use the generated classes.
     */
    public static void enable() throws JNCException {
        YangElement.setPackage(NAMESPACE, "gen.simple.simple");
        Simple.registerSchema();
    }

    /**
     * Register the schema for this namespace in the global
     * schema table (CsTree) making it possible to lookup
     * CsNode entries for all tagpaths
     */
    public static void registerSchema() throws JNCException {
        SchemaParser parser = new SchemaParser();
        HashMap<Tagpath, SchemaNode> h = SchemaTree.create(NAMESPACE);
        parser.readFile("src/main/java/gen/simple/simple/Simple.schema", h);
    }

}

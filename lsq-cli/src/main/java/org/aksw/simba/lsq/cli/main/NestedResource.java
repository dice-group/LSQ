package org.aksw.simba.lsq.cli.main;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public class NestedResource {
    protected String str;
    protected Resource current = null;
    protected Model model;
    protected NestedResource parent;

    public NestedResource(Model model, String str) {
        this(null, model, str, null);
    }

    public NestedResource(Resource current) {
        this(null, current.getModel(), current.getURI(), current);
    }

    public NestedResource(NestedResource parent, Model model, String str, Resource current) {
        this.parent = parent;
        this.model = model;
        this.str = str;
        this.current = current;
    }

    /**
     * A resource instance is only added to the model when .get() is called
     *
     * @return
     */
    public Resource get() {
        if(current == null) {
            Resource result = model.createResource(str);
            return result;
        }
        return current;
    }

    public String str() {
        return str;
    }

    public NestedResource nest(String name) {
        NestedResource result = name.isEmpty()
                ? this
                : new NestedResource(this, model, str + name, null);

        return result;
    }
}

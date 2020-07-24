package org.aksw.simba.lsq.model;

import java.math.BigDecimal;

import org.aksw.jena_sparql_api.mapper.annotation.HashId;
import org.aksw.jena_sparql_api.mapper.annotation.Inverse;
import org.aksw.jena_sparql_api.mapper.annotation.Iri;
import org.aksw.jena_sparql_api.mapper.annotation.ResourceView;
import org.aksw.jena_sparql_api.mapper.annotation.StringId;
import org.aksw.jena_sparql_api.mapper.hashid.HashIdCxt;
import org.aksw.simba.lsq.spinx.model.SpinBgpExec;
import org.aksw.simba.lsq.vocab.LSQ;
import org.apache.jena.datatypes.xsd.XSDDateTime;
import org.apache.jena.rdf.model.Resource;

@ResourceView
@HashId // Include the type into the hash value
public interface QueryExec extends Resource {
//    @Iri(LSQ.Strs.hasExec)
//    @Inverse
//    Resource getSpinQuery();
    @HashId
    @Iri(LSQ.Strs.hasQueryExec)
    @Inverse
    LocalExecution getLocalExecution();
    SpinBgpExec setLocalExecution(LocalExecution le);

//    @Iri(LSQ.Strs.benchmarkRun)
//    ExperimentRun getBenchmarkRun();
//    ElementExec setBenchmarkRun(Resource benchmarkRun);

    @Iri(LSQ.Strs.runTimeMs)
    BigDecimal getRuntimeInMs();
    QueryExec setRuntimeInMs(BigDecimal runtimeInMs);

    @Iri(LSQ.Strs.resultSize)
    Long getResultSetSize();
    QueryExec setResultSetSize(Long resultSetSize);

    @Iri(LSQ.Strs.atTime)
    XSDDateTime getTimestamp();
    RemoteExecution setTimestamp(XSDDateTime calendar);

    @StringId
    default String getStringId(HashIdCxt cxt) {
        LocalExecution le = getLocalExecution();
        ExperimentRun bmr = le.getBenchmarkRun();
        String result = "queryExec-" + cxt.getHashAsString(this) + "-" + cxt.getString(bmr);
        return result;
    }

}
package org.aksw.simba.lsq.spinx.model;

import java.util.Objects;
import java.util.Set;

import org.aksw.jena_sparql_api.mapper.annotation.HashId;
import org.aksw.jena_sparql_api.mapper.annotation.Inverse;
import org.aksw.jena_sparql_api.mapper.annotation.Iri;
import org.aksw.jena_sparql_api.mapper.annotation.ResourceView;
import org.aksw.jena_sparql_api.mapper.annotation.StringId;
import org.aksw.jena_sparql_api.mapper.hashid.HashIdCxt;
import org.aksw.simba.lsq.model.ElementExec;
import org.aksw.simba.lsq.model.LocalExecution;
import org.aksw.simba.lsq.vocab.LSQ;
import org.apache.jena.rdf.model.Resource;


/**
 * Execution result of a BGP
 *
 * @author raven
 *
 */
@ResourceView
//@IdPrefix("bgpexec-")
public interface SpinBgpExec
    extends ElementExec
{
    @Iri(LSQ.Strs.hasBgpExec)
    @Inverse
    LocalExecution getLocalExecution();
    SpinBgpExec setLocalExecution(LocalExecution le);

    // Link from the BGP to this exec
    @Iri(LSQ.Strs.hasExec)
    @HashId
    @Inverse
    SpinBgp getBgp();
    SpinBgpExec setBgp(SpinBgp bgp);


    // NOTE Calling the method getId is not recognized by the annotation processor
    // because there is a method Resource.setId which has incompatible types
    @StringId
    default String getStringId(HashIdCxt cxt) {
        SpinBgp bgp = getBgp();
        // TODO Replace the prefix with e.g. cxt.getClassLabel(SpinBgpExec.class)
//        String result = "bgpExec-" + cxt.getHashAsString(bgp) + "-" + getLocalExecution().getBenchmarkRun().getIdentifier();
        String result = "bgpExec-" + cxt.getHashAsString(bgp) + "-" + cxt.getString(getLocalExecution().getBenchmarkRun());
        return result;
    }


    // Link from this exec to the benchmark result of the BGP's extension query
//    @Iri(LSQ.Strs.hasExec)
//    @Inverse
//    LocalExecution getBgpQueryExec();
//    SpinBgpExec setBgpQueryExec(LocalExecution exec);


    @Iri(LSQ.Strs.hasTpInBgpExec)
    Set<TpInBgpExec> getTpInBgpExecs();

    @Iri(LSQ.Strs.hasJoinVarExec)
    Set<JoinVertexExec> getBgpNodeExecs();


    default TpInBgpExec findTpInBgpExec(Resource tpInBgp) {
        Resource expRun = getLocalExecution().getBenchmarkRun();
        Objects.requireNonNull(expRun);

        Set<TpInBgpExec> cands = getTpInBgpExecs();
        TpInBgpExec result = null;
        for(TpInBgpExec cand : cands) {
            if(Objects.equals(cand.getTpInBgp(), tpInBgp) && Objects.equals(cand.getBgpExec().getQueryExec().getLocalExecution().getBenchmarkRun(), expRun)) {
                result = cand;
                break;
            }
        }

        return result;
    }


    default JoinVertexExec findBgpNodeExec(Resource bpgNode) {
        Resource expRun = getLocalExecution().getBenchmarkRun();
        Objects.requireNonNull(expRun);

        Set<JoinVertexExec> cands = getBgpNodeExecs();
        JoinVertexExec result = null;
        for(JoinVertexExec cand : cands) {
            if(Objects.equals(cand.getBgpNode(), bpgNode) && Objects.equals(cand.getBgpExec().getQueryExec().getLocalExecution().getBenchmarkRun(), expRun)) {
                result = cand;
                break;
            }
        }

        return result;
    }


//    default TpExec findTpExec(LsqTriplePattern tp) {
//        Resource expRun = getLocalExecution().getBenchmarkRun();
//        Objects.requireNonNull(expRun);
//
//        Set<TpExec> cands = getT();
//        JoinVertexExec result = null;
//        for(JoinVertexExec cand : cands) {
//            if(Objects.equals(cand.getBgpNode(), bpgNode) && Objects.equals(cand.getBgpExec().getQueryExec().getBenchmarkRun(), expRun)) {
//                result = cand;
//                break;
//            }
//        }
//
//        return result;
//    }

    // TODO Add reverse link to SpinBGP
}
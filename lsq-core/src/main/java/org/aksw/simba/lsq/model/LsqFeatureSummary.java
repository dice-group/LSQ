package org.aksw.simba.lsq.model;

import java.util.Set;

import org.aksw.jena_sparql_api.mapper.annotation.Iri;
import org.aksw.simba.lsq.vocab.LSQ;
import org.apache.jena.rdf.model.Resource;


/**
 * Interface for access to structural features of an lsq query.
 * 
 * 
 * @author Claus Stadler, Jan 7, 2019
 *
 */
public interface LsqFeatureSummary {
//	@Iri
//	LsqQuery query();
	
	// numProjectVars
	@Iri(LSQ.Strs.projectVars)
	Integer getNumProjectVars();
	LsqFeatureSummary setNumProjectVars(Integer number);

	// Bgp summary
//    targetRes.addLiteral(LSQ.bgps, totalBgpCount).addLiteral(LSQ.minBGPTriples, minBgpTripleCount)
//    .addLiteral(LSQ.maxBGPTriples, maxBgpTripleCount).addLiteral(LSQ.tps, triplePatternCount);

	
	//LSQ.usesFeature
	Set<Resource> getQueryFeatures();
	
	//Set<Bgp> hasBGP();
	
	//hasTP
	//TriplePattern


	// usesService
	
//    SpinUtils.enrichWithHasTriplePattern(featureRes, spinRes);
//    SpinUtils.enrichWithTriplePatternText(spinRes);
//    //Selectivity2.enrichModelWithTriplePatternExtensionSizes(model, dataQef);
//
//    //
//    QueryStatistics2.getDirectQueryRelatedRDFizedStats(spinRes, featureRes);
//
//    QueryStatistics2.enrichWithPropertyPaths(featureRes, query);

}



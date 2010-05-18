package com.protocol7.neo4j.demo;

import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class Neo {

	public static void main(String[] args) {
		
		EmbeddedGraphDatabase db = new EmbeddedGraphDatabase("target/graph");
		Transaction tx = db.beginTx();
		try {
			Node neo = db.createNode();
			neo.setProperty("name", "Neo");
			
			Node trinity = db.createNode();
			trinity.setProperty("name", "Trinity");
			
			neo.createRelationshipTo(trinity, DynamicRelationshipType.withName("Knows"));
			tx.success();
		} catch(RuntimeException e) {
			tx.failure();
			throw e;
		} finally {
			tx.finish();
			
			db.shutdown();
		}
		
	}
}

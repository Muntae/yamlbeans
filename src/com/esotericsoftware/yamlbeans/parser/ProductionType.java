package com.esotericsoftware.yamlbeans.parser;

import java.util.HashMap;
import java.util.Map;

public interface ProductionType {
	
	int P_STREAM = 0;
	int P_STREAM_START = 1; // TERMINAL
	int P_STREAM_END = 2; // TERMINAL
	int P_IMPLICIT_DOCUMENT = 3;
	int P_EXPLICIT_DOCUMENT = 4;
	int P_DOCUMENT_START = 5;
	int P_DOCUMENT_START_IMPLICIT = 6;
	int P_DOCUMENT_END = 7;
	int P_BLOCK_NODE = 8;
	int P_BLOCK_CONTENT = 9;
	int P_PROPERTIES = 10;
	int P_PROPERTIES_END = 11;
	int P_FLOW_CONTENT = 12;
	int P_BLOCK_SEQUENCE = 13;
	int P_BLOCK_MAPPING = 14;
	int P_FLOW_SEQUENCE = 15;
	int P_FLOW_MAPPING = 16;
	int P_SCALAR = 17;
	int P_BLOCK_SEQUENCE_ENTRY = 18;
	int P_BLOCK_MAPPING_ENTRY = 19;
	int P_BLOCK_MAPPING_ENTRY_VALUE = 20;
	int P_BLOCK_NODE_OR_INDENTLESS_SEQUENCE = 21;
	int P_BLOCK_SEQUENCE_START = 22;
	int P_BLOCK_SEQUENCE_END = 23;
	int P_BLOCK_MAPPING_START = 24;
	int P_BLOCK_MAPPING_END = 25;
	int P_INDENTLESS_BLOCK_SEQUENCE = 26;
	int P_BLOCK_INDENTLESS_SEQUENCE_START = 27;
	int P_INDENTLESS_BLOCK_SEQUENCE_ENTRY = 28;
	int P_BLOCK_INDENTLESS_SEQUENCE_END = 29;
	int P_FLOW_SEQUENCE_START = 30;
	int P_FLOW_SEQUENCE_ENTRY = 31;
	int P_FLOW_SEQUENCE_END = 32;
	int P_FLOW_MAPPING_START = 33;
	int P_FLOW_MAPPING_ENTRY = 34;
	int P_FLOW_MAPPING_END = 35;
	int P_FLOW_INTERNAL_MAPPING_START = 36;
	int P_FLOW_INTERNAL_CONTENT = 37;
	int P_FLOW_INTERNAL_VALUE = 38;
	int P_FLOW_INTERNAL_MAPPING_END = 39;
	int P_FLOW_ENTRY_MARKER = 40;
	int P_FLOW_NODE = 41;
	int P_FLOW_MAPPING_INTERNAL_CONTENT = 42;
	int P_FLOW_MAPPING_INTERNAL_VALUE = 43;
	int P_ALIAS = 44;
	int P_EMPTY_SCALAR = 45;
	
	Map<String, String> DEFAULT_TAGS_1_0 = new HashMap();
	Map<String, String> DEFAULT_TAGS_1_1 = new HashMap();
}

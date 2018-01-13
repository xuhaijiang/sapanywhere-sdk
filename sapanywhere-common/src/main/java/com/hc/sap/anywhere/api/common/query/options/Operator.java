package com.hc.sap.anywhere.api.common.query.options;

public enum Operator {

	Equals("eq"),
	NotEquals("ne"),
	GreaterThan("gt"),
	GreaterThanorEqual("ge"),
	LessThan("lt"),
	LessThanorEqual("le"),
	And("and"),
	Or("or"),
	
	
	ORDERBY_DESC("desc"),
	ORDERBY_ASC("asc");

    private String operator;

    private Operator(String operator) {
        this.operator = operator;
    }

    public static Operator fromOperator(String operator) {
        for (Operator operator1 : Operator.values()) {
            if (operator1.operator == operator) {
                return operator1;
            }
        }
        return null;
    }

    public String getOperator() {
    	
        return this.operator;
    }
    
    
}

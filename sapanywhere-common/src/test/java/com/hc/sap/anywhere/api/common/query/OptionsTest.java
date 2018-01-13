package com.hc.sap.anywhere.api.common.query;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.hc.sap.anywhere.api.common.query.AbstractOption;
import com.hc.sap.anywhere.api.common.query.Options;
import com.hc.sap.anywhere.api.common.query.options.Expand;
import com.hc.sap.anywhere.api.common.query.options.Filter;
import com.hc.sap.anywhere.api.common.query.options.FilterTest;
import com.hc.sap.anywhere.api.common.query.options.Limit;
import com.hc.sap.anywhere.api.common.query.options.Offset;
import com.hc.sap.anywhere.api.common.query.options.Operator;
import com.hc.sap.anywhere.api.common.query.options.Orderby;
import com.hc.sap.anywhere.api.common.query.options.OrderbyTest;
import com.hc.sap.anywhere.api.common.query.options.Select;
import com.hc.sap.anywhere.api.common.query.options.SelectTest;

public class OptionsTest {

	@Test
    public void test(){
		System.out.println(Options.getSimpleOption(FilterTest.create(1)));
		System.out.println(Options.getSimpleOption(FilterTest.create1(1)));
	}
	
	@Test
    public void getFilterFromListTest(){
		
		System.out.println(Options.getFilterOrOrderbyOptionFromList(createFilterList(1), Operator.Or));
	}
	
	
	public static List<AbstractOption> create(int num){
		List<AbstractOption> ls = Lists.newArrayList();
		ls.add(FilterTest.create(1));
		ls.add(FilterTest.create(2));
		return ls;
	}
	
	public static List<AbstractOption> createFilterList(int num){
		List<AbstractOption> ls = Lists.newArrayList();
		ls.add(FilterTest.create(1));
		ls.add(FilterTest.create(2));
		return ls;
	}
	
	@Test
	public void testOptionHelper() throws Exception {
		System.out.println(testFilter());
		System.out.println(testOrderby());
		System.out.println(testLimit());
		System.out.println(testOffset());
		System.out.println(testSelect());
		System.out.println(testExpand());
	}
	
	
	public static String testFilter(){
		Filter<String,String> filter1 = FilterTest.create(1);
		Filter<String,String> filter2 = FilterTest.create(2);
		Filter<Filter,Filter> filter3 = FilterTest.create1(2);
		String filters = Options.getToOptionHelper(Filter.FILTER_PREFIX)
				.addOption(filter1)
				.addConnector(Operator.GreaterThan)
				.addOption(filter2)
				.addConnector(Operator.LessThan)
				.addOption(filter3)
				.get();
		return filters;
	}
	
	public static String testOrderby(){	
		Orderby orderby = OrderbyTest.create(1);
		Orderby orderby1 = OrderbyTest.create1(orderby);
		String orderbys = Options.getToOptionHelper(Orderby.ORDERBY_PREFIX)
				.addOption(orderby)
				.addConnector(",")
				.addOption(orderby1)
				.get();
		return orderbys;
	}
	
	public static String testLimit(){	
		Limit limit = new Limit(5);
		String limits = Options
				.getToOptionHelper(Limit.LIMIT_PREFIX)
				.addOption(limit)
				.get();
		return limits;
	}
	
	public static String testOffset(){	
		Offset offset = new Offset(1);
		String offsets = Options
				.getToOptionHelper(Offset.OFFSET_PREFIX)
				.addOption(offset)
				.get();
		return offsets;
	}
	
	public static String testSelect(){	
		Select select = SelectTest.create(1);
		String selects = Options.getToOptionHelper(Select.SELECT_PREFIX)
				.addOption(select)
				.get();
		
		return selects;
	}
	
	public static String testExpand(){		
		Expand expand = new Expand("ID","NAME");
		String expands = Options.getToOptionHelper(Expand.EXPAND_PREFIX)
				.addOption(expand)
				.get();
		return expands;
	}
	
}
